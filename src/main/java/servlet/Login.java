package servlet;

import dao.UserDAO;
import Entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 * 测试登录Servlet
 *
 * @author Implementist
 */
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的用户名和密码
            String userID = request.getParameter("AccountNumber").trim();
            String password = request.getParameter("Password").trim();

            //密码验证结果
            Boolean verifyResult = verifyLogin(userID, password);

            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();

            if (verifyResult) {
                params.put("Result", "success");
            } else {
                params.put("Result", "failed");
            }

            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 验证用户名密码是否正确
     *
     * @param password
     */
    private Boolean verifyLogin(String userID, String password) {
        UserEntity user = UserDAO.queryUser(userID, password);

        //账户密码验证
        return null != user && password.equals(user.getPassword());
    }
}
