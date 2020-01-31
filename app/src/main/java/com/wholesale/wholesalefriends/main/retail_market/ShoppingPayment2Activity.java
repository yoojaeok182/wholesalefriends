package com.wholesale.wholesalefriends.main.retail_market;

import android.os.Bundle;
import android.view.View;
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

    private int nSelect = -1;
    private int nSaip = 2;
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText("기본 결제방법");

        btmPaymentType01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(0);
            }
        });

        btmPaymentType02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(1);
            }
        });

        btmPaymentType03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(2);
            }
        });

        btnPurchase01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPurchase01.setBackgroundResource(R.drawable.l_roundbox_on_01);
                btnPurchase02.setBackgroundResource(R.drawable.r_roundbox_off_01);
                tvPurchase01.setTextColor(getResources().getColor(R.color.color_text_07));
                tvPurchase02.setTextColor(getResources().getColor(R.color.color_text_03));
                nSaip = 1;
            }
        });

        btnPurchase02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPurchase01.setBackgroundResource(R.drawable.l_roundbox_off_01);
                btnPurchase02.setBackgroundResource(R.drawable.r_roundbox_on_01);
                tvPurchase01.setTextColor(getResources().getColor(R.color.color_text_03));
                tvPurchase02.setTextColor(getResources().getColor(R.color.color_text_07));
                nSaip =2;
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void selectItem(int pos){
        btmPaymentType01.setBackgroundResource(R.drawable.box_off_01);
        btmPaymentType02.setBackgroundResource(R.drawable.box_off_01);
        btmPaymentType03.setBackgroundResource(R.drawable.box_off_01);

        ivCheck01.setBackgroundResource(R.drawable.check_default);
        ivCheck02.setBackgroundResource(R.drawable.check_default);
        ivCheck03.setBackgroundResource(R.drawable.check_default);

        switch (pos){
            case 0:
                nSelect =1;
                btmPaymentType01.setBackgroundResource(R.drawable.box_on_01);
                ivCheck01.setBackgroundResource(R.drawable.check_on);
                break;
            case 1:
                nSelect =2;
                btmPaymentType02.setBackgroundResource(R.drawable.box_on_01);
                ivCheck02.setBackgroundResource(R.drawable.check_on);
                break;
            case 2:
                nSelect =3;
                btmPaymentType03.setBackgroundResource(R.drawable.box_on_01);
                ivCheck03.setBackgroundResource(R.drawable.check_on);
                break;
        }

    }
}
