package com.wholesale.wholesalefriends.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.AlarmSettingActivity;
import com.wholesale.wholesalefriends.main.ServiceCenterActivity;

public class MyPageFragment extends Fragment implements View.OnClickListener {

    private Button btnManagerment;
    private ImageView ivAlarmToggle;
    private LinearLayout btnAlarmSetting;
    private LinearLayout btnMyShopIntroduce;
    private LinearLayout btnKakao;
    private LinearLayout btnNotice;
    private LinearLayout btnSerViceCenter;
    private TextView tvVersion;
    private LinearLayout btnAppInfo;
    private LinearLayout btnClauseTerms;
    private LinearLayout btnPersonalTerms;
    private LinearLayout btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mypage_2, container, false);

        btnManagerment = view.findViewById(R.id.btnManagerment);
        ivAlarmToggle = view.findViewById(R.id.ivAlarmToggle);
        btnAlarmSetting = view.findViewById(R.id.btnAlarmSetting);
        btnMyShopIntroduce = view.findViewById(R.id.btnMyShopIntroduce);
        btnKakao =view. findViewById(R.id.btnKakao);
        btnNotice =view. findViewById(R.id.btnNotice);
        btnSerViceCenter =view. findViewById(R.id.btnSerViceCenter);
        tvVersion = view.findViewById(R.id.tvVersion);
        btnAppInfo = view.findViewById(R.id.btnAppInfo);
        btnClauseTerms =view. findViewById(R.id.btnClauseTerms);
        btnPersonalTerms = view.findViewById(R.id.btnPersonalTerms);
        btnLogout = view.findViewById(R.id.btnLogout);

        initBtn();

        return view;
    }

    private void initBtn(){
        btnManagerment.setOnClickListener(this);
        btnAlarmSetting.setOnClickListener(this);
        btnMyShopIntroduce.setOnClickListener(this);
        btnKakao.setOnClickListener(this);
        btnNotice.setOnClickListener(this);
        btnSerViceCenter.setOnClickListener(this);
        btnAppInfo.setOnClickListener(this);
        btnClauseTerms.setOnClickListener(this);
        btnPersonalTerms.setOnClickListener(this);
        btnPersonalTerms.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnManagerment:
                break;
            case R.id.btnAlarmSetting:
                intent = new Intent(getActivity(), AlarmSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMyShopIntroduce:
                break;

            case R.id.btnNotice:
                break;
            case R.id.btnSerViceCenter:
                intent= new Intent(getActivity(), ServiceCenterActivity.class);

                break;
            case R.id.btnAppInfo:
                break;
            case R.id.btnClauseTerms:
                break;
            case R.id.btnPersonalTerms:
                break;
            case R.id.btnLogout:
                break;

        }
        if(intent!=null) startActivity(intent);
    }
}
