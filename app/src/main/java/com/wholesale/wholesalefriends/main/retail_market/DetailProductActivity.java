package com.wholesale.wholesalefriends.main.retail_market;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.DetailProductViewAdapter;
import com.wholesale.wholesalefriends.main.adapter.ProductOptionColorAdapter;
import com.wholesale.wholesalefriends.main.adapter.ProductOptionSizeAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ProductViewImageData;
import com.wholesale.wholesalefriends.main.data.ProductViewInfoData;
import com.wholesale.wholesalefriends.main.data.ProductViewOptionColorData;
import com.wholesale.wholesalefriends.main.data.ProductViewOptionSizeData;
import com.wholesale.wholesalefriends.main.data.ProductViewResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.module.util.Util;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

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
    private RecyclerView recyclerColorView;
    private RecyclerView recyclerSizeView;
    private LinearLayout btnCart;
    private LinearLayout btnOrder2;
    private LinearLayout llayoutForOption;


    private ProductOptionColorAdapter productOptionColorAdapter;
    private ProductOptionSizeAdapter productOptionSizeAdapter;


    private ArrayList<ProductViewOptionColorData> listOptionColor = new ArrayList<>();
    private ArrayList<ProductViewOptionSizeData> listOptionSize = new ArrayList<>();

    private boolean isPanelShown;
    private LinearLayout btnOptionClose;


    private String strProductOption1;
    private String strProductOption2;
    private int nProductAmount;
    private ImageButton btnSubtract;
    private TextView tvAmount;
    private ImageButton btnPlus;

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
        isPanelShown = false;

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
        recyclerColorView = findViewById(R.id.recyclerColorView);
        recyclerSizeView = findViewById(R.id.recyclerSizeView);
        btnCart = findViewById(R.id.btnCart);
        btnOrder2 = findViewById(R.id.btnOrder2);
        llayoutForOption = findViewById(R.id.llayoutForOption);
        btnOptionClose = findViewById(R.id.btnOptionClose);
        btnSubtract = findViewById(R.id.btnSubtract);
        tvAmount = findViewById(R.id.tvAmount);
        btnPlus = findViewById(R.id.btnPlus);

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

        tvTitle.setText(product_name);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.favorites(DetailProductActivity.this, SharedPreference.getIntSharedPreference(DetailProductActivity.this, Constant.CommonKey.user_no) + "",
                        2 + "", product_id + "", resultFavoriteHandler, errHandler);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isPanelShown) {
                    Animation bottomUp = AnimationUtils.loadAnimation(DetailProductActivity.this, R.anim.slide_in_up);
                    llayoutForOption.startAnimation(bottomUp);
                    llayoutForOption.setVisibility(View.VISIBLE);
                    isPanelShown = true;
                }
            }
        });

        btnOptionClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPanelShown) {
                    Animation bottomUp = AnimationUtils.loadAnimation(DetailProductActivity.this, R.anim.slide_in_down);
                    llayoutForOption.startAnimation(bottomUp);
                    llayoutForOption.setVisibility(View.GONE);
                    isPanelShown = false;
                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtInquire.getText().toString().trim().length() == 0) {
                    final CommonAlertDialog dg = new CommonAlertDialog(DetailProductActivity.this, false, false);
                    dg.setMessage("문의하실 내용이 없습니다.\n입력해주세요.");
                    dg.show();
                    return;
                }
                API.qnaWrite(DetailProductActivity.this, product_id + "", SharedPreference.getIntSharedPreference(DetailProductActivity.this, Constant.CommonKey.user_no) + "",
                        edtInquire.getText().toString().trim(), resultWriteHandler, errHandler);
            }
        });

        productOptionColorAdapter = new ProductOptionColorAdapter(this, listOptionColor);
        productOptionSizeAdapter = new ProductOptionSizeAdapter(this, listOptionSize);

        productOptionColorAdapter.setListSelectItemListener(new ProductOptionColorAdapter.ListSelectItemListener() {
            @Override
            public void itemClick(int pos, int prevPos, ProductViewOptionColorData data) {

                productOptionColorAdapter.getItem(prevPos).setSelect(false);
                productOptionColorAdapter.getItem(pos).setSelect(true);
                strProductOption1 = data.getCode_value();
                productOptionColorAdapter.notifyDataSetChanged();

            }
        });

        productOptionSizeAdapter.setListSelectItemListener(new ProductOptionSizeAdapter.ListSelectItemListener() {

            @Override
            public void itemClick(int pos, int prevPos, ProductViewOptionSizeData data) {
                productOptionSizeAdapter.getItem(prevPos).setSelect(false);
                productOptionSizeAdapter.getItem(pos).setSelect(true);
                strProductOption2 = data.getCode_value();
                productOptionSizeAdapter.notifyDataSetChanged();
            }
        });
        recyclerColorView.setLayoutManager(new WrapContentGridLayoutManager(DetailProductActivity.this, 5) {
            @Override
            public boolean canScrollHorizontally() {
//                return super.canScrollHorizontally();
                return false;
            }

            @Override
            public boolean canScrollVertically() {
//                return super.canScrollVertically();
                return false;
            }
        });
        recyclerColorView.setAdapter(productOptionColorAdapter);

        recyclerSizeView.setLayoutManager(new WrapContentGridLayoutManager(DetailProductActivity.this, 5) {
            @Override
            public boolean canScrollHorizontally() {
//                return super.canScrollHorizontally();
                return false;
            }

            @Override
            public boolean canScrollVertically() {
//                return super.canScrollVertically();
                return false;
            }
        });

        recyclerSizeView.setAdapter(productOptionSizeAdapter);


        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nProductAmount<=0){
                    return;
                }
                nProductAmount--;
                tvAmount.setText(nProductAmount+"");
            }
        });


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nProductAmount++;
                tvAmount.setText(nProductAmount+"");
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(strProductOption1 == null ||(strProductOption1!=null&& strProductOption1.length() ==0)){
                    final CommonAlertDialog dg = new CommonAlertDialog(DetailProductActivity.this, false, false);
                    dg.setMessage("색상을 선택해주세요.");
                    dg.show();
                    return;
                }

                if(strProductOption2 == null ||(strProductOption1!=null&& strProductOption2.length() ==0)){
                    final CommonAlertDialog dg = new CommonAlertDialog(DetailProductActivity.this, false, false);
                    dg.setMessage("사이즈를 선택해주세요.");
                    dg.show();
                    return;
                }
                if(nProductAmount <=0){
                    final CommonAlertDialog dg = new CommonAlertDialog(DetailProductActivity.this, false, false);
                    dg.setMessage("수량을 선택해주세요.");
                    dg.show();
                    return;
                }
                API.cartAdd(DetailProductActivity.this, product_id + "", SharedPreference.getIntSharedPreference(DetailProductActivity.this, Constant.CommonKey.user_no) + "",
                        SharedPreference.getIntSharedPreference(DetailProductActivity.this, Constant.CommonKey.store_id) + "", strProductOption1, strProductOption2, nProductAmount + "", resultCartAddHandler, errHandler);
            }
        });

        btnOrder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailProductActivity.this, ShoppingPaymentActivity.class);
                startActivity(intent1);
            }
        });
        loadData();


    }

    private void initIndigator(int size) {
        ivIndigator01.setVisibility(View.GONE);
        ivIndigator02.setVisibility(View.GONE);
        ivIndigator03.setVisibility(View.GONE);
        ivIndigator04.setVisibility(View.GONE);
        ivIndigator05.setVisibility(View.GONE);

        if (size >= 5) {
            ivIndigator01.setVisibility(View.VISIBLE);
            ivIndigator02.setVisibility(View.VISIBLE);
            ivIndigator03.setVisibility(View.VISIBLE);
            ivIndigator04.setVisibility(View.VISIBLE);
            ivIndigator05.setVisibility(View.VISIBLE);
        } else {
            switch (size) {
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

    private void showIndigator(int pos) {
        ivIndigator01.setBackgroundResource(R.drawable.icon_indigatior);
        ivIndigator02.setBackgroundResource(R.drawable.icon_indigatior);
        ivIndigator03.setBackgroundResource(R.drawable.icon_indigatior);
        ivIndigator04.setBackgroundResource(R.drawable.icon_indigatior);
        ivIndigator05.setBackgroundResource(R.drawable.icon_indigatior);

        switch (pos) {
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

    private Handler resultCartAddHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    Animation bottomUp = AnimationUtils.loadAnimation(DetailProductActivity.this, R.anim.slide_in_down);
                    llayoutForOption.startAnimation(bottomUp);
                    llayoutForOption.setVisibility(View.GONE);
                    isPanelShown = false;

                    for(int i=0; i<productOptionColorAdapter.getItemCount();i++){
                        productOptionColorAdapter.getItem(i).setSelect(false);
                    }
                    productOptionColorAdapter.notifyDataSetChanged();

                    for(int i=0; i<productOptionSizeAdapter.getItemCount();i++){
                        productOptionSizeAdapter.getItem(i).setSelect(false);
                    }
                    productOptionSizeAdapter.notifyDataSetChanged();

                    Toast.makeText(DetailProductActivity.this, "장바구니에 담겼습니다.", Toast.LENGTH_SHORT).show();

                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };

    private Handler resultWriteHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {

                    Toast.makeText(DetailProductActivity.this, "상품 문의가 완료되었습니다.", Toast.LENGTH_SHORT).show();

                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };
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

                        productOptionColorAdapter.clear();
                        productOptionSizeAdapter.clear();
                        if (response.getOption() != null) {
                            if (response.getOption().getColor() != null && response.getOption().getColor().size() > 0) {
                                productOptionColorAdapter.addAll(response.getOption().getColor());
                            }

                            if (response.getOption().getSize() != null && response.getOption().getSize().size() > 0) {
                                productOptionSizeAdapter.addAll(response.getOption().getSize());
                            }
                        }
                        if (response.getImage() != null && response.getImage().size() > 0) {
                            initIndigator(response.getImage().size());

                            list.addAll(response.getImage());
                            DetailProductViewAdapter detailProductViewAdapter = new DetailProductViewAdapter(DetailProductActivity.this, list);
                            viewPager.setAdapter(detailProductViewAdapter);
                            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {
                                    showIndigator(position % 5);
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });
                        } else {
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


                                } else {
                                    ivStoreLogo.setVisibility(View.GONE);
                                }

                                if (info.getKeyword() != null && info.getKeyword().indexOf(",") > -1) {
                                    String[] tag = info.getKeyword().split(",");
                                    LayoutInflater inflater = null;
                                    View header = inflater.inflate(R.layout.adapter_deatail_product_keyword, null);
                                    TextView tvKeyWord = header.findViewById(R.id.tvKeyWord);
                                    if (tag != null && tag.length > 0) {
                                        llayoutForKeword.setVisibility(View.VISIBLE);
                                        llayoutForKeword.removeAllViews();
                                        for (int i = 0; i < tag.length; i++) {
                                            tvKeyWord.setText(tag[i]);
                                        }
                                        llayoutForKeword.addView(header);
                                    } else {
                                        llayoutForKeword.setVisibility(View.GONE);
                                    }
                                } else {
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
