package com.wholesale.wholesalefriends.main.join;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.LoginActivity;
import com.wholesale.wholesalefriends.main.Main2Activity;
import com.wholesale.wholesalefriends.main.MainActivity;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;

import org.json.JSONObject;

public class JoinStep6Activity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private TextView tvStoreName;
    private TextView tvErrorMsg01;
    private EditText edtCeoeName;
    private TextView tvErrorMsg02;
    private EditText edtId;
    private TextView tvErrorMsg03;
    private EditText edtPwd;
    private TextView tvErrorMsg04;
    private EditText edtPwd2;
    private TextView tvErrorMsg05;
    private EditText edtPhone;
    private TextView tvErrorMsg06;
    private ImageView ivTerms01Check;
    private TextView tvTerms01;
    private LinearLayout btnTerms01;
    private ImageView ivTerms02Check;
    private TextView tvTerms02;
    private LinearLayout btnTerms02;
    private TextView tvErrorMsg07;
    private Button btnOk;


    private int store_type=-1;
    private int level = 0;
    private int store_id =-1;
    private String store_name;
    private boolean isChecked01, isChecked02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_step_06);

        Intent intent = getIntent();
        if (intent.hasExtra("level")) {
            level = intent.getExtras().getInt("level");
        }
        if(intent.hasExtra("store_type")){
            store_type = intent.getExtras().getInt("store_type");
        }
        if(intent.hasExtra("store_id")){
            store_id = intent.getExtras().getInt("store_id");
        }

        if(intent.hasExtra("store_name")){
            store_name = intent.getExtras().getString("store_name");
        }

        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        tvStoreName = findViewById(R.id.tvStoreName);
        tvErrorMsg01 = findViewById(R.id.tvErrorMsg01);
        edtCeoeName = findViewById(R.id.edtCeoeName);
        tvErrorMsg02 = findViewById(R.id.tvErrorMsg02);
        edtId = findViewById(R.id.edtId);
        tvErrorMsg03 = findViewById(R.id.tvErrorMsg03);
        edtPwd = findViewById(R.id.edtPwd);
        tvErrorMsg04 = findViewById(R.id.tvErrorMsg04);
        edtPwd2 = findViewById(R.id.edtPwd2);
        tvErrorMsg05 = findViewById(R.id.tvErrorMsg05);
        edtPhone = findViewById(R.id.edtPhone);
        tvErrorMsg06 = findViewById(R.id.tvErrorMsg06);
        ivTerms01Check = findViewById(R.id.ivTerms01Check);
        tvTerms01 = findViewById(R.id.tvTerms01);
        btnTerms01 = findViewById(R.id.btnTerms01);
        ivTerms02Check = findViewById(R.id.ivTerms02Check);
        tvTerms02 = findViewById(R.id.tvTerms02);
        btnTerms02 = findViewById(R.id.btnTerms02);
        tvErrorMsg07 = findViewById(R.id.tvErrorMsg07);
        btnOk = findViewById(R.id.btnOk);

        if (store_type == 1) {
            tvTitle.setText("도매 회원가입");
        } else {
            tvTitle.setText("소매 회원가입");
        }


        tvStoreName.setText(store_name);

        ivTerms01Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivTerms01Check.setBackgroundResource(R.drawable.check_on);
                isChecked01 = true;
            }
        });
        ivTerms02Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivTerms01Check.setBackgroundResource(R.drawable.check_on);
                isChecked02 = true;
            }
        });

        tvTerms01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        tvTerms02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInput()) {
                    final CommonAlertDialog dg = new CommonAlertDialog(JoinStep6Activity.this, true, true);
                    dg.setTitle("회원가입");
                    dg.setMessage("입력하신 내용으로 회원가입을\n진행합니다.");
                    dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (dg.isOk()) {
                                String ceoName = edtCeoeName.getText().toString();
                                String strId = edtId.getText().toString();
                                String strPwd = edtPwd.getText().toString();
                                String strStore_id = store_id+"";
                                String strPhoneNumber = edtPhone.getText().toString();

                                API.join(JoinStep6Activity.this, store_type,  level,  ceoName,  strId,  strPwd,
                                        strPhoneNumber,  null,  null,  null,
                                        null,  null,  null,
                                        null, null,  strStore_id,  resultHandler, errHandler );
                            }
                        }
                    });
                }
            }
        });


        edtCeoeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
        edtPwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });

        edtPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
        edtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
    }

    private boolean checkInput() {
        String ceoName = edtCeoeName.getText().toString();
        String strId = edtId.getText().toString();
        String strPwd = edtPwd.getText().toString();
        String strPwd2 = edtPwd2.getText().toString();
        String strPhoneNumber = edtPhone.getText().toString();
        tvErrorMsg01.setVisibility(View.GONE);
        tvErrorMsg02.setVisibility(View.GONE);
        tvErrorMsg03.setVisibility(View.GONE);
        tvErrorMsg04.setVisibility(View.GONE);
        tvErrorMsg05.setVisibility(View.GONE);
        tvErrorMsg06.setVisibility(View.GONE);
        tvErrorMsg07.setVisibility(View.GONE);

        if ((ceoName != null && ceoName.length() == 0)) {
            tvErrorMsg02.setVisibility(View.VISIBLE);
            return false;
        }

        if ((strId != null && strId.length() == 0)) {
            tvErrorMsg03.setVisibility(View.VISIBLE);
            return false;
        }

        if ((strPwd != null && strPwd.length() == 0)) {
            tvErrorMsg04.setVisibility(View.VISIBLE);
            return false;
        }

        if ((strPwd2 != null && strPwd2.length() == 0)) {
            tvErrorMsg05.setVisibility(View.VISIBLE);
            return false;
        }

        if ((strPhoneNumber != null && strPhoneNumber.length() == 0)) {
            tvErrorMsg06.setVisibility(View.VISIBLE);
            return false;
        }


        if (!isChecked01 ||!isChecked02 ) {
            tvErrorMsg07.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private void checkRegistBtn() {
        String ceoName = edtCeoeName.getText().toString();
        String strId = edtId.getText().toString();
        String strPwd = edtPwd.getText().toString();
        String strPwd2 = edtPwd2.getText().toString();
        String strPhoneNumber = edtPhone.getText().toString();
        if ((ceoName != null && ceoName.length() == 0) || (strId != null && strId.length() == 0) || (strPwd != null && strPwd.length() == 0) ||
                (strPwd2 != null && strPwd2.length() == 0) || (strPhoneNumber != null && strPhoneNumber.length() == 0)) {
            btnOk.setBackgroundResource(R.drawable.btn_02);
            btnOk.setEnabled(false);
        } else {
            btnOk.setEnabled(true);
            btnOk.setBackgroundResource(R.drawable.btn_02_on);
        }

    }

    private Handler resultHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    SharedPreference.putSharedPreference(JoinStep6Activity.this, Constant.CommonKey.user_no,jsonObject.getString("user_id"));
                    SharedPreference.putSharedPreference(JoinStep6Activity.this, Constant.CommonKey.user_id,edtId.getText().toString());
                    SharedPreference.putSharedPreference(JoinStep6Activity.this, Constant.CommonKey.user_pwd,edtPwd.getText().toString());
                    Integer store_type = null;
                    Integer level = null;
                    if(!jsonObject.isNull("store_type")){
                        store_type = jsonObject.getInt("store_type");
                    }
                    if(!jsonObject.isNull("level")){
                        level = jsonObject.getInt("level");
                    }
                    Intent intent = null;
                    if(store_type!=null &&store_type ==2){
                        intent = new Intent(JoinStep6Activity.this, MainActivity.class);
                    }else{
                        intent = new Intent(JoinStep6Activity.this, Main2Activity.class);
                    }
                    startActivity(intent);
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };
    private Handler errHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){

                    if(jsonObject.getString("error")!=null && jsonObject.getString("error").length()>0){
                        final CommonAlertDialog dg = new CommonAlertDialog(JoinStep6Activity.this,false,true);
                        dg.setTitle("계정 정보 확인");
                        dg.setMessage(jsonObject.getString("error"));
                        dg.show();

                    }
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };
}
