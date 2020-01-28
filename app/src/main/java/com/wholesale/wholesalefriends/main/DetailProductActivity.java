package com.wholesale.wholesalefriends.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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

import co.lujun.androidtagview.TagContainerLayout;

public class DetailProductActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivIndigator01;
    private ImageView ivIndigator02;
    private ImageView ivIndigator03;
    private ImageView ivIndigator04;
    private ImageView ivIndigator05;
    private ImageView ivAugmentation;
    private TextView tvProductName;
    private TextView tvProductInfo;
    private TextView tvProductPrice;
    private LinearLayout llayoutForKeword;
    private TextView tvContent;
    private EditText edtInquire;
    private Button btnSend;
    private LinearLayout btnAdd;
    private LinearLayout btnOrder;


    private String product_name;
    private int product_id;
    private ViewPager viewPager;
    private ImageView ivStoreLogo;
//    private TagContainerLayout tagGroup;

    private ArrayList<ProductViewImageData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

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
        ivAugmentation = findViewById(R.id.ivAugmentation);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductInfo = findViewById(R.id.tvProductInfo);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        llayoutForKeword = findViewById(R.id.llayoutForKeword);
        tvContent = findViewById(R.id.tvContent);
        edtInquire = findViewById(R.id.edtInquire);
        btnSend = findViewById(R.id.btnSend);
        btnAdd = findViewById(R.id.btnAdd);
        btnOrder = findViewById(R.id.btnOrder);

        if (product_name != null) {
            tvTitle.setText(product_name);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPager = findViewById(R.id.viewPager);
        ivStoreLogo = findViewById(R.id.tvStoreName);
//        tagGroup = findViewById(R.id.tagGroup);

        tvTitle.setText(product_name);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.favorites(DetailProductActivity.this,SharedPreference.getIntSharedPreference(DetailProductActivity.this,Constant.CommonKey.user_no)+"",
                        2+"",product_id+"",resultFavoriteHandler,errHandler);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO
                Intent intent1 = new Intent(DetailProductActivity.this,ShoppingPaymentActivity.class);
                startActivity(intent1);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtInquire.getText().toString().trim().length() == 0){
                    final CommonAlertDialog dg = new CommonAlertDialog(DetailProductActivity.this,false,false);
                    dg.setMessage("문의하실 내용이 없습니다.\n입력해주세요.");
                    dg.show();
                    return;
                }

                //TODO
            }
        });

        loadData();
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
        API.productVIew(this, product_id + "", SharedPreference.getIntSharedPreference(this, Constant.CommonKey.user_no) + "", resultHandler, errHandler);
    }

    private Handler resultFavoriteHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {

                    loadData();
//                    Toast.makeText(DetailProductActivity.this,"관심 상품으로 등록하였습니다.",Toast.LENGTH_SHORT).show();

                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };


    private Handler resultHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;
                list.clear();
                viewPager.removeAllViews();
                if (jsonObject.getBoolean("result")) {
                    ProductViewResponse response = new Gson().fromJson(jsonObject.toString(), ProductViewResponse.class);
                    if (response != null) {

                        if(response.getImage()!=null && response.getImage().size()>0){
                            initIndigator(response.getImage().size());

                            list.addAll(response.getImage());
                            DetailProductViewAdapter detailProductViewAdapter = new DetailProductViewAdapter(DetailProductActivity.this,list);
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
                                tvContent.setText(info.getDetail());

                                if (info.getLogo() != null) {
                                    Glide.with(DetailProductActivity.this).asBitmap().load(info.getLogo())
                                            .listener(new RequestListener<Bitmap>() {
                                                @Override
                                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                                    ivStoreLogo.setVisibility(View.GONE);
                                                    return false;
                                                }

                                                @Override
                                                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                                    ivStoreLogo.setVisibility(View.VISIBLE);
                                                    return false;
                                                }
                                            }).into(ivStoreLogo);


                                    /*if(tag!=null && tag.length>0){
                                        tagGroup.setVisibility(View.VISIBLE);
                                        for(int i=0; i<tag.length;i++){
                                            tagGroup.addTag("$"+tag[i]);
                                        }
                                    }else{
                                        tagGroup.setVisibility(View.GONE);
                                    }*/
                                } else {
                                    ivStoreLogo.setVisibility(View.GONE);
                                }

                                if(info.getKeyword()!=null && info.getKeyword().indexOf(",")>-1){
                                    String[] tag = info.getKeyword().split(",");
                                    LayoutInflater inflater=null;
                                    View header = inflater.inflate(R.layout.adapter_deatail_product_keyword,null);
                                    TextView tvKeyWord = header.findViewById(R.id.tvKeyWord);
                                    if(tag!=null && tag.length>0){
                                        llayoutForKeword.setVisibility(View.VISIBLE);
                                        llayoutForKeword.removeAllViews();
                                        for(int i=0; i<tag.length;i++){
                                            tvKeyWord.setText(tag[i]);
                                        }
                                        llayoutForKeword.addView(header);
                                    }else{
                                        llayoutForKeword.setVisibility(View.GONE);
                                    }
                                }else{
                                    llayoutForKeword.setVisibility(View.GONE);
                                }

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

                if (!jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(DetailProductActivity.this, false, true);
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
