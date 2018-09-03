package com.zhangjh.obj_msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 概述：
 * <p>
 * <p>详述：
 *
 * @author zhangjianghao on 2018-08-31.
 */
@Component
public class RefundNotifySender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void orderObjectMessage(Order order) {
        rabbitTemplate.convertAndSend("notify.refund", order);
        logger.info("往队列notify.refund投递消息：{}", order.toString());
    }
}
