package com.walkover.pos.AdminSlidingPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.walkover.pos.AdminInvoicePage.InvoiceCreationAdmin;
import com.walkover.pos.OldInvoicePackage.OldSalesActivity;
import com.walkover.pos.PurchaseOrder.PurchaseOrder;
import com.walkover.pos.R;
import com.walkover.pos.SalesAndInvoicePackage.CustomDrawerAdapter;
import com.walkover.pos.SalesAndInvoicePackage.DrawerItem;
import com.walkover.pos.SalesAndInvoicePackage.SalesPageActivity;
import com.walkover.pos.SalesOrderActivity.SalesOrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 14/2/17.
 */

public class AdminSlidingActivity extends AppCompatActivity {
    ViewPager mViewPager;
    Toolbar toolbar;
    private ListView leftDrawer;
    public static TextView txt_ItemName;
    private ImageView nav_icon;
    List<DrawerItem> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_sliding_page);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_ItemName = (TextView)findViewById(R.id.txt_ItemName);
        setSupportActionBar(toolbar);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_icon = (ImageView) findViewById(R.id.nav_icon);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        leftDrawer = (ListView) findViewById(R.id.leftDrawer);
        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem("Create Invoice", R.drawable.sales_icon, ""));
        dataList.add(new DrawerItem("Old Invoices", R.drawable.old_invoice, ""));
        dataList.add(new DrawerItem("Logout", R.drawable.logout_invoice, ""));

        View headerView1 = layoutInflater.inflate(R.layout.nav_header, null);
        leftDrawer.addHeaderView(headerView1);
        CustomDrawerAdapter adapter = new CustomDrawerAdapter(this, R.layout.draweritem_view,
                dataList);

        leftDrawer.setAdapter(adapter);
        mViewPager = (ViewPager) findViewById(R.id.pager);
/** set the adapter for ViewPager */
        mViewPager.setAdapter(new SamplePagerAdapter(
                getSupportFragmentManager()));



        leftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        Intent intent2 = new Intent(AdminSlidingActivity.this, InvoiceCreationAdmin.class);
                        startActivity(intent2);
                        break;

                    case 2:
                        Intent intent = new Intent(AdminSlidingActivity .this, OldSalesActivity.class);
                        startActivity(intent);
                        break;

                    case 3:

                        break;
                }
            }
        });


    }

    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (position == 0) {
                txt_ItemName.setText("Purchase Order");
                return new PurchaseOrder();
            } else {
                txt_ItemName.setText("Sales Order");
                return new SalesOrderFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

}


