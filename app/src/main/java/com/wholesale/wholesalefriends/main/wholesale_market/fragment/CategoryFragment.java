package com.wholesale.wholesalefriends.main.wholesale_market.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;

public class CategoryFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
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
        tvCategorySearch =view. findViewById(R.id.tvCategorySearch);
        recyclerView = view.findViewById(R.id.recyclerView);

        return view;

    }
}
