package servlet;

import Entity.ProjectEntity;
import Entity.UserEntity;
import Tool.JsonDateValueProcessor;
import dao.ProjectDAO;
import dao.UserDAO;
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
 * 获取个人详细信息
 */
@WebServlet(name = "UserDetail")
public class UserDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try(PrintWriter out = response.getWriter()){
            //获取某个项目的所有所有详细信息并返回给前端
            String tell = request.getParameter("tell").trim();

            UserEntity user = UserDAO.queryUserDetail(tell);
            JSONObject jsonObject = JSONObject.fromObject(user);

            out.write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
