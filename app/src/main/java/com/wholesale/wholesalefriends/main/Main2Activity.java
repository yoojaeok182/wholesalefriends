package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.fragment.CustomerFragment;
import com.wholesale.wholesalefriends.main.fragment.HomeFragment;
import com.wholesale.wholesalefriends.main.fragment.MyPageFragment;
import com.wholesale.wholesalefriends.main.fragment.OrderListFragment;

public class Main2Activity extends GroupActivity {

    private RelativeLayout btnMenu;
    private ImageView ivLogo;
    private TextView tvTitle;
    private RelativeLayout btnNotice;
    private RelativeLayout btnMore;
    private ViewPager viewPager;
    private LinearLayout btnMenu01;
    private LinearLayout btnMenu02;
    private LinearLayout btnMenu03;
    private LinearLayout btnMenu04;
    private ImageView ivStaffType;
    private LinearLayout laySetting;
    private TextView tvShopLocale;
    private LinearLayout btnOrder;
    private LinearLayout btnProduct;
    private LinearLayout btnShowProductSearch;
    private LinearLayout btnCustomer;
    private LinearLayout btnAllProduct;
    private LinearLayout btnMyShopBest30;
    private LinearLayout btnMyShopNotice;
    private LinearLayout btnMyShopServiceCenter;
    private LinearLayout btnClose;
    private DrawerLayout drawerLayout;


    private HomeFragment homeFragment;
    private OrderListFragment orderListFragment;
    private CustomerFragment customerFragment;
    private MyPageFragment myPageFragment;
    private ImageView ivMenu01;
    private ImageView ivMenu02;
    private ImageView ivMenu03;
    private ImageView ivMenu04;
    private TextView tvMenu01;
    private TextView tvMenu02;
    private TextView tvMenu03;
    private TextView tvMenu04;

    public static boolean isFirstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        isFirstRun = true;

        btnMenu = findViewById(R.id.btnMenu);
        ivLogo = findViewById(R.id.ivLogo);
        tvTitle = findViewById(R.id.tvTitle);
        btnNotice = findViewById(R.id.btnNotice);
        btnMore = findViewById(R.id.btnMore);
        viewPager = findViewById(R.id.viewPager);
        btnMenu01 = findViewById(R.id.btnMenu01);
        btnMenu02 = findViewById(R.id.btnMenu02);
        btnMenu03 = findViewById(R.id.btnMenu03);
        btnMenu04 = findViewById(R.id.btnMenu04);
        ivStaffType = findViewById(R.id.ivStaffType);
        laySetting = findViewById(R.id.laySetting);
        tvShopLocale = findViewById(R.id.tvShopLocale);
        btnOrder = findViewById(R.id.btnOrder);
        btnProduct = findViewById(R.id.btnProduct);
        btnShowProductSearch = findViewById(R.id.btnShowProductSearch);
        btnCustomer = findViewById(R.id.btnCustomer);
        btnAllProduct = findViewById(R.id.btnAllProduct);
        btnMyShopBest30 = findViewById(R.id.btnMyShopBest30);
        btnMyShopNotice = findViewById(R.id.btnMyShopNotice);
        btnMyShopServiceCenter = findViewById(R.id.btnMyShopServiceCenter);
        btnClose = findViewById(R.id.btnClose);
        drawerLayout = findViewById(R.id.drawer_layout);
        ivMenu01 = findViewById(R.id.ivMenu01);
        ivMenu02 = findViewById(R.id.ivMenu02);
        ivMenu03 = findViewById(R.id.ivMenu03);
        ivMenu04 = findViewById(R.id.ivMenu04);
        tvMenu01 = findViewById(R.id.tvMenu01);
        tvMenu02 = findViewById(R.id.tvMenu02);
        tvMenu03 = findViewById(R.id.tvMenu03);
        tvMenu04 = findViewById(R.id.tvMenu04);
        homeFragment = new HomeFragment();
        orderListFragment = new OrderListFragment();
        customerFragment = new CustomerFragment();
        myPageFragment = new MyPageFragment();
        init();


    }

    private void init() {

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getDrawaerController(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getDrawaerController(false);

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentMenu(0);
                updateTableFragment(0);
            }
        });
        btnMenu01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentMenu(0);
                updateTableFragment(0);
            }
        });

        btnMenu02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentMenu(1);
                updateTableFragment(1);
            }
        });

        btnMenu03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentMenu(2);
                updateTableFragment(2);
            }
        });

        btnMenu04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentMenu(3);
                updateTableFragment(3);
            }
        });
        setCurrentMenu(0);
        updateTableFragment(0);
    }

    private void getDrawaerController(boolean isOpen) {

    }


    private void setCurrentMenu(int pos) {

        ivMenu01.setBackgroundResource(R.drawable.btn_main_menu_01_select);
        ivMenu02.setBackgroundResource(R.drawable.btn_main_menu_02_select);
        ivMenu03.setBackgroundResource(R.drawable.btn_main_menu_03_select);
        ivMenu04.setBackgroundResource(R.drawable.btn_main_menu_04_select);


        tvMenu01.setTextColor(getResources().getColor(R.color.btn_textcolor_01_select));
        tvMenu02.setTextColor(getResources().getColor(R.color.btn_textcolor_01_select));
        tvMenu03.setTextColor(getResources().getColor(R.color.btn_textcolor_01_select));
        tvMenu04.setTextColor(getResources().getColor(R.color.btn_textcolor_01_select));

        switch (pos) {
            case 0:
                ivMenu01.setBackgroundResource(R.drawable.botton_icon_home_on);
                tvMenu01.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 1:
                ivMenu02.setBackgroundResource(R.drawable.botton_icon_category_on);
                tvMenu02.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 2:
                ivMenu03.setBackgroundResource(R.drawable.botton_icon_list_on);
                tvMenu03.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 3:
                ivMenu04.setBackgroundResource(R.drawable.botton_icon_notice_on);
                tvMenu04.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
        }

    }

    public void updateTableFragment(int reqNewFragmentIndex) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (reqNewFragmentIndex) {
            case 0:
                if (!homeFragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, homeFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            case 1:
                if (!orderListFragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, orderListFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            case 2:
                if (!customerFragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, customerFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            case 3:
                if (!myPageFragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, myPageFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            default:
                break;
        }
    }
}
