package com.wholesale.wholesalefriends.main.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.wholesale.wholesalefriends.R;

public class LoadingDialog extends Dialog {
	private Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			int color = ctx.getResources().getColor(R.color.colorPrimary);
			getWindow().setStatusBarColor(color);
		}
	}
	public LoadingDialog(Context context) {
		super(context,android.R.style.Theme_Translucent_NoTitleBar);
		ctx = context;
		setContentView(R.layout.handa_dialog_loading);
	}
}
