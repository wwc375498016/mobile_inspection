package servlet;

import dao.CheckDAO;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 发起检查，新建检查记录
 */
@WebServlet(name = "CheckStart")
public class CheckStart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            //获得请求中传来的检查项目、检查时间、检查人
            String CheckProject = request.getParameter("CheckProject").trim();
            String Rummager1 = request.getParameter("Rummager1").trim();
            String Rummager2 = null;
            String Rummager3 = null;
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            if(request.getParameter("Rummager2")!=null){
                Rummager2 = request.getParameter("Rummager2").trim();
            }
            if(request.getParameter("Rummager3")!=null){
                Rummager3 = request.getParameter("Rummager3").trim();
            }
            Date CheckTime = new Date(System.currentTimeMillis());

            if (CheckDAO.insertCheck(CheckProject, CheckTime, Rummager1, Rummager2, Rummager3)) {
                params.put("Result", "success");
            } else {
                params.put("Result", "failed");
            }
            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
