package com.wholesale.wholesalefriends.main.retail_market;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    private ArrayList<Integer> c_id = new ArrayList<>();
    private ArrayList<PaymentListData>list = new ArrayList<>();

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
            c_id = intent.getExtras().getIntegerArrayList(Constant.CommonKey.intent_c_id);
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
                startActivity(intent);
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

    }

    private void loadList() {
        c_id.add(8);
        c_id.add(5);
        c_id.add(6);
        if(order_type ==2){
            API.paymentList(this,order_type, SharedPreference.getIntSharedPreference(this, Constant.CommonKey.user_no) + "",c_id, resultListHandler, errHandler);

        }else{
            API.paymentList(this,order_type,  SharedPreference.getIntSharedPreference(this, Constant.CommonKey.user_no)  + "",p_id,p_option_1,p_option_2,amount+"", resultListHandler, errHandler);

        }
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
                        /*else{
                            if(cartListAdapter.getItemCount()>0){
                                int nowSize = cartListAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+cartListResponse.getProduct().size();i++){
                                    cartListAdapter.add(cartListResponse.getProduct().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<cartListResponse.getProduct().size();i++){
                                    cartListAdapter.add(cartListResponse.getProduct().get(i),i);
                                }
                            }
                        }

                        if(cartListResponse.getProduct().size() >= Constant.PAGE_GO){
                            isExistMore = true;
                        }else{
                            isExistMore = false;
                        }*/


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
}
