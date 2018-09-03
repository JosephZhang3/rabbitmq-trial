package com.zhangjh.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.util.Map;

/**
 * 概述：
 * <p>
 * <p>详述：
 *
 * @author zhangjianghao on 2018-08-30.
 */
public class MessageUtil {

    public static Message getMessageWithHeader(Map<String, Object> header, Object msg) {
        MessageProperties messageProperties = new MessageProperties();
        for (Map.Entry<String, Object> entry : header.entrySet()) {
            messageProperties.setHeader(entry.getKey(), entry.getValue());
        }
        MessageConverter messageConverter = new SimpleMessageConverter();
        return messageConverter.toMessage(msg, messageProperties);
    }
}
