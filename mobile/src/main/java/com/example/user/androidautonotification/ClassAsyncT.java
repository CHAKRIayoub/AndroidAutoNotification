package com.example.user.androidautonotification;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 24/06/2017.
 */

public class ClassAsyncT extends AsyncTask<String,Void,String> {

    public ProgressBar progressbar;
    public CustomListAdapter adpt;
    public ArrayList<NOtification> list;
    String data = "";


    public ClassAsyncT(CustomListAdapter adpt, ArrayList<NOtification> listt, ProgressBar bar ) {

        this.adpt = adpt;
        this.list = listt;
        this.progressbar = bar;

    }



    @Override
    protected String doInBackground(String... params) {


        URL url = null;
        String JSON_STRING = "";

        try {

            //connexion with web service____________________________________________________________________

            url = new URL("http://www.ayoubchakri.co.nf/notifications/getNotifications.php");

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





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


























        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        progressbar.setVisibility(View.INVISIBLE);

        myfunction();

    }

    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);

    }


    public void myfunction(){

        try {

            if (data != null) {

                JSONObject jsonobject = new JSONObject(data);
                JSONArray jsonarray = jsonobject.getJSONArray("result");
                String titre, text, date;
                int count = 0;

                while (count < jsonarray.length()) {

                    JSONObject jo = jsonarray.getJSONObject(count);

                    titre = jo.getString("titre");
                    text = jo.getString("text");
                    date = jo.getString("date");

                    list.add(new NOtification(titre,text,date));
                    adpt.notifyDataSetChanged();

                    count++;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
