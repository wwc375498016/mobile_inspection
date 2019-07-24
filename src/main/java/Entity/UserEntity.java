package Entity;

public class UserEntity {
    //用户ID
    private int userID;

    //用户姓名
    private String userName;

    //用户密码
    private String password;

    //用户身份证
    private String id;

    //用户电话
    private String tell;

    //用户性别0为女1为男
    private String gender;

    //用户工号
    private String workNumber;

    //用户年龄
    private String age;

    public int getuserID() {
        return userID;
    }
    public void setuserID(int userID) {
        this.userID = userID;
    }

    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
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
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getworkNumber() {
        return workNumber;
    }
    public void setworkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getTell() {
        return tell;
    }
    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public UserEntity(){}
    public UserEntity(int userID, String userName, String password, String gender, String id, String tell, String workNumber, String age) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.tell = tell;
        this.gender = gender;
        this.workNumber = workNumber;
        this.age = age;
    }

}
