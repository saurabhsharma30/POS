package com.walkover.pos.SalesAndInvoicePackage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.epson.epos2.Epos2Exception;
import com.epson.epos2.printer.Printer;
import com.epson.epos2.printer.PrinterStatusInfo;
import com.epson.epos2.printer.ReceiveListener;
import com.walkover.pos.LoginPackage.LoginBean;
import com.walkover.pos.OldInvoicePackage.OldSalesActivity;
import com.walkover.pos.Printer.DiscoveryActivity;
import com.walkover.pos.Printer.SpnModelLang;
import com.walkover.pos.Printer.SpnModelsItem;
import com.walkover.pos.R;
import com.walkover.pos.utils.GridViewWithHeaderAndFooter;
import com.walkover.pos.utils.ShowMsg;
import com.walkover.pos.utils.TwoWayView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 8/2/17.
 */

public class SalesPageActivity extends AppCompatActivity implements ReceiveListener {

    GridViewWithHeaderAndFooter sales_Grid;
    TwoWayView categoryListView, addOnView;
    Toolbar toolbar;
    private ListView leftDrawer;
    private ArrayList<LoginBean> list = new ArrayList<>();
    private ArrayList<LoginBean> addon_list = new ArrayList<>();
    private ArrayList<SalesCatBean> cat_header_list = new ArrayList<>();
    CustomDrawerAdapter adapter;
    List<DrawerItem> dataList;
    LinearLayout ll_charge;
    private ArrayList<String> product_list;
    private ArrayList<String> product_list_description;
    private ArrayList<String> product_list_price;
    private AppAdapter mAdapter;
    boolean visibility_Flag = false;
    private RelativeLayout add_customer_layout, printer_layout;
    View add_customer_view, printer_view;
    SearchView search_view;
    private LinearLayout all_Invoice;
    String target;
    ArrayList<AddBean> addBeanArrayList = new ArrayList<>();
    private ImageView nav_icon;
    private SwipeMenuListView mListView;

    //Printer
    private Printer mPrinter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_page_activity);

        setUpPane();
        BillingDetails();
    }


    private void setUpPane() {

        sales_Grid = (GridViewWithHeaderAndFooter) findViewById(R.id.sales_Grid);
        add_customer_layout = (RelativeLayout) findViewById(R.id.add_customer_layout);
        add_customer_view = (View) findViewById(R.id.add_customer_view);
        printer_layout = (RelativeLayout) findViewById(R.id.printer_layout);
        ll_charge = (LinearLayout) findViewById(R.id.ll_charge);
        printer_view = (View) findViewById(R.id.printer_view);
        all_Invoice = (LinearLayout) findViewById(R.id.all_Invoice);
        search_view = (SearchView) findViewById(R.id.search_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i = 0; i < 5; i++) {
            SalesCatBean bean = new SalesCatBean();
            bean.setCatid(String.valueOf(i));
            bean.setCatname("Alchol");
            cat_header_list.add(bean);
        }
        JSONObject invoiceobj = invoicearray();
        Log.e("invoiceobj", "invoiceobj:=== " + invoiceobj.toString());

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_icon = (ImageView) findViewById(R.id.nav_icon);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        leftDrawer = (ListView) findViewById(R.id.leftDrawer);
        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem("Sales/Admin", R.drawable.sales_icon, ""));
        dataList.add(new DrawerItem("Old Invoices", R.drawable.old_invoice, ""));
        dataList.add(new DrawerItem("Logout", R.drawable.logout_invoice, ""));

        View headerView1 = layoutInflater.inflate(R.layout.nav_header, null);
        leftDrawer.addHeaderView(headerView1);
        adapter = new CustomDrawerAdapter(this, R.layout.draweritem_view,
                dataList);

        leftDrawer.setAdapter(adapter);
        /*drawer.setDrawerShadow(R.drawable.menu_img2,
                GravityCompat.START);*/

        add_customer_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SalesPageActivity.this);
                dialog.setContentView(R.layout.add_customer_dialog);
                dialog.setCanceledOnTouchOutside(true);
                TextView addcustomer = (TextView) dialog.findViewById(R.id.addcustomer);


                addcustomer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

        });

        all_Invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesPageActivity.this, OldSalesActivity.class);
                startActivity(intent);
            }
        });


        leftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:
                     /*   Intent intent2 = new Intent(SalesPageActivity.this, AdminSlidingActivity.class);
                        startActivity(intent2);*/
                        break;

                    case 2:
                        Intent intent = new Intent(SalesPageActivity.this, OldSalesActivity.class);
                        startActivity(intent);
                        break;

                    case 3:

                        break;
                }
            }
        });

        View headerView = layoutInflater.inflate(R.layout.sales_page_header, null);
        categoryListView = (TwoWayView) headerView.findViewById(R.id.categoryListView);
        View footerView = layoutInflater.inflate(R.layout.sales_cat_footer, null);
        addOnView = (TwoWayView) footerView.findViewById(R.id.addOnView);
        sales_Grid.addHeaderView(headerView);
        sales_Grid.addFooterView(footerView);
        // gridView.addFooterView(footerView);

        SalesCatHeaderAdapter salesCatHeaderAdapter = new SalesCatHeaderAdapter(SalesPageActivity.this, cat_header_list);


        for (int j = 0; j < 10; j++) {
            Log.e("Home", "onCreate:---->>" + j);
            LoginBean bean = new LoginBean();
            bean.setUsername("Whiskey");
            bean.setImageurl("https://images6.alphacoders.com/327/327456.jpg");
            list.add(bean);
        }


        SalesPageAdapter adapter = new SalesPageAdapter(SalesPageActivity.this, list);
        categoryListView.setAdapter(salesCatHeaderAdapter);
        addOnView.setAdapter(adapter);
        sales_Grid.setAdapter(adapter);


        ////////////////////////////// Billing Details Page//////////////////////////////////////////
        product_list = new ArrayList<>();
        product_list_description = new ArrayList<>();
        product_list_price = new ArrayList<>();
        product_list.add("1");
        product_list.add("3");
        product_list.add("6");
        product_list.add("4");
        product_list.add("2");
        /////////////////////////////////
        product_list_description.add("Hot Coffee");
        product_list_description.add("Hot Coffee");
        product_list_description.add("Hot Coffee");
        product_list_description.add("Hot Coffee");
        product_list_description.add("Hot Coffee");
        ////////////////////////////////////////////
        product_list_price.add("120");
        product_list_price.add("120");
        product_list_price.add("120");
        product_list_price.add("120");
        product_list_price.add("120");

        mListView = (SwipeMenuListView) findViewById(R.id.listView);

        for (int i = 0; i <= 5; i++) {
            AddBean addBean = new AddBean("0", "Hot Coffee", "120");
            addBeanArrayList.add(addBean);
        }
//        mAdapter = new AppAdapter(getApplicationContext(),product_list,product_list_description,product_list_price);
        mAdapter = new AppAdapter(getApplicationContext(), addBeanArrayList);
        mListView.setAdapter(mAdapter);

        View footerView2 = ((LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.sales_bill_footer_layout, null, false);
        mListView.addFooterView(footerView2);

        nav_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(SalesPageActivity.this, "", Toast.LENGTH_SHORT).show();
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                    //onBackPressed();
                }
            }
        });


        search_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visibility_Flag) {
                    add_customer_layout.setVisibility(View.GONE);
                    add_customer_view.setVisibility(View.GONE);
                    printer_view.setVisibility(View.GONE);
                    printer_layout.setVisibility(View.GONE);
                    visibility_Flag = false;
                } else {
                    add_customer_layout.setVisibility(View.VISIBLE);
                    add_customer_view.setVisibility(View.VISIBLE);
                    printer_view.setVisibility(View.VISIBLE);
                    printer_layout.setVisibility(View.VISIBLE);
                    visibility_Flag = true;
                }


            }
        });


        ll_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SalesPageActivity.this, DiscoveryActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               /* if (TextUtils.isEmpty(newText)) {
                    adapter.filter("");
                    userlist.clearTextFilter();
                } else {
                    adapter.filter(newText);
                }*/
                return true;
            }
        });

        return true;
    }

    public void BillingDetails() {
        product_list = new ArrayList<>();
        product_list_description = new ArrayList<>();
        product_list_price = new ArrayList<>();
        product_list.add("1");
        product_list.add("3");
        product_list.add("6");
        product_list.add("4");
        product_list.add("2");
        /////////////////////////////////
        product_list_description.add("Hot Coffee");
        product_list_description.add("Hot Coffee");
        product_list_description.add("Hot Coffee");
        product_list_description.add("Hot Coffee");
        product_list_description.add("Hot Coffee");
        ////////////////////////////////////////////
        product_list_price.add("120");
        product_list_price.add("120");
        product_list_price.add("120");
        product_list_price.add("120");
        product_list_price.add("120");

        mListView = (SwipeMenuListView) findViewById(R.id.listView);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                //  openItem.setBackground(getResources().getColor(R.color.home_activity_table_row_even_bac));
                // set item width
                openItem.setWidth(dp2px(70));
                // set item title
                openItem.setTitle("Coffee");
                // set item title fontsize
                openItem.setTitleSize(12);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                SwipeMenuItem price_item = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                price_item.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // price_item.setBackground(getResources().getColor(R.color.home_activity_table_row_even_bac));
                // set item width
                price_item.setWidth(dp2px(70));
                // set item title
                price_item.setTitle("$ 12");
                // set item title fontsize
                price_item.setTitleSize(12);
                // set item title font color
                price_item.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(price_item);

                // create "delete" item
                SwipeMenuItem newitem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                newitem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // deleteItem.setBackground(getResources().getColor(R.color.home_activity_delete_bac));
                // set item width
                newitem.setWidth(dp2px(70));
                // set a icon
                newitem.setIcon(R.drawable.bill_discount_icon);
                // add to menu
                menu.addMenuItem(newitem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // deleteItem.setBackground(getResources().getColor(R.color.home_activity_delete_bac));
                // set item width
                deleteItem.setWidth(dp2px(70));
                // set a icon
                deleteItem.setIcon(R.drawable.bill_delete_icon);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                    case 2:
                        // delete
                        break;

                    case 3:
                        // delete
                        product_list.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
            }

            @Override
            public void onSwipeEnd(int position) {
            }
        });


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    // TODO: 16/2/17 Printer Code


    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        if (data != null && resultCode == RESULT_OK) {

            target = data.getStringExtra(getString(R.string.title_target));
            if (target != null) {
                Toast.makeText(this, target, Toast.LENGTH_SHORT).show();
                try {
                    if (!runPrintReceiptSequence()) {
                        //    updateButtonState(true);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private boolean initializeObject() {
        try {

            SpnModelsItem spnModelsItem = new SpnModelsItem(getString(R.string.printerseries_u220), Printer.TM_U220);
            SpnModelLang spnModelLang = new SpnModelLang(getString(R.string.lang_southasia), Printer.MODEL_SOUTHASIA);
            mPrinter = new Printer(spnModelsItem.getModelConstant(), spnModelLang.getModelConstant(), SalesPageActivity.this);
        } catch (Exception e) {
            ShowMsg.showException(e, "Printer", SalesPageActivity.this);
            return false;
        }

        mPrinter.setReceiveEventListener(this);

        return true;
    }


    private boolean createReceiptData() {
        String method = "";
        Bitmap logoData = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        StringBuilder textData = new StringBuilder();
        final int barcodeWidth = 2;
        final int barcodeHeight = 100;

        if (mPrinter == null) {
            return false;
        }

        try {
            method = "addTextAlign";
            mPrinter.addTextAlign(Printer.ALIGN_CENTER);

            method = "addImage";
            mPrinter.addImage(logoData, 0, 0,
                    logoData.getWidth(),
                    logoData.getHeight(),
                    Printer.COLOR_1,
                    Printer.MODE_MONO,
                    Printer.HALFTONE_DITHER,
                    Printer.PARAM_DEFAULT,
                    Printer.COMPRESS_AUTO);

            method = "addFeedLine";
            mPrinter.addFeedLine(1);
            textData.append("THE STORE 123 (555) 555 – 5555\n");
            textData.append("STORE DIRECTOR – John Smith\n");
            textData.append("\n");
            textData.append("7/01/07 16:58 6153 05 0191 134\n");
            textData.append("ST# 21 OP# 001 TE# 01 TR# 747\n");
            textData.append("------------------------------\n");
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());

          /*  textData.append("400 OHEIDA 3PK SPRINGF  9.99 R\n");
            textData.append("410 3 CUP BLK TEAPOT    9.99 R\n");
            textData.append("445 EMERIL GRIDDLE/PAN 17.99 R\n");
            textData.append("438 CANDYMAKER ASSORT   4.99 R\n");
            textData.append("474 TRIPOD              8.99 R\n");
            textData.append("433 BLK LOGO PRNTED ZO  7.99 R\n");
            textData.append("458 AQUA MICROTERRY SC  6.99 R\n");
            textData.append("493 30L BLK FF DRESS   16.99 R\n");
            textData.append("407 LEVITATING DESKTOP  7.99 R\n");
            textData.append("441 **Blue Overprint P  2.99 R\n");
            textData.append("476 REPOSE 4PCPM CHOC   5.49 R\n");
            textData.append("461 WESTGATE BLACK 25  59.99 R\n");
            textData.append("------------------------------\n");*/

            try {
                JSONObject invoiceobj = invoicearray();
                JSONArray array = invoiceobj.optJSONArray("req");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject productobj = array.optJSONObject(i);
                    String productname = productobj.optString("product");
                    String price = productobj.optString("price");

                    textData.append(productname + "-------------------->  " + price + "\n");
                }
                textData.append("--------------------------------------\n");


            } catch (Exception e) {
                e.printStackTrace();
            }
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());

            textData.append("SUBTOTAL                160.38\n");
            textData.append("TAX                      14.43\n");
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());

            method = "addTextSize";
            mPrinter.addTextSize(2, 2);
            method = "addText";
            mPrinter.addText("TOTAL    174.81\n");
            method = "addTextSize";
            mPrinter.addTextSize(1, 1);
            method = "addFeedLine";
            mPrinter.addFeedLine(1);

            textData.append("CASH                    200.00\n");
            textData.append("CHANGE                   25.19\n");
            textData.append("------------------------------\n");
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());

            textData.append("Purchased item total number\n");
            textData.append("Sign Up and Save !\n");
            textData.append("With Preferred Saving Card\n");
            method = "addText";
            mPrinter.addText(textData.toString());
            textData.delete(0, textData.length());
            method = "addFeedLine";
            mPrinter.addFeedLine(2);

            method = "addBarcode";
            mPrinter.addBarcode("01209457",
                    Printer.BARCODE_CODE39,
                    Printer.HRI_BELOW,
                    Printer.FONT_A,
                    barcodeWidth,
                    barcodeHeight);

            method = "addCut";
            mPrinter.addCut(Printer.CUT_FEED);
        } catch (Exception e) {
            ShowMsg.showException(e, method, SalesPageActivity.this);
            return false;
        }

        textData = null;

        return true;
    }


    private void finalizeObject() {
        if (mPrinter == null) {
            return;
        }

        mPrinter.clearCommandBuffer();

        mPrinter.setReceiveEventListener(null);

        mPrinter = null;
    }


    private boolean printData() {
        if (mPrinter == null) {
            return false;
        }

        if (!connectPrinter()) {
            return false;
        }

        PrinterStatusInfo status = mPrinter.getStatus();

        dispPrinterWarnings(status);

        if (!isPrintable(status)) {
            ShowMsg.showMsg(makeErrorMessage(status), SalesPageActivity.this);
            try {
                mPrinter.disconnect();
            } catch (Exception ex) {
                // Do nothing
            }
            return false;
        }

        try {
            mPrinter.sendData(Printer.PARAM_DEFAULT);
        } catch (Exception e) {
            ShowMsg.showException(e, "sendData", SalesPageActivity.this);
            try {
                mPrinter.disconnect();
            } catch (Exception ex) {
                // Do nothing
            }
            return false;
        }

        return true;
    }

    private boolean runPrintReceiptSequence() {
        if (!initializeObject()) {
            return false;
        }

        if (!createReceiptData()) {
            finalizeObject();
            return false;
        }

        if (!printData()) {
            finalizeObject();
            return false;
        }

        return true;
    }

    private boolean connectPrinter() {
        boolean isBeginTransaction = false;

        if (mPrinter == null) {
            return false;
        }

        try {
            mPrinter.connect(target, Printer.PARAM_DEFAULT);
        } catch (Exception e) {
            ShowMsg.showException(e, "connect", SalesPageActivity.this);
            return false;
        }

        try {
            mPrinter.beginTransaction();
            isBeginTransaction = true;
        } catch (Exception e) {
            ShowMsg.showException(e, "beginTransaction", SalesPageActivity.this);
        }

        if (isBeginTransaction == false) {
            try {
                mPrinter.disconnect();
            } catch (Epos2Exception e) {
                // Do nothing
                return false;
            }
        }

        return true;
    }

    private void disconnectPrinter() {
        if (mPrinter == null) {
            return;
        }

        try {
            mPrinter.endTransaction();
        } catch (final Exception e) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
                    ShowMsg.showException(e, "endTransaction", SalesPageActivity.this);
                }
            });
        }

        try {
            mPrinter.disconnect();
        } catch (final Exception e) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
                    ShowMsg.showException(e, "disconnect", SalesPageActivity.this);
                }
            });
        }

        finalizeObject();
    }

    private boolean isPrintable(PrinterStatusInfo status) {
        if (status == null) {
            return false;
        }

        if (status.getConnection() == Printer.FALSE) {
            return false;
        } else if (status.getOnline() == Printer.FALSE) {
            return false;
        } else {
            ;//print available
        }

        return true;
    }

    private String makeErrorMessage(PrinterStatusInfo status) {
        String msg = "";

        if (status.getOnline() == Printer.FALSE) {
            msg += getString(R.string.handlingmsg_err_offline);
        }
        if (status.getConnection() == Printer.FALSE) {
            msg += getString(R.string.handlingmsg_err_no_response);
        }
        if (status.getCoverOpen() == Printer.TRUE) {
            msg += getString(R.string.handlingmsg_err_cover_open);
        }
        if (status.getPaper() == Printer.PAPER_EMPTY) {
            msg += getString(R.string.handlingmsg_err_receipt_end);
        }
        if (status.getPaperFeed() == Printer.TRUE || status.getPanelSwitch() == Printer.SWITCH_ON) {
            msg += getString(R.string.handlingmsg_err_paper_feed);
        }
        if (status.getErrorStatus() == Printer.MECHANICAL_ERR || status.getErrorStatus() == Printer.AUTOCUTTER_ERR) {
            msg += getString(R.string.handlingmsg_err_autocutter);
            msg += getString(R.string.handlingmsg_err_need_recover);
        }
        if (status.getErrorStatus() == Printer.UNRECOVER_ERR) {
            msg += getString(R.string.handlingmsg_err_unrecover);
        }
        if (status.getErrorStatus() == Printer.AUTORECOVER_ERR) {
            if (status.getAutoRecoverError() == Printer.HEAD_OVERHEAT) {
                msg += getString(R.string.handlingmsg_err_overheat);
                msg += getString(R.string.handlingmsg_err_head);
            }
            if (status.getAutoRecoverError() == Printer.MOTOR_OVERHEAT) {
                msg += getString(R.string.handlingmsg_err_overheat);
                msg += getString(R.string.handlingmsg_err_motor);
            }
            if (status.getAutoRecoverError() == Printer.BATTERY_OVERHEAT) {
                msg += getString(R.string.handlingmsg_err_overheat);
                msg += getString(R.string.handlingmsg_err_battery);
            }
            if (status.getAutoRecoverError() == Printer.WRONG_PAPER) {
                msg += getString(R.string.handlingmsg_err_wrong_paper);
            }
        }
        if (status.getBatteryLevel() == Printer.BATTERY_LEVEL_0) {
            msg += getString(R.string.handlingmsg_err_battery_real_end);
        }

        return msg;
    }

    private void dispPrinterWarnings(PrinterStatusInfo status) {
        //  EditText edtWarnings = (EditText)findViewById(R.id.edtWarnings);
        String warningsMsg = "";

        if (status == null) {
            return;
        }

        if (status.getPaper() == Printer.PAPER_NEAR_END) {
            warningsMsg += getString(R.string.handlingmsg_warn_receipt_near_end);
        }

        if (status.getBatteryLevel() == Printer.BATTERY_LEVEL_1) {
            warningsMsg += getString(R.string.handlingmsg_warn_battery_near_end);
        }

        Toast.makeText(this, warningsMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPtrReceive(Printer printer, final int i, final PrinterStatusInfo printerStatusInfo, String s) {
        runOnUiThread(new Runnable() {
            @Override
            public synchronized void run() {
                ShowMsg.showResult(i, makeErrorMessage(printerStatusInfo), SalesPageActivity.this);

                dispPrinterWarnings(printerStatusInfo);

                //   updateButtonState(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        disconnectPrinter();
                    }
                }).start();
            }
        });
    }


    public JSONObject invoicearray() {
        JSONObject obj = null;
        try {
            obj = new JSONObject();
            JSONArray req = new JSONArray();

            JSONObject reqObj = new JSONObject();
            reqObj.put("id", "1");
            reqObj.put("product", "Chips");
            reqObj.put("price", "Rs 200");
            req.put(reqObj);
            reqObj = new JSONObject();
            reqObj.put("id", "2");
            reqObj.put("product", "Cold Drink");
            reqObj.put("price", "Rs 50");
            req.put(reqObj);

            reqObj = new JSONObject();
            reqObj.put("id", "2");
            reqObj.put("product", "Burger");
            reqObj.put("price", "Rs 500");
            req.put(reqObj);
            obj.put("req", req);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
