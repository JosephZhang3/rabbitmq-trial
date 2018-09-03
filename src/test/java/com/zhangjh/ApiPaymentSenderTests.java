package com.zhangjh;

import com.zhangjh.api_payment.ApiPaymentSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiPaymentSenderTests {

    @Autowired
    private ApiPaymentSender apiPaymentSender;

    @Test
    public void testOrder(){
        apiPaymentSender.order("订单管理");
    }

    @Test
    public void testOrderQuery(){
        apiPaymentSender.orderQuery("订单查询");
    }

    @Test
    public void testOrderDetailQuery(){
        apiPaymentSender.orderDetailQuery("订单详情查询");
    }
}
