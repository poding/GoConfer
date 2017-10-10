package com.whashtag.GoConfer.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.whashtag.GoConfer.R;
import android.content.SharedPreferences;

/**
 * Created by USER on 2017-09-20.
 */

public class LoginActivity extends AppCompatActivity {
    private SessionCallback callback; // 콜백 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

        //카카오톡 아이디값 저장하기
        SharedPreferences sp = getApplicationContext().getSharedPreferences("sp",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ID","영쓰야 여기다가 아이디값을 넣으세요우");
        editor.commit();
    }

        @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            // 세션 연결성공 시 redirectSignupActivity() 호출
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            // 세션 연결이 실패 시, 로그인 화면을 다시 불러온다.
            setContentView(R.layout.activity_login);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        Log.d("MyTag", "세션 연결 성공");
        final Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
