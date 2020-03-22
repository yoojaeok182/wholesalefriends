package com.wholesale.wholesalefriends.main.wholesale_market;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.CategoryStoreListAdapter;
import com.wholesale.wholesalefriends.main.adapter.DetailProductViewAdapter;
import com.wholesale.wholesalefriends.main.adapter.WholesaleDetailProductInquireAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ProductListResponse;
import com.wholesale.wholesalefriends.main.data.ProductQnaListData;
import com.wholesale.wholesalefriends.main.data.ProductQnaListResponse;
import com.wholesale.wholesalefriends.main.data.ProductViewImageData;
import com.wholesale.wholesalefriends.main.data.ProductViewWholesaleResponse;
import com.wholesale.wholesalefriends.main.data.ProductWholesaleViewInfoData;
import com.wholesale.wholesalefriends.main.data.StoreListData;
import com.wholesale.wholesalefriends.main.data.StoreListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.module.util.ImageUtil;
import com.wholesale.wholesalefriends.module.util.Util;
import com.wholesale.wholesalefriends.widget.WrapContentLinearLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class DetailProduct2Activity extends GroupActivity {


    private ArrayList<ProductViewImageData> list = new ArrayList<>();

    private String product_name;
    private int product_id;
    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ViewPager viewPager;
    private ImageView ivIndigator01;
    private ImageView ivIndigator02;
    private ImageView ivIndigator03;
    private ImageView ivIndigator04;
    private ImageView ivIndigator05;
    private TextView tvProductInfo;
    private TextView tvProductName;
    private TextView tvProductPrice;
    private ImageView btnShare;
    private TextView tvStyle;
    private Button btnModify;
    private Button btnDelete;
    private Button btnSoldOut;
    private TextView tvColor;
    private TextView tvSize;
    private TextView tvMix;
    private EditText tvProducingCountry;
    private TextView tvInfo;
    private ImageView ivThick01;
    private ImageView ivThick02;
    private ImageView ivThick03;
    private ImageView ivTrans01;
    private ImageView ivTrans02;
    private ImageView ivTrans03;
    private ImageView ivStretch01;
    private ImageView ivStretch02;
    private ImageView ivStretch03;
    private ImageView ivStretch04;
    private ImageView ivFabric01;
    private ImageView ivFabric02;
    private ImageView ivArrow;
    private LinearLayout btnProductInquire;
    private RecyclerView recyclerView;
    private ImageView ivCheck;
    private EditText edtComment;
    private Button btnWrite;
    private LinearLayout llayoutForComment;
    private LinearLayout llayoutForProductInqurireList;
    private LinearLayout llayoutForGuide;

    private ImageButton btnClipBoard;
    private boolean isShowUp;
    private int nSoldOut;

    private ProductWholesaleViewInfoData productWholesaleViewInfoData;
    private TextView tvWashingInfo01;
    private TextView tvWashingInfo02;
    private TextView tvWashingInfo03;
    private TextView tvWashingInfo04;
    private TextView tvWashingInfo05;
    private TextView tvWashingInfo06;
    private TextView tvWashingInfo07;
    private TextView tvWashingInfo08;


    private View rootPhoto;
//    private ImageView ivDummyPhoto;
    private int nViewHeight;
    private WholesaleDetailProductInquireAdapter wholesaleDetailProductInquireAdapter;
    private ArrayList<ProductQnaListData>listQnaList= new ArrayList<>();
    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;


    private String strComment;
    private Integer select_q_id;
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
        viewPager = findViewById(R.id.viewPager);
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
        ivThick02 = findViewById(R.id.ivThick02);
        ivThick03 = findViewById(R.id.ivThick03);
        ivTrans01 = findViewById(R.id.ivTrans01);
        ivTrans02 = findViewById(R.id.ivTrans02);
        ivTrans03 = findViewById(R.id.ivTrans03);
        ivStretch01 = findViewById(R.id.ivStretch01);
        ivStretch02 = findViewById(R.id.ivStretch02);
        ivStretch03 = findViewById(R.id.ivStretch03);
        ivStretch04 = findViewById(R.id.ivStretch04);
        ivFabric01 = findViewById(R.id.ivFabric01);
        ivFabric02 = findViewById(R.id.ivFabric02);
        ivArrow = findViewById(R.id.ivArrow);
        btnProductInquire = findViewById(R.id.btnProductInquire);
        recyclerView = findViewById(R.id.recyclerView);
        ivCheck = findViewById(R.id.ivCheck);
        edtComment = findViewById(R.id.edtComment);
        btnWrite = findViewById(R.id.btnWrite);
        llayoutForComment = findViewById(R.id.llayoutForComment);
        llayoutForProductInqurireList = findViewById(R.id.llayoutForProductInqurireList);
        llayoutForGuide = findViewById(R.id.llayoutForGuide);
        tvStyle= findViewById(R.id.tvStyle);
        btnClipBoard = findViewById(R.id.btnClipBoard);
        tvWashingInfo01 = findViewById(R.id.tvWashingInfo01);
        tvWashingInfo02 = findViewById(R.id.tvWashingInfo02);
        tvWashingInfo03 = findViewById(R.id.tvWashingInfo03);
        tvWashingInfo04 = findViewById(R.id.tvWashingInfo04);
        tvWashingInfo05 = findViewById(R.id.tvWashingInfo05);
        tvWashingInfo06 = findViewById(R.id.tvWashingInfo06);
        tvWashingInfo07 = findViewById(R.id.tvWashingInfo07);
        tvWashingInfo08 = findViewById(R.id.tvWashingInfo08);

        rootPhoto = findViewById(R.id.rootPhoto);
//        ivDummyPhoto = findViewById(R.id.ivDummyPhoto);


        if (product_name != null) {
            tvTitle.setText(product_name);
        }

     /*   ivDummyPhoto.post(new Runnable() {
            @Override
            public void run() {
                ImageUtil.requestImageViewUI(DetailProduct2Activity.this,rootPhoto,ivDummyPhoto);
            }
        });*/

        wholesaleDetailProductInquireAdapter = new WholesaleDetailProductInquireAdapter(this,listQnaList);
        wholesaleDetailProductInquireAdapter.setAdapterListener(new WholesaleDetailProductInquireAdapter.AdapterListener() {
            @Override
            public void moreLoading(int page) {
                if(isExistMore){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            loadQnaList(page+1);
                        }
                    },500);
                    isExistMore =false;
                }
            }

            @Override
            public void onClickItem(ProductQnaListData data, int pos) {
                select_q_id = data.getId();
                llayoutForComment.setVisibility(View.VISIBLE);

            }
        });

        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
        recyclerView.setAdapter(wholesaleDetailProductInquireAdapter);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        if (!SharedPreference.getBooleanSharedPreference(this, Constant.CommonKey.guide_show)) {
            llayoutForGuide.setVisibility(View.VISIBLE);
            llayoutForGuide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    llayoutForGuide.setVisibility(View.GONE);
                    SharedPreference.putSharedPreference(DetailProduct2Activity.this, Constant.CommonKey.guide_show, true);
                }
            });

        }

        llayoutForProductInqurireList.post(new Runnable() {
            @Override
            public void run() {

                ViewGroup.LayoutParams params = llayoutForProductInqurireList.getLayoutParams();

                nViewHeight = params.height;
                setValueAnimation(isShowUp);
            }
        });

        llayoutForProductInqurireList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowUp) {
                    ivArrow.setBackgroundResource(R.drawable.icon_droparrow);

                    isShowUp = false;
                } else {
                    ivArrow.setBackgroundResource(R.drawable.icon_uparrow);
                    isShowUp = true;
                }
                setValueAnimation(isShowUp);

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SharedPreference.getIntSharedPreference(DetailProduct2Activity.this, Constant.CommonKey.level) == 2) {
                    final CommonAlertDialog dg = new CommonAlertDialog(DetailProduct2Activity.this, false, true);
                    dg.setTitle("상품 삭제");
                    dg.setMessage("상품 삭제는 대표자만\n가능합니다.");
                    dg.show();
                    return;
                }
                final CommonAlertDialog dg = new CommonAlertDialog(DetailProduct2Activity.this, true, true);
                dg.setTitle("상품 삭제");
                dg.setMessage("상품을 삭제하시겠습니까?");
                dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (dg.isOk()) {
                            API.productDel(DetailProduct2Activity.this, SharedPreference.getIntSharedPreference(DetailProduct2Activity.this, Constant.CommonKey.user_no) + "", product_id + "||", resultDelOkListHandler, errHandler);

                        }
                    }
                });
                dg.show();
            }
        });
        btnSoldOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productWholesaleViewInfoData.getIs_soldout() == 1) {
                    nSoldOut = 1;
                    API.restock(DetailProduct2Activity.this, SharedPreference.getIntSharedPreference(DetailProduct2Activity.this, Constant.CommonKey.user_no) + "", product_id + "||", resultOkListHandler, errHandler);

                } else {
                    nSoldOut = 0;
                    API.soldOut(DetailProduct2Activity.this, SharedPreference.getIntSharedPreference(DetailProduct2Activity.this, Constant.CommonKey.user_no) + "", product_id + "||", resultOkListHandler, errHandler);

                }

            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
               /* Intent intent1 = new Intent(DetailProduct2Activity.this, ProductRegistrationActivity.class);
                startActivity(intent1);*/
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Util.requestRecommend(DetailProduct2Activity.this);
            }
        });

        btnClipBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

                ClipData clipData = ClipData.newPlainText("wholesale", productWholesaleViewInfoData.getDetail());

                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(DetailProduct2Activity.this,"클립보드에 '상세설명'이 복사되엇습니다. ",Toast.LENGTH_SHORT).show();

            }
        });


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtComment.getText().toString()!=null && edtComment.getText().toString().length()>0){

                    strComment = edtComment.getText().toString();
                }else{
                    strComment ="";
                }

                if(strComment.length() ==0){
                    final  CommonAlertDialog dg = new CommonAlertDialog(DetailProduct2Activity.this,false,true);
                    dg.setTitle("상품 댓글");
                    dg.setMessage("댓글을 입력해주세요.");
                    dg.show();
                    return;
                }
                qnaWrite();
            }
        });


        edtComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    if(edtComment.getText().toString()!=null && edtComment.getText().toString().length()>0){

                        strComment = edtComment.getText().toString();
                    }else{
                        strComment ="";
                    }
                    if(strComment.length() ==0){
                        final  CommonAlertDialog dg = new CommonAlertDialog(DetailProduct2Activity.this,false,true);
                        dg.setTitle("상품 댓글");
                        dg.setMessage("댓글을 입력해주세요.");
                        dg.show();
                        return false;
                    }
                    qnaWrite();

                }
                return false;
            }
        });
        loadData();

        clearData();
        loadQnaList(1);


    }

    private void qnaWrite(){
        API.qnaReplyWrite(this,SharedPreference.getIntSharedPreference(this, Constant.CommonKey.user_no)+"",select_q_id+"",strComment,resultQnaWriteListHandler,errQnaHandler);
    }

    private Handler resultQnaWriteListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    llayoutForComment.setVisibility(View.GONE);
                    clearData();
                    loadQnaList(1);
                } else {
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };


    private Handler resultDelOkListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    Toast.makeText(DetailProduct2Activity.this, "해당 상품이 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                    if (Main2Activity.getInstance() != null) {
                        Main2Activity.getInstance().getHomeRefresh();
                    }
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 500);
                } else {
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };

    private Handler resultOkListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    if (nSoldOut == 1) { //재입고
                        Toast.makeText(DetailProduct2Activity.this, "재입고 처리가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        btnSoldOut.setText("품절");
                    } else {
                        Toast.makeText(DetailProduct2Activity.this, "품절 처리가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        btnSoldOut.setText("재입고");
                    }
                    if (Main2Activity.getInstance() != null) {
                        Main2Activity.getInstance().getHomeRefresh();
                    }
                    loadData();
                } else {
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };

    private void setValueAnimation(boolean isShowUp) {

        if(nViewHeight == 0) return;
        ViewGroup.LayoutParams layoutParams = llayoutForProductInqurireList.getLayoutParams();
        if(isShowUp){
            layoutParams.height = nViewHeight +Util.convertDpToPixel(380,this);
        }else{
            layoutParams.height = nViewHeight;
        }
        llayoutForProductInqurireList.setLayoutParams(layoutParams);

      /*  ValueAnimator anim = ValueAnimator.ofInt(llayoutForProductInqurireList.getMeasuredHeight(), isShowUp ? -200 : 200);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = llayoutForProductInqurireList.getLayoutParams();
                layoutParams.height = val;
                llayoutForProductInqurireList.setLayoutParams(layoutParams);

            }
        });
        anim.setDuration(1500);
        anim.start();*/
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

    private void clearData()
    {
        isExistMore = false;
        isSwipeRefresh = true;
        wholesaleDetailProductInquireAdapter.clear();
    }
    private void loadQnaList(int page){

        wholesaleDetailProductInquireAdapter.setnCurrentPage(page);
        API.qnaList(this,page+"",product_id+"",resultQnaListHandler,errHandler);
    }
    private void loadData() {
        API.wholesaleView(this, SharedPreference.getIntSharedPreference(this, Constant.CommonKey.user_no) + "", product_id + "",
                resultHandler, errHandler);
    }


    private Handler resultQnaListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;
                if(jsonObject.getBoolean("result")){
                    ProductQnaListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), ProductQnaListResponse.class);
                    if(productListResponse!=null && productListResponse.getData().size()>0){
                        if(isSwipeRefresh){
                            wholesaleDetailProductInquireAdapter.clear();
                            wholesaleDetailProductInquireAdapter.addAll(productListResponse.getData());
                            isSwipeRefresh = false;
                        }else{
                            if(wholesaleDetailProductInquireAdapter.getItemCount()>0){
                                int nowSize = wholesaleDetailProductInquireAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getData().size();i++){
                                    wholesaleDetailProductInquireAdapter.add(productListResponse.getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getData().size();i++){
                                    wholesaleDetailProductInquireAdapter.add(productListResponse.getData().get(i),i);
                                }
                            }
                        }

                        if(productListResponse.getData().size() >= Constant.PAGE_GO){
                            isExistMore = true;
                        }else{
                            isExistMore = false;
                        }
                    }else{
                        isExistMore = false;
                    }
                }else{
                    isExistMore = false;
                }
            }catch (Throwable e){
                isExistMore = false;
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
                if (jsonObject.getBoolean("result")) {
                    ProductViewWholesaleResponse response = new Gson().fromJson(jsonObject.toString(), ProductViewWholesaleResponse.class);
                    if (response != null) {
                        if (response.getInfo() != null) {
                            productWholesaleViewInfoData = response.getInfo();
                            if (productWholesaleViewInfoData != null) {
                                tvProductName.setText(productWholesaleViewInfoData.getName());
                                tvProductInfo.setText(productWholesaleViewInfoData.getCatagory());
                                tvProductPrice.setText(Util.getFormattedPrice(productWholesaleViewInfoData.getPrice()));
                                tvColor.setText(productWholesaleViewInfoData.getColor());
                                tvSize.setText(productWholesaleViewInfoData.getSize());
                                tvMix.setText(productWholesaleViewInfoData.getMaterial());
                                tvProducingCountry.setText(productWholesaleViewInfoData.getOrigin_info());
                                tvInfo.setText(productWholesaleViewInfoData.getDetail());
                                tvStyle.setText(requestStyle(Integer.valueOf(productWholesaleViewInfoData.getStyle_info())));
                                requestThick(Integer.valueOf(productWholesaleViewInfoData.getCloth_info_1()));
                                requestTrans(Integer.valueOf(productWholesaleViewInfoData.getCloth_info_2()));
                                requestClothOption3(Integer.valueOf(productWholesaleViewInfoData.getCloth_info_2()));
                                requestClothOption4(Integer.valueOf(productWholesaleViewInfoData.getCloth_info_4()));

                                String washingInfo = productWholesaleViewInfoData.getWashing_info();

                                if(washingInfo!=null && washingInfo.indexOf(",")>-1){
                                    String[] arr = washingInfo.split(",");

                                    for(int i=0; i<arr.length;i++){
                                        if(arr[i]!=null&& arr[i].length()>0){
                                            requestWashingInfo(Integer.valueOf(arr[i]));
                                        }
                                    }
                                }

                                if (response.getImage() != null && response.getImage().size() > 0) {
                                    initIndigator(response.getImage().size());

                                    list.addAll(response.getImage());
                                    DetailProductViewAdapter detailProductViewAdapter = new DetailProductViewAdapter(DetailProduct2Activity.this, list,productWholesaleViewInfoData.getIs_soldout());
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
                            }
                        }


                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };

    private String requestStyle(int value) {
        String[] style =getResources().getStringArray(R.array.style_code_name);

        if (value >= style.length) {

            value = 0;
        }

        return style[value];


    }

    private void requestThick(int value) {
        ivThick01.setBackgroundResource(R.drawable.box_off_03);
        ivThick02.setBackgroundResource(R.drawable.box_off_03);
        ivThick03.setBackgroundResource(R.drawable.box_off_03);


        switch (value) {
            case 1:
                ivThick01.setBackgroundResource(R.drawable.box_on_03);
                break;
            case 2:
                ivThick02.setBackgroundResource(R.drawable.box_on_03);
                break;
            case 3:
                ivThick03.setBackgroundResource(R.drawable.box_on_03);
                break;
        }


    }

    private void requestTrans(int value) {
        ivTrans01.setBackgroundResource(R.drawable.box_off_03);
        ivTrans02.setBackgroundResource(R.drawable.box_off_03);
        ivTrans03.setBackgroundResource(R.drawable.box_off_03);


        switch (value) {
            case 1:
                ivTrans01.setBackgroundResource(R.drawable.box_on_03);
                break;
            case 2:
                ivTrans02.setBackgroundResource(R.drawable.box_on_03);
                break;
            case 3:
                ivTrans03.setBackgroundResource(R.drawable.box_on_03);
                break;
        }


    }

    private void requestClothOption3(int value) {
        ivStretch01.setBackgroundResource(R.drawable.box_off_03);
        ivStretch02.setBackgroundResource(R.drawable.box_off_03);
        ivStretch03.setBackgroundResource(R.drawable.box_off_03);
        ivStretch04.setBackgroundResource(R.drawable.box_off_03);


        switch (value) {
            case 1:
                ivStretch01.setBackgroundResource(R.drawable.box_on_03);
                break;
            case 2:
                ivStretch02.setBackgroundResource(R.drawable.box_on_03);
                break;
            case 3:
                ivStretch03.setBackgroundResource(R.drawable.box_on_03);
                break;
            case 4:
                ivStretch04.setBackgroundResource(R.drawable.box_on_03);
                break;
        }


    }


    private void requestClothOption4(int value) {
        ivFabric01.setBackgroundResource(R.drawable.box_off_03);
        ivFabric02.setBackgroundResource(R.drawable.box_off_03);


        switch (value) {
            case 1:
                ivFabric01.setBackgroundResource(R.drawable.box_on_03);
                break;
            case 2:
                ivFabric02.setBackgroundResource(R.drawable.box_on_03);
                break;
        }


    }

    private void requestWashingInfo(int value) {
       /* tvWashingInfo01.setTextColor(getResources().getColor(R.color.color_text_04));
        tvWashingInfo02.setTextColor(getResources().getColor(R.color.color_text_04));
        tvWashingInfo03.setTextColor(getResources().getColor(R.color.color_text_04));
        tvWashingInfo04.setTextColor(getResources().getColor(R.color.color_text_04));
        tvWashingInfo05.setTextColor(getResources().getColor(R.color.color_text_04));
        tvWashingInfo06.setTextColor(getResources().getColor(R.color.color_text_04));
        tvWashingInfo07.setTextColor(getResources().getColor(R.color.color_text_04));
        tvWashingInfo08.setTextColor(getResources().getColor(R.color.color_text_04));*/


        switch (value) {
            case 1:
                tvWashingInfo01.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 2:
                tvWashingInfo02.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 3:
                tvWashingInfo03.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 4:
                tvWashingInfo04.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 5:
                tvWashingInfo05.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 6:
                tvWashingInfo06.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 7:
                tvWashingInfo07.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
            case 8:
                tvWashingInfo08.setTextColor(getResources().getColor(R.color.color_text_07));
                break;
        }


    }


    private Handler errQnaHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (!jsonObject.getBoolean("result")) {

                    llayoutForComment.setVisibility(View.GONE);
                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(DetailProduct2Activity.this, false, true);
                        dg.setTitle(product_name);
                        dg.setMessage(jsonObject.getString("error"));
                        dg.show();

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
                        final CommonAlertDialog dg = new CommonAlertDialog(DetailProduct2Activity.this, false, true);
                        dg.setTitle(product_name);
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
