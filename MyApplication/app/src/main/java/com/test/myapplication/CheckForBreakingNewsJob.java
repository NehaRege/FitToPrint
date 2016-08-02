package com.test.myapplication;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by LangstonSmith on 8/1/16.
 */
@TargetApi(21)
public class CheckForBreakingNewsJob extends JobService {

    private String TAG = "BreakingNewsCheckJob";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob: Breaking news check job has started");

//        TODO: Need to eventually make API call here to check for any articles that are breaking

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob: Breaking news check job has stopped");

        return false;
    }



}
