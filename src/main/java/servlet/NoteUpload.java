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
import java.util.HashMap;
import java.util.Map;
/**
 * 上传笔录
 */
@WebServlet(name = "NoteUpload")
public class NoteUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            //获得请求中传来的检查项目、检查时间、检查人
            String CheckProject = request.getParameter("projectName").trim();
            String Address = request.getParameter("address").trim();
            String CheckTime = request.getParameter("date").trim();
            String CheckType = request.getParameter("type").trim();
            String Situation = request.getParameter("result").trim();
            String MAR = request.getParameter("require").trim();
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            if (CheckDAO.insertNote(CheckProject,Address,CheckType,CheckTime,Situation,MAR)) {
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
