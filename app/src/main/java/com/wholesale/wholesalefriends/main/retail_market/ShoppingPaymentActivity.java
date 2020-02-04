package com.wholesale.wholesalefriends.main.retail_market;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.CartListAdapter;
import com.wholesale.wholesalefriends.main.adapter.PaymentListAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.CartListProductData;
import com.wholesale.wholesalefriends.main.data.CartListResponse;
import com.wholesale.wholesalefriends.main.data.PaymentListData;
import com.wholesale.wholesalefriends.main.data.PaymentListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.main.dialog.PaymentOkBankInfoAlertDialog;
import com.wholesale.wholesalefriends.main.dialog.PaymentOkInputAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.widget.WrapContentLinearLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingPaymentActivity extends GroupActivity {


    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;
    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageButton btnPayment;
    private RecyclerView recyclerView;
    private LinearLayout btnOrder;
    private LinearLayout llayoutForData;
    private LinearLayout llayoutForNoData;

    private int order_type;
    private int p_id;
    private String p_option_1;
    private String p_option_2;
    private int amount;
    private String c_id;

    private ArrayList<Integer> arrCID = new ArrayList<>();
    private ArrayList<PaymentListData>list = new ArrayList<>();


 /*   private ArrayList<String>arrUserId = new ArrayList<>();
    private ArrayList<String>arrStoreId = new ArrayList<>();
    private ArrayList<String>arrPaymentId = new ArrayList<>();
    private ArrayList<String>arrPaymentInfo = new ArrayList<>();
    private ArrayList<String>arrPId = new ArrayList<>();
    private ArrayList<String>arrPoption1 = new ArrayList<>();
    private ArrayList<String>arrPoption2= new ArrayList<>();
    private ArrayList<String>arrAmount = new ArrayList<>();
    private ArrayList<String>arrPrice = new ArrayList<>();
    private ArrayList<String>arrTotal = new ArrayList<>();
    private ArrayList<String>arrMessage = new ArrayList<>();*/


    private String strUserID = "";
    private String strStoreID = "";
    private String strPaymentID = "";
    private String strPaymentInfo = "";
    private String strPID = "";
    private String strPOption1 = "";
    private String strPOption2 = "";
    private String strAmount = "";
    private String strPrice = "";
    private String strTotal = "";
    private String strMessage = "";



    private PaymentListAdapter paymentListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_payment);

        Intent intent = getIntent();
        if(intent.hasExtra(Constant.CommonKey.intent_order_type)){
            order_type = intent.getExtras().getInt(Constant.CommonKey.intent_order_type);
        }

        if(intent.hasExtra(Constant.CommonKey.intent_p_id)){
            p_id = intent.getExtras().getInt(Constant.CommonKey.intent_p_id);
        }

        if(intent.hasExtra(Constant.CommonKey.intent_p_option_1)){
            p_option_1 = intent.getExtras().getString(Constant.CommonKey.intent_p_option_1);
        }

        if(intent.hasExtra(Constant.CommonKey.intent_p_option_2)){
            p_option_2 = intent.getExtras().getString(Constant.CommonKey.intent_p_option_2);
        }


        if(intent.hasExtra(Constant.CommonKey.intent_amount)){
            amount = intent.getExtras().getInt(Constant.CommonKey.intent_amount);
        }

        if(intent.hasExtra(Constant.CommonKey.intent_c_id)){
            arrCID = intent.getExtras().getIntegerArrayList(Constant.CommonKey.intent_c_id);
        }
        if(intent.hasExtra(Constant.CommonKey.intent_c_id2)){
            c_id = intent.getExtras().getString(Constant.CommonKey.intent_c_id2);
        }
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        btnPayment = findViewById(R.id.btnPayment);
        recyclerView = findViewById(R.id.recyclerView);
        btnOrder = findViewById(R.id.btnOrder);
        llayoutForData = findViewById(R.id.llayoutForData);
        llayoutForNoData = findViewById(R.id.llayoutForNoData);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("주문하기");

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingPaymentActivity.this, ShoppingPayment2Activity.class);
                startActivityForResult(intent,100);
            }
        });

        paymentListAdapter = new PaymentListAdapter(this,list);
        paymentListAdapter.setListSelectItemListener(new PaymentListAdapter.ListSelectItemListener() {
            @Override
            public void itemClick(int pos, PaymentListData data) {

            }
        });
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
        recyclerView.setAdapter(paymentListAdapter);
        clearData();
        loadList();

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int payment_id = SharedPreference.getIntSharedPreference(ShoppingPaymentActivity.this, Constant.CommonKey.payment_id);

                if(payment_id ==0){

                    final CommonAlertDialog dg = new CommonAlertDialog(ShoppingPaymentActivity.this,true,true);
                    dg.setTitle("주문 완료");
                    dg.setMessage("자주 이용하는 결제 수단이 저장되어 있지 않습니다.\n결제 수단을 등록/변경 하시겠습니까?");
                    dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if(dg.isOk()){
                                Intent intent = new Intent(ShoppingPaymentActivity.this, ShoppingPayment2Activity.class);
                                startActivityForResult(intent,100);

                            }
                        }
                    });

                    if(!isFinishing())dg.show();


                    return;
                }

                if(strUserID.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("회원 ID가 존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }

                if(strStoreID.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("상가 ID가 존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }



                if(strPaymentID.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("결제하실 상품의 결재방법 ID가\n존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }

                if(strPID.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("결제하실 상품의 상품 ID가\n존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }

                if(strPOption1.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("결제하실 상품의 색상 옵션값이\n존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }

                if(strPOption2.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("결제하실 상품의 사이즈 옵션값이\n존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }

                if(strAmount.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("결제하실 상품의 수량이\n존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }

                if(strPrice.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("결제하실 상품의 상품 단가가\n존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }

                if(strTotal.length() == 0){
                    final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,false,false);
                    dg.setTitle("주문 완료");
                    dg.setMessage("결제하실 상품의 가격이\n존재하지 않습니다.");
                    if(!isFinishing())dg.show();
                    return;
                }


                final PaymentOkInputAlertDialog dg = new PaymentOkInputAlertDialog(ShoppingPaymentActivity.this,true,true);
                dg.setTitle("주문 완료");
                dg.setMessage("해당 상품을 주문 하시겠습니까?");
                dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(dg.isOk()){
                            for(int i=0; i<paymentListAdapter.getItemCount();i++){
                                paymentListAdapter.getItem(i).setSend_content(dg.getContent());
                            }
                            strMessage =dg.getContent();
                            API.formAdd(ShoppingPaymentActivity.this,strUserID,strStoreID,strPaymentID,strPaymentInfo,strPID,strPOption1,strPOption2,strAmount,strPrice,strTotal,
                                    strMessage,resultSaveHandler,errHandler);

                        }
                    }
                });

                if(!isFinishing())dg.show();
            }
        });


    }

    private void loadList() {
        API.paymentList(this,order_type, SharedPreference.getIntSharedPreference(this, Constant.CommonKey.user_no) + "",
                c_id,p_id,p_option_1,p_option_2,amount+"",  resultListHandler, errHandler);

    }

    private void clearData() {
        isSwipeRefresh = true;
        isExistMore = false;
        paymentListAdapter.clear();
        if (paymentListAdapter.getItemCount() == 0) {
            btnOrder.setEnabled(false);
            recyclerView.setVisibility(View.GONE);
            llayoutForNoData.setVisibility(View.VISIBLE);
        } else {
            btnOrder.setEnabled(true);
            recyclerView.setVisibility(View.VISIBLE);
            llayoutForNoData.setVisibility(View.GONE);
        }
    }



    private Handler resultSaveHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;
                if (jsonObject.getBoolean("result")) {


                    String account_info ="";
                    String order_number="";

                    if(!jsonObject.isNull("order_number")){
                        order_number = jsonObject.getString("order_number");
                    }
                    if(!jsonObject.isNull("account_info")){
                        account_info = jsonObject.getString("account_info");
                    }

                    if(order_number.length()>0 && account_info.length()>0){

                        final PaymentOkBankInfoAlertDialog dg = new PaymentOkBankInfoAlertDialog(ShoppingPaymentActivity.this,false,true);
                        dg.setTitle("주문 완료");
                        dg.setMessage("주문이 완료되었습니다.\n\n주문 번호 : "+order_number);
                        dg.setMessage2(account_info);
                        dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if(dg.isOk()){
                                    finish();
                                }
                            }
                        });
                        if(!isFinishing())dg.show();
                    }else if(order_number.length()>0){
                        final  CommonAlertDialog dg = new CommonAlertDialog(ShoppingPaymentActivity.this,false,true);
                        dg.setTitle("주문 완료");
                        dg.setMessage("주문이 완료되었습니다.\n\n주문 번호 : "+order_number);
                        dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if(dg.isOk()){

                                    finish();
                                }
                            }
                        });
                        if(!isFinishing())dg.show();
                    }


                } else {
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }

        }
    };

    private Handler resultListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;
                if (jsonObject.getBoolean("result")) {
                    PaymentListResponse paymentListResponse = new Gson().fromJson(jsonObject.toString(), PaymentListResponse.class);
                    if (paymentListResponse != null && paymentListResponse.getList().size() > 0) {
                        if (isSwipeRefresh) {
                            paymentListAdapter.clear();
                            paymentListAdapter.addAll(paymentListResponse.getList());

                            isSwipeRefresh = false;
                        }

                      /*  arrUserId.clear();
                        arrStoreId.clear();
                        arrPaymentId.clear();
                        arrPaymentInfo.clear();
                        arrPId.clear();
                        arrPoption1.clear();
                        arrPoption2.clear();
                        arrAmount.clear();
                        arrPrice.clear();
                        arrTotal.clear();*/

                          strUserID = "";
                          strStoreID = "";
                          strPaymentID = "";
                          strPaymentInfo = "";
                          strPID = "";
                          strPOption1 = "";
                          strPOption2 = "";
                          strAmount = "";
                          strPrice = "";
                          strTotal = "";
                          strMessage = "";

                        for(int i=0; i<paymentListAdapter.getItemCount();i++){
                           /* arrUserId.add(paymentListAdapter.getItem(i).getUser_no());
                            arrStoreId.add(paymentListAdapter.getItem(i).getStore_id());
                            arrPaymentId.add(paymentListAdapter.getItem(i).getPayment_id());
                            arrPaymentInfo.add(paymentListAdapter.getItem(i).getPayment());

                            arrPId.add(paymentListAdapter.getItem(i).getP_id()+"");
                            arrPoption1.add(paymentListAdapter.getItem(i).getOption_1());
                            arrPoption2.add(paymentListAdapter.getItem(i).getOption_2());
                            arrAmount.add(paymentListAdapter.getItem(i).getAmount()+"");
                            arrPrice.add(paymentListAdapter.getItem(i).getPrice()+"");
                            arrTotal.add(paymentListAdapter.getItem(i).getTotal()+"");*/


                            strUserID = strUserID+ paymentListAdapter.getItem(i).getUser_no()+"||";
                            strStoreID = strStoreID+ paymentListAdapter.getItem(i).getStore_id()+"||";
                            strPaymentID = strPaymentID+ paymentListAdapter.getItem(i).getPayment_id()+"||";
                            strPaymentInfo = strPaymentInfo+ paymentListAdapter.getItem(i).getPayment()+"||";
                            strPID = strPID+ paymentListAdapter.getItem(i).getP_id()+"||";
                            strPOption1 = strPOption1+ paymentListAdapter.getItem(i).getOption_1()+"||";
                            strPOption2 = strPOption2+ paymentListAdapter.getItem(i).getOption_2()+"||";
                            strAmount = strAmount+ paymentListAdapter.getItem(i).getAmount()+"||";
                            strPrice = strPrice+ paymentListAdapter.getItem(i).getPrice()+"||";
                            strTotal = strTotal+ paymentListAdapter.getItem(i).getTotal()+"||";

                        }

                        if(paymentListAdapter.getItemCount()>0){
                            if(paymentListAdapter.getItem(0).getPayment_id()!=null && paymentListAdapter.getItem(0).getPayment_id().length()>0){
                                SharedPreference.putSharedPreference(ShoppingPaymentActivity.this, Constant.CommonKey.payment_id,Integer.valueOf(paymentListAdapter.getItem(0).getPayment_id()));
                                int payment_id = SharedPreference.getIntSharedPreference(ShoppingPaymentActivity.this, Constant.CommonKey.payment_id);

                                if(payment_id ==2){
                                    SharedPreference.putSharedPreference(ShoppingPaymentActivity.this, Constant.CommonKey.payment_tel,paymentListAdapter.getItem(0).getTel());

                                }

                                if(payment_id ==3){
                                    SharedPreference.putSharedPreference(ShoppingPaymentActivity.this, Constant.CommonKey.payment_name,paymentListAdapter.getItem(0).getPayment_name());

                                }
                            }

                        }
                    } else {
                        isExistMore = false;
                    }
                } else {
                    isExistMore = false;
                }

            } catch (Throwable e) {
                isExistMore = false;
                e.printStackTrace();
            }

            if (paymentListAdapter.getItemCount() == 0) {
                btnOrder.setEnabled(false);
                recyclerView.setVisibility(View.GONE);
                llayoutForNoData.setVisibility(View.VISIBLE);
            } else {
                btnOrder.setEnabled(true);
                recyclerView.setVisibility(View.VISIBLE);
                llayoutForNoData.setVisibility(View.GONE);
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
                        final CommonAlertDialog dg = new CommonAlertDialog(ShoppingPaymentActivity.this, false, true);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:

                    paymentListAdapter.clear();
                    isSwipeRefresh= true;
                    isExistMore = false;
                    loadList();
                    break;
            }
        }


    }
}
