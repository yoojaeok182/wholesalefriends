package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

import co.lujun.androidtagview.TagContainerLayout;

public class SearchActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ckAll;
    private LinearLayout btnCkAll;
    private ImageView ckProduct;
    private LinearLayout btnCkProduct;
    private ImageView ckShopSearch;
    private LinearLayout btnCkShopSearch;
    private EditText tvCategorySearch;
    private TagContainerLayout tagGroup;
    private LinearLayout llayoutForRecommKeword;
    private RecyclerView recyclerView;
    private LinearLayout llayoutFOrSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ckAll = findViewById(R.id.ckAll);
        btnCkAll = findViewById(R.id.btnCkAll);
        ckProduct = findViewById(R.id.ckProduct);
        btnCkProduct = findViewById(R.id.btnCkProduct);
        ckShopSearch = findViewById(R.id.ckShopSearch);
        btnCkShopSearch = findViewById(R.id.btnCkShopSearch);
        tvCategorySearch = findViewById(R.id.tvCategorySearch);
        tagGroup = findViewById(R.id.tagGroup);
        llayoutForRecommKeword = findViewById(R.id.llayoutForRecommKeword);
        recyclerView = findViewById(R.id.recyclerView);
        llayoutFOrSearch = findViewById(R.id.llayoutFOrSearch);
    }
}
