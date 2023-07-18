package com.xiaoxin.req;

public class SysUserLoginReq {


    private String name;


    private String password;



    public String getLoginName() {
        return name;
    }

    public void setLoginName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SysUserEntity{" +
                ", Name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
