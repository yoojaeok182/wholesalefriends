package com.wholesale.wholesalefriends.main.retail_market.fragment;

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
import com.wholesale.wholesalefriends.main.adapter.HomeMain02ListAdapter;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.BestProductListData;
import com.wholesale.wholesalefriends.main.data.BestProductListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.widget.AutofitRecyclerView;
import com.wholesale.wholesalefriends.widget.MarginDecoration;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeMainViewPager02Fragment extends Fragment {

    private static Context ctx;
    private RecyclerView recyclerView;
    private HomeMain02ListAdapter homeMain02ListAdapter = null;
    private ArrayList<BestProductListData> listDatas = new ArrayList<>();

    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;

    public static HomeMainViewPager02Fragment newInstance(Context context) {
        ctx = context;
        HomeMainViewPager02Fragment fragment = new HomeMainViewPager02Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewpager_home_02, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        homeMain02ListAdapter = new HomeMain02ListAdapter(getActivity(), listDatas);
        homeMain02ListAdapter.setAdapterListener(new HomeMain02ListAdapter.AdapterListener() {
            @Override
            public void moreLoading(int page) {

                if(isExistMore){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            loadList(page+1);
                        }
                    },500);
                    isExistMore =false;
                }
            }

            @Override
            public void onClickItem(BestProductListData data, int pos) {
                Intent intent = new Intent(ctx, DetailProductActivity.class);
                intent.putExtra(Constant.CommonKey.product_id,data.getId());
                intent.putExtra(Constant.CommonKey.product_name,data.getName());
                startActivity(intent);
            }
        });
        homeMain02ListAdapter.setnCurrentPage(1);
       /* final WrapContentGridLayoutManager manager = (WrapContentGridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addItemDecoration(new MarginDecoration(getActivity(),ctx.getResources().getDimensionPixelSize(R.dimen.item_margin_half2)));*/

//        recyclerView.setLayoutManager(manager);
        recyclerView.setLayoutManager(new WrapContentGridLayoutManager(getActivity(),3));

        recyclerView.setAdapter(homeMain02ListAdapter);
        loadList(1);
        return view;
    }

    private void loadList(int page) {
        homeMain02ListAdapter.setnCurrentPage(page);
        API.bestProductList(ctx,page,resultListHandler,errHandler);
    }

    private Handler resultListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    BestProductListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), BestProductListResponse.class);
                    if(productListResponse!=null && productListResponse.getList().getData().size()>0){

                        if(isSwipeRefresh){
                            homeMain02ListAdapter.clear();
                            homeMain02ListAdapter.addAll(productListResponse.getList().getData());
                            isSwipeRefresh = false;
                        }else{
                            if(homeMain02ListAdapter.getItemCount()>0){
                                int nowSize = homeMain02ListAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getList().getData().size();i++){
                                    homeMain02ListAdapter.add(productListResponse.getList().getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getList().getData().size();i++){
                                    homeMain02ListAdapter.add(productListResponse.getList().getData().get(i),i);
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
                        final CommonAlertDialog dg = new CommonAlertDialog(ctx, false, false);
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
