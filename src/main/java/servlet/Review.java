package servlet;

import Entity.CheckEntity;
import Entity.ProjectEntity;
import dao.CheckDAO;
import dao.ProjectDAO;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *拉取审阅信息
 */
@WebServlet(name = "Review")
public class Review extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try(PrintWriter out = response.getWriter()){
            //获取某个项目的所有所有详细信息并返回给前端
            String CheckProject = request.getParameter("checkProject").trim();
            String Address = request.getParameter("address").trim();
            String CheckTime = request.getParameter("checkTime").trim();

            ProjectEntity project = ProjectDAO.queryProject(CheckProject,Address);
            CheckEntity check = CheckDAO.reviewCheck(CheckProject,Address,CheckTime);

            JSONObject jsonObject =  new JSONObject();
            System.out.println(check.getRummager1());
            String rummager = null;
            rummager = check.getRummager1();
            if(check.getRummager2()!=null){
                rummager = rummager+" "+check.getRummager2();
            }
            if(check.getRummager3()!=null){
                rummager = rummager+" "+check.getRummager3();
            }
            if(check.getRummager4()!=null){
                rummager = rummager+" "+check.getRummager4();
            }
            if(check.getRummager5()!=null){
                rummager = rummager+" "+check.getRummager5();
            }

            jsonObject.put("rummager",rummager);
            jsonObject.put("Contractors",project.getContractors());
            jsonObject.put("UnitConstruction",project.getUnitConstruction());
            jsonObject.put("SupervisionUnion",project.getSupervisionUnion());
            jsonObject.put("Situation",check.getSituation());
            jsonObject.put("MeasuresAndRequirements",check.getMeasuresAndRequirements());
            out.write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
