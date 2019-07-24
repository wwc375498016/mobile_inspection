package servlet;

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
 * 拉取所有用户的信用户名
 */
@WebServlet(name = "Users")
public class Users extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String tell = request.getParameter("tell").trim();

        try(PrintWriter out = response.getWriter()){
            List<String> usersName = UserDAO.getContact(tell);
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for(int i = 0;i < usersName.size();i++){
                jsonObject.put("UserName",usersName.get(i));
                jsonArray.add(jsonObject);
            }
            out.write(jsonArray.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
