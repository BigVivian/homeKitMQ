package com.eb.mt;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.eb.StbTaskOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by kxc on 15/9/14.
 */
public class Consumer {
    static Logger log = LoggerFactory.getLogger(Consumer.class);
    public static void main(String [] args){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "homeKitConsumer");
        consumer.setNamesrvAddr("192.168.1.194:9876");
        consumer.setInstanceName("musicConsumber");
        final boolean binary;
        if(args.length > 0 && "-b".equals(args[0])){
            binary = true;
        }else{
            binary = false;
        }
        try{
            consumer.subscribe("user_log","*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    log.info("receive message from top {}", "user_log");
                    Message message = msgs.get(0);
                    try{
                        if(binary){
                            StbTaskOuterClass.StbTask task = StbTaskOuterClass.StbTask.parseFrom(message.getBody());
                            log.info("receive StbTask from broker: " + message.getBody().length);
                        }else{
                            log.info("receive StbTask from broker: "+message.getBody().length);
                        }
                    }catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            log.info("subscribe topic ");
        }catch (MQClientException ce){
            ce.printStackTrace();
        }
    }
}
