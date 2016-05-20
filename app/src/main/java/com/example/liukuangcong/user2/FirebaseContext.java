package com.example.liukuangcong.user2;

/**
 * Created by liukuangcong on 5/17/16.
 */
import com.firebase.client.Firebase;

public class FirebaseContext extends android.app.Application {
    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}