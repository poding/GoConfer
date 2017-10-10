package com.whashtag.GoConfer.login;

import android.app.Activity;
import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.whashtag.GoConfer.application.ApplicationController;

/**
 * Session Config에 대해서는 default값들이 존재한다.
 * 필요한 상황에서만 override해서 사용하면 됨.
 * @return Session의 설정값.
 */
public class KakaoSDKAdapter extends KakaoAdapter {
    @Override
    public IApplicationConfig getApplicationConfig() {
        return new IApplicationConfig() {
            @Override
            public Activity getTopActivity() {
                return ApplicationController.getCurrentActivity();
            }

            @Override
            public Context getApplicationContext() {
                return ApplicationController.getInstance();
            }
        };
    }
    @Override
    public ISessionConfig getSessionConfig() {
        return new ISessionConfig() {
            @Override
            public AuthType[] getAuthTypes() {
                return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
            }

            @Override
            public boolean isUsingWebviewTimer() {
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
}
