package com.zhangjh.controller;


import com.zhangjh.api_core.ApiCoreSender;
import com.zhangjh.api_payment.ApiPaymentSender;
import com.zhangjh.fanout_exchange.ApiReportSender;
import com.zhangjh.headers_exchange.ApiCreditSender;
import com.zhangjh.obj_msg.Order;
import com.zhangjh.obj_msg.RefundNotifySender;
import com.zhangjh.payment_notify.PaymentNotifySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    private final PaymentNotifySender paymentNotifySender;
    private final ApiCoreSender apiCoreSender;
    private final ApiPaymentSender apiPaymentSender;
    private final ApiCreditSender apiCreditSender;
    private final ApiReportSender apiReportSender;
    private final RefundNotifySender refundNotifySender;

    @Autowired
    public TestController(PaymentNotifySender paymentNotifySender, ApiCoreSender apiCoreSender, ApiPaymentSender apiPaymentSender, ApiCreditSender apiCreditSender, ApiReportSender apiReportSender, RefundNotifySender refundNotifySender) {
        this.paymentNotifySender = paymentNotifySender;
        this.apiCoreSender = apiCoreSender;
        this.apiPaymentSender = apiPaymentSender;
        this.apiCreditSender = apiCreditSender;
        this.apiReportSender = apiReportSender;
        this.refundNotifySender = refundNotifySender;
    }

    /**
     * 提交订单
     */
    @RequestMapping("/placeOrder")
    public void placeOrder() {
        paymentNotifySender.send("支付订单号：" + System.nanoTime());
    }

    @RequestMapping("/user")
    public void order() {
        apiCoreSender.user("用户管理");
        apiCoreSender.userQuery("用户查询");
    }

    @RequestMapping("/query")
    public void query() {
        apiPaymentSender.order("订单管理");
        apiPaymentSender.orderQuery("订单查询");
        apiPaymentSender.orderDetailQuery("订单详情查询");
    }

    /**
     * 检查授信是否匹配
     */
    @RequestMapping("/checkCredit")
    public void checkCredit() {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("type", "cash");
        apiCreditSender.creditBank(headerMap, "银行授信，部分匹配");

        headerMap.clear();

        headerMap.put("type", "cash");
        headerMap.put("aging", "fast");
        apiCreditSender.creditBank(headerMap, "银行授信，完全匹配");

        headerMap.clear();

        headerMap.put("type", "cash");
        apiCreditSender.creditFinance(headerMap, "金融公司授信，部分匹配");

        headerMap.clear();

        headerMap.put("type", "cash");
        headerMap.put("aging", "fast");
        apiCreditSender.creditFinance(headerMap, "金融公司授信，完全匹配");
    }

    /**
     * 生成报表
     */
    @RequestMapping("/generateReport")
    public void generateReport() {
        apiReportSender.generateReport("开始生成报表 QAQ");
    }

    /**
     * 一对多的提醒
     */
    @RequestMapping("/one2many")
    public void one2many() {
        for (int i = 0; i <= 20; i++)
            paymentNotifySender.send("提醒-支付订单号：" + i);
    }

    /**
     * 以对象的方式传递消息
     */
    @RequestMapping("/objMsg")
    public void objMsg() {
        Order order = new Order();
        order.setOrderId(String.valueOf(System.currentTimeMillis()));
        order.setAmount(new BigDecimal(782347));
        order.setCreatTime(new Date());

        refundNotifySender.orderObjectMessage(order);
    }

}
