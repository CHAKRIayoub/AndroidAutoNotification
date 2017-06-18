/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.user.androidautonotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class MessageReadReceiver extends BroadcastReceiver {
    private static final String TAG = MessageReadReceiver.class.getSimpleName();
    public static int n = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        String titre = "";
        String text = "";

        //get data
        Bundle bndl= intent.getExtras();
        titre = bndl.getString("titre");
        text = bndl.getString("text");







        //send to auto
        NotificationManagerCompat managernotf;
        NotificationCompat.Builder notf = new NotificationCompat.Builder(context);
        notf.setContentTitle(titre);
        notf.setContentText(text);
        notf.setSmallIcon(R.drawable.google);

        int thisConversationId  = 42;

        String conversationName = "Ministre of transport";
        NotificationCompat.CarExtender.UnreadConversation.Builder unreadConvBuilder =
                new NotificationCompat.CarExtender.UnreadConversation.Builder(conversationName)
                        /*.setReadPendingIntent(msgReadPendingIntent)
                        .setReplyAction(msgReplyPendingIntent, remoteInput)*/;

        unreadConvBuilder.addMessage(titre+" : "+text)
                .setLatestTimestamp(System.currentTimeMillis());

        notf.extend(new android.support.v4.app.NotificationCompat.CarExtender()
                .setUnreadConversation(unreadConvBuilder.build()));


        managernotf = NotificationManagerCompat.from(context);
        managernotf.notify(n,notf.build());
        n++;







    }
    }

