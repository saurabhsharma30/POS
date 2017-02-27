package com.walkover.pos.LoginPackage;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.walkover.pos.AdminSlidingPackage.AdminSlidingActivity;
import com.walkover.pos.R;
import com.walkover.pos.SalesAndInvoicePackage.SalesPageActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private GridView userlist;
    private ArrayList<LoginBean> list = new ArrayList<>();
    Toolbar toolbar;
    LoginPageAdapter adapter;
    int cout = 0;
    String p1Str = "", p2Str = "", p3Str = "", p4Str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userlist = (GridView) findViewById(R.id.userlist);

        userlist.setNumColumns(5);


        for (int i = 0; i < 25; i++) {
            LoginBean bean = new LoginBean();
            bean.setUsername("Emma Watson");
            bean.setImageurl("http://orig07.deviantart.net/683b/f/2012/327/9/8/anime_girl_avatar_by_avatarw0rld-d5lwqr6.png");
            list.add(bean);
        }

        adapter = new LoginPageAdapter(LoginActivity.this, list);
        userlist.setAdapter(adapter);

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PinDialog();
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
                if (TextUtils.isEmpty(newText)) {
                    adapter.filter("");
                    userlist.clearTextFilter();
                } else {
                    adapter.filter(newText);
                }
                return true;
            }
        });

        return true;
    }

    public void PinDialog() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.pin_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView p1 = (TextView) dialog.findViewById(R.id.p1);
        final TextView p2 = (TextView) dialog.findViewById(R.id.p2);
        final TextView p3 = (TextView) dialog.findViewById(R.id.p3);
        final TextView p4 = (TextView) dialog.findViewById(R.id.p4);
        final TextView n0 = (TextView) dialog.findViewById(R.id.n0);
        final TextView n1 = (TextView) dialog.findViewById(R.id.n1);
        final TextView n2 = (TextView) dialog.findViewById(R.id.n2);
        final TextView n3 = (TextView) dialog.findViewById(R.id.n3);
        final TextView n4 = (TextView) dialog.findViewById(R.id.n4);
        final TextView n5 = (TextView) dialog.findViewById(R.id.n5);
        final TextView n6 = (TextView) dialog.findViewById(R.id.n6);
        final TextView n7 = (TextView) dialog.findViewById(R.id.n7);
        final TextView n8 = (TextView) dialog.findViewById(R.id.n8);
        final TextView n9 = (TextView) dialog.findViewById(R.id.n9);
        final ImageView ivBack = (ImageView) dialog.findViewById(R.id.ivBack);
        final ImageView ivDone = (ImageView) dialog.findViewById(R.id.ivDone);

        n0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("0");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("0");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("0");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("0");
                } else {
                    //EditText is not empty
                }


            }
        });

        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("1");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("1");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("1");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("1");
                } else {
                    //EditText is not empty
                }

            }
        });

        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("2");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("2");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("2");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("2");
                } else {
                    //EditText is not empty
                }
            }
        });

        n3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("3");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("3");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("3");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("3");
                } else {
                    //EditText is not empty
                }
            }
        });

        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("4");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("4");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("4");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("4");
                } else {
                    //EditText is not empty
                }
            }
        });

        n5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("5");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("5");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("5");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("5");
                } else {
                    //EditText is not empty
                }
            }
        });

        n6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("6");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("6");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("6");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("6");
                } else {
                    //EditText is not empty
                }
            }
        });

        n7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("7");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("7");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("7");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("7");
                } else {
                    //EditText is not empty
                }
            }
        });

        n8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("8");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("8");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("8");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("8");
                } else {
                    //EditText is not empty
                }
            }
        });

        n9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (p1Str.isEmpty() || p1Str.length() == 0 || p1Str.equals("") || p1Str == null) {
                    //EditText is empty
                    p1.setText("9");
                } else if (p2Str.isEmpty() || p2Str.length() == 0 || p2Str.equals("") || p2Str == null) {
                    //EditText is empty
                    p2.setText("9");
                } else if (p3Str.isEmpty() || p3Str.length() == 0 || p3Str.equals("") || p3Str == null) {
                    //EditText is empty
                    p3.setText("9");
                } else if (p4Str.isEmpty() || p4Str.length() == 0 || p4Str.equals("") || p4Str == null) {
                    //EditText is empty
                    p4.setText("9");
                } else {
                    //EditText is not empty
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();

                if (!p4Str.isEmpty() || p4Str.length() != 0 || !p4Str.equals("") || p4Str != null) {
                    //EditText is empty
                    p4.setText("");
                }

                if (!p3Str.isEmpty() || p3Str.length() != 0 || !p3Str.equals("") || p3Str != null) {
                    //EditText is empty
                    p3.setText("");
                }

                if (!p2Str.isEmpty() || p2Str.length() != 0 || !p2Str.equals("") || p2Str != null) {
                    //EditText is empty
                    p2.setText("");
                }

                if (!p1Str.isEmpty() || p1Str.length() != 0 || !p1Str.equals("") || p1Str != null) {
                    //EditText is empty
                    p1.setText("");
                }
            }
        });

        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Str = p1.getText().toString().trim();
                p2Str = p2.getText().toString().trim();
                p3Str = p3.getText().toString().trim();
                p4Str = p4.getText().toString().trim();
                Log.e("password", "p1 : " + p1Str + " p2 : " + p2Str + " p3 : " + p3Str + " p4 : " + p4Str);

                final String pass = p1Str + p2Str + p3Str + p4Str;

                if (pass.equalsIgnoreCase("1234")) {

                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.setContentView(R.layout.opening_balance_dialog);
                    dialog.setCanceledOnTouchOutside(true);
                    TextView addcustomer = (TextView) dialog.findViewById(R.id.addcustomer);


                    addcustomer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LoginActivity.this, SalesPageActivity.class);
                            startActivity(intent);
                            finish();
                            dialog.dismiss();

                        }
                    });

                    dialog.show();
                } else {
                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.setContentView(R.layout.opening_balance_dialog);
                    dialog.setCanceledOnTouchOutside(true);
                    TextView addcustomer = (TextView) dialog.findViewById(R.id.addcustomer);


                    addcustomer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LoginActivity.this, AdminSlidingActivity.class);
                            startActivity(intent);
                            finish();
                            dialog.dismiss();

                        }
                    });

                    dialog.show();
                }

            }
        });


    }

}
