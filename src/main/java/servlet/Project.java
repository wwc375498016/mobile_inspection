package servlet;

import Entity.ProjectEntity;
import dao.ProjectDAO;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拉取所有项目的名称和地址
 */
@WebServlet(name = "Project")
public class Project extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        try{
            //获取所有项目信息并放入JSONArray中传回前端
            List<ProjectEntity> projectlist = ProjectDAO.queryAllProject();
            Map<String, String> params = new HashMap<>();
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(int i = 0;i < projectlist.size();i++){
                jsonObject.put("ProjectName",projectlist.get(i).getProjectName());
                jsonObject.put("Address",projectlist.get(i).getAddress());
                jsonArray.add(jsonObject);
            }
            out.write(jsonArray.toString());
        }catch (Exception e){
            out.print("get data error");
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
