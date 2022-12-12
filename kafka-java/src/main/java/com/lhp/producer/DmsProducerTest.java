package com.lhp.producer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;

public class DmsProducerTest {
    public static final String TOPIC_NAME = "LHP_TEST_TOPIC-22";

    @Test
    public void testProducer() throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.110.41:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        producer.send(new ProducerRecord<String, String>(TOPIC_NAME, "lhp-key", "value-test1"));
        producer.send(new ProducerRecord<String, String>(TOPIC_NAME, "lhp-key", "value-test2"));
        producer.send(new ProducerRecord<String, String>(TOPIC_NAME, "lhp-key", "value-test3"));

    }

    @Test
    public void testnull() {

        String url = "192.168.100.41/ddd";
        String[] split = url.split("/");
        System.out.println(
                split[1]
        );

//        System.out.println(null==0);
    }


    @Test
    public void testConsumer() throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.110.41:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());// key反序列化方式
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());// value反系列化方式

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        System.out.println(consumer.listTopics().keySet());

    }

}
