package com.zhangjh.fanout_exchange;

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
 * @author zhangjianghao on 2018-08-30.
 */
@Component
public class ApiReportReceiver {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    @RabbitListener(queues = "api.report.payment")
    public void payment(String msg) {
        logger.info("从队列api.report.payment中获取消息：{}", msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.report.refund")
    public void refund(String msg) {
        logger.info("从队列api.report.refund中获取消息：{}", msg);
    }
}
