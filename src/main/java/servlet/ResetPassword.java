package servlet;

import Entity.ProjectEntity;
import Entity.RegisterTokenEntity;
import dao.ProjectDAO;
import dao.RegisterTokenDAO;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重置密码接口
 */
@WebServlet(name = "ResetPassword")
public class ResetPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        try{
            //获取从前端传来的信息
            String tell = request.getParameter("tell").trim();
            String newPassword = request.getParameter("newPassword").trim();
            String verifyCode = request.getParameter("VCode").trim();
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            //判断账号是否存在
            if(!UserDAO.ifexistUser(tell)){
                params.put("Result", "该号码未注册");
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
                } else if(UserDAO.ifPasswordSame(tell,newPassword)){
                    params.put("Result", "新密码与旧密码重复");
                    jsonObject.put("params", params);
                    out.write(jsonObject.toString());
                }else{
                    //重置密码
                    if(UserDAO.resetPassword(tell,newPassword)){
                        params.put("Result", "success");

                    }else{
                        params.put("Result", "failed");
                    }
                    jsonObject.put("params", params);
                    out.write(jsonObject.toString());
                }
            }
        }catch (Exception e){
            out.print("get data error");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
