package com.wholesale.wholesalefriends.main.retail_market;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.retail_market.fragment.RetailCategoryFragment;
import com.wholesale.wholesalefriends.main.retail_market.fragment.RetailCustomerFragment;
import com.wholesale.wholesalefriends.main.retail_market.fragment.RetailHomeFragment;
import com.wholesale.wholesalefriends.main.retail_market.fragment.RetailNoticeFragment;

public class MainActivity extends GroupActivity {

    private ImageView ivLogo;
    private TextView tvTitle;
    private RelativeLayout btnSearch;
    private RelativeLayout btnCart;
    private RelativeLayout btnMyPage;
    private LinearLayout layoutcontainer;
    private LinearLayout btnMenu01;
    private LinearLayout btnMenu02;
    private LinearLayout btnMenu03;
    private LinearLayout btnMenu04;
    private ImageView ivMenu01;
    private TextView tvMenu01;
    private ImageView ivMenu02;
    private TextView tvMenu02;
    private ImageView ivMenu03;
    private TextView tvMenu03;
    private ImageView ivMenu04;
    private TextView tvMenu04;

    private RetailHomeFragment retailHomeFragment;
    private RetailCategoryFragment retailCategoryFragment;
    private RetailCustomerFragment retailCustomerFragment;
    private RetailNoticeFragment retailNoticeFragment;

    public static boolean isFirstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isFirstRun = true;
        ivLogo = findViewById(R.id.ivLogo);
        tvTitle = findViewById(R.id.tvTitle);
        btnSearch = findViewById(R.id.btnSearch);
        btnCart = findViewById(R.id.btnCart);
        btnMyPage = findViewById(R.id.btnMyPage);
        layoutcontainer = findViewById(R.id.layoutcontainer);
        ivMenu01 = findViewById(R.id.ivMenu01);
        tvMenu01 = findViewById(R.id.tvMenu01);
        btnMenu01 = findViewById(R.id.btnMenu01);
        ivMenu02 = findViewById(R.id.ivMenu02);
        tvMenu02 = findViewById(R.id.tvMenu02);
        btnMenu02 = findViewById(R.id.btnMenu02);
        ivMenu03 = findViewById(R.id.ivMenu03);
        tvMenu03 = findViewById(R.id.tvMenu03);
        btnMenu03 = findViewById(R.id.btnMenu03);
        ivMenu04 = findViewById(R.id.ivMenu04);
        tvMenu04 = findViewById(R.id.tvMenu04);
        btnMenu04 = findViewById(R.id.btnMenu04);

        retailHomeFragment = new RetailHomeFragment();
        retailCategoryFragment = new RetailCategoryFragment();
        retailCustomerFragment = new RetailCustomerFragment();
        retailNoticeFragment = new RetailNoticeFragment();


        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentMenu(0);
                updateTableFragment(0);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShoppingBasketActivity.class);
                startActivity(intent);
            }
        });

        btnMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPage1Activity.class);
                startActivity(intent);
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

    private void setCurrentMenu(int pos) {

        ivMenu01.setBackgroundResource(R.drawable.btn_main_menu_01_select);
        ivMenu02.setBackgroundResource(R.drawable.btn_main_menu_02_select);
        ivMenu03.setBackgroundResource(R.drawable.btn_main_menu_03_select);
        ivMenu04.setBackgroundResource(R.drawable.btn_main_menu_04_select);


        tvMenu01.setTextColor(getResources().getColor(R.color.btn_textcolor_01_select));
        tvMenu02.setTextColor(getResources().getColor(R.color.btn_textcolor_01_select));
        tvMenu03.setTextColor(getResources().getColor(R.color.btn_textcolor_01_select));
        tvMenu04.setTextColor(getResources().getColor(R.color.btn_textcolor_01_select));

        switch (pos)
        {
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
                if (!retailHomeFragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, retailHomeFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            case 1:
                if (!retailCategoryFragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, retailCategoryFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            case 2:
                if (!retailCustomerFragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, retailCustomerFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            case 3:
                if (!retailNoticeFragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, retailNoticeFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            default:
                break;
        }
    }
}
