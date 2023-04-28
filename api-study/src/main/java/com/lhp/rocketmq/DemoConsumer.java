package com.lhp.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/4/11 14:00
 */
public class DemoConsumer {


    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("testGr666oup");
        consumer.subscribe("lhp-demo", "*");
        consumer.setNamesrvAddr(Demo.namesrvAddr);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.println("Receive Message:" + msgs.toString());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 签收
            }
        });

        consumer.start();
    }
}
