package com.example.user.androidautonotification;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.id.list;


public class NotficationsService extends IntentService {

    public static boolean servicefonctionne = false;
    public static int id;

    /*
    * Constructor
    * */
    public NotficationsService() {
        super("Readwebservice");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DataBase MyDB = new DataBase( getApplicationContext() );
        id = MyDB.get_last_id();

        while (servicefonctionne = true){

            //preparer uun intent pour declecher le Broadcastreciever qui lance la notification
            Intent itnt = new Intent();
            itnt.setAction("com.example.user.androidautonotification.ACTION_MESSAGE_READ");

            //lire les données service WEB

            URL url = null;
            String JSON_STRING = "";
            String data = "";

            try {

                //connexion avec le web service____________________________________________________________________

                url = new URL("http://www.ayoubchakri.co.nf/notifications/getNotifications.php?id="+id);
                // id : id de la derniére Notification reçu

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder response = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    response.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();

                httpURLConnection.disconnect();

                data = response.toString().trim();


                try {

                    if (data != null) {

                        //recuperer les données JSON

                        JSONObject jsonobject = new JSONObject(data);
                        JSONArray jsonarray = jsonobject.getJSONArray("result");
                        String titre, text, date;
                        int count = 0;

                        while (count < jsonarray.length()) {

                            JSONObject jo = jsonarray.getJSONObject(count);

                            id = jo.getInt("id");
                            MyDB.inset_id(id);
                            titre = jo.getString("titre");
                            text = jo.getString("text");
                            date = jo.getString("date");


                            //put extras données
                            if ( !(titre == null || text == null) ){

                                itnt.putExtra("titre", titre);
                                itnt.putExtra("text", text);
                                itnt.putExtra("date", date);

                                //decleché le broadcast recievr
                                sendBroadcast(itnt);

                            }

                            count++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
