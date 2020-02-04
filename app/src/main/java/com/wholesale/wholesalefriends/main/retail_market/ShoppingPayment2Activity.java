package com.wholesale.wholesalefriends.main.retail_market;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.PaymentListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;

import org.json.JSONObject;

public class ShoppingPayment2Activity extends GroupActivity {


    private int nSelect = -1;
    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivCheck01;
    private LinearLayout btmPaymentType01;
    private ImageView ivCheck02;
    private LinearLayout btmPaymentType02;
    private ImageView ivCheck03;
    private LinearLayout btmPaymentType03;
    private ImageView ivCheck04;
    private LinearLayout btmPaymentType04;
    private EditText edtSaipTel;
    private LinearLayout llayoutForSaipTel;
    private LinearLayout llayoutForPaymentOption01;
    private EditText edtName;
    private LinearLayout llayoutForPaymentOption02;
    private RelativeLayout groupPaymentOption;
    private LinearLayout btnSave;


    private String[] arrPaymentType = {"매장 수령 후 결제","사입삼촌 대납","계좌이체","건사입요청"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_payment2);

        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivCheck01 = findViewById(R.id.ivCheck01);
        btmPaymentType01 = findViewById(R.id.btmPaymentType01);
        ivCheck02 = findViewById(R.id.ivCheck02);
        btmPaymentType02 = findViewById(R.id.btmPaymentType02);
        ivCheck03 = findViewById(R.id.ivCheck03);
        btmPaymentType03 = findViewById(R.id.btmPaymentType03);
        ivCheck04 = findViewById(R.id.ivCheck04);
        btmPaymentType04 = findViewById(R.id.btmPaymentType04);
        edtSaipTel = findViewById(R.id.edtSaipTel);
        llayoutForSaipTel = findViewById(R.id.llayoutForSaipTel);
        llayoutForPaymentOption01 = findViewById(R.id.llayoutForPaymentOption01);
        edtName = findViewById(R.id.edtName);
        llayoutForPaymentOption02 = findViewById(R.id.llayoutForPaymentOption02);
        groupPaymentOption = findViewById(R.id.groupPaymentOption);
        btnSave = findViewById(R.id.btnSave);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText("기본 결제방법");

        btmPaymentType01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(0);
            }
        });

        btmPaymentType02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(1);
            }
        });

        btmPaymentType03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(2);
            }
        });

        btmPaymentType04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(3);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nSelect ==2){
                    if(edtSaipTel.getText().toString().length() ==0){
                        final CommonAlertDialog dg = new CommonAlertDialog(ShoppingPayment2Activity.this,false,false);
                        dg.setMessage("사입삼촌 전화번호를 입력해주세요.");
                        dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if(dg.isOk()){

                                }
                            }
                        });
                        if(!isFinishing())dg.show();
                        return;
                    }
                }else if(nSelect ==3){
                    if(edtName.getText().toString().length() ==0){
                        final CommonAlertDialog dg = new CommonAlertDialog(ShoppingPayment2Activity.this,false,false);
                        dg.setMessage("입금자명을 입력해주세요.");
                        dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if(dg.isOk()){

                                }
                            }
                        });
                        if(!isFinishing())dg.show();
                        return;
                    }
                }


                API.paymentOption(ShoppingPayment2Activity.this, SharedPreference.getIntSharedPreference(ShoppingPayment2Activity.this, Constant.CommonKey.store_id)+""
                        ,nSelect,edtSaipTel.getText().toString(),edtName.getText().toString(),resultSaveHandler,errHandler);

            }
        });


        int payment_id = SharedPreference.getIntSharedPreference(this, Constant.CommonKey.payment_id);
        if(payment_id!=0){
            nSelect = payment_id;
            selectItem(nSelect-1);

            String payment_tel = SharedPreference.getSharedPreference(this, Constant.CommonKey.payment_tel);
            String payment_name = SharedPreference.getSharedPreference(this, Constant.CommonKey.payment_name);

            if(payment_tel!=null && payment_tel.length()>0){
                edtSaipTel.setText(payment_tel);
            }

            if(payment_name!=null && payment_name.length()>0){
                edtName.setText(payment_name);
            }

        }
    }

    private void selectItem(int pos) {
        btmPaymentType01.setBackgroundResource(R.drawable.box_off_01);
        btmPaymentType02.setBackgroundResource(R.drawable.box_off_01);
        btmPaymentType03.setBackgroundResource(R.drawable.box_off_01);
        btmPaymentType04.setBackgroundResource(R.drawable.box_off_01);

        ivCheck01.setBackgroundResource(R.drawable.check_default);
        ivCheck02.setBackgroundResource(R.drawable.check_default);
        ivCheck03.setBackgroundResource(R.drawable.check_default);
        ivCheck04.setBackgroundResource(R.drawable.check_default);


        groupPaymentOption.setVisibility(View.GONE);
        llayoutForPaymentOption01.setVisibility(View.GONE);
        llayoutForPaymentOption02.setVisibility(View.GONE);
        switch (pos) {
            case 0:
                nSelect = 1;
                btmPaymentType01.setBackgroundResource(R.drawable.box_on_01);
                ivCheck01.setBackgroundResource(R.drawable.check_on);
                break;
            case 1:
                groupPaymentOption.setVisibility(View.VISIBLE);
                llayoutForPaymentOption01.setVisibility(View.VISIBLE);
                nSelect = 2;
                btmPaymentType02.setBackgroundResource(R.drawable.box_on_01);
                ivCheck02.setBackgroundResource(R.drawable.check_on);
                break;
            case 2:
                groupPaymentOption.setVisibility(View.VISIBLE);
                llayoutForPaymentOption02.setVisibility(View.VISIBLE);
                nSelect = 3;
                btmPaymentType03.setBackgroundResource(R.drawable.box_on_01);
                ivCheck03.setBackgroundResource(R.drawable.check_on);
                break;
            case 3:
                nSelect = 4;
                btmPaymentType04.setBackgroundResource(R.drawable.box_on_01);
                ivCheck04.setBackgroundResource(R.drawable.check_on);
                break;
        }

    }

    private Handler resultHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;
                if (jsonObject.getBoolean("result")) {
                } else {
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }

        }
    };
    private Handler resultSaveHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;
                if (jsonObject.getBoolean("result")) {

                    SharedPreference.putSharedPreference(ShoppingPayment2Activity.this, Constant.CommonKey.payment_id,nSelect);

                    if(nSelect ==2){
                        SharedPreference.putSharedPreference(ShoppingPayment2Activity.this, Constant.CommonKey.payment_tel,edtSaipTel.getText().toString());

                    }

                    if(nSelect ==3){
                        SharedPreference.putSharedPreference(ShoppingPayment2Activity.this, Constant.CommonKey.payment_name,edtName.getText().toString());

                    }

                    final CommonAlertDialog dg = new CommonAlertDialog(ShoppingPayment2Activity.this,false,true);
                    dg.setTitle("기본 결제방법");
                    dg.setMessage("기번 결제 방법이 \n등록 또는 변경 되었습니다.");
                    dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Intent intent = new Intent();//startActivity()를 할것이 아니므로 그냥 빈 인텐트로 만듦
                          /*  intent.putExtra("payment",arrPaymentType[nSelect-1]);
                            intent.putExtra("payment_id",nSelect);
                            intent.putExtra("payment_tel",edtSaipTel.getText().toString());
                            intent.putExtra("payment_name",edtName.getText().toString());*/
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    });
                    if(!isFinishing())dg.show();
                } else {
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }

        }
    };

    private Handler errHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (!jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(ShoppingPayment2Activity.this, false, true);
                        dg.setTitle("계정 정보 확인");
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
