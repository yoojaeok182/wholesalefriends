package com.wholesale.wholesalefriends.main;

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

public class MyInfoManagementActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivPhoto;
    private RelativeLayout rlyPhoto;
    private ImageView ivStaffType;
    private TextView tvName;
    private EditText edtChangePwd;
    private EditText edtChangePwd2;
    private TextView edtPhone;
    private ImageButton btnPhoneLoad;
    private EditText edtRegNumber;
    private LinearLayout btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_management);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivPhoto = findViewById(R.id.ivPhoto);
        rlyPhoto = findViewById(R.id.rlyPhoto);
        ivStaffType = findViewById(R.id.ivStaffType);
        tvName = findViewById(R.id.tvName);
        edtChangePwd = findViewById(R.id.edtChangePwd);
        edtChangePwd2 = findViewById(R.id.edtChangePwd2);
        edtPhone = findViewById(R.id.edtPhone);
        btnPhoneLoad = findViewById(R.id.btnPhoneLoad);
        edtRegNumber = findViewById(R.id.edtRegNumber);
        btnSave = findViewById(R.id.btnSave);

        tvTitle.setText("내정보 관리");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
