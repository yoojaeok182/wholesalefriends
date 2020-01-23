package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class DetailProduct2Activity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivIndigator01;
    private ImageView ivIndigator02;
    private ImageView ivIndigator03;
    private ImageView ivIndigator04;
    private ImageView ivIndigator05;
    private TextView tvProductInfo;
    private TextView tvProductName;
    private TextView tvProductPrice;
    private ImageView btnShare;
    private Button btnModify;
    private Button btnDelete;
    private Button btnSoldOut;
    private TextView tvColor;
    private TextView tvSize;
    private TextView tvMix;
    private EditText tvProducingCountry;
    private TextView tvInfo;
    private ImageView ivThick01;
    private LinearLayout btnThick01;
    private ImageView ivThick02;
    private LinearLayout btnThick02;
    private ImageView ivThick031;
    private LinearLayout btnThick03;
    private ImageView ivTrans01;
    private LinearLayout btnTrans01;
    private ImageView ivTrans02;
    private LinearLayout btnTrans02;
    private ImageView ivTrans03;
    private LinearLayout btnTrans03;
    private ImageView ivStretch01;
    private LinearLayout btnStretch01;
    private ImageView ivStretch02;
    private LinearLayout btnStretch02;
    private ImageView ivStretch03;
    private LinearLayout btnStretch03;
    private ImageView ivStretch04;
    private LinearLayout btnStretch04;
    private ImageView ivFabric01;
    private LinearLayout btnFabric01;
    private ImageView ivFabric02;
    private LinearLayout btnFabric02;
    private RecyclerView recyclerView;
    private LinearLayout llayoutForGuide;
    private ImageView ivCheck;
    private EditText edtComment;
    private Button btnWrite;
    private LinearLayout llayoutForComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product2);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivIndigator01 = findViewById(R.id.ivIndigator01);
        ivIndigator02 = findViewById(R.id.ivIndigator02);
        ivIndigator03 = findViewById(R.id.ivIndigator03);
        ivIndigator04 = findViewById(R.id.ivIndigator04);
        ivIndigator05 = findViewById(R.id.ivIndigator05);
        tvProductInfo = findViewById(R.id.tvProductInfo);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        btnShare = findViewById(R.id.btnShare);
        btnModify = findViewById(R.id.btnModify);
        btnDelete = findViewById(R.id.btnDelete);
        btnSoldOut = findViewById(R.id.btnSoldOut);
        tvColor = findViewById(R.id.tvColor);
        tvSize = findViewById(R.id.tvSize);
        tvMix = findViewById(R.id.tvMix);
        tvProducingCountry = findViewById(R.id.tvProducingCountry);
        tvInfo = findViewById(R.id.tvInfo);
        ivThick01 = findViewById(R.id.ivThick01);
        btnThick01 = findViewById(R.id.btnThick01);
        ivThick02 = findViewById(R.id.ivThick02);
        btnThick02 = findViewById(R.id.btnThick02);
        ivThick031 = findViewById(R.id.ivThick031);
        btnThick03 = findViewById(R.id.btnThick03);
        ivTrans01 = findViewById(R.id.ivTrans01);
        btnTrans01 = findViewById(R.id.btnTrans01);
        ivTrans02 = findViewById(R.id.ivTrans02);
        btnTrans02 = findViewById(R.id.btnTrans02);
        ivTrans03 = findViewById(R.id.ivTrans03);
        btnTrans03 = findViewById(R.id.btnTrans03);
        ivStretch01 = findViewById(R.id.ivStretch01);
        btnStretch01 = findViewById(R.id.btnStretch01);
        ivStretch02 = findViewById(R.id.ivStretch02);
        btnStretch02 = findViewById(R.id.btnStretch02);
        ivStretch03 = findViewById(R.id.ivStretch03);
        btnStretch03 = findViewById(R.id.btnStretch03);
        ivStretch04 = findViewById(R.id.ivStretch04);
        btnStretch04 = findViewById(R.id.btnStretch04);
        ivFabric01 = findViewById(R.id.ivFabric01);
        btnFabric01 = findViewById(R.id.btnFabric01);
        ivFabric02 = findViewById(R.id.ivFabric02);
        btnFabric02 = findViewById(R.id.btnFabric02);
        recyclerView = findViewById(R.id.recyclerView);
        llayoutForGuide = findViewById(R.id.llayoutForGuide);
        ivCheck = findViewById(R.id.ivCheck);
        edtComment = findViewById(R.id.edtComment);
        btnWrite = findViewById(R.id.btnWrite);
        llayoutForComment = findViewById(R.id.llayoutForComment);
    }
}
