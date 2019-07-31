package Entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 检查记录实体类
 */
public class CheckEntity {
    //检查记录ID
    private int CheckID;

    //检查项目名称
    private String CheckProject;

    //检查项目地址
    private String Address;

    //检查类型
    private String CheckType;

    //检查时间
    private String CheckTime;

    //检查人1
    private String Rummager1;

    //检查人2
    private String Rummager2;

    //检查人3
    private String Rummager3;

    //检查人4
    private String Rummager4;

    //检查人5
    private String Rummager5;

    //被检查人
    private String TheInspected;

    //基本情况文本
    private String Situation;

    //执法措施及要求
    private String MeasuresAndRequirements;

    //签名文件路径
    private String SignaturePath;

    public int getCkeckID() { return CheckID; }
    public void setCkeckID(int CheckID) { this.CheckID = CheckID; }

    public String getCheckProject(){return CheckProject;}
    public void setCheckProject(String CheckProject){this.CheckProject = CheckProject;}

    public String getAddress(){return Address;}
    public void setAddress(String Address){this.Address = Address;}

    public String getCheckType(){return CheckType;}
    public void setCheckType(String CheckType){this.CheckType = CheckType;}

    public String getCheckTime(){return CheckTime;}
    public void setCheckTime(String CheckTime){this.CheckTime = CheckTime;}

    public String getRummager1(){return Rummager1;}
    public void setRummager1(String Rummager1){this.Rummager1 = Rummager1;}

    public String getRummager2(){return Rummager2;}
    public void setRummager2(String Rummager2){this.Rummager2 = Rummager2;}

    public String getRummager3(){return Rummager3;}
    public void setRummager3(String Rummager3){this.Rummager3 = Rummager3;}

    public String getRummager4(){return Rummager4;}
    public void setRummager4(String Rummager4){this.Rummager4 = Rummager4;}

    public String getRummager5(){return Rummager5;}
    public void setRummager5(String Rummager5){this.Rummager5 = Rummager5;}

    public String getTheInspected(){return TheInspected;}
    public void setTheInspected(String TheInspected){this.TheInspected = TheInspected;}

    public String getSituation(){return Situation;}
    public void setSituation(String Situation){this.Situation = Situation;}

    public String getMeasuresAndRequirements(){return MeasuresAndRequirements;}
    public void setMeasuresAndRequirements(String MeasuresAndRequirements){this.MeasuresAndRequirements = MeasuresAndRequirements;}

    public String getSignaturePath(){return SignaturePath;}
    public void setSignaturePath(String SignaturePath){this.SignaturePath = SignaturePath;}

    public CheckEntity(){}
    public CheckEntity(int CheckID, String CheckProject, String Address,String CheckType, String CheckTime, String Rummager1, String Rummager2, String Rummager3, String Rummager4, String Rummager5, String TheInspected, String Situation, String MeasuresAndRequirements, String SignaturePath) {
        this.CheckID = CheckID;
        this.CheckProject = CheckProject;
        this.Address = Address;
        this.CheckType = CheckType;
        this.CheckTime = CheckTime;
        this.Rummager1 = Rummager1;
        this.Rummager2 = Rummager2;
        this.Rummager3 = Rummager3;
        this.Rummager4 = Rummager4;
        this.Rummager5 = Rummager5;
        this.TheInspected = TheInspected;
        this.Situation = Situation;
        this.MeasuresAndRequirements = MeasuresAndRequirements;
        this.SignaturePath = SignaturePath;
    }
}
