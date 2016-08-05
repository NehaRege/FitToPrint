package com.test.myapplication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by LangstonSmith on 8/2/16.
 */
@TargetApi(21)
public class DailyReadTheNewsNotificationJob extends JobService {

    private String TAG = "DailyReadTheNewsNotificationJob";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob: Daily morning notification job has started");

        int NOTIFICATION_ID = 3;

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

        builder.setSmallIcon(R.drawable.ic_wb_sunny_black_48dp);
        builder.setContentTitle(getString(R.string.notification_morning_title));
        builder.setContentText(getString(R.string.notification_morning_text));
        builder.setAutoCancel(true);
        builder.setContentIntent(pIntent);
        builder.setPriority(Notification.PRIORITY_DEFAULT);


        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

        return false;

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob: Daily morning notification job has ended");


        return false;
    }

}
