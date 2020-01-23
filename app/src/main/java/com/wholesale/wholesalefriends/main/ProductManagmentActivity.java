package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class ProductManagmentActivity extends GroupActivity {


    private RelativeLayout btnClose;
    private TextView tvSelectItemCount;
    private RelativeLayout btnAllCheck;
    private RelativeLayout btnRemove;
    private EditText tvCategorySearch;
    private ImageButton btnManagerment;
    private RecyclerView horRecyclerView;
    private LinearLayout btnAutoRecommAdd;
    private TextView btnHidden;
    private TextView tvTobMenu01;
    private LinearLayout llayoutTopMenu01;
    private TextView tvTobMenu02;
    private LinearLayout llayoutTopMenu02;
    private LinearLayout llayoutTopLine01;
    private LinearLayout llayoutTopLine02;
    private TextView tvSearchItem;
    private LinearLayout btnProcuctManage;
    private LinearLayout btnFilter;
    private RecyclerView recyclerView;
    private LinearLayout btnReWareHousing;
    private LinearLayout btnSoldOut;
    private LinearLayout btnTop30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_managment);
        btnClose = findViewById(R.id.btnClose);
        tvSelectItemCount = findViewById(R.id.tvSelectItemCount);
        btnAllCheck = findViewById(R.id.btnAllCheck);
        btnRemove = findViewById(R.id.btnRemove);
        tvCategorySearch = findViewById(R.id.tvCategorySearch);
        btnManagerment = findViewById(R.id.btnManagerment);
        horRecyclerView = findViewById(R.id.horRecyclerView);
        btnAutoRecommAdd = findViewById(R.id.btnAutoRecommAdd);
        btnHidden = findViewById(R.id.btnHidden);
        tvTobMenu01 = findViewById(R.id.tvTobMenu01);
        llayoutTopMenu01 = findViewById(R.id.llayoutTopMenu01);
        tvTobMenu02 = findViewById(R.id.tvTobMenu02);
        llayoutTopMenu02 = findViewById(R.id.llayoutTopMenu02);
        llayoutTopLine01 = findViewById(R.id.llayoutTopLine01);
        llayoutTopLine02 = findViewById(R.id.llayoutTopLine02);
        tvSearchItem = findViewById(R.id.tvSearchItem);
        btnProcuctManage = findViewById(R.id.btnProcuctManage);
        btnFilter = findViewById(R.id.btnFilter);
        recyclerView = findViewById(R.id.recyclerView);
        btnReWareHousing = findViewById(R.id.btnReWareHousing);
        btnSoldOut = findViewById(R.id.btnSoldOut);
        btnTop30 = findViewById(R.id.btnTop30);
    }
}
