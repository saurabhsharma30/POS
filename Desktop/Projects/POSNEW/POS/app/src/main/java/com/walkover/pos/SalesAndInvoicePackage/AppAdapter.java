package com.walkover.pos.SalesAndInvoicePackage;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.walkover.pos.R;

import java.util.ArrayList;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

/**
 * Created by saurabh on 9/2/17.
 */

public class AppAdapter extends BaseAdapter {
    ArrayList<AddBean> beanArrayList;
    Context context;


    public AppAdapter(Context context, ArrayList<AddBean> beanArrayList) {
        this.beanArrayList = beanArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return beanArrayList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context,
                    R.layout.item_list_app, null);
            new ViewHolder(convertView);
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.quantity.setText(beanArrayList.get(position).getProductCount());
        holder.description.setText(beanArrayList.get(position).getProductDesc());
        holder.price.setText(beanArrayList.get(position).getProductPrice());
        if (position % 2 == 0) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.home_activity_table_row_even_bac));
        }

        holder.quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SimpleTooltip tooltip = new SimpleTooltip.Builder(context)
                        .anchorView(v)
                        .gravity(Gravity.START)
                        .animated(true)
                        .transparentOverlay(false)
                        .dismissOnOutsideTouch(true)
                        .contentView(R.layout.tooltip_custom, R.id.n1)
                        .contentView(R.layout.tooltip_custom, R.id.n2)
                        .contentView(R.layout.tooltip_custom, R.id.n3)
                        .contentView(R.layout.tooltip_custom, R.id.n4)
                        .contentView(R.layout.tooltip_custom, R.id.n5)
                        .contentView(R.layout.tooltip_custom, R.id.n6)
                        .contentView(R.layout.tooltip_custom, R.id.n7)
                        .contentView(R.layout.tooltip_custom, R.id.n8)
                        .contentView(R.layout.tooltip_custom, R.id.n9)
                        .contentView(R.layout.tooltip_custom, R.id.n0)
                        .contentView(R.layout.tooltip_custom, R.id.ivBack)
                        .contentView(R.layout.tooltip_custom, R.id.ivDone)
                        .build();

                tooltip.findViewById(R.id.n0).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("0", holder.quantity);
                        beanArrayList.get(position).setProductCount("0");

                    }
                });

                tooltip.findViewById(R.id.n1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("1", holder.quantity);
                        beanArrayList.get(position).setProductCount("1");

                    }
                });

                tooltip.findViewById(R.id.n2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("2", holder.quantity);
                        beanArrayList.get(position).setProductCount("2");

                    }
                });

                tooltip.findViewById(R.id.n3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("3", holder.quantity);
                        beanArrayList.get(position).setProductCount("3");

                    }
                });

                tooltip.findViewById(R.id.n4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("4", holder.quantity);
                        beanArrayList.get(position).setProductCount("4");

                    }

                });

                tooltip.findViewById(R.id.n5).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("5", holder.quantity);
                        beanArrayList.get(position).setProductCount("5");

                    }
                });

                tooltip.findViewById(R.id.n6).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("6", holder.quantity);
                        beanArrayList.get(position).setProductCount("6");

                    }
                });

                tooltip.findViewById(R.id.n7).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("7", holder.quantity);
                        beanArrayList.get(position).setProductCount("7");

                    }
                });

                tooltip.findViewById(R.id.n8).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("8", holder.quantity);
                        beanArrayList.get(position).setProductCount("8");

                    }
                });

                tooltip.findViewById(R.id.n9).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {

                        add_calc("9", holder.quantity);
                        beanArrayList.get(position).setProductCount("9");

                    }
                });

                tooltip.findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {
                        f_calc(holder.quantity);

                    }
                });

                tooltip.findViewById(R.id.ivDone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {
                        if (tooltip.isShowing()) {
                            tooltip.dismiss();
                        }
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();

                    }
                });

                tooltip.show();
            }
        });

        return convertView;
    }


    class ViewHolder {
        TextView quantity, description, price;
        LinearLayout main_layout;

        public ViewHolder(View view) {
            quantity = (TextView) view.findViewById(R.id.quantity);
            description = (TextView) view.findViewById(R.id.description);
            price = (TextView) view.findViewById(R.id.price);
            main_layout = (LinearLayout) view.findViewById(R.id.main_layout);
            view.setTag(this);
        }
    }

    public void add_calc(String num, TextView quantity) {
        String numStr = quantity.getText().toString();
        numStr = numStr + num;
        quantity.setText(numStr);
    }

    public void f_calc(TextView quantity) {
        String numStr = quantity.getText().toString();
        numStr = numStr.substring(0, numStr.length() - 1);
        quantity.setText(numStr);
        if (numStr.length() == 0) {
        }
    }

}
