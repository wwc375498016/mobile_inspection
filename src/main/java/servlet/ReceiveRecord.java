package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 使用fileload——Apache文件上传组件来进行文件的接收
 */
@WebServlet(name = "ReceiveRecord")
public class ReceiveRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();



        // 解析结果放在List中
        try {
            // 创建文件项目工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // 设置文件上传路径File
            File file =new File("D:\\RecordFromAPP");
            String upload=file.getPath();
            // 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
            String temp = System.getProperty("java.io.tmpdir");
            /*如果你不用tomcat，直接一个java类main方法，然后直接输出System.getProperty
            ("java.io.tmpdir")那么结果是你当前系统下的临时文件目录如win7：C:\Users\用户
            名\AppData\Local\Temp。但如果你把web程序放入tomcat下然后再输出System.getProperty
            ("java.io.tmpdir")，那么这是结果是：D:\apache-tomcat-7.0.47\temp
             */

            // 设置缓冲区大小为 5M
            factory.setSizeThreshold(1024 * 1024 * 5);
            // 设置临时文件夹为temp
            factory.setRepository(new File(temp));
            // 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求（文件上传解析器）
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);//创建一个上传工具，指定使用缓存区与临时文件存储位置.
            //监听文件上传进度
            servletFileUpload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
            //解决上传文件名的中文乱码
            servletFileUpload.setHeaderEncoding("UTF-8");


            List<FileItem> list = servletFileUpload.parseRequest(new ServletRequestContext(request));
            for (FileItem item : list) {
                String name = item.getFieldName();
                InputStream is = item.getInputStream();

                if (name.contains("content")) {
                    System.out.println(inputStream2String(is));
                } else if (name.contains("file")) {
                    try {
                        inputStream2File(is, upload + "\\" + item.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            out.write("success");
        } catch (FileUploadException e) {
            e.printStackTrace();
            out.write("failure");
        }

        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    // 流转化成字符串
    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    // 流转化成文件
    public static void inputStream2File(InputStream is, String savePath) throws Exception {
        System.out.println("文件保存路径为:" + savePath);
        File file = new File(savePath);
        InputStream inputSteam = is;
        BufferedInputStream fis = new BufferedInputStream(inputSteam);
        FileOutputStream fos = new FileOutputStream(file);
        int f;
        while ((f = fis.read()) != -1) {
            fos.write(f);
        }
        fos.flush();
        fos.close();
        fis.close();
        inputSteam.close();
    }
}
