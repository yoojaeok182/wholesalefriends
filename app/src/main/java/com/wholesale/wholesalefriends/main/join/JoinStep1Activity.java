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
import com.wholesale.wholesalefriends.main.LoginActivity;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

/**
 * 도매회원가입 또는 소매 회원가입을 선택한다.
 */
public class JoinStep1Activity extends GroupActivity {

    private RelativeLayout mBtnBack;
    private TextView mTvTitle;
    private ImageView mIvCheck01;
    private LinearLayout mBtnJoin01;
    private ImageView mIvCheck02;
    private LinearLayout mBtnJoin02;
    private Button mBtnOk;


    private int nMode =-1;

    private static JoinStep1Activity instance;
    public static JoinStep1Activity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_step_01);

        instance = this;

        mBtnBack = findViewById(R.id.btnBack);
        mTvTitle = findViewById(R.id.tvTitle);
        mIvCheck01 = findViewById(R.id.ivCheck01);
        mBtnJoin01 = findViewById(R.id.btnJoin01);
        mIvCheck02 = findViewById(R.id.ivCheck02);
        mBtnJoin02 = findViewById(R.id.btnJoin02);
        mBtnOk = findViewById(R.id.btnOk);
        mTvTitle.setText("회원가입");
        mBtnOk.setEnabled(false);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBtnJoin01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect(0);

                mBtnOk.setEnabled(true);
                mBtnOk.setBackgroundResource(R.drawable.btn_02_on);
            }
        });

        mBtnJoin02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect(1);
                mBtnOk.setEnabled(true);
                mBtnOk.setBackgroundResource(R.drawable.btn_02_on);
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                if(nMode == 0){
                     intent = new Intent(JoinStep1Activity.this,JoinStep2Activity.class);


                }else{
                     intent = new Intent(JoinStep1Activity.this,RetailJoinStep02Activity.class);
                }
                intent.putExtra("store_type",nMode);
                startActivity(intent);
            }
        });
    }

    private void setSelect(int pos){
        mBtnJoin01.setBackgroundResource(R.drawable.box_off_01);
        mBtnJoin02.setBackgroundResource(R.drawable.box_off_01);
        mIvCheck01.setBackgroundResource(R.drawable.check_default);
        mIvCheck02.setBackgroundResource(R.drawable.check_default);
        switch (pos){
            case 0:
                nMode  = 0;
                mBtnJoin01.setBackgroundResource(R.drawable.box_on_01);
                mIvCheck01.setBackgroundResource(R.drawable.check_on);
                break;
            case 1:
                nMode  = 1;
                mBtnJoin02.setBackgroundResource(R.drawable.box_on_01);
                mIvCheck02.setBackgroundResource(R.drawable.check_on);
                break;
        }
    }
}
