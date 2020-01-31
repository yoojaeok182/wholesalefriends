package com.wholesale.wholesalefriends.main.retail_market.fragment;

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
import com.wholesale.wholesalefriends.main.adapter.NoticeAdapter;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.NoticeListData;
import com.wholesale.wholesalefriends.main.data.NoticeListResponse;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.widget.WrapContentLinearLayoutManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class RetailNoticeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NoticeListData> list = new ArrayList<>();
    private NoticeAdapter noticeAdapter;

    private boolean isSwipeRefresh;
    private boolean isExistMore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_retail_notice, container, false);
        recyclerView =view.findViewById(R.id.recyclerView);

        init();

        return view;

    }

    private void init(){
        noticeAdapter = new NoticeAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        recyclerView.setAdapter(noticeAdapter);

        clearData();
        loadList(1);
    }

    private void clearData(){
        isSwipeRefresh = true;
        isExistMore =false;
        noticeAdapter.clear();
    }
    private void loadList(int page){
        noticeAdapter.setnCurrentPage(page);
        API.noticeList(getActivity(),page+"",resultListHandler,errHandler);
    }

    private Handler resultListHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    NoticeListResponse productListResponse = new Gson().fromJson(jsonObject.toString(), NoticeListResponse.class);
                    if(productListResponse!=null && productListResponse.getData().size()>0){

                        if(isSwipeRefresh){
                            noticeAdapter.clear();
                            noticeAdapter.addAll(productListResponse.getData());
                            isSwipeRefresh = false;
                        }else{
                            if(noticeAdapter.getItemCount()>0){
                                int nowSize = noticeAdapter.getItemCount();
                                for(int i=nowSize; i<nowSize+productListResponse.getData().size();i++){
                                    noticeAdapter.add(productListResponse.getData().get(i-nowSize),i);
                                }
                            }else{
                                for(int i=0; i<productListResponse.getData().size();i++){
                                    noticeAdapter.add(productListResponse.getData().get(i),i);
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

                if (!jsonObject.getBoolean("result")) {

                    if (jsonObject.getString("error") != null && jsonObject.getString("error").length() > 0) {
                        final CommonAlertDialog dg = new CommonAlertDialog(getActivity(), false, true);
                        dg.setTitle("공지사항");
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
