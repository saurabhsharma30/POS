package com.walkover.pos.PurchaseOrder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.walkover.pos.R;

import java.util.ArrayList;

/**
 * Created by saurabh on 9/2/17.
 */

public class PurchaseOrderAdapter extends BaseAdapter {

    ArrayList<PurchaseOrderBean> purchase_Order_list = new ArrayList<>();
    Context context;


    public PurchaseOrderAdapter(Context context, ArrayList<PurchaseOrderBean> purchase_Order_list) {

        this.purchase_Order_list = purchase_Order_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return purchase_Order_list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final PurchaseOrderBean purchaseOrderBean = purchase_Order_list.get(position);
        if (convertView == null) {
            convertView = View.inflate(context,
                    R.layout.purchase_order_item, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.txt_ProductName.setText(purchaseOrderBean.getProductname());
        return convertView;
    }

    class ViewHolder {
        TextView txt_ProductName;


        public ViewHolder(View view) {
            txt_ProductName = (TextView) view.findViewById(R.id.txt_ProductName);

            view.setTag(this);
        }
    }
}
