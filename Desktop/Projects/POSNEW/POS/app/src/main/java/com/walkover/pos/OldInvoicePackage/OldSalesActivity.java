package com.walkover.pos.OldInvoicePackage;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.walkover.pos.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by saurabh on 10/2/17.
 */

public class OldSalesActivity extends Activity implements View.OnClickListener ,DatePickerDialog.OnDateSetListener {

    private ListView old_invoice_list;
    private ImageView iv_back;
    private LinearLayout all_Invoice;
    private OldSaleAdapter adapter;
    Calendar newCalendar;
    private EditText et_StartDate, et_EndDate;
    private ArrayList<OldSalesBean> old_sales_list = new ArrayList<>();
    private int years,months,dayOfMonths;
    private boolean flagStartDate= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_invoice_layout);
        setUpPane();


    }

    private void setUpPane() {
        old_invoice_list = (ListView) findViewById(R.id.old_invoice_list);
        all_Invoice = (LinearLayout) findViewById(R.id.all_Invoice);
        et_StartDate = (EditText) findViewById(R.id.et_StartDate);
        et_EndDate = (EditText) findViewById(R.id.et_EndDate);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        clickable();
        for (int i = 0; i < 10; i++) {

            OldSalesBean oldsalesBean = new OldSalesBean();
            oldsalesBean.setBillno("123");
            oldsalesBean.setOrderno("321");
            oldsalesBean.setPriceno("300");
            oldsalesBean.setBilldateandtime("21 Feb 2017 at 11:30");

            old_sales_list.add(oldsalesBean);
        }

        adapter = new OldSaleAdapter(OldSalesActivity.this, old_sales_list);
        old_invoice_list.setAdapter(adapter);


    }

    public void clickable() {
        et_StartDate.setOnClickListener(this);
        et_EndDate.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_StartDate:
                et_EndDate.setEnabled(false);
                startDatePicker();
             
                break;

            case R.id.et_EndDate:

                endDatePicker();
                break;

            case R.id.iv_back:
                finish();
                break;
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        et_EndDate.setEnabled(true);
        years = year;
        months = month;
        dayOfMonths = dayOfMonth;

        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        newCalendar.set(year, month, dayOfMonth);
        if (flagStartDate) {
            String date = (dateFormatter.format(newCalendar.getTime()));
            et_EndDate.setText(date);
           // endDat = date;
        } else {
            String date = (dateFormatter.format(newCalendar.getTime()));
          //  startDat = date;
            flagStartDate = true;
            et_StartDate.setText(date);
            et_EndDate.setEnabled(true);
            et_EndDate.setText("");
           // endDat = "";
        }
    }



    private void startDatePicker() {
        newCalendar = Calendar.getInstance();
        newCalendar.add(Calendar.DAY_OF_MONTH, 0);
        int mYear = newCalendar.get(Calendar.YEAR);
        int mMonth = newCalendar.get(Calendar.MONTH);
        int mDay = newCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDate = new DatePickerDialog(OldSalesActivity.this, this, mYear, mMonth, mDay);
        mDate.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        mDate.show();

    }

    private void endDatePicker() {
        try {
            newCalendar.add(Calendar.DAY_OF_MONTH, 1);
            DatePickerDialog mDate = new DatePickerDialog(OldSalesActivity.this, this, years, months, dayOfMonths);
            mDate.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
            mDate.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
