package com.wholesale.wholesalefriends.main.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.wholesalefriends.R;

public class AlertDialog extends Dialog {
	private boolean _isOk;
	private boolean mIsOnce;
	private CheckBox mCheckBox;
	private Context ctx;
	private int nStatusColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			int color = ctx.getResources().getColor(R.color.colorPrimary);
			getWindow().setStatusBarColor(color);
		}
	}
	public AlertDialog(Context context,String message,boolean isCancel) {
		super(context,android.R.style.Theme_Translucent_NoTitleBar);
		ctx = context;
		setContentView(R.layout.handa_dialog_alert);

		try{
			TextView txt = findViewById(R.id.txt);
			txt.setText(message);

			Button ok =findViewById(R.id.ok);
			ok.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							_isOk = true;
							dismiss();
						}
					}, 300);
				}
			});
			
			RelativeLayout cancel = findViewById(R.id.btnClose);
			if(isCancel){
				cancel.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								_isOk = false;
								dismiss();
							}
						}, 300);
					}
				});
			}else{
				cancel.setVisibility(View.GONE);
			}
			int resId = context.getResources().getIdentifier("chk", "id", context.getPackageName());
			if(resId != 0){
				mCheckBox = (CheckBox)findViewById(resId);
				mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						mIsOnce = isChecked;
					}
				});
				mCheckBox.setVisibility(View.GONE);
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

	public int getnStatusColor() {
		return nStatusColor;
	}

	public void setnStatusColor(int nStatusColor) {
		this.nStatusColor = nStatusColor;
	}
	public boolean isOk(){
		return _isOk;
	}
	public boolean isOnce(){
		return mIsOnce;
	}
	public void showOnce(){
		if(mCheckBox != null){
			mCheckBox.setVisibility(View.VISIBLE);
		}
	}
}
