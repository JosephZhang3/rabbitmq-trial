package com.zhangjh.api_core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiCoreSender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void user(String msg) {
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user", msg);
        logger.info("根据主题routing键 api.core.user 投递消息：" + msg);
    }

    public void userQuery(String msg) {
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user.query", msg);
        logger.info("根据主题routing键 api.core.user.query 投递消息：" + msg);
    }

}
