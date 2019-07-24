package dao;

import DBManager.DBManager;
import Entity.ProjectEntity;
import Entity.RegisterTokenEntity;
import servlet.Register;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterTokenDAO {
    /**
     * 查询号码对应的token信息
     */
    public static RegisterTokenEntity queryRegisterToken(String tell) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM registertoken WHERE Tell=?");

        //查询数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, tell);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                RegisterTokenEntity registerTokenEntity = new RegisterTokenEntity();
                registerTokenEntity.setTell(resultSet.getString("Tell"));
                registerTokenEntity.setVCode(resultSet.getString("VCode"));
                registerTokenEntity.setTime(resultSet.getTimestamp("Time"));
                return registerTokenEntity;
            }else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 判断该号码是否获取过验证码
     */
    public static boolean IfTokenExist(String tell) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM registertoken WHERE Tell=?");

        //查询数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, tell);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return  true;
            }else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 插入token信息
     */
    public static boolean insertRegisterToken(String Tell, String VCode, Timestamp Time) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if(RegisterTokenDAO.IfTokenExist(Tell)){
            //生成SQL代码
            StringBuilder sqlStatement = new StringBuilder();
            sqlStatement.append("UPDATE registertoken SET Vcode=?,Time=? WHERE Tell=?");

            //更新token
            try {
                preparedStatement = connection.prepareStatement(sqlStatement.toString());
                preparedStatement.setString(1, VCode);
                preparedStatement.setTimestamp(2, Time);
                preparedStatement.setString(3, Tell);
                if(preparedStatement.executeUpdate()==1){
                    return true;
                }else {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBManager.closeAll(connection, preparedStatement, resultSet);
            }
        } else{
            //生成SQL代码
            StringBuilder sqlStatement = new StringBuilder();
            sqlStatement.append("INSERT INTO registertoken(Tell,VCode,Time,Used) VALUES(?,?,?,0)");

            //插入新token
            try {
                preparedStatement = connection.prepareStatement(sqlStatement.toString());
                preparedStatement.setString(1, Tell);
                preparedStatement.setString(2, VCode);
                preparedStatement.setTimestamp(3, Time);
                if(preparedStatement.executeUpdate()==1){
                    return true;
                }else {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBManager.closeAll(connection, preparedStatement, resultSet);
            }
        }
    }

    /**
     * 修改某手机状态为已注册
     */
    public static boolean ChangeUsed(String Tell) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE registertoken SET Used=1 WHERE Tell=?");

        //更新token
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Tell);
            if(preparedStatement.executeUpdate()==1){
                return true;
            }else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
}
