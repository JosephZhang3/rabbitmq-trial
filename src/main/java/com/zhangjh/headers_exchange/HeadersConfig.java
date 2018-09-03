package com.zhangjh.headers_exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据投递消息中设置的header参数来匹配
 */
@Configuration
public class HeadersConfig {

    /**
     * credit.bank队列
     **/

    @Bean
    public Queue creditBankQueue() {
        return new Queue("credit.bank");
    }

    @Bean
    public HeadersExchange creditBankExchange() {
        return new HeadersExchange("creditBankExchange");
    }

    @Bean
    public Binding bindingCreditFooExchange(Queue creditBankQueue, HeadersExchange creditBankExchange) {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("type", "cash");
        headerMap.put("aging", "fast");
        //header参数必须全部匹配
        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headerMap).match();
    }

    /**
     * credit.finance队列
     **/

    @Bean
    public Queue creditFinanceQueue() {
        return new Queue("credit.finance");
    }

    @Bean
    public HeadersExchange creditFinanceExchange() {
        return new HeadersExchange("creditFinanceExchange");
    }

    @Bean
    public Binding bindingCreditBarExchange(Queue creditFinanceQueue, HeadersExchange creditFinanceExchange) {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("type", "cash");
        headerMap.put("aging", "fast");
        //header参数部分匹配即可
        return BindingBuilder.bind(creditFinanceQueue).to(creditFinanceExchange).whereAny(headerMap).match();
    }
}
