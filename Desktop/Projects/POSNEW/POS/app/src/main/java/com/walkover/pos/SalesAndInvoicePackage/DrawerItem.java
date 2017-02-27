package com.walkover.pos.SalesAndInvoicePackage;

/**
 * Created by saurabh on 9/2/17.
 */

public class DrawerItem {

    String ItemName;
    int imgResID;
    String count;

    public DrawerItem(String itemName, int imgResID, String count) {
        ItemName = itemName;
        this.imgResID = imgResID;
        this.count = count;
    }

    public String getItemName() {
        return ItemName;
    }

    public int getImgResID() {
        return imgResID;
    }

    public String getCount() {
        return count;
    }
}
