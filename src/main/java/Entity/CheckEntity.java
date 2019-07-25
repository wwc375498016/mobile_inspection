package Entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 检查记录实体类
 */
public class CheckEntity {
    //检查记录ID
    private int CheckID;

    //检查项目名称
    private String CheckProject;

    //检查类型
    private String CheckType;

    //检查时间
    private Timestamp CheckTime;

    //检查人1
    private String Rummager1;

    //检查人2
    private String Rummager2;

    //检查人3
    private String Rummager3;

    //被检查人
    private String TheInspected;

    //基本情况文本路径
    private String SituationPath;

    //执法措施及要求路径
    private String MeasuresAndRequirementsPath;

    //签名文件路径
    private String SignaturePath;

    public int getCkeckID() { return CheckID; }
    public void setCkeckID(int CheckID) { this.CheckID = CheckID; }

    public String getCheckProject(){return CheckProject;}
    public void setCheckProject(String CheckProject){this.CheckProject = CheckProject;}

    public String getCheckType(){return CheckType;}
    public void setCheckType(String CheckType){this.CheckType = CheckType;}

    public Timestamp getCheckTime(){return CheckTime;}
    public void setCheckTime(Timestamp CheckTime){this.CheckTime = CheckTime;}

    public String getRummager1(){return Rummager1;}
    public void setRummager1(String Rummager1){this.Rummager1 = Rummager1;}

    public String getRummager2(){return Rummager2;}
    public void setRummager2(String Rummager2){this.Rummager2 = Rummager2;}

    public String getRummager3(){return Rummager3;}
    public void setRummager3(String Rummager3){this.Rummager3 = Rummager3;}

    public String getTheInspected(){return TheInspected;}
    public void setTheInspected(String TheInspected){this.TheInspected = TheInspected;}

    public String getSituationPath(){return SituationPath;}
    public void setSituationPath(String SituationPath){this.SituationPath = SituationPath;}

    public String getMeasuresAndRequirementsPath(){return MeasuresAndRequirementsPath;}
    public void setMeasuresAndRequirementsPath(String MeasuresAndRequirementsPath){this.MeasuresAndRequirementsPath = MeasuresAndRequirementsPath;}

    public String getSignaturePath(){return SignaturePath;}
    public void setSignaturePath(String SignaturePath){this.SignaturePath = SignaturePath;}

    public CheckEntity(){}
    public CheckEntity(int CheckID, String CheckProject, String CheckType, Timestamp CheckTime, String Rummager1, String Rummager2, String Rummager3, String TheInspected, String SituationPath, String MeasuresAndRequirementsPath, String SignaturePath) {
        this.CheckID = CheckID;
        this.CheckProject = CheckProject;
        this.CheckType = CheckType;
        this.CheckTime = CheckTime;
        this.Rummager1 = Rummager1;
        this.Rummager2 = Rummager2;
        this.Rummager3 = Rummager3;
        this.TheInspected = TheInspected;
        this.SituationPath = SituationPath;
        this.MeasuresAndRequirementsPath = MeasuresAndRequirementsPath;
        this.SignaturePath = SignaturePath;
    }
}
