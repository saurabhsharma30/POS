package com.walkover.pos.SalesAndInvoicePackage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.walkover.pos.R;

import java.util.ArrayList;


public class SalesCatHeaderAdapter extends BaseAdapter implements Filterable {

    Activity context;
    private static LayoutInflater inflater = null;
    ArrayList<SalesCatBean> List;
    ArrayList<SalesCatBean> searchList = new ArrayList<>();

    public SalesCatHeaderAdapter() {
    }

    public SalesCatHeaderAdapter(Activity context, ArrayList<SalesCatBean> List) {
        this.List = List;
        this.context = context;
        this.searchList.addAll(List);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<SalesCatBean> results = new ArrayList<SalesCatBean>();
                if (searchList == null)
                    searchList = List;
                if (constraint != null) {
                    if (searchList != null && searchList.size() > 0) {
                        for (final SalesCatBean g : searchList) {
                            if (g.getCatname().toLowerCase()
                                    .contains(constraint.toString())) {
                                results.add(g);
                            } else if (g.getCatname().toUpperCase()
                                    .contains(constraint.toString())) {
                                results.add(g);
                            } else {
//                                    Toast.makeText(context, "No user found.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                List = (ArrayList<SalesCatBean>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {

        private TextView cat_name;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        final SalesCatBean catBean = List.get(position);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sales_cat_header_item, null);
            holder.cat_name = (TextView) convertView.findViewById(R.id.cat_name);
            holder.cat_name.setText(catBean.getCatname());

        }
        return convertView;

    }
}