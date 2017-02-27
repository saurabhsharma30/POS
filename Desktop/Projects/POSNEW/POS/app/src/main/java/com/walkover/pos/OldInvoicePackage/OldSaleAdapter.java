package com.walkover.pos.OldInvoicePackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.walkover.pos.R;

import java.util.ArrayList;

/**
 * Created by Sachin on 15-12-16.
 */

public class OldSaleAdapter extends BaseAdapter {

    Context context;
    ArrayList<OldSalesBean> old_Sales_List = new ArrayList<>();


    public OldSaleAdapter(Context context, ArrayList<OldSalesBean> old_Sales_List) {
        this.context = context;
        this.old_Sales_List = old_Sales_List;

    }

    @Override
    public int getCount() {
        return old_Sales_List.size();
    }

    @Override
    public Object getItem(int i) {
        return old_Sales_List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    public class ViewHolder {

        private TextView txt_OrderNumber, txt_BillNumber, txt_PriceNumber, txt_DateNumber;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder viewhold;
        View v = null;
        try {
            OldSalesBean oldSalesBean = old_Sales_List.get(position);
            v = view;

            if (v == null) {

                viewhold = new ViewHolder();
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = layoutInflater.inflate(R.layout.old_invoice_item, viewGroup, false);

                viewhold.txt_OrderNumber = (TextView) v.findViewById(R.id.txt_OrderNumber);
                viewhold.txt_BillNumber = (TextView) v.findViewById(R.id.txt_BillNumber);
                viewhold.txt_PriceNumber = (TextView) v.findViewById(R.id.txt_PriceNumber);
                viewhold.txt_DateNumber = (TextView) v.findViewById(R.id.txt_DateNumber);

                if (position % 2 == 0) {
                    v.setBackgroundColor(context.getResources().getColor(R.color.home_activity_table_row_even_bac));
                }
                viewhold.txt_OrderNumber.setText("Order No :- "+oldSalesBean.getOrderno());
                viewhold.txt_BillNumber.setText("Bill No :- "+oldSalesBean.getBillno());
                viewhold.txt_PriceNumber.setText("Price "+oldSalesBean.getPriceno());
                viewhold.txt_DateNumber.setText(oldSalesBean.getBilldateandtime());

            }

        } catch (Exception e) {

        }
        return v;


    }


}
