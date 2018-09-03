package com.zhangjh.fanout_exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 概述：发送消息至reportExchange交换机，与routing key无关，随便指定一个就行
 * <p>
 * <p>详述：
 *
 * @author zhangjianghao on 2018-08-30.
 */
@Component
public class ApiReportSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void generateReport(String msg) {
        rabbitTemplate.convertAndSend("reportExchange", "api.generate.reports", msg);
        logger.info("路由键：api.generate.reports，投递消息：{}", msg);
    }
}
