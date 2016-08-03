package com.test.myapplication;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;
import com.test.myapplication.TrendingTopicsObject.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnArticleSelectedListener {

    private String TAG = "MainActivity";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DetailFragment detailFragment;
    RecyclerViewFragment recyclerViewFragment;
    Toolbar toolbar;

    private RecyclerView recyclerView;
    private List<Value> data;
    private CustomRecyclerViewAdapter adapter;
    public String BASE_URL = "https://api.cognitive.microsoft.com/bing/v5.0/news/";
    private String API_KEY = "0c6fd6e160ad457e9b8ae87389b75e44";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpDrawersandView();
        Log.i(TAG, "onCreate: Drawers and views set up");

        initializeFacebookSDK();
        Log.i(TAG, "onCreate: Facebook SDK stuff initialized");


        setRecycleFragment();
        Log.i(TAG, "onCreate: RecycleFragment set up");

        loadArticles();

        Log.i(TAG, "onCreate: loadArticles() run");


//        setUpBreakingNewsCheckJob();

//        setUpMorningNotificationJob();


    }



    private void loadArticles() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(TAG, "loadArticles: Retrofit object made");

        BingAPIService request = retrofit.create(BingAPIService.class);

        Log.i(TAG, "loadArticles: BingAPIService request created");

        Call<TrendingTopicsObject> call = request.getTrendingTopics(API_KEY);

        call.enqueue(new Callback<TrendingTopicsObject>() {
            @Override
            public void onResponse(Call<TrendingTopicsObject> call, Response<TrendingTopicsObject> response) {

                try {

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                    TrendingTopicsObject trendingTopicsObject = response.body();


                    Log.i(TAG, "onResponse:  body gotten");

                    ArrayList<Value> data = new ArrayList<Value>();

                    Log.i(TAG, "onResponse: data ArrayList of Values created");

                    data.addAll(trendingTopicsObject.getValue());

                    Log.i(TAG, "onResponse: all articles gotten");

                    Bundle bundle = new Bundle();

                    Log.i(TAG, "onResponse: bundle created");

                    bundle.putParcelableArrayList("ArrayList of articles", data);

                    Log.i(TAG, "onResponse: data arraylist of articles put in bundle");

                    RecyclerViewFragment mFrag = new RecyclerViewFragment();

                    Log.i(TAG, "onResponse: mFrag created");

                    mFrag.setArguments(bundle);

                    Log.i(TAG, "onResponse: bundle successfully passed through in mFrag.setArguments()");

                    Log.i(TAG, "onCreate: articles loaded");

                    fragmentTransaction.commit();

                    Log.i(TAG, "onCreate: fragmentTransaction committed");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(TAG, "onCreate: articles not loaded");

                }


            }

            @Override
            public void onFailure(Call<TrendingTopicsObject> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }

    private void initializeFacebookSDK() {

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication(), "1757738834448963");


    }

    private void setRecycleFragment() {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        recyclerViewFragment = new RecyclerViewFragment();
        fragmentTransaction.add(R.id.fragment_container, recyclerViewFragment);
        fragmentTransaction.commit();

        setUpDrawersandView();

        setUpBreakingNewsCheckJob();





    }

    @Override
    public void onArticleSelected(Value selectedArticle) {
        detailFragment = new DetailFragment();
        detailFragment.setDetailArticle(selectedArticle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detailFragment, null);
        fragmentTransaction.commit();
    }

    private void setUpDrawersandView() {

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @TargetApi(21)
    private void setUpBreakingNewsCheckJob() {

        String TAG = "MainActivity";

        JobInfo breakingNewsJob = new JobInfo.Builder(1,
                new ComponentName(getPackageName(),
                        CheckForBreakingNewsJob.class.getName()))
                .setPeriodic(3600000) //<– Check for breaking news every hour
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int result = jobScheduler.schedule(breakingNewsJob);
        if (result <= 0) {
            //stuff went wrong
            Log.i(TAG, "setUpBreakNewsCheckJob: Error with breaking news job check");
        }



    }

    @TargetApi(21)
    private void setUpMorningNotificationJob() {

        String TAG = "MainActivity";

        JobInfo dailyMorningNotificationJob = new JobInfo.Builder(1,
                new ComponentName(getPackageName(),
                        MorningReadTheNewsNotificationJob.class.getName()))
                .setPeriodic(3600000) //<– Check for breaking news every hour
                .setRequiresCharging(false)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int result = jobScheduler.schedule(dailyMorningNotificationJob);
        if (result <= 0) {
            //stuff went wrong
            Log.i(TAG, "setUpMorningNotificationJob: Error with breaking news job check");
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_trending) {

            toolbar.setTitle(R.string.toolbar_name_trending);

        } else if (id == R.id.nav_followed) {

            toolbar.setTitle(R.string.toolbar_name_followed);

        } else if (id == R.id.nav_business) {

            toolbar.setTitle(R.string.toolbar_name_business);

        } else if (id == R.id.nav_entertainment) {

            toolbar.setTitle(R.string.toolbar_name_entertainment);

        } else if (id == R.id.nav_health) {

            toolbar.setTitle(R.string.toolbar_name_health);

        } else if (id == R.id.nav_politics) {

            toolbar.setTitle(R.string.toolbar_name_politics);

        } else if (id == R.id.nav_scienceandtech) {

            toolbar.setTitle(R.string.toolbar_name_scienceandtech);

        } else if (id == R.id.nav_sports) {

            toolbar.setTitle(R.string.toolbar_name_sports);

        } else if (id == R.id.nav_US_and_UK) {

            toolbar.setTitle(R.string.toolbar_name_usanduk);

        } else if (id == R.id.nav_world) {

            toolbar.setTitle(R.string.toolbar_name_world);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
