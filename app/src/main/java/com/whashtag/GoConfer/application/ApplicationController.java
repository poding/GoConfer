package com.whashtag.GoConfer.application;

import android.app.Activity;
import android.app.Application;

import com.kakao.auth.KakaoSDK;
import com.whashtag.GoConfer.login.KakaoSDKAdapter;

/**
 * Created by USER on 2017-09-20.
 */

public class ApplicationController extends Application {
    private static ApplicationController instance = null; // 인스턴스 객체 선언
    private static volatile Activity currentActivity = null;

    //static 객체를 반환하는 이유 : 매번 객체를 생성하지 않고 다른 activity에서도 사용 가능
    public static ApplicationController getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationController.instance = this; // 인스턴스 객체 초기화
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    public static Activity getCurrentActivity() { return currentActivity; }

    public static void setCurrentActivity(Activity currentActivity) {
        ApplicationController.currentActivity = currentActivity;
    }
    /**
     *  어플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
