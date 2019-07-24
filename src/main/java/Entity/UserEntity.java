package Entity;

public class UserEntity {
    //用户ID
    private String UserID;

    //用户姓名
    private String userName;

    //用户密码
    private String password;

    //用户地址
    private String address;

    //用户电话
    private String tell;

    //用户性别0为女1为男
    private String gender;

    //
    private String birthday;
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setSex(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }
    public UserEntity(){}
    public UserEntity(String UserID, String userName, String password, String gender, String address, String tell, String birthday) {
        this.UserID = UserID;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.tell = tell;
        this.gender = gender;
        this.birthday = birthday;
        // this.age = age;
    }

}
