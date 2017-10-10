package com.whashtag.GoConfer;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.whashtag.GoConfer.login.LoginActivity;
import com.whashtag.GoConfer.main.MainActivity;

import android.content.SharedPreferences;

/**
 * Created by user on 2017-09-20.
 */

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    BluetoothAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sp = getSharedPreferences("ID", Activity.MODE_PRIVATE);
        final String my_id = sp.getString("ID","empty");

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                //id값이 없으면 로그인액티비티로 이동
                if(my_id.equals("empty")){

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                }else {//id값이 있으면 메인액티비티로 바로 이동
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };


        //블루투스 on/off 체크하고 꺼져있으면 켜주고 로그인화면으로 넘어갈수 있도록 함
        adapter = BluetoothAdapter.getDefaultAdapter();
        if(adapter.getState() == BluetoothAdapter.STATE_TURNING_ON || adapter.getState() == BluetoothAdapter.STATE_ON){
            Log.d("오류잡자:","안녕!1");
            handler.sendEmptyMessageDelayed(0,3000);
            Log.d("오류잡자:","안녕!2");

        }else {
            Log.d("오류잡자:","안녕!3");
            adapter.enable();
            Log.d("오류잡자:","안녕!4");
            handler.sendEmptyMessageDelayed(0, 3000);
            Log.d("오류잡자:","안녕!5");
        }



    }

}


