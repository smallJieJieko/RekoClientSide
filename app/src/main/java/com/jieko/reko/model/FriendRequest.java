package com.jieko.reko.model;

public class FriendRequest {
    //请求编号
    private Long requestNumber;
    //请求者
    private Long sendApplication;
    //接收请求者
    private Long getApplication;
    //请求状态
    private String state = "wait";

    public Long getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(Long requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Long getSendApplication() {
        return sendApplication;
    }

    public void setSendApplication(Long sendApplication) {
        this.sendApplication = sendApplication;
    }

    public Long getGetApplication() {
        return getApplication;
    }

    public void setGetApplication(Long getApplication) {
        this.getApplication = getApplication;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public FriendRequest(){}

    public FriendRequest(Long requestNumber, Long sendApplication, Long getApplication, String state) {
        this.requestNumber = requestNumber;
        this.sendApplication = sendApplication;
        this.getApplication = getApplication;
        this.state = state;
    }
}
