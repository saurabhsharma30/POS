package com.walkover.pos.SalesOrderActivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.walkover.pos.PurchaseOrder.PurchaseOrderBean;
import com.walkover.pos.R;
import java.util.ArrayList;

/**
 * Created by saurabh on 9/2/17.
 */

public class SalesOrderAdapter extends BaseAdapter {

    ArrayList<PurchaseOrderBean> purchase_Order_list = new ArrayList<>();
    Context context;


    public SalesOrderAdapter(Context context, ArrayList<PurchaseOrderBean> purchase_Order_list) {

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
                    R.layout.sales_order_item, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.txt_ProductName.setText(purchaseOrderBean.getProductname());
        holder.counter_TXT.setText(purchaseOrderBean.getRemianing_min());
        return convertView;
    }

    class ViewHolder {
        TextView txt_ProductName,counter_TXT;


        public ViewHolder(View view) {
            txt_ProductName = (TextView) view.findViewById(R.id.txt_ProductName);
            counter_TXT = (TextView)view.findViewById(R.id.counter_TXT);
            view.setTag(this);
        }
    }


    // //////////////COUNT DOWN START/////////////////////////
//    public void countDownStart() {
//    //    Log.e("start_date", start_date);
//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                handler.postDelayed(this, 1000);
//                try {
//                    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                    Date futureDate = dateFormat.parse(start_date);
//                    Date currentDate = new Date();
//                    if (!currentDate.after(futureDate)) {
//                        long diff = futureDate.getTime()
//                                - currentDate.getTime();
//                        long days = diff / (24 * 60 * 60 * 1000);
//                        diff -= days * (24 * 60 * 60 * 1000);
//                        long hours = diff / (60 * 60 * 1000);
//                        diff -= hours * (60 * 60 * 1000);
//                        long minutes = diff / (60 * 1000);
//                        diff -= minutes * (60 * 1000);
//                        long seconds = diff / 1000;
//                        tvCountDown.setText("" + String.format("%02d", days) + ":" + String.format("%02d", hours) + ":" +
//                                String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
//                    } else {
//                        tvCountDownTxt.setVisibility(View.GONE);
//                        tvCountDown.setVisibility(View.GONE);
//                        if (challenge_flag.trim().equals("Past Challenge")) {
//                            btnStartingSoon.setText("Past Challenge");
//                            final int sdk = android.os.Build.VERSION.SDK_INT;
//                            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                                btnStartingSoon.setBackgroundDrawable(getResources().getDrawable(R.drawable.app_past));
//                            } else {
//                                btnStartingSoon.setBackground(getResources().getDrawable(R.drawable.app_past));
//                            }
//
//                        } else if (challenge_flag.trim().equals("Active")) {
//                            btnStartingSoon.setText("Active");
//                            final int sdk = android.os.Build.VERSION.SDK_INT;
//                            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                                btnStartingSoon.setBackgroundDrawable(getResources().getDrawable(R.drawable.app_active));
//                            } else {
//                                btnStartingSoon.setBackground(getResources().getDrawable(R.drawable.app_active));
//                            }
//
//                        } else if (challenge_flag.trim().equals("Abandoned")) {
//                            btnStartingSoon.setText("Abandoned");
//                            final int sdk = android.os.Build.VERSION.SDK_INT;
//                            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                                btnStartingSoon.setBackgroundDrawable(getResources().getDrawable(R.drawable.abandoned_btn));
//                            } else {
//                                btnStartingSoon.setBackground(getResources().getDrawable(R.drawable.abandoned_btn));
//                            }
//
//                        } else {
//
//                        }
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        handler.postDelayed(runnable, 0);
//    }
    // //////////////COUNT DOWN END/////////////////////////
}
