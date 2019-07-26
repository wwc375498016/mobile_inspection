package servlet;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除录音
 */
@WebServlet(name = "RecordDelete")
public class RecordDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try(PrintWriter out = response.getWriter() ){
            String projectName = request.getParameter("projectName").trim();
            String address = request.getParameter("address").trim();
            String date = request.getParameter("date").trim();
            String time = request.getParameter("time").trim();
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();

            String fileName = "C:/xampp/tomcat/webapps/voice/" +projectName+" "+address+"/"+date+"/"+time+".amr";
            File file = new File(fileName);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    params.put("Result", "success");
                } else {
                    params.put("Result", "failed");
                }
            } else {
                params.put("Result", "File does not exist");
            }
            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
