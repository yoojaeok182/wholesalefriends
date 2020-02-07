package com.wholesale.wholesalefriends.main.wholesale_market;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.module.util.Util;

public class AppInfoActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private TextView tvCurrentVersion;
    private Button btnUpdate;
    private TextView tvUpdateComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        tvCurrentVersion = findViewById(R.id.tvCurrentVersion);
        btnUpdate = findViewById(R.id.btnUpdate);
        tvUpdateComment = findViewById(R.id.tvUpdateComment);

        tvTitle.setText("앱 버전 정보");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tvCurrentVersion.setText(Util.version(this));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("market://details?id=com.wholesale.wholesalefriends"));
                startActivity(intent1);
            }
        });
    }
}
