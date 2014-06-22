package com.selfmummy.yambapushtestclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class PushReceiver extends BroadcastReceiver {

    public static final String TAG = "RECEIVER";

    public PushReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        String messageType = gcm.getMessageType(intent);
        Log.d(TAG, messageType);
        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                String data = extras.toString();
                Log.d(TAG, "Received: " + data);
                String content = extras.getString("message");
                Intent intentToActivity = new Intent();
                intentToActivity.putExtra("message", content);
                intentToActivity.setAction(MainActivity.INTERNAL);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentToActivity);
            } else {
                Log.d(TAG, "Unexpected Message Type");
            }
        }
    }
}
