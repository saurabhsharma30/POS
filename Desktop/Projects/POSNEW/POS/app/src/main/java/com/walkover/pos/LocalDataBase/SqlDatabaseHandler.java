package com.walkover.pos.LocalDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.walkover.pos.PurchaseOrder.PurchaseOrderBean;

import java.util.ArrayList;

/**
 * Created by saurabh on 20/2/17.
 */

public class SqlDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "InvoiceDatabase";
    private static final String TABLE_ORDER_COUNTER = "order_counter";

    private static final String KEY_ORDERID = "id";
    private static final String KEY_PRODUCTNAME = "product_name";
    private static final String KEY_CURRENTTIME = "current_time";
    private static final String KEY_EXPECTEDTIME = "expected_time";
    private static final String KEY_REMAININGMINUTES = "remaining_min";


    public SqlDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ORDER_COUNTER + "("
                + KEY_PRODUCTNAME + " TEXT,"
                + KEY_ORDERID + " TEXT,"
                + KEY_CURRENTTIME + " TEXT,"
                + KEY_EXPECTEDTIME + " TEXT,"
                + KEY_REMAININGMINUTES + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_COUNTER);
        onCreate(db);
    }


    public void InsertIntoCounterTable(PurchaseOrderBean counterBean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCTNAME, counterBean.getProductname());
        values.put(KEY_ORDERID, counterBean.getOrderid());
        values.put(KEY_CURRENTTIME, counterBean.getCurrent_time());
        values.put(KEY_EXPECTEDTIME, counterBean.getExpected_time());
        values.put(KEY_REMAININGMINUTES, counterBean.getRemianing_min());
        long i = db.insert(TABLE_ORDER_COUNTER, null, values);

        Log.e("INSERT", "InsertIntoCounterTable:---- " + i);
        db.close();
    }

    public int UpdateIntoCounterTable(PurchaseOrderBean purchaseOrderBean) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_REMAININGMINUTES, purchaseOrderBean.getRemianing_min());
        values.put(KEY_CURRENTTIME, purchaseOrderBean.getCurrent_time());
        return db.update(TABLE_ORDER_COUNTER, values, KEY_ORDERID + " = ?",
                new String[]{String.valueOf(purchaseOrderBean.getOrderid())});
    }


    public ArrayList<PurchaseOrderBean> getAllCounter() {
        ArrayList<PurchaseOrderBean> contactList = new ArrayList<PurchaseOrderBean>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ORDER_COUNTER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PurchaseOrderBean purchaseOrderBean = new PurchaseOrderBean();
                purchaseOrderBean.setProductname((cursor.getString(0)));
                purchaseOrderBean.setOrderid(cursor.getString(1));
                purchaseOrderBean.setRemianing_min(cursor.getString(4));

                // Adding contact to list
                contactList.add(purchaseOrderBean);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
