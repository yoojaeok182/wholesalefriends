package com.wholesale.wholesalefriends.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class MyPage2Activity extends GroupActivity implements View.OnClickListener {


    private Button btnManagerment;
    private ImageView ivAlarmToggle;
    private LinearLayout btnAlarmSetting;
    private LinearLayout btnMyShopIntroduce;
    private LinearLayout btnKakao;
    private LinearLayout btnNotice;
    private LinearLayout btnSerViceCenter;
    private TextView tvVersion;
    private LinearLayout btnAppInfo;
    private LinearLayout btnClauseTerms;
    private LinearLayout btnPersonalTerms;
    private LinearLayout btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_2);
        btnManagerment = findViewById(R.id.btnManagerment);
        ivAlarmToggle = findViewById(R.id.ivAlarmToggle);
        btnAlarmSetting = findViewById(R.id.btnAlarmSetting);
        btnMyShopIntroduce = findViewById(R.id.btnMyShopIntroduce);
        btnKakao = findViewById(R.id.btnKakao);
        btnNotice = findViewById(R.id.btnNotice);
        btnSerViceCenter = findViewById(R.id.btnSerViceCenter);
        tvVersion = findViewById(R.id.tvVersion);
        btnAppInfo = findViewById(R.id.btnAppInfo);
        btnClauseTerms = findViewById(R.id.btnClauseTerms);
        btnPersonalTerms = findViewById(R.id.btnPersonalTerms);
        btnLogout = findViewById(R.id.btnLogout);

        initBtn();
    }

    private void initBtn(){
        btnManagerment.setOnClickListener(this);
        btnAlarmSetting.setOnClickListener(this);
        btnMyShopIntroduce.setOnClickListener(this);
        btnKakao.setOnClickListener(this);
        btnNotice.setOnClickListener(this);
        btnSerViceCenter.setOnClickListener(this);
        btnAppInfo.setOnClickListener(this);
        btnClauseTerms.setOnClickListener(this);
        btnPersonalTerms.setOnClickListener(this);
        btnPersonalTerms.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnManagerment:
                break;
            case R.id.btnAlarmSetting:
                intent = new Intent(this,AlarmSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMyShopIntroduce:
                break;

            case R.id.btnNotice:
                break;
            case R.id.btnSerViceCenter:
                intent= new Intent(this,ServiceCenterActivity.class);

                break;
            case R.id.btnAppInfo:
                break;
            case R.id.btnClauseTerms:
                break;
            case R.id.btnPersonalTerms:
                break;
            case R.id.btnLogout:
                break;

        }
       if(intent!=null) startActivity(intent);
    }
}
