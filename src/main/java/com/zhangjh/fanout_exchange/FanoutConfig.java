package com.zhangjh.fanout_exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 概述：转发消息到所有绑定了路由器的队列，和routing key没有关系！
 * <p>
 * <p>详述：
 *
 * @author zhangjianghao on 2018-08-30.
 */
@Configuration
public class FanoutConfig {

    /**
     * 报表支付信息队列
     */

    @Bean
    public Queue reportPaymentQueue() {
        return new Queue("api.report.payment");
    }

    @Bean
    public FanoutExchange reportExchange() {
        return new FanoutExchange("reportExchange");
    }

    @Bean
    public Binding bindingReportPaymentExchange(Queue reportPaymentQueue, FanoutExchange reportExchange) {
        return BindingBuilder.bind(reportPaymentQueue).to(reportExchange);
    }

    /**
     * 报表退款信息队列
     */

    @Bean
    public Queue reportRefundQueue() {
        return new Queue("api.report.refund");
    }

    @Bean
    public Binding bindingReportRefundExchange(Queue reportRefundQueue, FanoutExchange reportExchange) {
        return BindingBuilder.bind(reportRefundQueue).to(reportExchange);
    }
}
