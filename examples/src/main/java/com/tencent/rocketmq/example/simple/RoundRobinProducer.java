package com.tencent.rocketmq.example.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class RoundRobinProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("test1|InstanceTest5", "pidTest");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 1; i < 100; i++) {
            Message message = new Message("topicTest", "tagTest", ("Hello world — " + i).getBytes());
            try {
                SendResult result = producer.send(message);
                System.out.printf("Topic:%s send success, misId is:%s, queueId is: %s%n", message.getTopic(),
                        result.getMsgId(), result.getMessageQueue().getQueueId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
