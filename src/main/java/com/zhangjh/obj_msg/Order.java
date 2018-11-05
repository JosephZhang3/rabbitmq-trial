package com.zhangjh.obj_msg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 概述：传输/接收的对象，一定要实现serializable接口，而且包名必须完全相同
 * <p>
 * <p>详述：
 *
 * @author zhangjianghao on 2018-08-31.
 */
public class Order implements Serializable {

    //类的唯一序列化标识，避免与其它同名类混淆
    private static final long serialVersionUID = 1l;

    private String orderId;
    private BigDecimal amount;
    private Date creatTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "[订单ID:" + this.getOrderId() + ",金额:" + this.getAmount() + ",创建时间:" + this.getCreatTime() + "]";
    }
}
