package com.lhp.producer;

import cn.hutool.core.codec.Base64;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DmsProducerTest {
    public static final String TOPIC_NAME = "SENSITIVE_TASK_RESULT_TOPIC";

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
    public void testnull() throws UnsupportedEncodingException {

        String aaa = "{\"appName\": \"1637733752374734849\",\"colRegexMap\": [\"db[.]stg[:]tb[.]stg_wzw_test[:]colName[.]编号[:]colCode[.]collect_id[:]regex[.]b{3,6}[:]task_id[.]1637733752374734849[:]table_id[.]1584495349898649601\"],\"colSqlMap\": [\"db[.]stg[:]tb[.]stg_wzw_test[:]colName[.]消息[:]colCode[.]message[:]regex[.][\\u4e00-\\u9fa5][:]task_id[.]1637733752374734849[:]table_id[.]1584495349898649601\"],\"id\": \"1637733752374734849\",\"kafkaServers\": \"192.168.110.43:9092,192.168.110.41:9092,192.168.110.42:9092\",\"sourceDbTableMap\": \"stg:stg_wzw_test\",\"taskNo\": \"wzw_Test\",\"topic\": \"SENSITIVE_TASK_RESULT_TOPIC\"}";
        String ss = java.util.Base64.getEncoder().encodeToString(aaa.getBytes());

        System.out.println("ss = " + ss);

        String encode = Base64.encode(aaa);


        System.out.println(Objects.equals(encode, ss));


        String decode = Base64.decodeStr(encode);
        byte[] decode1 = java.util.Base64.getDecoder().decode(ss);
        String s = new String(decode1);

        System.out.println(Objects.equals(decode, s));


        String url = "192.168.100.41/ddd";
        String[] split = url.split("/");
        System.out.println(
                split[1]
        );

//        System.out.println(null==0);
    }

    @Test
    public void testReg() {
        Pattern pattern = Pattern.compile("\\]-\\[(\\S*)\\]-");
        Matcher matcher = pattern.matcher("[sensitive-task]-[1636663822732685313]-1-20230320140621726");
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
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
