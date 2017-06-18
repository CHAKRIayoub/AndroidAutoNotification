package com.example.user.androidautonotification;

import android.app.PendingIntent;
import android.support.v4.app.RemoteInput;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotficationsService.servicefonctionne = true;
        Intent itnt = new Intent(getApplicationContext(), NotficationsService.class);
        startService(itnt);

    }








}

