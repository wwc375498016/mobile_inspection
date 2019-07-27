package dao;

import DBManager.DBManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckDAO {
    /**
     * 插入新检查记录
     */
    public static boolean insertCheck(String CheckProject, String CheckType, Date CheckTime, String Rummager1, String Rummager2, String Rummager3, String Rummager4, String Rummager5, String TheInspected){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO checkrecord(CheckProject,CheckType,CheckTime,Rummager1,Rummager2,Rummager3,Rummager4,Rummager5,TheInspected) VALUES(?,?,?,?,?,?,?,?,?)");

        //插入新检查记录
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CheckProject);
            preparedStatement.setString(2, CheckType);
            preparedStatement.setDate(3, CheckTime);
            preparedStatement.setString(4, Rummager1);
            preparedStatement.setString(5, Rummager2);
            preparedStatement.setString(6, Rummager3);
            preparedStatement.setString(7, Rummager4);
            preparedStatement.setString(8, Rummager5);
            preparedStatement.setString(9, TheInspected);

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
