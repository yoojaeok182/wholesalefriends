package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class MyPage1Activity extends GroupActivity implements View.OnClickListener {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageButton btnPayment;
    private TextView tvName;
    private TextView tvId;
    private TextView tvStatus;
    private LinearLayout btnMyInfo;
    private LinearLayout btnLogOut;
    private LinearLayout btnOrderList;
    private LinearLayout btnInterestProduct;
    private LinearLayout btnService;
    private LinearLayout btnMenu01;
    private LinearLayout btnMenu02;
    private LinearLayout btnMenu03;
    private LinearLayout btnMenu04;
    private LinearLayout btnMenu05;
    private LinearLayout btnMenu06;
    private LinearLayout btnMenu07;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_1);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        btnPayment = findViewById(R.id.btnPayment);
        tvName = findViewById(R.id.tvName);
        tvId = findViewById(R.id.tvId);
        tvStatus = findViewById(R.id.tvStatus);
        btnMyInfo = findViewById(R.id.btnMyInfo);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnOrderList = findViewById(R.id.btnOrderList);
        btnInterestProduct = findViewById(R.id.btnInterestProduct);
        btnService = findViewById(R.id.btnService);
        btnMenu01 = findViewById(R.id.btnMenu01);
        btnMenu02 = findViewById(R.id.btnMenu02);
        btnMenu03 = findViewById(R.id.btnMenu03);
        btnMenu04 = findViewById(R.id.btnMenu04);
        btnMenu05 = findViewById(R.id.btnMenu05);
        btnMenu06 = findViewById(R.id.btnMenu06);
        btnMenu07 = findViewById(R.id.btnMenu07);

        initBtn();
    }

    private void initBtn(){

        btnMyInfo.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        btnOrderList.setOnClickListener(this);
        btnInterestProduct.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnMenu01.setOnClickListener(this);
        btnMenu02.setOnClickListener(this);
        btnMenu03.setOnClickListener(this);
        btnMenu04.setOnClickListener(this);
        btnMenu05.setOnClickListener(this);
        btnMenu06.setOnClickListener(this);
        btnMenu07.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMyInfo:
                break;
            case R.id.btnLogOut:
                break;
            case R.id.btnOrderList:
                break;

            case R.id.btnInterestProduct:
                break;
            case R.id.btnService:
                break;
            case R.id.btnMenu01:
                break;
            case R.id.btnMenu02:
                break;
            case R.id.btnMenu03:
                break;
            case R.id.btnMenu04:
                break;
            case R.id.btnMenu05:
                break;
            case R.id.btnMenu06:
                break;
            case R.id.btnMenu07:
                break;

        }
    }
}
