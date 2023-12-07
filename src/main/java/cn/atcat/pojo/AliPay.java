package cn.atcat.pojo;

import lombok.Data;

@Data
public class AliPay {
    private String traceNo; // 订单号
    private double totalAmount; // 订单金额
    private String subject;// 商品名称
    private String alipayTraceNo; // 支付宝交易号
}

