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
import android.support.v4.app.RemoteInput;
import android.util.Log;

/**
 * A receiver that gets called when a reply is sent to a given conversationId
 */
public class MessageReplyReceiver extends BroadcastReceiver {

    private static final String TAG = MessageReplyReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        int conversationId = intent.getIntExtra(" conversation_id", -1);
        // If you set up the intent as described in
        // "Create conversation read and reply intents",
        // you can get the conversation ID by calling:
    }

    /**
     * Get the message text from the intent.
     * Note that you should call
     * RemoteInput.getResultsFromIntent() to process
     * the RemoteInput.
     */
    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput =
                RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence("voice_reply_key");
        }
        return null;
        }
    }

