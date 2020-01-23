package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class ShoppingBasketActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivCheck;
    private LinearLayout btnAllCheck;
    private Button btnDelete;
    private RecyclerView recyclerView;
    private LinearLayout btnAdd;
    private LinearLayout btnOrder;
    private LinearLayout llayoutForData;
    private LinearLayout llayoutForNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivCheck = findViewById(R.id.ivCheck);
        btnAllCheck = findViewById(R.id.btnAllCheck);
        btnDelete = findViewById(R.id.btnDelete);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        btnOrder = findViewById(R.id.btnOrder);
        llayoutForData = findViewById(R.id.llayoutForData);
        llayoutForNoData = findViewById(R.id.llayoutForNoData);
    }
}
