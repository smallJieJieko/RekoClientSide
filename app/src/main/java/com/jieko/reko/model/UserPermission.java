package com.jieko.reko.model;



public class UserPermission {

    //用户编号
    private Long uPnumber;
    //修改个人信息
    private Boolean changeDetail=false;
    //创建活动
    private Boolean createActivity=false;
    //查看活动
    private Boolean lookActivity=false;
    //修改活动、包括删除自己的活动
    private Boolean changeActivity=false;
    //删除活动
    private Boolean deleteAchitity=false;
    //添加好友
    private Boolean addFriends=false;
    //查看好友
    private Boolean lookFriends=false;
    //删除好友
    private Boolean deleteFriends=false;
    //设计管理员
    private Boolean setAdministrator=false;
    //删除用户
    private Boolean deleteUser=false;

    public Long getuPnumber() {
        return uPnumber;
    }

    public void setuPnumber(Long uPnumber) {
        this.uPnumber = uPnumber;
    }

    public Boolean getChangeDetail() {
        return changeDetail;
    }

    public void setChangeDetail(Boolean changeDetail) {
        this.changeDetail = changeDetail;
    }

    public Boolean getCreateActivity() {
        return createActivity;
    }

    public void setCreateActivity(Boolean createActivity) {
        this.createActivity = createActivity;
    }

    public Boolean getLookActivity() {
        return lookActivity;
    }

    public void setLookActivity(Boolean lookActivity) {
        this.lookActivity = lookActivity;
    }

    public Boolean getChangeActivity() {
        return changeActivity;
    }

    public void setChangeActivity(Boolean changeActivity) {
        this.changeActivity = changeActivity;
    }

    public Boolean getDeleteAchitity() {
        return deleteAchitity;
    }

    public void setDeleteAchitity(Boolean deleteAchitity) {
        this.deleteAchitity = deleteAchitity;
    }

    public Boolean getAddFriends() {
        return addFriends;
    }

    public void setAddFriends(Boolean addFriends) {
        this.addFriends = addFriends;
    }

    public Boolean getLookFriends() {
        return lookFriends;
    }

    public void setLookFriends(Boolean lookFriends) {
        this.lookFriends = lookFriends;
    }

    public Boolean getDeleteFriends() {
        return deleteFriends;
    }

    public void setDeleteFriends(Boolean deleteFriends) {
        this.deleteFriends = deleteFriends;
    }

    public Boolean getSetAdministrator() {
        return setAdministrator;
    }

    public void setSetAdministrator(Boolean setAdministrator) {
        this.setAdministrator = setAdministrator;
    }

    public Boolean getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(Boolean deleteUser) {
        this.deleteUser = deleteUser;
    }

    public UserPermission(){

    }

    public UserPermission(Long uPnumber, Boolean changeDetail, Boolean createActivity, Boolean lookActivity,
                          Boolean changeActivity, Boolean deleteAchitity, Boolean addFriends, Boolean lookFriends,
                          Boolean deleteFriends, Boolean setAdministrator, Boolean deleteUser) {
        this.uPnumber = uPnumber;
        this.changeDetail = changeDetail;
        this.createActivity = createActivity;
        this.lookActivity = lookActivity;
        this.changeActivity = changeActivity;
        this.deleteAchitity = deleteAchitity;
        this.addFriends = addFriends;
        this.lookFriends = lookFriends;
        this.deleteFriends = deleteFriends;
        this.setAdministrator = setAdministrator;
        this.deleteUser = deleteUser;
    }
}
