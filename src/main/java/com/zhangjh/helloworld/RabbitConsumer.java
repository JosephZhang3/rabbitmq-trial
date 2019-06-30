package com.zhangjh.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 消息消费者
 * 手动档，不使用注解，一行行手撸
 */
public class RabbitConsumer {
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "192.168.12.37";
    private static final int PORT = 5672;//rabbit server default port

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses = new Address[]{new Address(IP_ADDRESS, PORT)};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("cuteRabbit");
        factory.setPassword("123456");
        //注意这里，消费者连接rabbit服务器时，不是在工厂类的属性中写死地址，而是新建连接时才当动态参数给定
        Connection conn = factory.newConnection(addresses);
        Channel channel = conn.createChannel();
        channel.basicQos(64);//设定消费端最多接收未被ack的消息的个数

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("received message:" + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME, consumer);

        TimeUnit.SECONDS.sleep(5);
        channel.close();
        conn.close();
    }
}
