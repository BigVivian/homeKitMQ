package com.eb.util;

import com.eb.StbTaskOuterClass;
import com.eb.model.StbTask;

/**
 * Copyright (C) 2015 iQIYI.COM - All Rights Reserved
 * Created by kxc on 15/12/12.
 */
public class CompressUtil {

    public static byte[] compress(StbTask object){
        if(object instanceof StbTask){
            StbTaskOuterClass.StbTask task = StbTaskOuterClass.StbTask.newBuilder().setId(object.getId())
                    .setTaskName(object.getTaskName())
                    .setCreateUserId(object.getCreateUserId())
                    .setTaskCompleteReason(object.getTaskCompleteReason())
                    .setFirmwareIds(object.getFirmwareIds())
                    .setBatchOperateId(object.getBatchOperateId())
                    .setStatus(object.getStatus())
                    .setTaskOperateType(object.getTaskOperateType())
                    .setMinute(object.getMinute())
                    .setHour(object.getHour())
                    .setDates(object.getDates())
                    .setMonth(object.getMonth())
                    .setWeek(object.getWeek())
                    .setTypetimes(object.getTypetimes())
                    .setTasktype(object.getTasktype())
                    .setTaskparameter(object.getTaskparameter())
                    .setCreateTime(object.getCreateTime())
                    .setUserGroupMsg(object.getUserGroupMsg())
                    .setGroupId(object.getGroupId()).build();
            return task.toByteArray();
        }else{
            return null;
        }
    }

    public static byte[]  uncompress(byte [] data){
        try {
            StbTaskOuterClass.StbTask task = StbTaskOuterClass.StbTask.parseFrom(data);
            return  task.toByteArray();
        }catch (Exception e){
            return null;
        }
    }
}
