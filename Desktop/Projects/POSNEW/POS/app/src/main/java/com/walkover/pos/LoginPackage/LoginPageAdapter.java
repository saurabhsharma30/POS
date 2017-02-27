package com.walkover.pos.LoginPackage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.walkover.pos.R;
import com.walkover.pos.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by saurabh on 31/1/17.
 */

public class LoginPageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<LoginBean> login_Userlist = new ArrayList<>();
    private List<LoginBean> searchList;

    public LoginPageAdapter(Context mContext, ArrayList<LoginBean> login_Userlist) {
        this.mContext = mContext;
        this.login_Userlist = login_Userlist;
        this.searchList = new ArrayList<>();
        this.searchList.addAll(login_Userlist);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return login_Userlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return login_Userlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        // login_Userlist.clear();
        if (charText.length() == 0) {
            login_Userlist.addAll(searchList);
        } else {
            for (LoginBean s : searchList) {
                if (s.getUsername().toLowerCase(Locale.getDefault()).contains(charText)) {
                    login_Userlist.add(s);
                } else if (s.getUsername().toUpperCase(Locale.getDefault()).contains(charText)) {
                    login_Userlist.add(s);
                } else {
//                    Toast.makeText(mContext, "No user found.", Toast.LENGTH_SHORT).show();
                }
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
        final LoginBean loginBean = login_Userlist.get(position);


        if (view == null) {

            viewhold = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.login_user_item, viewGroup, false);

            viewhold.user_name = (TextView) view.findViewById(R.id.user_name);
            viewhold.user_image = (ImageView) view.findViewById(R.id.user_image);
            viewhold.user_name.setText(loginBean.getUsername());
            Constants.LoadImage(mContext, viewhold.user_image, loginBean.getImageurl());


        }

        return view;


    }

}

