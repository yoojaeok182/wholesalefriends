package com.wholesale.wholesalefriends.main.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mmin18.widget.RealtimeBlurView;
import com.wholesale.wholesalefriends.R;

public class CommonAlertDialog extends Dialog {

    private RealtimeBlurView mBlurView;
    private TextView mTvTitle;
    private TextView mTxt;
    private Button mOk;
    private RelativeLayout mBtnClose;

    private boolean isOk;

    public CommonAlertDialog(final Context context, boolean isShow, boolean isTitleShow) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.handa_dialog_alert);
        mBlurView = findViewById(R.id.blur_view);
        mTvTitle = findViewById(R.id.tvTitle);
        mTxt = findViewById(R.id.txt);
        mOk = findViewById(R.id.ok);
        mBtnClose = findViewById(R.id.btnClose);

        mBtnClose.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.INVISIBLE);
        if(isShow){
            mBtnClose.setVisibility(View.VISIBLE);
        }

        if(isTitleShow){
            mTvTitle.setVisibility(View.VISIBLE);
        }

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOk = true;
                dismiss();
            }
        });
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOk = false;
                dismiss();
            }
        });
    }


    public void setTitle(String title) {
        if(title!= null&&mTvTitle!= null){
            mTvTitle.setText(title);
        }
    }

    public void setMessage(String content) {
        if(content!= null&&mTxt!= null){
            mTxt.setText(content);
        }
    }

    public boolean isOk() {
        return isOk;
    }
}
