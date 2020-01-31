package com.wholesale.wholesalefriends.main.wholesale_market;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.DetailProductViewAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ProductViewImageData;
import com.wholesale.wholesalefriends.main.data.ProductViewInfoData;
import com.wholesale.wholesalefriends.main.data.ProductViewResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.module.util.Util;

import org.json.JSONObject;

import java.util.ArrayList;

public class DetailProduct2Activity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivIndigator01;
    private ImageView ivIndigator02;
    private ImageView ivIndigator03;
    private ImageView ivIndigator04;
    private ImageView ivIndigator05;
    private TextView tvProductInfo;
    private TextView tvProductName;
    private TextView tvProductPrice;
    private ImageView btnShare;
    private Button btnModify;
    private Button btnDelete;
    private Button btnSoldOut;
    private TextView tvColor;
    private TextView tvSize;
    private TextView tvMix;
    private EditText tvProducingCountry;
    private TextView tvInfo;
    private ImageView ivThick01;
    private LinearLayout btnThick01;
    private ImageView ivThick02;
    private LinearLayout btnThick02;
    private ImageView ivThick031;
    private LinearLayout btnThick03;
    private ImageView ivTrans01;
    private LinearLayout btnTrans01;
    private ImageView ivTrans02;
    private LinearLayout btnTrans02;
    private ImageView ivTrans03;
    private LinearLayout btnTrans03;
    private ImageView ivStretch01;
    private LinearLayout btnStretch01;
    private ImageView ivStretch02;
    private LinearLayout btnStretch02;
    private ImageView ivStretch03;
    private LinearLayout btnStretch03;
    private ImageView ivStretch04;
    private LinearLayout btnStretch04;
    private ImageView ivFabric01;
    private LinearLayout btnFabric01;
    private ImageView ivFabric02;
    private LinearLayout btnFabric02;
    private RecyclerView recyclerView;
    private LinearLayout llayoutForGuide;
    private ImageView ivCheck;
    private EditText edtComment;
    private Button btnWrite;
    private LinearLayout llayoutForComment;
    private ViewPager viewPager;

    private ArrayList<ProductViewImageData> list = new ArrayList<>();

    private String product_name;
    private int product_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product2);

        Intent intent = getIntent();
        if (intent.hasExtra(Constant.CommonKey.product_name)) {
            product_name = intent.getExtras().getString(Constant.CommonKey.product_name);
        }

        if (intent.hasExtra(Constant.CommonKey.product_id)) {
            product_id = intent.getExtras().getInt(Constant.CommonKey.product_id);
        }

        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivIndigator01 = findViewById(R.id.ivIndigator01);
        ivIndigator02 = findViewById(R.id.ivIndigator02);
        ivIndigator03 = findViewById(R.id.ivIndigator03);
        ivIndigator04 = findViewById(R.id.ivIndigator04);
        ivIndigator05 = findViewById(R.id.ivIndigator05);
        tvProductInfo = findViewById(R.id.tvProductInfo);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        btnShare = findViewById(R.id.btnShare);
        btnModify = findViewById(R.id.btnModify);
        btnDelete = findViewById(R.id.btnDelete);
        btnSoldOut = findViewById(R.id.btnSoldOut);
        tvColor = findViewById(R.id.tvColor);
        tvSize = findViewById(R.id.tvSize);
        tvMix = findViewById(R.id.tvMix);
        tvProducingCountry = findViewById(R.id.tvProducingCountry);
        tvInfo = findViewById(R.id.tvInfo);
        ivThick01 = findViewById(R.id.ivThick01);
        btnThick01 = findViewById(R.id.btnThick01);
        ivThick02 = findViewById(R.id.ivThick02);
        btnThick02 = findViewById(R.id.btnThick02);
        ivThick031 = findViewById(R.id.ivThick031);
        btnThick03 = findViewById(R.id.btnThick03);
        ivTrans01 = findViewById(R.id.ivTrans01);
        btnTrans01 = findViewById(R.id.btnTrans01);
        ivTrans02 = findViewById(R.id.ivTrans02);
        btnTrans02 = findViewById(R.id.btnTrans02);
        ivTrans03 = findViewById(R.id.ivTrans03);
        btnTrans03 = findViewById(R.id.btnTrans03);
        ivStretch01 = findViewById(R.id.ivStretch01);
        btnStretch01 = findViewById(R.id.btnStretch01);
        ivStretch02 = findViewById(R.id.ivStretch02);
        btnStretch02 = findViewById(R.id.btnStretch02);
        ivStretch03 = findViewById(R.id.ivStretch03);
        btnStretch03 = findViewById(R.id.btnStretch03);
        ivStretch04 = findViewById(R.id.ivStretch04);
        btnStretch04 = findViewById(R.id.btnStretch04);
        ivFabric01 = findViewById(R.id.ivFabric01);
        btnFabric01 = findViewById(R.id.btnFabric01);
        ivFabric02 = findViewById(R.id.ivFabric02);
        btnFabric02 = findViewById(R.id.btnFabric02);
        recyclerView = findViewById(R.id.recyclerView);
        llayoutForGuide = findViewById(R.id.llayoutForGuide);
        ivCheck = findViewById(R.id.ivCheck);
        edtComment = findViewById(R.id.edtComment);
        btnWrite = findViewById(R.id.btnWrite);
        llayoutForComment = findViewById(R.id.llayoutForComment);
        viewPager = findViewById(R.id.viewPager);
        if (product_name != null) {
            tvTitle.setText(product_name);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        if(!SharedPreference.getBooleanSharedPreference(this, Constant.CommonKey.guide_show)){
            llayoutForGuide.setVisibility(View.VISIBLE);
            llayoutForGuide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    llayoutForGuide.setVisibility(View.GONE);
                    SharedPreference.putSharedPreference(DetailProduct2Activity.this, Constant.CommonKey.guide_show,true);
                }
            });

        }

    }
    private void initIndigator(int size){
        ivIndigator01.setVisibility(View.GONE);
        ivIndigator02.setVisibility(View.GONE);
        ivIndigator03.setVisibility(View.GONE);
        ivIndigator04.setVisibility(View.GONE);
        ivIndigator05.setVisibility(View.GONE);

        if(size>=5){
            ivIndigator01.setVisibility(View.VISIBLE);
            ivIndigator02.setVisibility(View.VISIBLE);
            ivIndigator03.setVisibility(View.VISIBLE);
            ivIndigator04.setVisibility(View.VISIBLE);
            ivIndigator05.setVisibility(View.VISIBLE);
        }else{
            switch (size){
                case 1:
                    ivIndigator01.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ivIndigator01.setVisibility(View.VISIBLE);
                    ivIndigator02.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    ivIndigator01.setVisibility(View.VISIBLE);
                    ivIndigator02.setVisibility(View.VISIBLE);
                    ivIndigator03.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    ivIndigator01.setVisibility(View.VISIBLE);
                    ivIndigator02.setVisibility(View.VISIBLE);
                    ivIndigator03.setVisibility(View.VISIBLE);
                    ivIndigator04.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void showIndigator(int pos){
        ivIndigator01.setBackgroundResource(R.drawable.icon_indigatior);
        ivIndigator02.setBackgroundResource(R.drawable.icon_indigatior);
        ivIndigator03.setBackgroundResource(R.drawable.icon_indigatior);
        ivIndigator04.setBackgroundResource(R.drawable.icon_indigatior);
        ivIndigator05.setBackgroundResource(R.drawable.icon_indigatior);

        switch (pos){
            case 0:
                ivIndigator01.setBackgroundResource(R.drawable.icon_indigatior_on);
                break;
            case 1:
                ivIndigator02.setBackgroundResource(R.drawable.icon_indigatior_on);

                break;
            case 2:
                ivIndigator03.setBackgroundResource(R.drawable.icon_indigatior_on);

                break;
            case 3:
                ivIndigator04.setBackgroundResource(R.drawable.icon_indigatior_on);

                break;
            case 4:
                ivIndigator05.setBackgroundResource(R.drawable.icon_indigatior_on);

                break;
        }
    }
    private void loadData() {
        API.productVIew(this, product_id + "",
                SharedPreference.getIntSharedPreference(this, Constant.CommonKey.user_no) + "", resultHandler, errHandler);
    }

    private Handler resultHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;
                list.clear();
                if (jsonObject.getBoolean("result")) {
                    ProductViewResponse response = new Gson().fromJson(jsonObject.toString(), ProductViewResponse.class);
                    if (response != null) {

                        if(response.getImage()!=null && response.getImage().size()>0){
                            initIndigator(response.getImage().size());

                            list.addAll(response.getImage());
                            DetailProductViewAdapter detailProductViewAdapter = new DetailProductViewAdapter(DetailProduct2Activity.this,list);
                            viewPager.setAdapter(detailProductViewAdapter);
                            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {
                                    showIndigator(position%5);
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });
                        }else{
                            initIndigator(0);
                        }
                        if (response.getInfo() != null && response.getInfo().size() > 0) {
                            ProductViewInfoData info = response.getInfo().get(0);
                            if (info != null) {
                                tvProductName.setText(info.getName());
                                tvProductInfo.setText(info.getStore_name());
                                tvProductPrice.setText(Util.getFormattedPrice(info.getPrice()));


                            }
                        }
                    }
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

                if (jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(DetailProduct2Activity.this, false, true);
                        dg.setTitle("상품");
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
