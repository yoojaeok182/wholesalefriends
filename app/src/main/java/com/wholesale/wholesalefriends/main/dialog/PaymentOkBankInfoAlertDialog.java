package com.wholesale.wholesalefriends.main.dialog;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mmin18.widget.RealtimeBlurView;
import com.wholesale.wholesalefriends.R;

import static android.content.Context.CLIPBOARD_SERVICE;

public class PaymentOkBankInfoAlertDialog extends Dialog {

    private RealtimeBlurView mBlurView;
    private TextView mTvTitle;
    private TextView mTxt;
    private Button mOk;
    private RelativeLayout mBtnClose;

    private boolean isOk;
    private EditText edtPaymentComment;
    private TextView tvContent02;

    private LinearLayout llayoutForBank;

    public PaymentOkBankInfoAlertDialog(final Context context, boolean isShow, boolean isTitleShow) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.dialog_common_alert2);
        mBlurView = findViewById(R.id.blur_view);
        mTvTitle = findViewById(R.id.tvTitle);
        mTxt = findViewById(R.id.txt);
        mOk = findViewById(R.id.ok);
        mBtnClose = findViewById(R.id.btnClose);
        edtPaymentComment = findViewById(R.id.edtPaymentComment);
        llayoutForBank= findViewById(R.id.llayoutForBank);
        mBtnClose.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.INVISIBLE);
        if (isShow) {
            mBtnClose.setVisibility(View.VISIBLE);
        }

        if (isTitleShow) {
            mTvTitle.setVisibility(View.VISIBLE);
        }
        llayoutForBank.setVisibility(View.GONE);
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
        tvContent02 = findViewById(R.id.tvContent02);
    }


    public void setTitle(String title) {
        if (title != null && mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    public void setMessage(String content) {
        if (content != null && mTxt != null) {
            mTxt.setText(content);
        }
    }
    public void setMessage2(String content) {
        if (content != null && tvContent02 != null) {
            tvContent02.setText(content);
            llayoutForBank.setVisibility(View.VISIBLE);

        }
    }
    public boolean isOk() {
        return isOk;
    }

    public String getContent() {
        return edtPaymentComment.getText().toString().trim();
    }
}
