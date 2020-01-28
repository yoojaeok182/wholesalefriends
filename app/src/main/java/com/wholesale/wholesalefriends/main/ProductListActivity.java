package com.wholesale.wholesalefriends.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.HomeMain01ListAdapter;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.main.data.ProductListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class ProductListActivity extends GroupActivity {


    private String category_code;
    private String category_name;
    private RecyclerView recyclerView;
    private HomeMain01ListAdapter homeMain01ListAdapter = null;
    private ArrayList<ProductListData> listDatas = new ArrayList<>();
    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;
    private RelativeLayout btnBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Intent intent = getIntent();
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        if (intent.hasExtra(Constant.CommonKey.category_code)) {
            category_code = intent.getExtras().getString(Constant.CommonKey.category_code);
        }
        if (intent.hasExtra(Constant.CommonKey.category_name)) {
            category_name = intent.getExtras().getString(Constant.CommonKey.category_name);
        }

       if(category_name!=null && category_name.length()>0) tvTitle.setText(category_name);
        else tvTitle.setText("상품");

        recyclerView = findViewById(R.id.recyclerView);
        isSwipeRefresh = true;

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        homeMain01ListAdapter = new HomeMain01ListAdapter(this, listDatas);
        homeMain01ListAdapter.setAdapterListener(new HomeMain01ListAdapter.AdapterListener() {
            @Override
            public void moreLoading(int page) {
                if (isExistMore) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            loadList(page + 1, category_code, "", "");
                        }
                    }, 500);
                    isExistMore = false;
                }
            }

            @Override
            public void onClickItem(ProductListData data, int pos) {
                Intent intent = new Intent(ProductListActivity.this, DetailProductActivity.class);
                intent.putExtra(Constant.CommonKey.product_id, data.getId());
                intent.putExtra(Constant.CommonKey.product_name, data.getName());
                startActivity(intent);
            }
        });
        homeMain01ListAdapter.setnCurrentPage(1);
        recyclerView.setLayoutManager(new WrapContentGridLayoutManager(this, 3));

        recyclerView.setAdapter(homeMain01ListAdapter);
        loadList(1, category_code != null ? (category_code + "") : "", "", "");

    }

    private void loadList(int page, String category, String is_sale, String store_id) {
        homeMain01ListAdapter.setnCurrentPage(page);
        API.productList(ProductListActivity.this, page + "", category, is_sale, store_id, resultListHandler, errHandler);
    }

    private Handler resultListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {
                    ProductListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), ProductListResponse.class);
                    if (productListResponse != null && productListResponse.getList().getData().size() > 0) {

                        if (isSwipeRefresh) {
                            homeMain01ListAdapter.clear();
                            homeMain01ListAdapter.addAll(productListResponse.getList().getData());
                            isSwipeRefresh = false;
                        } else {
                            if (homeMain01ListAdapter.getItemCount() > 0) {
                                int nowSize = homeMain01ListAdapter.getItemCount();
                                for (int i = nowSize; i < nowSize + productListResponse.getList().getData().size(); i++) {
                                    homeMain01ListAdapter.add(productListResponse.getList().getData().get(i - nowSize), i);
                                }
                            } else {
                                for (int i = 0; i < productListResponse.getList().getData().size(); i++) {
                                    homeMain01ListAdapter.add(productListResponse.getList().getData().get(i), i);
                                }
                            }
                        }

                        if (productListResponse.getList().getData().size() >= Constant.PAGE_GO) {
                            isExistMore = true;
                        } else {
                            isExistMore = false;
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
        }
    };

    private Handler errHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(ProductListActivity.this, false, true);
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
