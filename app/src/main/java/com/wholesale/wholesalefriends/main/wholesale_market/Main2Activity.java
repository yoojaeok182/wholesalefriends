package com.wholesale.wholesalefriends.main.wholesale_market;

import android.content.Intent;
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
import com.wholesale.wholesalefriends.main.ProductRegistrationActivity;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.wholesale_market.fragment.CustomerFragment;
import com.wholesale.wholesalefriends.main.wholesale_market.fragment.HomeFragment;
import com.wholesale.wholesalefriends.main.wholesale_market.fragment.MyPageFragment;
import com.wholesale.wholesalefriends.main.wholesale_market.fragment.OrderListFragment;
import com.wholesale.wholesalefriends.module.SharedPreference;

public class Main2Activity extends GroupActivity {

    private RelativeLayout btnMenu;
    private ImageView ivLogo;
    private TextView tvTitle;
    private RelativeLayout btnNotice;
    private RelativeLayout btnProductAdd;
    private ViewPager viewPager;
    private LinearLayout btnMenu01;
    private LinearLayout btnMenu02;
    private LinearLayout btnMenu03;
    private LinearLayout btnMenu04;
    private ImageView ivStaffType;
    private LinearLayout laySetting;
    private TextView tvShopLocale;
    private LinearLayout btnOrderQna;
    private LinearLayout btnProductQna;
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
    private TextView tvStoreName;

    private RelativeLayout btnPMClose;
    private TextView tvSelectItemCount;
    private RelativeLayout btnAllCheck;
    private RelativeLayout btnRemove;
    private ImageView ivAllCheck;

    private LinearLayout btnReWareHousing;
    private LinearLayout btnSoldOut;
    private LinearLayout btnTop30;


    private View llayoutForTitle;
    private View llayoutForProductManagerMode;
    private View llayoutForMenu;
    private View llayoutForProductManagerModeBottom;


    public static boolean isFirstRun;

    private static  Main2Activity instance;
    public static Main2Activity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        instance = this;
        isFirstRun = true;

        btnMenu = findViewById(R.id.btnMenu);
        ivLogo = findViewById(R.id.ivLogo);
        tvTitle = findViewById(R.id.tvTitle);
        btnNotice = findViewById(R.id.btnNotice);
        btnProductAdd = findViewById(R.id.btnProductAdd);
        viewPager = findViewById(R.id.viewPager);
        btnMenu01 = findViewById(R.id.btnMenu01);
        btnMenu02 = findViewById(R.id.btnMenu02);
        btnMenu03 = findViewById(R.id.btnMenu03);
        btnMenu04 = findViewById(R.id.btnMenu04);
        ivStaffType = findViewById(R.id.ivStaffType);
        laySetting = findViewById(R.id.laySetting);
        tvShopLocale = findViewById(R.id.tvShopLocale);
        btnOrderQna = findViewById(R.id.btnOrderQna);
        btnProductQna = findViewById(R.id.btnProductQna);
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
        tvStoreName = findViewById(R.id.tvStoreName);

        llayoutForTitle = findViewById(R.id.llayoutForTitle);
        llayoutForProductManagerMode = findViewById(R.id.llayoutForProductManagerMode);
        llayoutForMenu = findViewById(R.id.llayoutForMenu);
        llayoutForProductManagerModeBottom = findViewById(R.id.llayoutForProductManagerModeBottom);

        btnPMClose = findViewById(R.id.btnPMClose);
        tvSelectItemCount = findViewById(R.id.tvSelectItemCount);
        btnAllCheck = findViewById(R.id.btnAllCheck);
        btnRemove = findViewById(R.id.btnRemove);
        ivAllCheck = findViewById(R.id.ivAllCheck);

        btnReWareHousing = findViewById(R.id.btnReWareHousing);
        btnSoldOut = findViewById(R.id.btnSoldOut);
        btnTop30 = findViewById(R.id.btnTop30);


        homeFragment = new HomeFragment();
        orderListFragment = new OrderListFragment();
        customerFragment = new CustomerFragment();
        myPageFragment = new MyPageFragment();

        init();

        initSetting();

        if(homeFragment!=null)homeFragment.setManagerMode(btnPMClose,tvSelectItemCount,btnAllCheck,ivAllCheck,btnRemove,btnReWareHousing,btnSoldOut,btnTop30);

    }

    private void initSetting(){

        if(SharedPreference.getIntSharedPreference(Main2Activity.this, Constant.CommonKey.level) ==2){  //직원
            ivStaffType.setBackgroundResource(R.drawable.btn_staff);
        }else{
            ivStaffType.setBackgroundResource(R.drawable.btn_staff3);
        }
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

        btnOrderQna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        btnProductQna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        btnShowProductSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMyShopBest30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMyShopNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMyShopServiceCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        laySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void init() {


        btnProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, ProductRegistrationActivity.class);
                startActivity(intent);
            }
        });
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentMenu(0);
                updateTableFragment(0);
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
        setVisibilityProductManager(false);
    }

    public void setVisibilityProductManager(boolean isShow){
        if(isShow){
            llayoutForTitle.setVisibility(View.GONE);
            llayoutForMenu.setVisibility(View.GONE);
            llayoutForProductManagerMode.setVisibility(View.VISIBLE);
            llayoutForProductManagerModeBottom.setVisibility(View.VISIBLE);




            btnPMClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setVisibilityProductManager(false);
                    homeFragment.setHiddenAdapterCheck();
                }
            });

            homeFragment.requestProcuctManager(btnAllCheck,ivAllCheck,tvSelectItemCount,btnReWareHousing,btnSoldOut,btnTop30);

        }else{
            llayoutForTitle.setVisibility(View.VISIBLE);
            llayoutForMenu.setVisibility(View.VISIBLE);
            llayoutForProductManagerMode.setVisibility(View.GONE);
            llayoutForProductManagerModeBottom.setVisibility(View.GONE);
        }

    }

    public  void getHomeRefresh(){
        if(homeFragment!=null){
            homeFragment.getRefresh();
        }
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
        ivLogo.setVisibility(View.GONE);
        tvTitle.setVisibility(View.GONE);
        switch (pos) {
            case 0:
                ivLogo.setVisibility(View.GONE);
                ivMenu01.setBackgroundResource(R.drawable.botton_icon_home_on);
                tvMenu01.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 1:
                ivLogo.setVisibility(View.GONE);
                ivMenu02.setBackgroundResource(R.drawable.botton_icon_category_on);
                tvMenu02.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 2:
                ivLogo.setVisibility(View.GONE);
                ivMenu03.setBackgroundResource(R.drawable.botton_icon_list_on);
                tvMenu03.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 3:
                tvTitle.setVisibility(View.VISIBLE);
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
