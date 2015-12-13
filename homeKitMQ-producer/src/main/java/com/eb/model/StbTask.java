package com.eb.model;

/**
 * Copyright (C) 2015 iQIYI.COM - All Rights Reserved
 * Created by kxc on 15/12/12.
 */
public class StbTask {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getTaskCompleteReason() {
        return taskCompleteReason;
    }

    public void setTaskCompleteReason(String taskCompleteReason) {
        this.taskCompleteReason = taskCompleteReason;
    }

    public String getFirmwareIds() {
        return firmwareIds;
    }

    public void setFirmwareIds(String firmwareIds) {
        this.firmwareIds = firmwareIds;
    }

    public Long getBatchOperateId() {
        return batchOperateId;
    }

    public void setBatchOperateId(Long batchOperateId) {
        this.batchOperateId = batchOperateId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTaskOperateType() {
        return taskOperateType;
    }

    public void setTaskOperateType(Integer taskOperateType) {
        this.taskOperateType = taskOperateType;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getTypetimes() {
        return typetimes;
    }

    public void setTypetimes(Integer typetimes) {
        this.typetimes = typetimes;
    }

    public Integer getTasktype() {
        return tasktype;
    }

    public void setTasktype(Integer tasktype) {
        this.tasktype = tasktype;
    }

    public String getTaskparameter() {
        return taskparameter;
    }

    public void setTaskparameter(String taskparameter) {
        this.taskparameter = taskparameter;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getUserGroupMsg() {
        return userGroupMsg;
    }

    public void setUserGroupMsg(String userGroupMsg) {
        this.userGroupMsg = userGroupMsg;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public byte [] toBytes(){
        return new byte[20];
    }

    public static StbTask fromBytes(byte [] data){
        return new StbTask();
    }

    private String taskName;
    private Long createUserId;
    private String taskCompleteReason;
    private String firmwareIds;
    private Long batchOperateId;
    private Integer status;
    private Integer taskOperateType;
    private String minute;
    private String hour;
    private String dates;
    private String month;
    private String week;
    private Integer typetimes;
    private Integer tasktype;
    private String taskparameter;
    private Long createTime;
    private String userGroupMsg;
    private Integer groupId;
}
