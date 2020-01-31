package com.wholesale.wholesalefriends.main.retail_market.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wholesale.wholesalefriends.R;

public class RetailHomeFragment extends Fragment {


    private TextView tvTobMenu01;
    private LinearLayout llayoutTopMenu01;
    private TextView tvTobMenu02;
    private LinearLayout llayoutTopMenu02;
    private TextView tvTobMenu03;
    private LinearLayout llayoutTopMenu03;
    private LinearLayout llayoutTopLine01;
    private LinearLayout llayoutTopLine02;
    private LinearLayout llayoutTopLine03;


    private HomeMainViewPager01Fragment homeMainViewPager01Fragment;
    private HomeMainViewPager02Fragment homeMainViewPager02Fragment;
    private HomeMainViewPager03Fragment homeMainViewPager03Fragment;

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


        homeMainViewPager01Fragment = HomeMainViewPager01Fragment.newInstance(getActivity());
        homeMainViewPager02Fragment  = HomeMainViewPager02Fragment.newInstance(getActivity());
        homeMainViewPager03Fragment = HomeMainViewPager03Fragment.newInstance(getActivity());


        llayoutTopMenu01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentTab(0);
                updateTableFragment(0);
            }
        });

        llayoutTopMenu02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentTab(1);
                updateTableFragment(1);
            }
        });
        llayoutTopMenu03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentTab(2);
                updateTableFragment(2);
            }
        });
        setCurrentTab(0);
        updateTableFragment(0);
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

    public void updateTableFragment(int reqNewFragmentIndex) {

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (reqNewFragmentIndex) {
            case 0:
                if (!homeMainViewPager01Fragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, homeMainViewPager01Fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            case 1:
                if (!homeMainViewPager02Fragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, homeMainViewPager02Fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }


                break;
            case 2:
                if (!homeMainViewPager03Fragment.isAdded()) {
                    fragmentTransaction.replace(R.id.layoutcontainer, homeMainViewPager03Fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }
                break;
            default:
                break;
        }
    }
}
