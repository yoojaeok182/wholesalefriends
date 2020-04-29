package com.wholesale.wholesalefriends.main.retail_market;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.CartListAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.CartListProductData;
import com.wholesale.wholesalefriends.main.data.CartListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.module.util.Util;
import com.wholesale.wholesalefriends.widget.WrapContentLinearLayoutManager;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivCheck;
    private LinearLayout btnAllCheck;
    private Button btnDelete;
    private RecyclerView recyclerView;
    private LinearLayout btnAdd;
    private LinearLayout btnOrder;
    private LinearLayout llayoutForData;
    private LinearLayout llayoutForNoData;
    private LinearLayout llayoutForOption;
    private TextView tvTotalPrice;

    private CartListAdapter cartListAdapter;

    private boolean isAllCheck;
    private boolean isCheck;

    private ArrayList<CartListProductData>list = new ArrayList<>();
    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;

    private List<Integer> arrCid = new ArrayList<>();
    private String c_id = "";
    private int nSelectPos =-1;
    private int nAmountCount;

    private int nPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivCheck = findViewById(R.id.ivCheck);
        btnAllCheck = findViewById(R.id.btnAllCheck);
        btnDelete = findViewById(R.id.btnDelete);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        btnOrder = findViewById(R.id.btnOrder);
        llayoutForData = findViewById(R.id.llayoutForData);
        llayoutForNoData = findViewById(R.id.llayoutForNoData);
        llayoutForOption= findViewById(R.id.llayoutForOption);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvTitle.setText("장바구니");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAllCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isAllCheck){
                    ivCheck.setBackgroundResource(R.drawable.check_on);
                    isAllCheck = true;
                    btnOrder.setEnabled(true);
                    btnOrder.setBackgroundResource(R.drawable.btn_02_on);
                }else{
                    ivCheck.setBackgroundResource(R.drawable.check_default);
                    isAllCheck = false;
                    btnOrder.setEnabled(false);
                    btnOrder.setBackgroundResource(R.drawable.btn_02);

                }

                for(int i=0; i<cartListAdapter.getItemCount();i++){
                    cartListAdapter.getItem(i).setCheck(isAllCheck);
                }
                cartListAdapter.notifyDataSetChanged();

                c_id = "";
                arrCid.clear();

                c_id = "";
                arrCid.clear();
                for(int i=0; i<cartListAdapter.getItemCount();i++){
                    if(cartListAdapter.getItem(i).getC_id()>0 &&cartListAdapter.getItem(i).isCheck()){
                        arrCid.add(cartListAdapter.getItem(i).getC_id());
                        c_id = c_id+cartListAdapter.getItem(i).getC_id()+"||";

                    }
                }
                getSelectPrice();
            }

        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAllCheck){

                    API.cartDelete(ShoppingBasketActivity.this,SharedPreference.getIntSharedPreference(ShoppingBasketActivity.this,Constant.CommonKey.user_no)+"",
                            c_id,resultDeleteHandler,errHandler);
                }
            }
        });


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingBasketActivity.this,ShoppingPaymentActivity.class);
                intent.putExtra(Constant.CommonKey.intent_order_type,2);
                intent.putExtra(Constant.CommonKey.intent_c_id, (Serializable) arrCid);
                intent.putExtra(Constant.CommonKey.intent_c_id2,   c_id);

                startActivity(intent);
            }
        });
        cartListAdapter = new CartListAdapter(this,list);
        cartListAdapter.setListSelectItemListener(new CartListAdapter.ListSelectItemListener() {
            @Override
            public void itemClick(int pos, boolean isCheck, CartListProductData data) {
                nSelectPos = pos;
               checkBtnOrder();



                cartListAdapter.getItem(nSelectPos).setCheck(isCheck);
                cartListAdapter.notifyDataSetChanged();
                c_id = "";
                arrCid.clear();
                for(int i=0; i<cartListAdapter.getItemCount();i++){
                    if(cartListAdapter.getItem(i).getC_id()>0 &&cartListAdapter.getItem(i).isCheck()){
                        arrCid.add(cartListAdapter.getItem(i).getC_id());
                        c_id = c_id+cartListAdapter.getItem(i).getC_id()+"||";
                    }
                }


                for(int i=0; i<cartListAdapter.getItemCount();i++){
                    if(isCheck){
                        btnOrder.setBackgroundResource(R.drawable.btn_02_on);
                        btnOrder.setEnabled(true);
                        break;
                    }
                }
                getSelectPrice();

            }

            @Override
            public void modAmount(int pos, int count, CartListProductData data) {
                nSelectPos = pos;
                nAmountCount = count;
                API.cartAmountMod(ShoppingBasketActivity.this,SharedPreference.getIntSharedPreference(ShoppingBasketActivity.this,Constant.CommonKey.user_no)+"",
                        data.getStore_id()+"",data.getC_id()+"",count+"",resultModifyHandler,errHandler);
            }

            @Override
            public void itemDelete(int pos, CartListProductData data) {
                nSelectPos = pos;
                API.cartDelete(ShoppingBasketActivity.this,SharedPreference.getIntSharedPreference(ShoppingBasketActivity.this,Constant.CommonKey.user_no)+"",
                        data.getC_id()+"||",resultDeleteHandler,errHandler);
            }
        });
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
        recyclerView.setAdapter(cartListAdapter);
        clearData();
        loadList();
    }

    private void getSelectPrice(){
        nPrice = 0;
        for(int i=0; i<cartListAdapter.getItemCount();i++){
            if(cartListAdapter.getItem(i).isCheck()){

                nPrice += cartListAdapter.getItem(i).getPrice()*cartListAdapter.getItem(i).getAmount();
            }
        }

        tvTotalPrice.setText(Util.getFormattedPrice(nPrice));
    }
    private void loadList(){
        API.cartList(this, SharedPreference.getIntSharedPreference(this, Constant.CommonKey.user_no)+"",resultListHandler,errHandler);
    }

    private void clearData(){
        isSwipeRefresh = true;
        isExistMore= false;
        cartListAdapter.clear();
        /*if(cartListAdapter.getItemCount() ==0){
            btnOrder.setEnabled(false);
            recyclerView.setVisibility(View.GONE);
            llayoutForNoData.setVisibility(View.VISIBLE);
            llayoutForOption.setVisibility(View.GONE);
        }else{
            btnOrder.setEnabled(true);
            recyclerView.setVisibility(View.VISIBLE);
            llayoutForNoData.setVisibility(View.GONE);
            llayoutForOption.setVisibility(View.VISIBLE);
        }*/
    }

    private Handler resultModifyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    if(nAmountCount ==0){
                        clearData();
                        loadList();
                        return;
                    }
                    cartListAdapter.getItem(nSelectPos).setAmount(nAmountCount);
                    cartListAdapter.notifyDataSetChanged();
                    getSelectPrice();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };

    private Handler resultDeleteHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    if(isAllCheck){
                        clearData();
                        loadList();
                    }else{
                        cartListAdapter.remove(cartListAdapter.getItem(nSelectPos));
                    }

                    getSelectPrice();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };
    private Handler resultDeleteAllHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    isAllCheck = false;
                    clearData();
                    tvTotalPrice.setText("0원");
                    Toast.makeText(ShoppingBasketActivity.this, "전체 삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();

                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };

    private Handler resultListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;
                if(jsonObject.getBoolean("result")){
                    CartListResponse cartListResponse = new Gson().fromJson(jsonObject.toString(), CartListResponse.class);
                    if(cartListResponse!=null && cartListResponse.getProduct().size()>0){
                        if(isSwipeRefresh){
                            cartListAdapter.clear();
                            cartListAdapter.addAll(cartListResponse.getProduct());

                            isSwipeRefresh = false;
                        }




                    }else{
                        isExistMore = false;
                    }

                    if(cartListResponse!=null){
//                        tvTotalPrice.setText(Util.getFormattedPrice(Integer.valueOf(cartListResponse.getTotal())));
                    }else{
                        tvTotalPrice.setText("0원");
                    }
                }else{
                    isExistMore = false;
                    tvTotalPrice.setText("0원");
                }

            }catch (Throwable e){
                isExistMore = false;
                tvTotalPrice.setText("0원");
                e.printStackTrace();
            }

            if(cartListAdapter.getItemCount() ==0){
                tvTotalPrice.setText("0원");
                btnOrder.setEnabled(false);
                recyclerView.setVisibility(View.GONE);
                llayoutForNoData.setVisibility(View.VISIBLE);
                llayoutForOption.setVisibility(View.GONE);
            }else{
                btnOrder.setEnabled(true);
                recyclerView.setVisibility(View.VISIBLE);
                llayoutForNoData.setVisibility(View.GONE);
                llayoutForOption.setVisibility(View.VISIBLE);
            }

            checkBtnOrder();
        }
    };

    private void checkBtnOrder(){
        boolean isTempCheck = false;
        for(int i=0; i<cartListAdapter.getItemCount();i++){
            if(cartListAdapter.getItem(i).isCheck()){
                isTempCheck = true;
            }else{
                isTempCheck = false;
            }
        }

        if(isTempCheck){
            isAllCheck = true;
            btnOrder.setBackgroundResource(R.drawable.btn_02_on);
            btnOrder.setEnabled(true);
        }else{
            btnOrder.setBackgroundResource(R.drawable.btn_02);
            btnOrder.setEnabled(false);
        }
    }
    private Handler errHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (!jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(ShoppingBasketActivity.this, false, true);
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
