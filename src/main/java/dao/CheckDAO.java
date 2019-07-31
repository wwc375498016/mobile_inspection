package dao;

import DBManager.DBManager;
import Entity.CheckEntity;
import Entity.ProjectEntity;

import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckDAO {
    /**
     * 开始执法，上传项目基本信息
     */
    public static boolean startCheck(String CheckProject, String Address,String CheckTime,String Rummager1, String Rummager2, String Rummager3, String Rummager4, String Rummager5){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO checkrecord(CheckProject,Address,CheckTime,Rummager1,Rummager2,Rummager3,Rummager4,Rummager5) VALUES(?,?,?,?,?,?,?,?)");

        //插入新检查记录
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CheckProject);
            preparedStatement.setString(2, Address);
            preparedStatement.setString(3, CheckTime);
            preparedStatement.setString(4, Rummager1);
            preparedStatement.setString(5, Rummager2);
            preparedStatement.setString(6, Rummager3);
            preparedStatement.setString(7, Rummager4);
            preparedStatement.setString(8, Rummager5);

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

    /**
     * 保存笔录
     */
    public static boolean insertNote(String CheckProject, String Address,String CheckType, String CheckTime,String Situation,String MAR){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE checkrecord SET Situation=?,CheckType=?,MeasuresAndRequirements=? WHERE CheckProject=? AND Address=? AND CheckTime=?");

        //插入新检查记录
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Situation);
            preparedStatement.setString(2, CheckType);
            preparedStatement.setString(3, MAR);
            preparedStatement.setString(4, CheckProject);
            preparedStatement.setString(5, Address);
            preparedStatement.setString(6, CheckTime);

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

    /**
     * 获取审阅界面信息
     * @return
     */
    public static CheckEntity reviewCheck(String CheckProject, String Address,String CheckTime) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM checkrecord WHERE CheckProject=? AND Address=? AND CheckTime=?");

        //查询数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CheckProject);
            preparedStatement.setString(2, Address);
            preparedStatement.setString(3, CheckTime);
            resultSet = preparedStatement.executeQuery();

            CheckEntity check = new CheckEntity();
            while(resultSet.next()) {
                check.setCheckProject(resultSet.getString("CheckProject"));
                check.setAddress(resultSet.getString("Address"));
                check.setCheckType(resultSet.getString("CheckType"));
                check.setCheckTime(resultSet.getString("CheckTime"));
                check.setRummager1(resultSet.getString("Rummager1"));
                check.setRummager2(resultSet.getString("Rummager2"));
                check.setRummager3(resultSet.getString("Rummager3"));
                check.setRummager4(resultSet.getString("Rummager4"));
                check.setRummager5(resultSet.getString("Rummager5"));
                check.setTheInspected(resultSet.getString("TheInspected"));
                check.setSituation(resultSet.getString("Situation"));
                check.setMeasuresAndRequirements(resultSet.getString("MeasuresAndRequirements"));
            }
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 查询标志位信息，判断记录是否已完成审阅
     * @return
     */
    public static int ifCompleted(String CheckProject, String Address,String CheckTime,String Rummager) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT Rummager1,Rummager2,Rummager3,Rummager4,Rummager5,IfCompleted FROM checkrecord WHERE CheckProject=? AND Address=? AND CheckTime=?");

        //查询数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CheckProject);
            preparedStatement.setString(2, Address);
            preparedStatement.setString(3, CheckTime);
            resultSet = preparedStatement.executeQuery();

            int judge = 0;

            while(resultSet.next()) {
                if(resultSet.getString("IfCompleted").equals("0")){
                    if(resultSet.getString("Rummager1").equals(Rummager)){
                        judge=1;
                    }else if(resultSet.getString("Rummager2")!=null && resultSet.getString("Rummager2").equals(Rummager)){
                        judge=1;
                    }else if(resultSet.getString("Rummager3")!=null && resultSet.getString("Rummager3").equals(Rummager)){
                        judge=1;
                    }else if(resultSet.getString("Rummager4")!=null && resultSet.getString("Rummager4").equals(Rummager)){
                        judge=1;
                    }else if(resultSet.getString("Rummager5")!=null && resultSet.getString("Rummager5").equals(Rummager)){
                        judge=1;
                    }

                    if (judge == 1) {
                        return 0;
                    }else {
                        return 4;
                    }

                }else {
                    return 1;//已完成
                }
            }
            return 2;//未创建该记录
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return 3;//其他错误
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 修改标志位信息
     */
    public static boolean checkComplete(String CheckProject, String Address,String CheckTime){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE checkrecord SET IfCompleted=? WHERE CheckProject=? AND Address=? AND CheckTime=?");

        //插入新检查记录
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, "1");
            preparedStatement.setString(2, CheckProject);
            preparedStatement.setString(3, Address);
            preparedStatement.setString(4, CheckTime);

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

    /**
     * 获取某检查的笔录
     */
    public static CheckEntity getNote(String CheckProject, String Address,String CheckTime) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM checkrecord WHERE CheckProject=? AND Address=? AND CheckTime=?");

        //查询数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CheckProject);
            preparedStatement.setString(2, Address);
            preparedStatement.setString(3, CheckTime);
            resultSet = preparedStatement.executeQuery();

            CheckEntity check = new CheckEntity();
            while(resultSet.next()) {
                check.setMeasuresAndRequirements(resultSet.getString("MeasuresAndRequirements"));
                check.setSituation(resultSet.getString("Situation"));
                check.setCheckType(resultSet.getString("CheckType"));
            }
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
}
