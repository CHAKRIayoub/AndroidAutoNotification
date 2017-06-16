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

    NotificationManagerCompat managernotf;
    static int n = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void btnclick(View view){

        NotificationCompat.Builder notf = new NotificationCompat.Builder(this);
        notf.setContentTitle("hello");
        notf.setContentText("this is my first notification");
        notf.setSmallIcon(R.drawable.google);

//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//        notf.setContentIntent(contentIntent);



        int thisConversationId  = 42;



        Intent msgReadIntent = new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction("com.example.user.androidautonotification.ACTION_MESSAGE_READ")
                .putExtra("conversation_id", thisConversationId);

        PendingIntent msgReadPendingIntent =
                PendingIntent.getBroadcast(getApplicationContext(),
                        thisConversationId,
                        msgReadIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Intent msgReplyIntent = new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction("com.example.user.androidautonotification.ACTION_MESSAGE_READ")
                .putExtra("conversation_id", thisConversationId);

        PendingIntent msgReplyPendingIntent =
                PendingIntent.getBroadcast(getApplicationContext(),
                        thisConversationId,
                        msgReplyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);



        RemoteInput remoteInput = new RemoteInput.Builder( "voice_reply_key" )
                .setLabel("how how hoooooooooooooooooow")
                .build();

        String conversationName = "ayoub chakri";
        NotificationCompat.CarExtender.UnreadConversation.Builder unreadConvBuilder =
                new NotificationCompat.CarExtender.UnreadConversation.Builder(conversationName)
                        .setReadPendingIntent(msgReadPendingIntent)
                        .setReplyAction(msgReplyPendingIntent, remoteInput);

        unreadConvBuilder.addMessage("hi are you there !!??")
                .setLatestTimestamp(System.currentTimeMillis());

        notf.extend(new android.support.v4.app.NotificationCompat.CarExtender()
                .setUnreadConversation(unreadConvBuilder.build()));


        managernotf = NotificationManagerCompat.from(getApplicationContext());
        managernotf.notify(n,notf.build());
        n++;




    }
}
