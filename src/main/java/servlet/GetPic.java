package servlet;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 将图片以base64码的方式返回给客户端
 */
@WebServlet(name = "GetPic")
public class GetPic extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的用户名和密码
            String picName = request.getParameter("picName").trim();
            InputStream in = null;
            byte[] data = null;
            // 读取图片字节数组
            in = new FileInputStream("C:/xampp/tomcat/webapps/pic/" +picName+".jpg");
            data = new byte[in.available()];
            in.read(data);
            in.close();
            // 对字节数组Base64编码
            String encoder = Base64.encodeBase64String(data);
            // 返回Base64编码过的字节数组字符串
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            params.put("Code", encoder);
            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
