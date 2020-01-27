package com.wholesale.wholesalefriends.main.join;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;

public class JoinStepAddrInputActivity extends GroupActivity {


    private static JoinStepAddrInputActivity instance;
    private RelativeLayout btnBack;
    private TextView tvTitle;
    private TextView tvSelectAddr1;
    private EditText edtAddr2;
    private TextView tvErrorMsg01;
    private EditText edtSiteName;
    private TextView tvErrorMsg02;
    private EditText edSiteUrl;
    private TextView tvErrorMsg03;
    private Button btnOk;
    private EditText edtAddr1;


    private int level = 0;
    private int store_type = -1;
    private int store_onoﬀ = -1;
    private String strStore;
    private String strFloor;
    private String strRoomNo;

    private int store_id;
    private int nWholesaleMode = -1;
    private LinearLayout llayoutForSite;

    public static JoinStepAddrInputActivity getInstance() {
        return instance;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_join_step_03);

        instance = this;
        Intent intent = getIntent();
        if (intent.hasExtra("store_id")) {
            store_id = intent.getExtras().getInt("store_id");
        }

        if (intent.hasExtra("level")) {
            level = intent.getExtras().getInt("level");
        }

        if (intent.hasExtra("store_onoﬀ")) {
            store_onoﬀ = intent.getExtras().getInt("store_onoﬀ");
        }
        if (intent.hasExtra("store_type")) {
            store_type = intent.getExtras().getInt("store_type");
        }

        if (intent.hasExtra("store")) {
            strStore = intent.getExtras().getString("store");
        }

        if (intent.hasExtra("floor")) {
            strFloor = intent.getExtras().getString("floor");
        }
        if (intent.hasExtra("roomNo")) {
            strRoomNo = intent.getExtras().getString("roomNo");
        }
        if (intent.hasExtra("wholesale")) {
            nWholesaleMode = intent.getExtras().getInt("wholesale");
        }

        llayoutForSite = findViewById(R.id.llayoutForSite);
        edtAddr1 = findViewById(R.id.edtAddr1);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        edtAddr2 = findViewById(R.id.edtAddr2);
        tvErrorMsg01 = findViewById(R.id.tvErrorMsg01);
        edtSiteName = findViewById(R.id.edtSiteName);
        tvErrorMsg02 = findViewById(R.id.tvErrorMsg02);
        edSiteUrl = findViewById(R.id.edSiteUrl);
        tvErrorMsg03 = findViewById(R.id.tvErrorMsg03);
        btnOk = findViewById(R.id.btnOk);



        llayoutForSite.setVisibility(View.GONE);
        if(store_type ==2){
            tvTitle.setText("소매 회원가입");
            llayoutForSite.setVisibility(View.VISIBLE);

        }else{
            tvTitle.setText("도매 회원가입");

            edtAddr2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if(i == EditorInfo.IME_ACTION_DONE){
                       hideKeyboardFrom(JoinStepAddrInputActivity.this,edtAddr2);

                    }
                    return false;
                }
            });
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput()){
                    Intent intent = new Intent(JoinStepAddrInputActivity.this, JoinStep4Activity.class);
                    intent.putExtra("store_type", store_type);
                    intent.putExtra("store_id", store_id);
                    intent.putExtra("store", strStore);
                    intent.putExtra("floor", strFloor);
                    intent.putExtra("roomNo", strRoomNo);
                    intent.putExtra("wholesale", nWholesaleMode);
                    intent.putExtra("level", level);
                    intent.putExtra("store_addr", edtAddr1.getText().toString()+" "+edtAddr2.getText().toString());


                    if(store_type ==2 ){
                        intent.putExtra("site_name", edtSiteName.getText().toString());
                        intent.putExtra("site_url", edSiteUrl.getText().toString());
                        intent.putExtra("store_onoﬀ", store_onoﬀ);

                    }
                    startActivity(intent);
                }

            }
        });

        edtAddr1.addTextChangedListener(new TextWatcher() {
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
        edtAddr2.addTextChangedListener(new TextWatcher() {
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



        edtSiteName.addTextChangedListener(new TextWatcher() {
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

        edSiteUrl.addTextChangedListener(new TextWatcher() {
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
    private void checkRegistBtn() {
        String strAddr1 = edtAddr1.getText().toString();
        String strAddr2 = edtAddr2.getText().toString();
        String strSiteName = edtSiteName.getText().toString();
        String strSiteUrl = edSiteUrl.getText().toString();

        if(store_type ==2){


            if ((strAddr1 != null && strAddr1.length() == 0) || (strAddr2 != null && strAddr2.length() == 0) || (strSiteName != null && strSiteName.length() == 0) ||
                    (strSiteUrl != null && strSiteUrl.length() == 0)) {
                btnOk.setBackgroundResource(R.drawable.btn_02);
                btnOk.setEnabled(false);
            } else {
                btnOk.setEnabled(true);
                btnOk.setBackgroundResource(R.drawable.btn_02_on);
            }
        }else{
            if ((strAddr1 != null && strAddr1.length() == 0) || (strAddr2 != null && strAddr2.length() == 0) ) {
                btnOk.setBackgroundResource(R.drawable.btn_02);
                btnOk.setEnabled(false);
            } else {
                btnOk.setEnabled(true);
                btnOk.setBackgroundResource(R.drawable.btn_02_on);
            }
        }


    }

    private boolean checkInput() {
        String strAddr1 = edtAddr1.getText().toString();
        String strAddr2 = edtAddr2.getText().toString();
        String strSiteName = edtSiteName.getText().toString();
        String strSiteUrl = edSiteUrl.getText().toString();
        tvErrorMsg01.setVisibility(View.GONE);
        tvErrorMsg02.setVisibility(View.GONE);
        tvErrorMsg03.setVisibility(View.GONE);
        if ((strAddr1 != null && strAddr1.length() == 0)) {
            tvErrorMsg01.setVisibility(View.VISIBLE);
            return false;
        }

        if ((strAddr2 != null && strAddr2.length() == 0)) {
            tvErrorMsg01.setVisibility(View.VISIBLE);
            return false;
        }

       if(store_type ==2){
           if ((strSiteName != null && strSiteName.length() == 0)) {
               tvErrorMsg02.setVisibility(View.VISIBLE);
               return false;
           }

           if ((strSiteUrl != null && strSiteUrl.length() == 0)) {
               tvErrorMsg03.setVisibility(View.VISIBLE);
               return false;
           }
       }


        return true;
    }


}
