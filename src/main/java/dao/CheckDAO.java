package dao;

import DBManager.DBManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckDAO {
    /**
     * 插入新检查记录
     */
    public static boolean insertCheck(String CheckProject, String Address,String CheckType, String CheckTime, String Rummager1, String Rummager2, String Rummager3, String Rummager4, String Rummager5, String TheInspected, String Situation, String MAR){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO checkrecord(CheckProject,Address,CheckType,CheckTime,Rummager1,Rummager2,Rummager3,Rummager4,Rummager5,TheInspected,Situation,MeasuresAndRequirements) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

        //插入新检查记录
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CheckProject);
            preparedStatement.setString(2, Address);
            preparedStatement.setString(3, CheckType);
            preparedStatement.setString(4, CheckTime);
            preparedStatement.setString(5, Rummager1);
            preparedStatement.setString(6, Rummager2);
            preparedStatement.setString(7, Rummager3);
            preparedStatement.setString(8, Rummager4);
            preparedStatement.setString(9, Rummager5);
            preparedStatement.setString(10, TheInspected);
            preparedStatement.setString(11, Situation);
            preparedStatement.setString(12, MAR);

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
