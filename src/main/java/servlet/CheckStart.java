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
            String CheckProject = request.getParameter("checkProject").trim();
            String CheckType = request.getParameter("checkType").trim();
            String TheInspected = request.getParameter("theInspected").trim();
            String Rummager1 = request.getParameter("rummager1").trim();
            String Rummager2 = null;
            String Rummager3 = null;
            String Rummager4 = null;
            String Rummager5 = null;
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            if(request.getParameter("rummager2")!=null){
                Rummager2 = request.getParameter("rummager2").trim();
            }
            if(request.getParameter("rummager3")!=null){
                Rummager3 = request.getParameter("rummager3").trim();
            }
            if(request.getParameter("rummager4")!=null){
                Rummager4 = request.getParameter("rummager4").trim();
            }
            if(request.getParameter("rummager5")!=null){
                Rummager5 = request.getParameter("rummager5").trim();
            }

            Date CheckTime = new Date(System.currentTimeMillis());

            if (CheckDAO.insertCheck(CheckProject, CheckType, CheckTime, Rummager1, Rummager2, Rummager3, Rummager4, Rummager5, TheInspected)) {
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
