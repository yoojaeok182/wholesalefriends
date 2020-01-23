package com.wholesale.wholesalefriends.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.adapter.HomeMainPageAdapter;

public class RetailHomeFragment extends Fragment {

    private ViewPager viewPager;

    private HomeMainPageAdapter homeMainPageAdapter;
    private TextView tvTobMenu01;
    private LinearLayout llayoutTopMenu01;
    private TextView tvTobMenu02;
    private LinearLayout llayoutTopMenu02;
    private TextView tvTobMenu03;
    private LinearLayout llayoutTopMenu03;
    private LinearLayout llayoutTopLine01;
    private LinearLayout llayoutTopLine02;
    private LinearLayout llayoutTopLine03;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_retail_home, container, false);
        tvTobMenu01 = view.findViewById(R.id.tvTobMenu01);
        llayoutTopMenu01 =view. findViewById(R.id.llayoutTopMenu01);
        tvTobMenu02 = view.findViewById(R.id.tvTobMenu02);
        llayoutTopMenu02 = view.findViewById(R.id.llayoutTopMenu02);
        tvTobMenu03 = view.findViewById(R.id.tvTobMenu03);
        llayoutTopMenu03 =view. findViewById(R.id.llayoutTopMenu03);
        llayoutTopLine01 =view. findViewById(R.id.llayoutTopLine01);
        llayoutTopLine02 = view.findViewById(R.id.llayoutTopLine02);
        llayoutTopLine03 =view. findViewById(R.id.llayoutTopLine03);
        viewPager = view.findViewById(R.id.viewPager);

        homeMainPageAdapter = new HomeMainPageAdapter(getChildFragmentManager(), getActivity());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        viewPager.setAdapter(homeMainPageAdapter);

        viewPager.setCurrentItem(0);

        llayoutTopLine01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        llayoutTopLine02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        llayoutTopLine03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });
        return view;

    }

    private void setCurrentTab(int pos){
        tvTobMenu01.setTextColor(getResources().getColor(R.color.btn_textcolor_03_select));
        tvTobMenu02.setTextColor(getResources().getColor(R.color.btn_textcolor_03_select));
        tvTobMenu03.setTextColor(getResources().getColor(R.color.btn_textcolor_03_select));

        llayoutTopLine01.setVisibility(View.INVISIBLE);
        llayoutTopLine02.setVisibility(View.INVISIBLE);
        llayoutTopLine03.setVisibility(View.INVISIBLE);

        switch (pos)
        {
            case 0:
                tvTobMenu01.setTextColor(getResources().getColor(R.color.color_text_07));
                llayoutTopLine01.setVisibility(View.VISIBLE);

                break;
            case 1:
                tvTobMenu02.setTextColor(getResources().getColor(R.color.color_text_07));
                llayoutTopLine02.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvTobMenu03.setTextColor(getResources().getColor(R.color.color_text_07));
                llayoutTopLine03.setVisibility(View.VISIBLE);
                break;
        }

    }
}
