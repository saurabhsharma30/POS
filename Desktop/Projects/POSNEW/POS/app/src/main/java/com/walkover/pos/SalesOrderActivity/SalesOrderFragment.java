package com.walkover.pos.SalesOrderActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.walkover.pos.LocalDataBase.SqlDatabaseHandler;
import com.walkover.pos.PurchaseOrder.PurchaseOrderBean;
import com.walkover.pos.R;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by saurabh on 14/2/17.
 */

public class SalesOrderFragment extends Fragment {

    private ListView orderId_List, InProgress_List, Done_List, Deilver_List;
    ArrayList<PurchaseOrderBean> PurchaseOrderList = new ArrayList<>();
    SqlDatabaseHandler dbHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sales_order_layout, container, false);
        setUpPane(view);
        return view;
    }

    private void setUpPane(View view) {
        orderId_List = (ListView) view.findViewById(R.id.orderId_List);
        InProgress_List = (ListView) view.findViewById(R.id.InProgress_List);
        Done_List = (ListView) view.findViewById(R.id.Done_List);
        Deilver_List = (ListView) view.findViewById(R.id.Deilver_List);

        for (int i = 0; i < 10; i++) {

            PurchaseOrderBean bean = new PurchaseOrderBean();
            bean.setProductname("Hot Coffee");
            bean.setOrderid(String.valueOf(i));
            int hours = new Time(System.currentTimeMillis()).getHours();
            int min = new Time(System.currentTimeMillis()).getMinutes();
            String currenttime = hours + ":" + min;
            bean.setCurrent_time(currenttime);
            String timeadded = addtime(currenttime);
            Log.e("Current TIME OF DEVICE", currenttime);
            Log.e("ADDED TIME OF DEVICE", timeadded);
            bean.setExpected_time(timeadded);
            try {
                DateFormat df = new java.text.SimpleDateFormat("hh:mm");
                Date date1 = df.parse(currenttime);
               Date date2 = df.parse(timeadded);
                long diff = date2.getTime() - date1.getTime();
                Log.e("diff", "diff:----> "+diff );
            } catch (Exception e) {
                e.printStackTrace();
            }
            dbHandler = new SqlDatabaseHandler(getActivity());
            dbHandler.InsertIntoCounterTable(bean);

        }

        PurchaseOrderList = dbHandler.getAllCounter();

        SalesOrderAdapter adapter = new SalesOrderAdapter(getActivity(), PurchaseOrderList);
        orderId_List.setAdapter(adapter);
        InProgress_List.setAdapter(adapter);
        Done_List.setAdapter(adapter);
        Deilver_List.setAdapter(adapter);
    }


    public String addtime(String time) {
        int minsToAdd = 20;
        Date date = new Date();
        date.setTime((((Integer.parseInt(time.split(":")[0])) * 60 + (Integer.parseInt(time.split(":")[1]))) + date.getTimezoneOffset()) * 60000);
        System.out.println(date.getHours() + ":" + date.getMinutes());
        date.setTime(date.getTime() + minsToAdd * 60000);
        System.out.println(date.getHours() + ":" + date.getMinutes());
        String addtime = date.getHours() + ":" + date.getMinutes();
        return addtime;
    }


}
