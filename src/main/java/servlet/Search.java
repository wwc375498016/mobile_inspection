package servlet;

import Entity.ProjectEntity;
import dao.ProjectDAO;
import dao.UserDAO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 判断搜索栏中搜索的信息内容是否存在
 */
@WebServlet(name = "Search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try(PrintWriter out = response.getWriter()){
            //搜索项目
            if(request.getParameter("ProjectName")!=null){
                String ProjectName = request.getParameter("ProjectName").trim();
                List<ProjectEntity> projectlist = ProjectDAO.fuzzySearchProject(ProjectName);
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                for(int i = 0;i < projectlist.size();i++){
                    jsonObject.put("ProjectName",projectlist.get(i).getProjectName());
                    jsonObject.put("Address",projectlist.get(i).getAddress());
                    jsonArray.add(jsonObject);
                }
                out.write(jsonArray.toString());
            }
            //搜索联系人
            else if(request.getParameter("UserName")!=null){
                String UserName = request.getParameter("UserName").trim();
                String tell = request.getParameter("tell").trim();
                List<String> usersName = UserDAO.fuzzySearchUsersName(UserName,tell);
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                for(int i = 0;i < usersName.size();i++){
                    jsonObject.put("UserName",usersName.get(i));
                    jsonArray.add(jsonObject);
                }
                out.write(jsonArray.toString());
            }
            //搜索政策
            else if(request.getParameter("PolicyName")!=null){

            }
            //搜索通知公告
            else if(request.getParameter("Announcement")!=null){

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
