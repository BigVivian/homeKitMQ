package com.eb.mt;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.eb.StbTaskOuterClass;
import com.eb.model.StbTask;
import com.eb.util.CompressUtil;
import com.eb.util.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by kxc on 15/9/14.
 */
public class Consumer {
    static Logger log = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "homeKitConsumer");
        consumer.setNamesrvAddr("192.168.1.194:9876");
        consumer.setInstanceName("musicConsumber");
        final boolean binary;
        if (args.length > 0 && "-b".equals(args[0])) {
            binary = true;
        } else {
            binary = false;
        }
        final String topicName = "t_stb_task";
        final Boolean compressTag = false;
        final Boolean encryptionTag = false;
        try {
            consumer.subscribe("t_stb_task", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    log.info("receive message from top {}", topicName);
                    Message message = msgs.get(0);
                    byte[] data = message.getBody();
                    if (encryptionTag) {
                        //decrypt data
                        data = EncryptionUtil.decrypt(data);
                    }
                    if (compressTag) {
                        //compress data to send
                        data = CompressUtil.uncompress(data);
                    }
                    StbTask task = StbTask.fromBytes(data);

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            log.info("subscribe topic ");
        } catch (MQClientException ce) {
            ce.printStackTrace();
        }
    }
}
