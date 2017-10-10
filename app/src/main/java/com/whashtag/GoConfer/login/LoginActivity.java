package com.whashtag.GoConfer.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.whashtag.GoConfer.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("오류잡자","나왔어");

        //카카오톡 아이디값 저장하기
        SharedPreferences sp = getApplicationContext().getSharedPreferences("sp",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ID","영쓰야 여기다가 아이디값을 넣으세요우");
        editor.commit();

        /*나중에 로그아웃할 때 아이디값 삭제하기
        SharedPreferences.Editor editor1 = sp.edit();
        editor.remove("ID");
        editor.commit();*/
    }
}
