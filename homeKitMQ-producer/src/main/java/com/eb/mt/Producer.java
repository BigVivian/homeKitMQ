package com.eb.mt;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.eb.DataUtil;
import com.eb.StbTaskOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxc on 15/9/14.
 */
public class Producer {
    static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String [] args){
        DefaultMQProducer producer = new DefaultMQProducer("HomeKitProducer");
        producer.setNamesrvAddr("192.168.1.194:9876");
        producer.setInstanceName("homeKitProducer");
        boolean binary = false;
        if(args.length > 0 && "-b".equals(args[0])){
            binary = true;
        }else{
            binary = false;
        }
        log.info("args {}",args);
        log.info("binary is {}",binary);
        List<Integer> sizeArray = new ArrayList<Integer>();
        try {
            producer.start();
            if(binary){
                List<StbTaskOuterClass.StbTask> data =  DataUtil.loadBinaryData();
                log.info("task list for send by binary "+data.size());
                if(data != null){
                    for (StbTaskOuterClass.StbTask task:data){
                        Message message = new Message("user_log",task.toByteArray());
                        try {
                            log.info("send binary StbTask to broker: " + task.getId() + "," + task.getTaskName());
                            SendResult result = producer.send(message);
                        } catch (MQBrokerException | RemotingException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        sizeArray.add(task.toByteArray().length);
                    }
                }else{
                    log.info("no found data for producer");
                }
            }else{
                List<String> data = DataUtil.loadTextData();
                log.info("task list for send by text "+data.size());
                if(data != null){
                    for (String task:data){
                        Message message = new Message("user_log",task.getBytes());
                        try{
                            log.info("send text StbTask to broker: {}",task);
                            SendResult result = producer.send(message);
                        }catch (MQBrokerException | RemotingException | InterruptedException e){
                            e.printStackTrace();
                        }
                        sizeArray.add(task.getBytes().length);
                    }
                }
            }
            int sum = 0;
            for (Integer size:sizeArray){
                sum += size;
            }
            log.info("total size {} average size {} ",sum,sum/sizeArray.size());
        }catch (MQClientException e){
            e.printStackTrace();
        }
    }
}
