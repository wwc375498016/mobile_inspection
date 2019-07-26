package dao;

import DBManager.DBManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckDAO {
    /**
     * 插入新检查记录
     */
    public static boolean insertCheck(String CheckProject, Date CheckTime, String Rummager1, String Rummager2, String Rummager3){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO checkrecord(CheckProject,CheckTime,Rummager1,Rummager2,Rummager3) VALUES(?,?,?,?,?)");

        //插入新检查记录
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CheckProject);
            preparedStatement.setDate(2, CheckTime);
            preparedStatement.setString(3, Rummager1);
            preparedStatement.setString(4, Rummager2);
            preparedStatement.setString(5, Rummager3);

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
