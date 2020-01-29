package com.wholesale.wholesalefriends.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.retail_market.DetailProductActivity;
import com.wholesale.wholesalefriends.main.adapter.HomeMain03ListAdapter;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.main.data.ProductListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeMainViewPager03Fragment extends Fragment {

    private static Context ctx;
    private RecyclerView recyclerView;
    private HomeMain03ListAdapter homeMain03ListAdapter = null;
    private ArrayList<ProductListData> listDatas = new ArrayList<>();

    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;

    public static HomeMainViewPager03Fragment newInstance(Context context) {
        ctx = context;
        HomeMainViewPager03Fragment fragment = new HomeMainViewPager03Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewpager_home_03, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        homeMain03ListAdapter = new HomeMain03ListAdapter(getActivity(), listDatas);
        homeMain03ListAdapter.setAdapterListener(new HomeMain03ListAdapter.AdapterListener() {
            @Override
            public void moreLoading(int page) {

                if(isExistMore){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            loadList(page+1,"","","");
                        }
                    },500);
                    isExistMore =false;
                }
            }

            @Override
            public void onClickItem(ProductListData data, int pos) {
                Intent intent = new Intent(ctx, DetailProductActivity.class);
                intent.putExtra(Constant.CommonKey.product_id,data.getId());
                intent.putExtra(Constant.CommonKey.product_name,data.getName());
                startActivity(intent);
            }
        });
        homeMain03ListAdapter.setnCurrentPage(1);
        recyclerView.setLayoutManager(new WrapContentGridLayoutManager(getActivity(), 2));

        recyclerView.setAdapter(homeMain03ListAdapter);
        loadList(1,"","","");
        return view;
    }

    private void loadList(int page,String category,String is_sale,String store_id) {
        homeMain03ListAdapter.setnCurrentPage(page);
        API.productList(ctx,page+"",category,is_sale,store_id,resultListHandler,errHandler);
    }

    private Handler resultListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    ProductListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), ProductListResponse.class);
                    if(productListResponse!=null && productListResponse.getList().getData().size()>0){

                        if(isSwipeRefresh){
                            homeMain03ListAdapter.clear();
                            homeMain03ListAdapter.addAll(productListResponse.getList().getData());
                            isSwipeRefresh = false;
                        }else{
                            if(homeMain03ListAdapter.getItemCount()>0){
                                int nowSize = homeMain03ListAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getList().getData().size();i++){
                                    homeMain03ListAdapter.add(productListResponse.getList().getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getList().getData().size();i++){
                                    homeMain03ListAdapter.add(productListResponse.getList().getData().get(i),i);
                                }
                            }
                        }

                        if(productListResponse.getList().getData().size() >= Constant.PAGE_GO){
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

    private Handler errHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(ctx, false, true);
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
