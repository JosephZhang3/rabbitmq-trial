package com.zhangjh.headers_exchange;

import com.zhangjh.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApiCreditSender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void creditBank(Map<String, Object> headerMap, String msg) {
        rabbitTemplate.convertAndSend("creditBankExchange", "credit.bank", MessageUtil.getMessageWithHeader(headerMap, msg));
        logger.info("往 credit.bank 队列投递消息：" + msg);
    }

    public void creditFinance(Map<String, Object> headerMap, String msg) {
        rabbitTemplate.convertAndSend("creditFinanceExchange", "credit.finance", MessageUtil.getMessageWithHeader(headerMap, msg));
        logger.info("往 credit.finance 队列投递消息：" + msg);
    }
}
