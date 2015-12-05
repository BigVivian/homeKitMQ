package com.eb;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) 2015 iQIYI.COM - All Rights Reserved
 * Created by kxc on 15/12/5.
 */
public class DataUtil {
    private static String dataFile = "/Users/kxc/study/homeKitMQ/homeKitMQ-producer/src/main/resources/data1000.csv";
    public static List<StbTaskOuterClass.StbTask> loadBinaryData(){
        File file = new File(dataFile);
        try{
            InputStreamReader ins = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(ins);
            String line;
            List<StbTaskOuterClass.StbTask> data = new ArrayList<>(1000);
            while ((line = reader.readLine()) != null){
                String [] params = line.split(",");
                StbTaskOuterClass.StbTask task = StbTaskOuterClass.StbTask.newBuilder().setId(Long.parseLong(params[0]))
                        .setTaskName(params[1])
                        .setCreateUserId(Long.parseLong(params[2]))
                        .setTaskCompleteReason(params[3])
                        .setFirmwareIds(params[4])
                        .setBatchOperateId(Long.parseLong(params[5]))
                        .setStatus(Integer.parseInt(params[6]))
                        .setTaskOperateType(Integer.parseInt(params[7]))
                        .setMinute(params[8])
                        .setHour(params[9])
                        .setDates(params[10])
                        .setMonth(params[11])
                        .setWeek(params[12])
                        .setTypetimes(Integer.parseInt(params[13]))
                        .setTasktype(Integer.parseInt(params[14]))
                        .setTaskparameter(params[15])
                        .setCreateTime(Long.parseLong(params[16]))
                        .setUserGroupMsg(params[17])
                        .setGroupId(Integer.parseInt(params[18])).build();
                data.add(task);
            }
            return  data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> loadTextData() {
        File file = new File(dataFile);
        List<String> lines = new ArrayList<>();
        try{
            InputStreamReader ins = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(ins);
            String line;
            Gson gson = new Gson();
            while ((line = reader.readLine()) != null){
                Map<String,Object> model = new HashMap<>();
                String [] params = line.split(",");
                model.put("id",params[0]);
                model.put("taskName",params[1]);
                model.put("createUserId",params[2]);
                model.put("taskCompleteReason",params[3]);
                model.put("firmwareIds",params[4]);
                model.put("batchOperateId",params[5]);
                model.put("status",params[6]);
                model.put("taskOperateType",params[7]);
                model.put("minute",params[8]);
                model.put("hour",params[9]);
                model.put("dates",params[10]);
                model.put("month",params[11]);
                model.put("week",params[12]);
                model.put("typetimes",params[13]);
                model.put("tasktype",params[14]);
                model.put("taskparameter",params[15]);
                model.put("createTime",params[16]);
                model.put("userGroupMsg",params[17]);
                model.put("groupId",params[18]);
                lines.add(gson.toJson(model));
            }
            return lines;
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return null;
    }
}
