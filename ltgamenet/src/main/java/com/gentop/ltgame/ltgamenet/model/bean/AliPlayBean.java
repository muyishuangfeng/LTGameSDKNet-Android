package com.gentop.ltgame.ltgamenet.model.bean;

/**
 * Created by Silence on 2018/1/16.
 */

public class AliPlayBean {
    private int id;
    private String alipayKey;
    private String result;
    private String orderInfo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlipayKey() {
        return alipayKey;
    }

    public void setAlipayKey(String alipayKey) {
        this.alipayKey = alipayKey;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }
}
