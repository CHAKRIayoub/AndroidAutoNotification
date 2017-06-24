package com.example.user.androidautonotification;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.androidautonotification.NOtification;
import com.example.user.androidautonotification.R;

import java.util.ArrayList;

/**
 */

public class CustomListAdapter extends BaseAdapter {

    public ArrayList<NOtification> listitm = new ArrayList<NOtification>();
    Context con = null;
    int res = 0;


    public CustomListAdapter( Context context, int ressource, ArrayList<NOtification> items ){
        this.listitm = items;
        this.con = context;
        this.res = ressource;
    }

    @Override
    public int getCount() {
        return listitm.size();
    }

    @Override
    public Object getItem(int position) {
        return listitm.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater linf = ((Activity) con).getLayoutInflater();
        View v = linf.inflate(res,parent, false);
        final NOtification i = listitm.get(position);

        TextView titre = (TextView) v.findViewById(R.id.tv_titre );
        TextView text = (TextView) v.findViewById(R.id.tv_text);
        TextView date = (TextView) v.findViewById(R.id.tv_date);

        titre.setText(" "+i.titre);
        text.setText(i.text);
        date.setText(i.date);





        return v;
    }
}
