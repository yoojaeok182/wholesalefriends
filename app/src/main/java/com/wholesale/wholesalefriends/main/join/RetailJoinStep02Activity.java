package com.wholesale.wholesalefriends.main.join;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;

public class RetailJoinStep02Activity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivTopMenu01;
    private ImageView ivTopMenuCheck01;
    private LinearLayout btnTobMenu01;
    private ImageView ivTopMenu02;
    private ImageView ivTopMenuCheck02;
    private LinearLayout btnTopMenu02;
    private ImageView ivRepresent;
    private ImageView ivCheck01;
    private LinearLayout btnJoin01;
    private ImageView ivMember;
    private ImageView ivCheck02;
    private LinearLayout btnJoin02;
    private Button btnOk;

    private int store_type=-1;
    private int level = 0;
    private int store_onoﬀ = -1;
    private static RetailJoinStep02Activity instance;
    public static RetailJoinStep02Activity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_join_step_02);

        instance = this;
        Intent intent = getIntent();
        if(intent.hasExtra("store_type")){
            store_type = intent.getExtras().getInt("store_type");
        }

        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivTopMenu01 = findViewById(R.id.ivTopMenu01);
        ivTopMenuCheck01 = findViewById(R.id.ivTopMenuCheck01);
        btnTobMenu01 = findViewById(R.id.btnTobMenu01);
        ivTopMenu02 = findViewById(R.id.ivTopMenu02);
        ivTopMenuCheck02 = findViewById(R.id.ivTopMenuCheck02);
        btnTopMenu02 = findViewById(R.id.btnTopMenu02);
        ivRepresent = findViewById(R.id.ivRepresent);
        ivCheck01 = findViewById(R.id.ivCheck01);
        btnJoin01 = findViewById(R.id.btnJoin01);
        ivMember = findViewById(R.id.ivMember);
        ivCheck02 = findViewById(R.id.ivCheck02);
        btnJoin02 = findViewById(R.id.btnJoin02);
        btnOk = findViewById(R.id.btnOk);

        tvTitle.setText("소매 회원가입");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(store_onoﬀ == -1){

                    CommonAlertDialog dg = new CommonAlertDialog(RetailJoinStep02Activity.this,false,false);
                    dg.setMessage("매장 종류를 선택해주세요.");
                    dg.show();
                    return;
                }

                if(level == 1){ //대펴
                    Intent intent = new Intent(RetailJoinStep02Activity.this,JoinStepAddrInputActivity.class);
                    intent.putExtra("store_type", store_type);
                    intent.putExtra("store_onoﬀ", store_onoﬀ);
                    intent.putExtra("level", level);

                    startActivity(intent);
                }else{
                    Intent intent = new Intent(RetailJoinStep02Activity.this,JoinStep5Activity.class);
                    intent.putExtra("level", level);
                    intent.putExtra("store_type",store_type);
                    startActivity(intent);
                }


            }
        });

        btnTobMenu01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopSelect(0);
            }
        });

        btnTopMenu02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopSelect(1);
            }
        });

        btnJoin01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setSelect(0);

                btnOk.setEnabled(true);
                btnOk.setBackgroundResource(R.drawable.btn_02_on);
            }
        });

        btnJoin02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect(1);
                btnOk.setEnabled(true);
                btnOk.setBackgroundResource(R.drawable.btn_02_on);
            }
        });

    }
    private void setTopSelect(int pos){
        btnTobMenu01.setBackgroundResource(R.drawable.box_off_01);
        btnTopMenu02.setBackgroundResource(R.drawable.box_off_01);
        ivTopMenuCheck01.setBackgroundResource(R.drawable.check_default);
        ivTopMenuCheck02.setBackgroundResource(R.drawable.check_default);
        ivTopMenu01.setBackgroundResource(R.drawable.icon_internet);
        ivTopMenu02.setBackgroundResource(R.drawable.icon_offline);
        switch (pos){
            case 0:
                store_onoﬀ  = 1;
                ivTopMenu01.setBackgroundResource(R.drawable.icon_internet_on);
                btnTobMenu01.setBackgroundResource(R.drawable.box_on_01);
                ivTopMenuCheck01.setBackgroundResource(R.drawable.check_on);
                break;
            case 1:
                store_onoﬀ  = 2;
                ivTopMenu02.setBackgroundResource(R.drawable.icon_offline_on);
                btnTopMenu02.setBackgroundResource(R.drawable.box_on_01);
                ivTopMenuCheck02.setBackgroundResource(R.drawable.check_on);
                break;
        }
    }
    private void setSelect(int pos){
        btnJoin01.setBackgroundResource(R.drawable.box_off_01);
        btnJoin02.setBackgroundResource(R.drawable.box_off_01);
        ivCheck01.setBackgroundResource(R.drawable.check_default);
        ivCheck02.setBackgroundResource(R.drawable.check_default);
        ivRepresent.setBackgroundResource(R.drawable.icon_delegate);
        ivMember.setBackgroundResource(R.drawable.icon_staff);
        switch (pos){
            case 0:
                level  = 1;
                ivRepresent.setBackgroundResource(R.drawable.icon_delegate_on);
                btnJoin01.setBackgroundResource(R.drawable.box_on_01);
                ivCheck01.setBackgroundResource(R.drawable.check_on);
                break;
            case 1:
                level  = 2;
                ivMember.setBackgroundResource(R.drawable.icon_staff_on);
                btnJoin02.setBackgroundResource(R.drawable.box_on_01);
                ivCheck02.setBackgroundResource(R.drawable.check_on);
                break;
        }
    }
}
