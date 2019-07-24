package dao;

import DBManager.DBManager;
import Entity.ProjectEntity;
import Entity.UserEntity;
import net.sf.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserDAO {

    /**
     *检查账户密码是否正确
     */
    public static UserEntity queryUser(String tell, String password) {

        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM users WHERE Tell=? AND Password=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, tell);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            UserEntity user = new UserEntity();
            if (resultSet.next()) {
                user.setUserName(resultSet.getString("Tell"));
                user.setPassword(resultSet.getString("Password"));
                user.setid(resultSet.getString("ID"));
                user.setTell(resultSet.getString("Tell"));
                user.setGender(resultSet.getString("Gender"));
                user.setworkNumber(resultSet.getString("WorkNumber"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
    判断用户是否存在
     */
    public static boolean ifexistUser(String tell) {

        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM users WHERE Tell=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, tell);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 重置密码时，判断新旧密码是否一样
     */
    public static boolean ifPasswordSame(String tell,String newPassword) {

        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM users WHERE Tell=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, tell);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if(resultSet.getString("Password").equals(newPassword)){//记得string要用equals比较
                    return true;
                }
                else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }


    /**
     用户注册，向数据库中加入用户信息
     */
    public static boolean registerUser(String userName, String password, String gender, String id, String tell, String workNumber,String age) {

        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();

        sqlStatement.append("INSERT INTO users(UserName,Password,ID,Tell,Gender,WorkNumber,Age) VALUES(?,?,?,?,?,?,?)");

        //设置数据库的字段值，UserID为自增不需要插入值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, id);
            preparedStatement.setString(4, tell);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, workNumber);
            preparedStatement.setString(7,age);
            if(preparedStatement.executeUpdate()!=0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     用户重置密码
     */
    public static boolean resetPassword(String tell,String newPassword) {

        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();

        sqlStatement.append("UPDATE users SET Password=? WHERE Tell=?");

        //修改账户对应密码
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,tell);
            preparedStatement.executeUpdate();
            return  true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 拉取除了本人外的用户名字
     */
    public static List<String> getContact(String tell) {

        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT UserName FROM users WHERE Tell<>?");

        //查询用户名
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,tell);
            resultSet = preparedStatement.executeQuery();
            List<String> usersName = new ArrayList<>();
            while(resultSet.next()){
                usersName.add(resultSet.getString("UserName"));
            }
            return usersName;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     *模糊搜索用户名
     */
    public static List<String> fuzzySearchUsersName(String userFuzzyName,String tell) {

        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT UserName FROM users WHERE UserName LIKE ? AND Tell<>?");

        //查询用户名
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,"%"+userFuzzyName+"%");
            preparedStatement.setString(2,tell);
            resultSet = preparedStatement.executeQuery();
            List<String> usersName = new ArrayList<>();
            while(resultSet.next()){
                usersName.add(resultSet.getString("UserName"));
            }
            return usersName;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 获取用户详细信息
     */
    public static UserEntity queryUserDetail(String tell) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM users WHERE Tell=?");

        //查询数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, tell);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                UserEntity user = new UserEntity();
                user.setworkNumber(resultSet.getString("WorkNumber"));
                user.setTell(resultSet.getString("Tell"));
                user.setGender(resultSet.getString("Gender"));
                user.setUserName(resultSet.getString("UserName"));
                user.setAge(resultSet.getString("Age"));
                user.setid(resultSet.getString("ID"));
                return user;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

}
