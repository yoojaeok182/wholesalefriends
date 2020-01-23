package com.wholesale.wholesalefriends.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class ProductCommentActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private RecyclerView recyclerView;
    private ImageView ivCheck;
    private EditText edtComment;
    private Button btnWrite;
    private LinearLayout llayoutForComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_comment);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.recyclerView);
        ivCheck = findViewById(R.id.ivCheck);
        edtComment = findViewById(R.id.edtComment);
        btnWrite = findViewById(R.id.btnWrite);
        llayoutForComment = findViewById(R.id.llayoutForComment);
    }
}
