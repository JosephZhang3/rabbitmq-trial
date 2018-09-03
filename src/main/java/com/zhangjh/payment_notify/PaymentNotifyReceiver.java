package com.zhangjh.payment_notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "notify.payment")
public class PaymentNotifyReceiver {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void receive(String msg) {
        logger.info("从队列 notify.payment 接收到消息：" + msg);
    }
}
