package com.zhangjh.obj_msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 概述：
 * <p>
 * <p>详述：
 *
 * @author zhangjianghao on 2018-08-31.
 */
@Component
public class RefundNotifyReceiver {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    @RabbitListener(queues = "notify.refund")
    public void receive(Order order) {
        logger.info("从队列notify.refund接收消息：{}", order.toString());
    }
}
