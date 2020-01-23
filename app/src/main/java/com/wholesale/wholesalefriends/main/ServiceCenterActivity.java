package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class ServiceCenterActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageButton btnKakao;
    private ImageButton btnEmail;
    private TextView tvFaq01;
    private LinearLayout llayoutForFaq01;
    private LinearLayout btnFaq01;
    private TextView tvFaq02;
    private LinearLayout llayoutForFaq02;
    private LinearLayout btnFaq02;
    private TextView tvFaq03;
    private LinearLayout llayoutForFaq03;
    private LinearLayout btnFaq03;
    private TextView tvFaq04;
    private LinearLayout llayoutForFaq04;
    private LinearLayout btnFaq04;
    private TextView tvFaq05;
    private LinearLayout llayoutForFaq05;
    private LinearLayout btnFaq05;
    private TextView tvFaq06;
    private LinearLayout llayoutForFaq06;
    private LinearLayout btnFaq06;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_center);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        btnKakao = findViewById(R.id.btnKakao);
        btnEmail = findViewById(R.id.btnEmail);
        tvFaq01 = findViewById(R.id.tvFaq01);
        llayoutForFaq01 = findViewById(R.id.llayoutForFaq01);
        btnFaq01 = findViewById(R.id.btnFaq01);
        tvFaq02 = findViewById(R.id.tvFaq02);
        llayoutForFaq02 = findViewById(R.id.llayoutForFaq02);
        btnFaq02 = findViewById(R.id.btnFaq02);
        tvFaq03 = findViewById(R.id.tvFaq03);
        llayoutForFaq03 = findViewById(R.id.llayoutForFaq03);
        btnFaq03 = findViewById(R.id.btnFaq03);
        tvFaq04 = findViewById(R.id.tvFaq04);
        llayoutForFaq04 = findViewById(R.id.llayoutForFaq04);
        btnFaq04 = findViewById(R.id.btnFaq04);
        tvFaq05 = findViewById(R.id.tvFaq05);
        llayoutForFaq05 = findViewById(R.id.llayoutForFaq05);
        btnFaq05 = findViewById(R.id.btnFaq05);
        tvFaq06 = findViewById(R.id.tvFaq06);
        llayoutForFaq06 = findViewById(R.id.llayoutForFaq06);
        btnFaq06 = findViewById(R.id.btnFaq06);
        recyclerView = findViewById(R.id.recyclerView);
    }
}
