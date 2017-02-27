package com.walkover.pos.PurchaseOrder;

/**
 * Created by saurabh on 14/2/17.
 */

public class PurchaseOrderBean {

    String productname;
    String orderid;

    String current_time;
    String expected_time;
    String remianing_min;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public String getExpected_time() {
        return expected_time;
    }

    public void setExpected_time(String expected_time) {
        this.expected_time = expected_time;
    }

    public String getRemianing_min() {
        return remianing_min;
    }

    public void setRemianing_min(String remianing_min) {
        this.remianing_min = remianing_min;
    }
}
