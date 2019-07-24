package servlet;

import Entity.RegisterTokenEntity;
import dao.RegisterTokenDAO;
import dao.UserDAO;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用验证码和手机账号进行登录
 */
@WebServlet(name = "LoginByVCode")
public class LoginByVCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的用户名和验证码
            String tell = request.getParameter("AccountNumber").trim();
            String verifyCode = request.getParameter("VCode").trim();

            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();

            if(!UserDAO.ifexistUser(tell)){
                params.put("Result", "用户名不存在");
                jsonObject.put("params", params);
                out.write(jsonObject.toString());
            }else{
                //验证码检验模块
                RegisterTokenEntity registerTokenEntity = RegisterTokenDAO.queryRegisterToken(tell);
                //获取当前系统时间戳
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                if (!registerTokenEntity.getVCode().equals(verifyCode)) {
                    params.put("Result", "验证码错误");
                    jsonObject.put("params", params);
                    out.write(jsonObject.toString());
                } else if ((timestamp.getTime() - registerTokenEntity.getTime().getTime()) > 1000 * 60) {//验证码设定的时间为1分钟过期
                    params.put("Result", "验证码已过期");
                    jsonObject.put("params", params);
                    out.write(jsonObject.toString());
                }else{
                    params.put("Result", "success");
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
