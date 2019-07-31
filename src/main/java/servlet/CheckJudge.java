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
 * 发起检查前判断是否有相同未完成检查
 */
@WebServlet(name = "CheckJudge")
public class CheckJudge extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            //获得请求中传来的检查项目、检查时间、检查人
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            String CheckProject = request.getParameter("checkProject").trim();
            String Address = request.getParameter("address").trim();
            String CheckTime = request.getParameter("checkTime").trim();
            String Rummager = request.getParameter("rummager").trim();
            //判断检查是否完成
            if(CheckDAO.ifCompleted(CheckProject,Address,CheckTime,Rummager)==1 || CheckDAO.ifCompleted(CheckProject,Address,CheckTime,Rummager)==2 || CheckDAO.ifCompleted(CheckProject,Address,CheckTime,Rummager)==4){
                //这包含了同一天对同一项目发起多次检查和完全新的检查，这是因为现在的日期记录只精确到年月日不能区分同一天对同一项目的两个检查
                params.put("Result", "ok");
                jsonObject.put("params", params);
                out.write(jsonObject.toString());
            }else if(CheckDAO.ifCompleted(CheckProject,Address,CheckTime,Rummager)==0){
                params.put("Result", "check incompleted");
                jsonObject.put("params", params);
                out.write(jsonObject.toString());
            }else{
                params.put("Result", "error");
                jsonObject.put("params", params);
                out.write(jsonObject.toString());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
