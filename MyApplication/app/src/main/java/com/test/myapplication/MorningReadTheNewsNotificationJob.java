package com.test.myapplication;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by LangstonSmith on 8/2/16.
 */
@TargetApi(21)
public class MorningReadTheNewsNotificationJob extends JobService {

    private String TAG = "MorningReadTheNewsNotificationJob";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob: Daily morning notification job has started");



        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob: Daily morning notification job has ended");




        return false;
    }

}
