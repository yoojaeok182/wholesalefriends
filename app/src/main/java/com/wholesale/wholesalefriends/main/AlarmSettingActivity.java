package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.module.view.WheelView;

public class AlarmSettingActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivAlarmToggle;
    private LinearLayout btnAlarmSetting;
    private ImageView ivDisturbToggle;
    private LinearLayout btnDisturbSetting;
    private WheelView wVStart;
    private WheelView wVEnd;
    private LinearLayout btnSystemAlarmSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivAlarmToggle = findViewById(R.id.ivAlarmToggle);
        btnAlarmSetting = findViewById(R.id.btnAlarmSetting);
        ivDisturbToggle = findViewById(R.id.ivDisturbToggle);
        btnDisturbSetting = findViewById(R.id.btnDisturbSetting);
        wVStart = findViewById(R.id.wVStart);
        wVEnd = findViewById(R.id.wVEnd);
        btnSystemAlarmSetting = findViewById(R.id.btnSystemAlarmSetting);
    }
}
