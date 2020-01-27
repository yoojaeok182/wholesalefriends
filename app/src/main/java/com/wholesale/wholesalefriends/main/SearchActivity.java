package com.wholesale.wholesalefriends.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.CategoryStoreListAdapter;
import com.wholesale.wholesalefriends.main.adapter.HomeMain01ListAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.NoticeListResponse;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.main.data.ProductListResponse;
import com.wholesale.wholesalefriends.main.data.RecommWordResponse;
import com.wholesale.wholesalefriends.main.data.StoreListData;
import com.wholesale.wholesalefriends.main.data.StoreListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class SearchActivity extends GroupActivity {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ckAll;
    private LinearLayout btnCkAll;
    private ImageView ckProduct;
    private LinearLayout btnCkProduct;
    private ImageView ckShopSearch;
    private LinearLayout btnCkShopSearch;
    private EditText edtSearch;
    private TagContainerLayout tagGroup;
    private LinearLayout llayoutForRecommKeword;
    private RecyclerView recyclerView;
    private LinearLayout llayoutFOrSearch;

    private RecyclerView recyclerViewShop;
    private HomeMain01ListAdapter homeMain01ListAdapter = null;

    private ArrayList<ProductListData> listDatas = new ArrayList<>();
    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;


    private CategoryStoreListAdapter categoryStoreListAdapter;
    private ArrayList<StoreListData>list= new ArrayList<>();




    private String strKeyword ="";

    private boolean isCheckAll;
    private boolean isCheckProduct;
    private boolean isCheckShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ckAll = findViewById(R.id.ckAll);
        btnCkAll = findViewById(R.id.btnCkAll);
        ckProduct = findViewById(R.id.ckProduct);
        btnCkProduct = findViewById(R.id.btnCkProduct);
        ckShopSearch = findViewById(R.id.ckShopSearch);
        btnCkShopSearch = findViewById(R.id.btnCkShopSearch);
        edtSearch = findViewById(R.id.edtSearch);
        tagGroup = findViewById(R.id.tagGroup);
        llayoutForRecommKeword = findViewById(R.id.llayoutForRecommKeword);
        recyclerView = findViewById(R.id.recyclerView);
        llayoutFOrSearch = findViewById(R.id.llayoutFOrSearch);

        recyclerViewShop = findViewById(R.id.recyclerViewShop);

        checkType(0);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTitle.setText("검색");

        btnCkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkType(0);
            }
        });

        btnCkProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkType(1);
            }
        });

        btnCkShopSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkType(2);
            }
        });
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    if(edtSearch.getText().toString()!=null && edtSearch.getText().toString().length()>0){

                        strKeyword = edtSearch.getText().toString();
                    }else{
                        strKeyword ="";
                    }

                    if(isCheckAll){
                        clearProductData();
                        loadList(1,"","",",",strKeyword);
                    }else if(isCheckProduct){
                        clearProductData();
                        loadList(1,"","",",",strKeyword);
                    }else if(isCheckShop){
                        clearShopData();
                        loadStoreList(1,strKeyword);
                    }

                }
                return false;
            }
        });


        isSwipeRefresh = true;

        categoryStoreListAdapter = new CategoryStoreListAdapter(this,list);
        recyclerViewShop.setLayoutManager(new WrapContentGridLayoutManager(this, 3));
        categoryStoreListAdapter.setListSelectItemListener(new CategoryStoreListAdapter.ListSelectItemListener() {
            @Override
            public void moreLoading(int page) {
                if(isExistMore){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            loadStoreList(page+1,strKeyword);
                        }
                    },500);
                    isExistMore =false;
                }
            }

            @Override
            public void itemClick(int pos, StoreListData data) {
                if(!(data.getFavorites()!=null && data.getFavorites().equals("1"))){
                    final  CommonAlertDialog dg = new CommonAlertDialog(SearchActivity.this,true,true);
                    dg.setTitle("거래처 추가 요청");
                    dg.setMessage("거래처 추가를\n요청하시겠습니까?");
                    dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if(dg.isOk()){
                                //TODO
                                categoryStoreListAdapter.getItem(pos).setFavorites("1");
                                categoryStoreListAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }

            }
        });
        recyclerViewShop.setAdapter(categoryStoreListAdapter);

        homeMain01ListAdapter = new HomeMain01ListAdapter(this, listDatas);
        homeMain01ListAdapter.setAdapterListener(new HomeMain01ListAdapter.AdapterListener() {
            @Override
            public void moreLoading(int page) {
                if(isExistMore){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            loadList(page+1,"","","",strKeyword);

                        }
                    },500);
                    isExistMore =false;
                }
            }

            @Override
            public void onClickItem(ProductListData data, int pos) {
                Intent intent = new Intent(SearchActivity.this, DetailProductActivity.class);
                intent.putExtra(Constant.CommonKey.product_id,data.getId());
                intent.putExtra(Constant.CommonKey.product_name,data.getName());
                startActivity(intent);
            }
        });
        homeMain01ListAdapter.setnCurrentPage(1);
        recyclerView.setLayoutManager(new WrapContentGridLayoutManager(this, 3));

        if(isCheckAll ){

        }else if(isCheckProduct){

        }else if(isCheckShop){

        }
        recyclerView.setAdapter(homeMain01ListAdapter);
        loadRecommWord();
    }


    private void checkType(int pos){
        isCheckShop = false;
        isCheckProduct = false;
        isCheckAll  = false;
        ckAll.setBackgroundResource(R.drawable.check_default);
        ckProduct.setBackgroundResource(R.drawable.check_default);
        ckShopSearch.setBackgroundResource(R.drawable.check_default);
        recyclerView.setVisibility(View.GONE);
        recyclerViewShop.setVisibility(View.GONE);
        switch (pos){
            case 0:
                isCheckAll = true;
                ckAll.setBackgroundResource(R.drawable.check_on);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case 1:
                isCheckProduct =true;
                ckProduct.setBackgroundResource(R.drawable.check_on);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case 2:
                recyclerViewShop.setVisibility(View.VISIBLE);
                isCheckShop =true;
                ckShopSearch.setBackgroundResource(R.drawable.check_on);
                break;
        }
    }

    private void clearShopData(){

    }
    private void clearProductData(){
        homeMain01ListAdapter.clear();
        isSwipeRefresh = true;
        isExistMore = false;
    }
    private void loadList(int page,String category,String is_sale,String store_id,String keyword) {
        homeMain01ListAdapter.setnCurrentPage(page);
        API.productList(SearchActivity.this,page+"",category,is_sale,store_id,keyword,resultProductListHandler,errHandler);
    }

    private void loadStoreList(int page,String strKeyword){
        categoryStoreListAdapter.setnCurrentPage(page);
        API.storeLList(SearchActivity.this,page+"","",
                SharedPreference.getIntSharedPreference(SearchActivity.this,Constant.CommonKey.user_no)+"",strKeyword,resultShopListHandler,errHandler);
    }

    private Handler resultShopListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                llayoutForRecommKeword.setVisibility(View.VISIBLE);
                llayoutFOrSearch.setVisibility(View.GONE);
                if(jsonObject.getBoolean("result")){
                    StoreListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), StoreListResponse.class);
                    if(productListResponse!=null && productListResponse.getData().size()>0){
                        llayoutForRecommKeword.setVisibility(View.GONE);
                        llayoutFOrSearch.setVisibility(View.VISIBLE);
                        if(isSwipeRefresh){
                            categoryStoreListAdapter.clear();
                            categoryStoreListAdapter.addAll(productListResponse.getData());
                            isSwipeRefresh = false;
                        }else{
                            if(categoryStoreListAdapter.getItemCount()>0){
                                int nowSize = categoryStoreListAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getData().size();i++){
                                    categoryStoreListAdapter.add(productListResponse.getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getData().size();i++){
                                    categoryStoreListAdapter.add(productListResponse.getData().get(i),i);
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

    private Handler resultProductListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;
                llayoutForRecommKeword.setVisibility(View.VISIBLE);
                llayoutFOrSearch.setVisibility(View.GONE);
                if(jsonObject.getBoolean("result")){
                    ProductListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), ProductListResponse.class);
                    if(productListResponse!=null && productListResponse.getList().size()>0){
                        llayoutForRecommKeword.setVisibility(View.GONE);

                        llayoutFOrSearch.setVisibility(View.VISIBLE);
                        if(isSwipeRefresh){
                            homeMain01ListAdapter.clear();
                            homeMain01ListAdapter.addAll(productListResponse.getList());
                            isSwipeRefresh = false;
                        }else{
                            if(homeMain01ListAdapter.getItemCount()>0){
                                int nowSize = homeMain01ListAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getList().size();i++){
                                    homeMain01ListAdapter.add(productListResponse.getList().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getList().size();i++){
                                    homeMain01ListAdapter.add(productListResponse.getList().get(i),i);
                                }
                            }
                        }

                        if(productListResponse.getList().size() >= Constant.PAGE_GO){
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



    private void  loadRecommWord(){
        API.recomWord(this,resultListHandler,errHandler);
    }


    private Handler resultListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    RecommWordResponse recommWordResponse = new Gson().fromJson(jsonObject.toString(), RecommWordResponse.class);
                    if(recommWordResponse!=null && recommWordResponse.getList().size()>0){

                        for(int i= 0 ; i<recommWordResponse.getList().size() ; i++){
                            tagGroup.addTag(recommWordResponse.getList().get(i).getWord());
                        }


                        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
                            @Override
                            public void onTagClick(int position, String text) {
                                strKeyword = text;
                                if(isCheckAll){
                                    clearProductData();
                                    loadList(1,"","",",",strKeyword);
                                }else if(isCheckProduct){
                                    clearProductData();
                                    loadList(1,"","",",",strKeyword);
                                }else if(isCheckShop){
                                    clearShopData();
                                    loadStoreList(1,strKeyword);
                                }
                            }

                            @Override
                            public void onTagLongClick(int position, String text) {

                            }

                            @Override
                            public void onSelectedTagDrag(int position, String text) {

                            }

                            @Override
                            public void onTagCrossClick(int position) {

                            }
                        });
                    }else{
                    }
                }else{
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };

    private Handler errHandler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                llayoutFOrSearch.setVisibility(View.GONE);
                llayoutForRecommKeword.setVisibility(View.VISIBLE);
                if (!jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(SearchActivity.this, false, true);
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
    private Handler errHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(SearchActivity.this, false, true);
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
