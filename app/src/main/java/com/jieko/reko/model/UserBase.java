package com.jieko.reko.model;


public class UserBase {
    //用户编号
    private Long uBnumber;
    //账户
    private String account;
    //密码
    private String  passWord;

    public Long getuBnumber() {
        return uBnumber;
    }

    public void setuBnumber(Long uBnumber) {
        this.uBnumber = uBnumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserBase(){

    }
    public UserBase(Long uBnumber, String account, String passWord) {
        this.uBnumber = uBnumber;
        this.account = account;
        this.passWord = passWord;
    }
}
