package com.wholesale.wholesalefriends.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.NoticeAdapter;
import com.wholesale.wholesalefriends.main.data.NoticeListData;
import com.wholesale.wholesalefriends.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;

public class RetailNoticeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NoticeListData> list = new ArrayList<>();
    private NoticeAdapter noticeAdapter;
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
    }
}
