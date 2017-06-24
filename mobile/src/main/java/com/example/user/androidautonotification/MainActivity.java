package com.example.user.androidautonotification;

import android.app.Notification;
import android.app.PendingIntent;
import android.support.v4.app.RemoteInput;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import static android.R.id.list;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressbar;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lancer le service qui nous permet de recevoir les notification
        NotficationsService.servicefonctionne = true;
        Intent itnt = new Intent(getApplicationContext(), NotficationsService.class);
        startService(itnt);


        //MainActivity  :  une listeview pour afficher l'historique des notifications
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        lv = (ListView) findViewById(R.id.listview);
        ArrayList<NOtification> list = new ArrayList<NOtification>();
        CustomListAdapter cls = new CustomListAdapter( this,R.layout.lv_model ,list );
        lv.setAdapter(cls);

        //lacer l' asyncTask qui nous permet de remplire la liste d'historique des Notification
        new ClassAsyncT(cls, list, progressbar).execute();

    }








}

