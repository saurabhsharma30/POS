package com.walkover.pos.SalesAndInvoicePackage;

/**
 * Created by Ashish on 14/02/2017.
 */

public class AddBean {

    String ProductCount;
    String ProductDesc;
    String ProductPrice;

    public AddBean(String productCount, String productDesc, String productPrice) {
        ProductCount = productCount;
        ProductDesc = productDesc;
        ProductPrice = productPrice;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public String getProductCount() {
        return ProductCount;
    }

    public void setProductCount(String productName) {
        ProductCount = productName;
    }
}
