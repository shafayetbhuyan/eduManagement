package common.entity;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String fullName;
    private String department;
    private String details;
    private String userName;
    private String password;
    private Integer type;

    public User(Integer id, String fullName, String department, String details, String userName, String password, Integer type) {
        this.id = id;
        this.fullName = fullName;
        this.department = department;
        this.details = details;
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public User(){

    }

//    public User(Integer id, String userName, String password, Integer type) {
//        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.type = type;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", details='" + details + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
