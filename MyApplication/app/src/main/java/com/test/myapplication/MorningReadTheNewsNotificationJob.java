package com.test.myapplication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by LangstonSmith on 8/2/16.
 */
@TargetApi(21)
public class MorningReadTheNewsNotificationJob extends JobService {

    private String TAG = "MorningReadTheNewsNotificationJob";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob: Daily morning notification job has started");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon);
        builder.setContentTitle(getString(R.string.notification_morning_title));
        builder.setContentText(getString(R.string.notification_morning_text));
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        notificationManager.notify(0, notification);

        return false;


    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob: Daily morning notification job has ended");


        return false;
    }

}
