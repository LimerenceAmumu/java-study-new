package com.lhp.rocketmq;

import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.MQClientAPIImpl;
import org.apache.rocketmq.client.impl.factory.MQClientInstance;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.protocol.body.TopicList;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : lihp
 * @Description : rocketmq
 * @date : 2023/4/11 11:16
 */
public class Demo {

    ///http://192.168.200.166:28080/#/topic

    public static String namesrvAddr = "192.168.200.166:9876";
    //    public static String brokerAddr = "192.168.217.250:10911";
    public static String defaultTopicName = "lhp-demo-default";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("testGr666oup");
//        consumer.subscribe("lhp-demo", "*");

        consumer.setNamesrvAddr(namesrvAddr);
        ClientConfig clientConfig = consumer.cloneClientConfig();
        String buildMQClientId = consumer.buildMQClientId();
        MQClientInstance mqClientInstance = new MQClientInstance(clientConfig, 0, buildMQClientId);
        // 这个必须要开启
        mqClientInstance.start();
        MQClientAPIImpl mqClientAPIImpl = mqClientInstance.getMQClientAPIImpl();
//        MQAdminImpl mqAdminImpl = mqClientInstance.getMQAdminImpl();
        // 获得当前的topic集合列表
        printTopicList(mqClientAPIImpl);

        // getmQClientFactory.shutdown();


        mqClientInstance.shutdown();
        consumer.shutdown();
    }

    public static Set<String> getTopics(MQClientAPIImpl mqClientAPIImpl) {
        Set<String> topics = new HashSet<>();
        long timeoutMillis = 2000L;
        try {
            TopicList topicList = mqClientAPIImpl.getTopicListFromNameServer(timeoutMillis);
            // String brokerAddr = topicList.getBrokerAddr();
            Set<String> topicSet = topicList.getTopicList();
            topics.addAll(topicSet);
            // System.out.println("成功获取到地址："+brokerAddr+"的所有的topic");
            // System.out.println(topicSet);
            TopicList systemTopicList = mqClientAPIImpl.getSystemTopicList(timeoutMillis);
            Set<String> sysTopicSet = systemTopicList.getTopicList();
            // System.out.println("输出当前系统的topic");
            // System.out.println(sysTopicSet);
            topics.addAll(sysTopicSet);
            return topics;
        } catch (RemotingException | MQClientException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 输出当前客户端获取到的topic
    public static void printTopicList(MQClientAPIImpl mqClientAPIImpl) {
        Set<String> topics = getTopics(mqClientAPIImpl);
        System.out.println("打印所有的主题");
        System.out.println(topics);
    }

    // 生产消息
    @Test
    public void test() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("lhp-test");
        // 只用namesrv的地址就行，它会从namesrv上拿到broker的地址和topic信息
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();

        int num = 0;
        while (num < 20) {
            num++;
            /**
             * rocketmq封装了Message
             * String topic,
             * String tags, 标签（分类）---> 筛选
             * byte[] body
             */
            Message message = new Message("lhp-test-001", "demo", "key001", ("hello rocketmq  秋水共长天一色:" + num).getBytes());
            Message message2 = new Message("lhp-test-002", "demo", "key002", ("hello rocketmq  秋水共长天一色:" + num).getBytes());
            Message message3 = new Message("lhp-test-003", "demo", "key003", ("hello rocketmq  秋水共长天一色:" + num).getBytes());
            // 发送消息，拿到返回SendResult
            SendResult result = producer.send(message);
            SendResult result2 = producer.send(message2);
            SendResult result3 = producer.send(message3);
            System.out.println(result);

        }
        producer.shutdown();
    }


}
