package com.jieko.reko.model;
import java.util.Date;

public class RekoActivity {
    //活动编号
    private Long aNumber;
    //创建者id
    private Long createrNumber;
    //活动名称
    private String aName;

    //标签1标签2
    private String label1Type;
    private String label2Site;
    //图片
    private String photograph;
    //内容类型
    private String contentType;
    //人数
    private int headcount;
    //活动状态
    private String activityState;
    //活动简介
    private String intro;
    //活动详情
    private String detail;
    //活动成员
    private String memberNumber;
    //活动开始时间
    private Date startTime;
    //活动结束时间
    private Date finishTime;

    public Long getaNumber() {
        return aNumber;
    }

    public void setaNumber(Long aNumber) {
        this.aNumber = aNumber;
    }

    public Long getCreaterNumber() {
        return createrNumber;
    }

    public void setCreaterNumber(Long createrNumber) {
        this.createrNumber = createrNumber;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getLabel1Type() {
        return label1Type;
    }

    public void setLabel1Type(String label1Type) {
        this.label1Type = label1Type;
    }

    public String getLabel2Site() {
        return label2Site;
    }

    public void setLabel2Site(String label2Site) {
        this.label2Site = label2Site;
    }

    public String getPhotograph() {
        return photograph;
    }

    public void setPhotograph(String photograph) {
        this.photograph = photograph;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getHeadcount() {
        return headcount;
    }

    public void setHeadcount(int headcount) {
        this.headcount = headcount;
    }

    public String getActivityState() {
        return activityState;
    }

    public void setActivityState(String activityState) {
        this.activityState = activityState;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    public RekoActivity(){}
    public RekoActivity(Long aNumber, Long createrNumber, String aName, String label1Type,
                        String label2Site, String photograph, String contentType, int headcount,
                        String activityState, String intro, String detail,
                        String memberNumber, Date startTime, Date finishTime) {
        this.aNumber = aNumber;
        this.createrNumber = createrNumber;
        this.aName = aName;
        this.label1Type = label1Type;
        this.label2Site = label2Site;
        this.photograph = photograph;
        this.contentType = contentType;
        this.headcount = headcount;
        this.activityState = activityState;
        this.intro = intro;
        this.detail = detail;
        this.memberNumber = memberNumber;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
}
