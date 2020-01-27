package com.wholesale.wholesalefriends.main.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.ProductListActivity;
import com.wholesale.wholesalefriends.main.adapter.CategoryStoreListAdapter;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.BestProductListResponse;
import com.wholesale.wholesalefriends.main.data.BuildSearchData;
import com.wholesale.wholesalefriends.main.data.BuildingListData;
import com.wholesale.wholesalefriends.main.data.BuildingListResponse;
import com.wholesale.wholesalefriends.main.data.BuildingSearchResponse;
import com.wholesale.wholesalefriends.main.data.CategoryListResponse;
import com.wholesale.wholesalefriends.main.data.CategoryStoreListData;
import com.wholesale.wholesalefriends.main.data.StoreListData;
import com.wholesale.wholesalefriends.main.data.StoreListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.main.join.JoinStep3Activity;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.AppData;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.widget.WrapContentGridLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class RetailCategoryFragment extends Fragment {

    private LinearLayout btnCategoryItem01;
    private LinearLayout btnCategoryItem02;
    private LinearLayout btnCategoryItem03;
    private LinearLayout btnCategoryItem04;
    private LinearLayout btnCategoryItem05;
    private LinearLayout btnCategoryItem06;
    private LinearLayout btnCategoryItem07;
    private LinearLayout btnCategoryItem08;
    private LinearLayout btnCategoryItem09;
    private LinearLayout btnCategoryItem10;
    private LinearLayout btnCategoryItem11;
    private LinearLayout btnCategoryItem12;
    private TextView tvCategorySearch;
    private RecyclerView recyclerView;
    private LinearLayout btnCategorySearch;

    private CategoryStoreListAdapter categoryStoreListAdapter;
    private ArrayList<StoreListData>list= new ArrayList<>();
    private boolean isSwipeRefresh = false;
    private boolean isExistMore = false;

    private String building_id ="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_retail_category, container, false);

        btnCategoryItem01 = view.findViewById(R.id.btnCategoryItem01);
        btnCategoryItem02 = view.findViewById(R.id.btnCategoryItem02);
        btnCategoryItem03 = view.findViewById(R.id.btnCategoryItem03);
        btnCategoryItem04 = view.findViewById(R.id.btnCategoryItem04);
        btnCategoryItem05 = view.findViewById(R.id.btnCategoryItem05);
        btnCategoryItem06 = view.findViewById(R.id.btnCategoryItem06);
        btnCategoryItem07 = view.findViewById(R.id.btnCategoryItem07);
        btnCategoryItem08 = view.findViewById(R.id.btnCategoryItem08);
        btnCategoryItem09 = view.findViewById(R.id.btnCategoryItem09);
        btnCategoryItem10 = view.findViewById(R.id.btnCategoryItem10);
        btnCategoryItem11 = view.findViewById(R.id.btnCategoryItem11);
        btnCategoryItem12 = view.findViewById(R.id.btnCategoryItem12);
        tvCategorySearch = view.findViewById(R.id.tvCategorySearch);
        recyclerView = view.findViewById(R.id.recyclerView);

        btnCategorySearch = view.findViewById(R.id.btnCategorySearch);

        init();

        return view;
    }

    private void init() {
        categoryStoreListAdapter = new CategoryStoreListAdapter(getActivity(),list);
        recyclerView.setLayoutManager(new WrapContentGridLayoutManager(getActivity(), 2));
        categoryStoreListAdapter.setListSelectItemListener(new CategoryStoreListAdapter.ListSelectItemListener() {
            @Override
            public void moreLoading(int page) {
                if(isExistMore){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            loadStoreList(page+1,building_id);
                        }
                    },500);
                    isExistMore =false;
                }
            }

            @Override
            public void itemClick(int pos, StoreListData data) {
                if(!(data.getFavorites()!=null && data.getFavorites().equals("1"))){
                    final  CommonAlertDialog dg = new CommonAlertDialog(getActivity(),true,true);
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
        recyclerView.setAdapter(categoryStoreListAdapter);

        String[] items =getResources().getStringArray(R.array.store_view_item);
        btnCategorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API.buildingList(getActivity(), resultHandler2,errHandler);

            }
        });
        btnCategoryItem01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(0,Constant.CommonCategoryMenuName.arrHomeMenu1[0]);
            }
        });
        btnCategoryItem02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(1,Constant.CommonCategoryMenuName.arrHomeMenu1[1]);
            }
        });
        btnCategoryItem03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(2,Constant.CommonCategoryMenuName.arrHomeMenu1[2]);
            }
        });
        btnCategoryItem04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(3,Constant.CommonCategoryMenuName.arrHomeMenu1[3]);
            }
        });
        btnCategoryItem05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(4,Constant.CommonCategoryMenuName.arrHomeMenu1[4]);
            }
        });
        btnCategoryItem06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(5,Constant.CommonCategoryMenuName.arrHomeMenu1[5]);
            }
        });
        btnCategoryItem07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(6,Constant.CommonCategoryMenuName.arrHomeMenu1[6]);
            }
        });
        btnCategoryItem08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(7,Constant.CommonCategoryMenuName.arrHomeMenu1[7]);
            }
        });
        btnCategoryItem09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(8,Constant.CommonCategoryMenuName.arrHomeMenu1[8]);
            }
        });
        btnCategoryItem10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(9,Constant.CommonCategoryMenuName.arrHomeMenu1[9]);
            }
        });
        btnCategoryItem11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(10,Constant.CommonCategoryMenuName.arrHomeMenu1[10]);
            }
        });
        btnCategoryItem12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProductList(11,Constant.CommonCategoryMenuName.arrHomeMenu1[11]);
            }
        });
        clearData();
        loadStoreList(1,"");
    }

    private void clearData(){
        categoryStoreListAdapter.clear();
        isSwipeRefresh = true;
        isExistMore = false;
    }
    private void goProductList(int position, String menuName){
        if(menuName.equals("전체")){
            Intent intent = new Intent(getActivity(), ProductListActivity.class);
            startActivity(intent);
        }else{
            CategoryListResponse response = AppData.getInstance().getCategoryListResponse();
            if(response!=null &&response.getList().size()>0){
                for(int i=0; i<response.getList().size();i++){
                    if(menuName.indexOf(response.getList().get(i).getName())>-1){
                        Intent intent = new Intent(getActivity(), ProductListActivity.class);
                        intent.putExtra(Constant.CommonKey.category_code,response.getList().get(i).getCode()+"");
                        startActivity(intent);
                    }
                }
            }
        }


    }

    private void loadStoreList(int page,String building_id){
        categoryStoreListAdapter.setnCurrentPage(page);
        API.storeLList(getActivity(),page+"",building_id, SharedPreference.getSharedPreference(getActivity(),Constant.CommonKey.user_no)+"",resultListHandler,errHandler);
    }


    private Handler resultHandler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){

                    BuildingListResponse buildingSearchResponse2 = new Gson().fromJson(msg.obj.toString(), BuildingListResponse.class);

                    if(buildingSearchResponse2!=null && buildingSearchResponse2.getList().size()>0){
                        ArrayList<BuildingListData> arrayList = new ArrayList<>();
                        arrayList.addAll(buildingSearchResponse2.getList());
                        String[] items = new String[arrayList.size()];
                        for(int i=0; i<arrayList.size();i++){
                            items[i]= arrayList.get(i).getName();
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setCancelable(false);
                        builder.setTitle("상가별 보기");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                building_id = buildingSearchResponse2.getList().get(which).getBuilding_id()+"";
                                clearData();
                              loadStoreList(1,building_id);
                            }
                        });
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //the user clicked on Cancel
                            }
                        });
                        builder.show();

                    }


                }
            }catch (Throwable e){
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
                    StoreListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), StoreListResponse.class);
                    if(productListResponse!=null && productListResponse.getData().size()>0){

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
    private Handler errHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject jsonObject = (JSONObject) msg.obj;

                if (jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(getActivity(), false, true);
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
