package com.zhangjh.payment_notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 支付提醒消息发送器
 */
@Component
public class PaymentNotifySender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public PaymentNotifySender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String msg) {
        logger.info("往队列 notify.payment 中投递消息：" + msg);
        rabbitTemplate.convertAndSend("notify.payment", msg);
    }
}
