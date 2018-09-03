package com.zhangjh.headers_exchange;

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
public class ApiCreditReceiver {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    @RabbitListener(queues = "credit.bank")
    public void creditBank(String msg) {
        logger.info("从 credit.bank 队列接收消息：" + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "credit.finance")
    public void creditFinance(String msg) {
        logger.info("从 credit.finance 队列接收消息：" + msg);
    }
}
