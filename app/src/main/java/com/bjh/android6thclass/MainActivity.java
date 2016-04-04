package com.bjh.android6thclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    6주차 Android 수업

    1. < Activity Lifecycle >
     1) Activity launched
        onCreate().. 메모리 할당  -> onStart()..작업 시작    -> onResume()..화면출력
     2) Activity finished
        onPause().. 화면정지   -> onStop()..작업 중단     -> onDestroy().. 메모리 반환

    게임 중 전화가 온다 >> 게임이 정지 (onPause) >> 전화끝 >> 게임 재시작 (onResume)
      onPause에서 진행상황을 저장하고
      onResume에서 불러오자
     SharedPreference 사용

    2. < Service >
     1) Activity Service lifecycle
        onStartCommand  /  onBindService  차이
        Thread를 별도로 만들어야 하는 경우 : onStartCommand에서...
        Service와 내 객체를 묶어준다. : onBindService에서...
        좀 더 자세히 알아볼 것
        ...
        onStartCommand << Service를 시작한 intent에서 put한 Extra를 가져올 수 있다.

    cf) Thread는 thread.start() 하면 Thread class 내의 run() 함수가 호출


    3. < Broadcast Receiver >
     1) BroadcastReceiver class를 상속받은 Receiver 생성
     2) Manifest에서 <intent-filter> 로 Action을 추가하여, SMS가 왔을 때 Receiver가 동작하도록 설정.

     */

    ListView listView;
    Button btn;
    MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new MyListAdapter();
        listView.setAdapter(mAdapter);


        Log.d("ACTIVITY", "onCreate()");

        for (int i=0; i<100; i++) {
            ListItem item = new ListItem();
            item.name = "이름" + i;
            mAdapter.add(item);
        }


        btn = (Button) findViewById(R.id.btn_service_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command", "command");
                startService(intent);
                Toast.makeText(MainActivity.this, "MyService 실행", Toast.LENGTH_SHORT).show();
            }
        });

        btn = (Button) findViewById(R.id.btn_service_finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                stopService(intent);
                Toast.makeText(MainActivity.this, "MyService 종료", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveScore() {
        SharedPreferences prefs = getSharedPreferences("세이브", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("SCORE", 10000);
        editor.commit();                // ***** commit 반드시!
    }

    private void loadScore() {
        SharedPreferences prefs = getSharedPreferences("세이브", MODE_PRIVATE);
        int score = prefs.getInt("SCORE", 0);
        Toast.makeText(MainActivity.this, "점수는 "+score, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ACTIVITY", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadScore();

        Log.d("ACTIVITY", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveScore();

        Log.d("ACTIVITY", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ACTIVITY", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ACTIVITY", "onDestroy()");
    }
}
