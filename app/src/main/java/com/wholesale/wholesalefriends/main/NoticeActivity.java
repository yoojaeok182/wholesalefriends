package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.NoticeAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.data.NoticeListData;
import com.wholesale.wholesalefriends.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;

public class NoticeActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private RecyclerView recyclerView;

    private ArrayList<NoticeListData>list = new ArrayList<>();
    private NoticeAdapter noticeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.recyclerView);

        noticeAdapter = new NoticeAdapter(this, list);
        tvTitle.setText("공지사항");

        init();
    }

    private void init(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
        recyclerView.setAdapter(noticeAdapter);

    }
}
