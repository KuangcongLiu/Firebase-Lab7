package com.example.liukuangcong.user2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class StartedService extends Service {
    public StartedService() {
    }

    final class MyThread implements Runnable {
        int startId;
        Firebase mref1;
        Firebase mref2;

        public MyThread(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {


            synchronized (this) {
                try {
                    mref1 = new Firebase("https://kuangcongliu-110.firebaseio.com/second");
                    mref1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String data = dataSnapshot.getValue(String.class);
                            Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });

                    mref2 = new Firebase("https://kuangcongliu-110.firebaseio.com/first");
                    String[] a1={"Hey","there","user","2"};


                    for(int i=0;i<4;i++) {
                        mref2.setValue(a1[i]);
                        wait(4000);

                    }
                } catch (
                        InterruptedException e
                        )

                {
                    e.printStackTrace();
                }

                stopSelf(startId);
            }

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(StartedService.this, "Service Started", Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new MyThread(startId));
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Toast.makeText(StartedService.this, "Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


}
