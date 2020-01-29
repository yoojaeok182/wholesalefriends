package com.wholesale.wholesalefriends.main.retail_market;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class ShoppingPaymentActivity extends GroupActivity {


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
    private ImageButton btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_payment);
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
        btnPayment = findViewById(R.id.btnPayment);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("주문하기");

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingPaymentActivity.this,ShoppingPayment2Activity.class);
                startActivity(intent);
            }
        });

    }
}
