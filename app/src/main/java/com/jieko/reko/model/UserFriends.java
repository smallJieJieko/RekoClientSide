package com.jieko.reko.model;

import java.io.Serializable;

public class UserFriends implements Serializable {
    //关系编号
    private Long relationshipNumber;
    //用户编号
    private Long uBnumber;
    //好友编号
    private Long uFnumber;

    public UserFriends(){}

    public UserFriends(Long relationshipNumber, Long uBnumber, Long uFnumber) {
        this.relationshipNumber = relationshipNumber;
        this.uBnumber = uBnumber;
        this.uFnumber = uFnumber;
    }

    public UserFriends(Long uBnumber, Long uFnumber) {
        this.uBnumber = uBnumber;
        this.uFnumber = uFnumber;
    }

    public Long getRelationshipNumber() {
        return relationshipNumber;
    }

    public void setRelationshipNumber(Long relationshipNumber) {
        this.relationshipNumber = relationshipNumber;
    }

    public Long getuBnumber() {
        return uBnumber;
    }

    public void setuBnumber(Long uBnumber) {
        this.uBnumber = uBnumber;
    }

    public Long getuFnumber() {
        return uFnumber;
    }

    public void setuFnumber(Long uFnumber) {
        this.uFnumber = uFnumber;
    }
}
