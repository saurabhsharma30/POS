package com.walkover.pos.SalesAndInvoicePackage.SalesProductUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.walkover.pos.LoginPackage.LoginBean;
import com.walkover.pos.R;
import com.walkover.pos.SalesAndInvoicePackage.SalesProductUtils.SalesProductBean;
import com.walkover.pos.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by saurabh on 31/1/17.
 */

public class SalesPageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<SalesProductBean> product_list = new ArrayList<>();
    private List<SalesProductBean> searchList;

    public SalesPageAdapter(Context mContext, ArrayList<SalesProductBean> product_list) {
        this.mContext = mContext;
        this.product_list = product_list;
        this.searchList = new ArrayList<>();
        this.searchList.addAll(product_list);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return product_list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return product_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
       // product_list.clear();
        if (charText.length() == 0) {
            product_list.addAll(searchList);
        } else {
            for (SalesProductBean s : searchList) {
              /*  if (s.getUsername().toLowerCase(Locale.getDefault()).contains(charText)) {
                    product_list.add(s);
                } else if (s.getUsername().toUpperCase(Locale.getDefault()).contains(charText)) {
                    product_list.add(s);
                } else {
//                    Toast.makeText(mContext, "No user found.", Toast.LENGTH_SHORT).show();
                }*/
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {

        private TextView user_name;
        private ImageView user_image;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder viewhold;
        final SalesProductBean salesProductBean = product_list.get(position);


        if (view == null) {

            viewhold = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.sales_user_item, viewGroup, false);

            viewhold.user_name = (TextView) view.findViewById(R.id.user_name);
            viewhold.user_image = (ImageView) view.findViewById(R.id.user_image);

            viewhold.user_name.setText(salesProductBean.getProduct_name());

            Constants.LoadImage(mContext, viewhold.user_image, salesProductBean.getProduct_pic());

        }

        return view;


    }

}

