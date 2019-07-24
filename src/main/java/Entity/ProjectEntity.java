package Entity;

import java.util.Date;

public class ProjectEntity {
    //项目ID
    private String ProjectID;

    //项目名称
    private String ProjectName;

    //项目地址
    private String Address;

    //项目单位
    private String UnitConstruction;

    //项目开始时间
    private Date StartTime;

    //项目监管单位
    private String SupervisionUnion;

    //项目进度
    private String CurrentProgress;

    //承包商
    private String Contractors;

    public String getProjectID() {
        return ProjectID;
    }
    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }


    public String getProjectName(){return ProjectName;}
    public void setProjectName(String ProjectName){this.ProjectName = ProjectName;}

    public String getAddress(){return Address;}
    public void setAddress(String Address){this.Address = Address;}

    public String getUnitConstruction(){return UnitConstruction;}
    public void setUnitConstruction(String UnitConstruction){this.UnitConstruction = UnitConstruction;}

    public Date getStartTime(){return StartTime;}
    public void setStartTime(Date StartTime){this.StartTime = StartTime;}

    public String getSupervisionUnion(){return SupervisionUnion;}
    public void setSupervisionUnion(String SupervisionUnion){this.SupervisionUnion = SupervisionUnion;}

    public String getCurrentProgress(){return CurrentProgress;}
    public void setCurrentProgress(String CurrentProgress){this.CurrentProgress = CurrentProgress;}

    public String getContractors(){return Contractors;}
    public void setContractors(String Contractors){this.Contractors = Contractors;}

    public ProjectEntity(){}
    public ProjectEntity(String ProjectID, String ProjectName, String Address, String UnitConstruction, Date StartTime, String SupervisionUnion, String CurrentProgress,String Contractors) {
        this.ProjectID = ProjectID;
        this.ProjectName = ProjectName;
        this.Address = Address;
        this.UnitConstruction = UnitConstruction;
        this.StartTime = StartTime;
        this.SupervisionUnion = SupervisionUnion;
        this.CurrentProgress = CurrentProgress;
        this.Contractors = Contractors;
    }
}
