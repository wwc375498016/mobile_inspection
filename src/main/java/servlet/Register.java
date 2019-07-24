package servlet;

import Entity.RegisterTokenEntity;
import Entity.UserEntity;
import dao.RegisterTokenDAO;
import dao.UserDAO;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册账户
 */
@WebServlet(name = "Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {
            //获得请求中传来的用户注册信息
            String tell = request.getParameter("tell").trim();
            String verifyCode = request.getParameter("VCode").trim();
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            if(UserDAO.ifexistUser(tell)) {
                params.put("Result", "Registered!");
                jsonObject.put("params", params);
                out.write(jsonObject.toString());
            }else {
                //验证码检验模块
                RegisterTokenEntity registerTokenEntity = RegisterTokenDAO.queryRegisterToken(tell);
                //获取当前系统时间戳
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());

                if (registerTokenEntity == null) {
                    params.put("Result", "手机号错误");
                    jsonObject.put("params", params);
                    out.write(jsonObject.toString());
                } else if (!registerTokenEntity.getVCode().equals(verifyCode)) {
                    params.put("Result", "验证码错误");
                    jsonObject.put("params", params);
                    out.write(jsonObject.toString());
                } else if ((timestamp.getTime() - registerTokenEntity.getTime().getTime()) > 1000 * 60) {//验证码设定的时间为1分钟过期
                        params.put("Result", "验证码已过期");
                        jsonObject.put("params", params);
                        out.write(jsonObject.toString());
                    } else {
                        String userName = request.getParameter("userName").trim();
                        String password = request.getParameter("Password").trim();
                        String id = request.getParameter("id").trim();
                        String gender = request.getParameter("gender").trim();
                        String workNumber = request.getParameter("workNumber").trim();
                        String age = request.getParameter("age").trim();
                        if (UserDAO.registerUser(userName, password, gender, id, tell, workNumber, age)) {
                            params.put("Result", "success");
                            RegisterTokenDAO.ChangeUsed(tell);
                        } else {
                            params.put("Result", "failed");
                        }
                        jsonObject.put("params", params);
                        out.write(jsonObject.toString());
                    }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
