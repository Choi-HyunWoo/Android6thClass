package com.bjh.android6thclass;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("SERVICE", "onBind()");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SERVICE", "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SERVICE", "onDestroy()");
    }

    MyThread thread;

    // App이 중지되어도 Service가 다시 실행된다.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERVICE", "onStartCommand()");

        if (intent != null) {
            String command = intent.getStringExtra("command");
            if (command != null) {
                thread = new MyThread();
                thread.start();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    class MyThread extends Thread {
        public void run () {
            for (int i=0; i<100; i++) {
                Log.d("THREAD", "#"+i+" 서비스에서 반복됨.");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }



}
