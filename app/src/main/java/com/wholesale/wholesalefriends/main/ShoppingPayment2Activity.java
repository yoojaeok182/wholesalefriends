package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class ShoppingPayment2Activity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivCheck01;
    private LinearLayout btmPaymentType01;
    private ImageView ivCheck02;
    private LinearLayout btmPaymentType02;
    private ImageView ivCheck03;
    private LinearLayout btmPaymentType03;
    private EditText edtName;
    private TextView tvPurchase01;
    private LinearLayout btnPurchase01;
    private TextView tvPurchase02;
    private LinearLayout btnPurchase02;
    private LinearLayout btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_payment2);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivCheck01 = findViewById(R.id.ivCheck01);
        btmPaymentType01 = findViewById(R.id.btmPaymentType01);
        ivCheck02 = findViewById(R.id.ivCheck02);
        btmPaymentType02 = findViewById(R.id.btmPaymentType02);
        ivCheck03 = findViewById(R.id.ivCheck03);
        btmPaymentType03 = findViewById(R.id.btmPaymentType03);
        edtName = findViewById(R.id.edtName);
        tvPurchase01 = findViewById(R.id.tvPurchase01);
        btnPurchase01 = findViewById(R.id.btnPurchase01);
        tvPurchase02 = findViewById(R.id.tvPurchase02);
        btnPurchase02 = findViewById(R.id.btnPurchase02);
        btnSave = findViewById(R.id.btnSave);
    }
}
