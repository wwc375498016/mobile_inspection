package dao;

import DBManager.DBManager;
import Entity.ProjectEntity;
import servlet.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectDAO {
    /**
     * 查询所有项目的项目名和地址
     * @return
     */
    public static List<ProjectEntity> queryAllProject() {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT ProjectName,Address FROM project");

        //查询数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());

            resultSet = preparedStatement.executeQuery();
            List<ProjectEntity> projectlist = new ArrayList<ProjectEntity>();

            while(resultSet.next()) {
                ProjectEntity project = new ProjectEntity();
                project.setProjectName(resultSet.getString("ProjectName"));
                project.setAddress(resultSet.getString("Address"));
                projectlist.add(project);
            }
            return projectlist;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 获取某项目的具体详细信息
     * @return
     */
    public static ProjectEntity queryProject(String projectName,String address) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM project WHERE ProjectName=? AND Address=?");

        //查询数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, projectName);
            preparedStatement.setString(2, address);
            resultSet = preparedStatement.executeQuery();

            ProjectEntity project = new ProjectEntity();
            while(resultSet.next()) {
                project.setProjectName(resultSet.getString("ProjectName"));
                project.setAddress(resultSet.getString("Address"));
                project.setUnitConstruction(resultSet.getString("UnitConstruction"));
                project.setSupervisionUnion(resultSet.getString("SupervisionUnion"));
                project.setCurrentProgress(resultSet.getString("CurrentProgress"));
                project.setContractors(resultSet.getString("Contractors"));

                Date save = new java.util.Date(resultSet.getDate("StartTime").getTime());
                project.setStartTime(save);
            }
            return project;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     *模糊搜索项目名及地址
     */
    public static List<ProjectEntity> fuzzySearchProject(String projectFuzzyName) {

        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT ProjectName,Address FROM project WHERE ProjectName LIKE ?");

        //查询用户名
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,"%"+projectFuzzyName+"%");
            resultSet = preparedStatement.executeQuery();
            List<ProjectEntity> projectlist = new ArrayList<>();
            while(resultSet.next()) {
                ProjectEntity project = new ProjectEntity();
                project.setProjectName(resultSet.getString("ProjectName"));
                project.setAddress(resultSet.getString("Address"));
                projectlist.add(project);
            }
            return projectlist;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
}
