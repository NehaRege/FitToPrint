package com.test.myapplication;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by LangstonSmith on 8/1/16.
 */
public class FireBaseGCM extends FirebaseMessagingService {

    private static final String TAG = "FireBaseGcm";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i(TAG, "onMessageReceived: " + remoteMessage.getFrom());

        Log.i(TAG, "onMessageReceived: body " + remoteMessage.getNotification().getBody());
    }


}
