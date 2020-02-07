package com.wholesale.wholesalefriends.main;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.module.AppData;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.module.model.LockTimeModel;
import com.wholesale.wholesalefriends.module.util.Util;
import com.wholesale.wholesalefriends.module.view.WheelView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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

    private LinearLayout llayoutForInterrupt;

    private int returnIndex;
    private String retunValue;

    private int returnIndex2;
    private String retunValue2;

    private TimePickerDialog timePickerDialog;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private int startHour, startMinute, endHour, endMinute, index = 0;

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
        btnSystemAlarmSetting = findViewById(R.id.btnSystemAlarmSetting);
        llayoutForInterrupt = findViewById(R.id.llayoutForInterrupt);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        tvTitle.setText("알림/방해금지모드 설정");


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAlarmSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreference.getBooleanSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_show)) {
                    ivAlarmToggle.setBackgroundResource(R.drawable.toggle_off);
                    SharedPreference.putSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_show, false);
                    llayoutForInterrupt.setVisibility(View.GONE);
                } else {
                    ivAlarmToggle.setBackgroundResource(R.drawable.toggle_on);
                    SharedPreference.putSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_show, true);
                    llayoutForInterrupt.setVisibility(View.VISIBLE);
                }
            }
        });


        btnDisturbSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initInterruptSetting();


            }
        });
        if (SharedPreference.getBooleanSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_interrupt_setting)) {
            ivDisturbToggle.setBackgroundResource(R.drawable.toggle_on);
            if(AppData.getInstance()!=null &&AppData.getInstance().getLockTime()!= null&& AppData.getInstance().getLockTime().isLockTime() ){
                tvStartTime.setText(Util.updateDisplay(AppData.getInstance().getLockTime().getStartHour(),
                        AppData.getInstance().getLockTime().getStartMin()));

                tvEndTime.setText(Util.updateDisplay(AppData.getInstance().getLockTime().getEndHour(),
                        AppData.getInstance().getLockTime().getEndMin()));

            }else{
                tvStartTime.setText(Util.updateDisplay(0, 0) );
                tvEndTime.setText( Util.updateDisplay(0, 0));

            }

        }else{
            ivDisturbToggle.setBackgroundResource(R.drawable.toggle_off);
        }

    }

    private void initInterruptSetting() {
        if (SharedPreference.getBooleanSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_interrupt_setting)) {


            AppData.getInstance().setLockTime(null);
            tvStartTime.setText(Util.updateDisplay(0, 0) );
            tvEndTime.setText( Util.updateDisplay(0, 0));
            ivDisturbToggle.setBackgroundResource(R.drawable.toggle_off);
            SharedPreference.putSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_interrupt_setting,false);

        } else {

            Context context = new ContextThemeWrapper(this, R.style.AppCompat_Translucent_Notitle);


            timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startHour = hourOfDay;
                    startMinute = minute;

                    timePickerDialog.dismiss();
                    timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            endHour = hourOfDay;
                            endMinute = minute;

                            if ((endHour * 60 + endMinute) <= (startHour * 60 + startMinute)) {
                                Toast.makeText(AlarmSettingActivity.this, "종료시간은 시작시간보다 빠를수 없습니다.", Toast.LENGTH_SHORT).show();

                                return;
                            }

                            LockTimeModel lockTimeModel = new LockTimeModel();
                            lockTimeModel.setLockTime(true);
                            lockTimeModel.setStartHour(startHour);
                            lockTimeModel.setStartMin(startMinute);
                            lockTimeModel.setEndHour(endHour);
                            lockTimeModel.setEndMin(endMinute);
                            AppData.getInstance().setLockTime(lockTimeModel);

                            if(AppData.getInstance()!=null &&AppData.getInstance().getLockTime()!= null&& AppData.getInstance().getLockTime().isLockTime() ){
                                tvStartTime.setText(Util.updateDisplay(startHour, startMinute) );
                                tvEndTime.setText( Util.updateDisplay(endHour, endMinute));
                            }else{
                                tvStartTime.setText(Util.updateDisplay(0, 0) );
                                tvEndTime.setText( Util.updateDisplay(0, 0));

                            }
                            ivDisturbToggle.setBackgroundResource(R.drawable.toggle_on);

                            SharedPreference.putSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_interrupt_setting,true);

                            SharedPreference.putSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_interrupt_date_start,tvStartTime.getText().toString());
                            SharedPreference.putSharedPreference(AlarmSettingActivity.this, Constant.CommonKey.alarm_interrupt_date_end,tvEndTime.getText().toString());

                            timePickerDialog.dismiss();
                        }
                    }, Calendar.HOUR_OF_DAY, Calendar.MINUTE,true);
                    timePickerDialog.setTitle("종료시간");



                    timePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ivDisturbToggle.setBackgroundResource(R.drawable.toggle_off);
                            timePickerDialog.dismiss();
                        }
                    });
                    timePickerDialog.show();
                }


            }, Calendar.HOUR_OF_DAY, Calendar.MINUTE,true);


            timePickerDialog.setTitle("시작시간");


            timePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE,"취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ivDisturbToggle.setBackgroundResource(R.drawable.toggle_off);
                    timePickerDialog.dismiss();
                }
            });
            timePickerDialog.show();
        }


    }
}
