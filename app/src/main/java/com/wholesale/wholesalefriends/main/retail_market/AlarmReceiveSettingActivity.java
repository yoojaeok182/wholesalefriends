package com.wholesale.wholesalefriends.main.retail_market;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;

import org.json.JSONObject;


/**
 * 사업자 인증
 */
public class AlarmReceiveSettingActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageofstaff);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText("직원 관리");
    }


    private Handler errHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (!jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(AlarmReceiveSettingActivity.this, false, false);
                        dg.setTitle("공지사항");
                        dg.setMessage(jsonObject.getString("error"));
                        dg.show();

                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };
}
