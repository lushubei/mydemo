package com.example.imakeyouth.vo.req;

/**
 * Created by lushubei on 17/11/4.
 */
public class LoginReq {
    private String userName;
    private String password;

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

    @Override
    public String toString(){
        return "loginBegin ["+ this.getUserName() + "]";
    }
}
