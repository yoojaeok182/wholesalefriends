package com.wholesale.wholesalefriends.main.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.wholesale.wholesalefriends.R;

import java.util.ArrayList;

/**
 * Created by DK on 2016-08-10.
 */
public class GroupActivity extends AppCompatActivity {
    private ArrayList<OnDestroyListener> _destroyListenerList = new ArrayList<OnDestroyListener>();
    public static ArrayList<FragmentActivity> arrActivity = new ArrayList<FragmentActivity>();
    protected boolean isBeforeLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrActivity.add(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP  ) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int color = getResources().getColor(R.color.colorPrimary);
            getWindow().setStatusBarColor(color);
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    protected void onDestroy() {
        arrActivity.remove(this);
        if(_destroyListenerList.size() > 0){
            for(OnDestroyListener listener : _destroyListenerList){
                if(listener != null) {
                    listener.onDestroy();
                }
            }
        }
        super.onDestroy();
    }


    /**
     * 모든 엑티비티 종료
     */
    public static void clearAllActivity() {
        for(int i = 0; i < arrActivity.size(); i++) {
            try {
                arrActivity.get(i).finish();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        arrActivity.clear();
    }

    public void setOnDestroyListener(OnDestroyListener listener){
        _destroyListenerList.add(listener);
    }
    public static interface OnDestroyListener{
        public void onDestroy();
    }
}
