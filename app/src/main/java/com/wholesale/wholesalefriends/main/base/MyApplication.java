package com.wholesale.wholesalefriends.main.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.wholesale.wholesalefriends.module.util.Util;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

import io.fabric.sdk.android.Fabric;

public class MyApplication extends MultiDexApplication {
    public static boolean DEBUG = false;
    public static MyApplication _instance;
    private FirebaseAnalytics mFirebaseAnalytics;

    public static MyApplication get_instance(){
        return _instance;
    }
    private static MyApplication sharedApplication;
    public static MyApplication getApplication() {
        return MyApplication.sharedApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DEBUG = Util.isDebuggable(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Fabric.with(this, new Crashlytics());
//        KakaoSDK.init(new KakaoSDKAdapter());

        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP){
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }

    public void  logEvent(String contentType, String contentValue){

        Bundle bundle = new Bundle();
        bundle.putString(contentType, contentValue);
        mFirebaseAnalytics.logEvent("구글무료", bundle);

    }

    private static class KakaoSDKAdapter extends KakaoAdapter {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         *
         * @return Session의 설정값.
         */
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[]{AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return MyApplication.getApplication();
                }
            };
        }
    }
}
