package com.eb.mt;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * Created by kxc on 15/9/14.
 */
public class Producer {

    public static void main(String [] args){
        DefaultMQProducer producer = new DefaultMQProducer("HomeKitProducer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("mmoProducer");
        try {
            producer.start();
            for (int i = 0; i < 20; i++){
                Message message = new Message("MusicTopic","mumu","music0000"+i,("Hilo-beyond").getBytes());
                try{
                    SendResult result = producer.send(message);
                }catch (MQBrokerException|RemotingException|InterruptedException e){
                    e.printStackTrace();
                }
            }
        }catch (MQClientException e){
            e.printStackTrace();
        }

    }
}
