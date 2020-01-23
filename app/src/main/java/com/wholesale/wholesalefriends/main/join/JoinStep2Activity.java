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

/*도매 회원가입*/
public class JoinStep2Activity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivRepresent;
    private ImageView ivCheck01;
    private LinearLayout btnJoin01;
    private ImageView ivMember;
    private ImageView ivCheck02;
    private LinearLayout btnJoin02;
    private Button btnOk;
    private int nMode =-1;
    private int store_type=-1;

    private static JoinStep2Activity instance;
    public static JoinStep2Activity getInstance(){
        return instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_step_02);

        instance = this;
        Intent intent = getIntent();
        if(intent.hasExtra("store_type")){
            store_type = intent.getExtras().getInt("store_type");
        }
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivRepresent = findViewById(R.id.ivRepresent);
        ivCheck01 = findViewById(R.id.ivCheck01);
        btnJoin01 = findViewById(R.id.btnJoin01);
        ivMember = findViewById(R.id.ivMember);
        ivCheck02 = findViewById(R.id.ivCheck02);
        btnJoin02 = findViewById(R.id.btnJoin02);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setEnabled(false);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvTitle.setText("도매 회원가입");

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


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nMode == 1){ //대펴
                    Intent intent = new Intent(JoinStep2Activity.this,JoinStep3Activity.class);
                    intent.putExtra("level", nMode);
                    intent.putExtra("store_type",store_type);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(JoinStep2Activity.this,JoinStep5Activity.class);
                    intent.putExtra("level", nMode);
                    intent.putExtra("store_type",store_type);
                    startActivity(intent);
                }

            }
        });
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
                nMode  = 1;
                ivRepresent.setBackgroundResource(R.drawable.icon_delegate_on);
                btnJoin01.setBackgroundResource(R.drawable.box_on_01);
                ivCheck01.setBackgroundResource(R.drawable.check_on);
                break;
            case 1:
                nMode  = 2;
                ivMember.setBackgroundResource(R.drawable.icon_staff_on);
                btnJoin02.setBackgroundResource(R.drawable.box_on_01);
                ivCheck02.setBackgroundResource(R.drawable.check_on);
                break;
        }
    }
}
