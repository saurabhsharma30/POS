package com.walkover.pos.PurchaseOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.walkover.pos.R;

import java.util.ArrayList;

import static com.walkover.pos.AdminSlidingPackage.AdminSlidingActivity.txt_ItemName;

/**
 * Created by saurabh on 13/2/17.
 */

public class PurchaseOrder extends Fragment {

    private ListView orderId_List, InProgress_List, Done_List, Deilver_List;
    ArrayList<PurchaseOrderBean> PurchaseOrderList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.purchase_order_layout, container, false);
        setUpPane(view);
//        txt_ItemName.setText("Purchase Order");
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

            PurchaseOrderList.add(bean);
        }

        PurchaseOrderAdapter adapter = new PurchaseOrderAdapter(getActivity(), PurchaseOrderList);
        orderId_List.setAdapter(adapter);
        InProgress_List.setAdapter(adapter);
        Done_List.setAdapter(adapter);
        Deilver_List.setAdapter(adapter);
    }
}
