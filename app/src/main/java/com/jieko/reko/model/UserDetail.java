package com.jieko.reko.model;


import java.sql.Date;

public class UserDetail   {
    //用户编号
    private Long uDnumber;
    //生日
    private Date birthday;
    //性别
    private String sex;
    //昵称
    private String nickName;
    //头像URL
    private String portrait;
    //创建活动数
    private int createANumber;
    //删除活动数
    private int joinANubmber;
    //参加活动\
    private String joinActivity;
    //关注活动
    private String attentionANumber;


    public Long getuDnumber() {
        return uDnumber;
    }

    public void setuDnumber(Long uDnumber) {
        this.uDnumber = uDnumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getCreateANumber() {
        return createANumber;
    }

    public void setCreateANumber(int createANumber) {
        this.createANumber = createANumber;
    }

    public int getJoinANubmber() {
        return joinANubmber;
    }

    public void setJoinANubmber(int joinANubmber) {
        this.joinANubmber = joinANubmber;
    }

    public String getAttentionANumber() {
        return attentionANumber;
    }

    public void setAttentionANumber(String attentionANumber) {
        this.attentionANumber = attentionANumber;
    }

    public String getJoinActivity() {
        return joinActivity;
    }

    public void setJoinActivity(String joinActivity) {
        this.joinActivity = joinActivity;
    }

    public UserDetail(){};

    public UserDetail(Long uDnumber, Date birthday, String sex, String nickName, String portrait, int createANumber, int joinANubmber, String joinActivity, String attentionANumber) {
        this.uDnumber = uDnumber;
        this.birthday = birthday;
        this.sex = sex;
        this.nickName = nickName;
        this.portrait = portrait;
        this.createANumber = createANumber;
        this.joinANubmber = joinANubmber;
        this.joinActivity = joinActivity;
        this.attentionANumber = attentionANumber;
    }
    public UserDetail(Long uDnumber) {
        this.uDnumber = uDnumber;
        this.birthday = null;
        this.sex = null;
        this.nickName = null;
        this.portrait = null;
        this.createANumber = 0;
        this.joinANubmber = 0;
        this.joinActivity = null;
        this.attentionANumber = null;
    }
}
