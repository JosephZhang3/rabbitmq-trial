package com.zhangjh;

import com.zhangjh.payment_notify.PaymentNotifySender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangjianghao on 2018-08-27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentNotifySenderTests {

    @Autowired
    private PaymentNotifySender paymentNotifySender;

    @Test
    public void testSender() {
        paymentNotifySender.send("支付订单号：" + System.nanoTime());
    }
}
