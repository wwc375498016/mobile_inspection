package servlet;

import com.zhenzi.sms.ZhenziSmsClient;
import dao.RegisterTokenDAO;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 发送随机验证码
 */
@WebServlet(name = "SendSmsServlet")
public class SendSmsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //短信平台相关参数
    private String apiUrl = "http://sms_developer.zhenzikj.com";
    private String appId = "101132";
    private String appSecret = "7ea66fd9-e245-4480-a9da-c33432c5b648";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String tell = request.getParameter("tell").trim();

            //该部分实现发送验证码功能
            String verifyCode = String.valueOf(new Random().nextInt(9000)+1000);//生成四位随机数，范围1000~9999
            ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com","101132", "7ea66fd9-e245-4480-a9da-c33432c5b648");
            String result = client.send(tell, "您的验证码为:" + verifyCode + "，请在5分钟内完成验证，请勿将验证码泄露给他人。\n");
            /*
            返回的result是一个json格式的字符串，如：
            {"code":0,"data":"发送成功"}
            code为信息代码，data保存对应返回的信息；0位发送成功，非零为失败。
             */

            //向前台返回对应的信息
            JSONObject jsonObject = JSONObject.fromObject(result);
            Map<String, String> params = new HashMap<>();
            if(jsonObject.getInt("code") != 0){//发送失败
                params.put("Result", "failed");
            }else{
                params.put("Result", "success");
            }
            System.out.println(jsonObject.toString()+"/n验证码："+verifyCode);//打印result给自己看错误详情

            //返回发送是否成功消息
            jsonObject.clear();
            jsonObject.put("params", params);
            out.write(jsonObject.toString());

            //建立Token信息
            Timestamp d = new Timestamp(System.currentTimeMillis());
            if(RegisterTokenDAO.insertRegisterToken(tell,verifyCode,d)){
                System.out.println("创建token成功！");
            }else{
                System.out.println("创建token失败！");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
