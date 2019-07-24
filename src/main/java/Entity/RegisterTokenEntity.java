package Entity;

import java.sql.Timestamp;
import java.util.Date;

public class RegisterTokenEntity {
    //tokenID
    private int RegisterTokenID;

    //token对应号码
    private String Tell;

    //token保存的验证码
    private String VCode;

    //token对应的时间
    private Timestamp Time;

    //token对应手机是否已完成注册
    private int Used;

    public int getRegisterTokenID(){return RegisterTokenID;}
    public void setRegisterTokenID(int RegisterTokenID){this.RegisterTokenID = RegisterTokenID;}

    public String getTell(){return Tell;}
    public void setTell(String Tell){this.Tell = Tell;}

    public String getVCode(){return VCode;}
    public void setVCode(String VCode){this.VCode = VCode;}

    public Timestamp getTime(){return Time;}
    public void setTime(Timestamp Time){this.Time = Time;}

    public int getUsed(){return Used;}
    public void setUsed(int Used){this.Used = Used;}

    public RegisterTokenEntity(){}
    public RegisterTokenEntity(int RegisterTokenID,String Tell,String VCode,Timestamp Time,int Used) {
        this.RegisterTokenID = RegisterTokenID;
        this.Tell = Tell;
        this.VCode = VCode;
        this.Time = Time;
        this.Used = Used;
    }
}
