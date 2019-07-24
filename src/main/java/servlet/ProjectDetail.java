package servlet;

import Entity.ProjectEntity;
import Tool.JsonDateValueProcessor;
import dao.ProjectDAO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 查看某一项目的详细信息
 */
@WebServlet(name = "ProjectDetail")
public class ProjectDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        try{
            //获取某个项目的所有所有详细信息并返回给前端
            String projectName = request.getParameter("projectName").trim();
            String address = request.getParameter("address").trim();

            ProjectEntity project = ProjectDAO.queryProject(projectName,address);
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
            JSONObject jsonObject = JSONObject.fromObject(project,jsonConfig);

            out.write(jsonObject.toString());
        }catch (Exception e){
            out.print("get data error");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
