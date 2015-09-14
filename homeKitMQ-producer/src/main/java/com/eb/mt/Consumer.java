package com.eb.mt;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by kxc on 15/9/14.
 */
public class Consumer {
    static Logger log = LoggerFactory.getLogger(Consumer.class);
    public static void main(String [] args){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "homeKitConsumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("musicConsumber");
        try{
            consumer.subscribe("MusicTopic","*");
        }catch (MQClientException ce){
            ce.printStackTrace();
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    log.info("recieve message from top {}","MusicTopic");
                    Message  message = msgs.get(0);
                    if("MusicTopic".equals(message.getTopic())){
                        log.info("read from my music top {}",message.getBody().toString());
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
        }
    }
}
