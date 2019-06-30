package com.zhangjh.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 * 手动档，不使用注解，一行行手撸
 */
public class RabbitProducer {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "192.168.12.37";
    private static final int PORT = 5672;//rabbit server default port

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("cuteRabbit");
        factory.setPassword("123456");

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        //创建一个type="direct"、持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
        //创建一个持久化的，非排他的，非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //把交换器与队列通过路由键进行绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        //发送一条持久化的纯文本消息：hello world!   注意是以字节传输的
        String msg = "hello world!";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());

        //别忘了释放资源
        channel.close();
        conn.close();
    }
}
