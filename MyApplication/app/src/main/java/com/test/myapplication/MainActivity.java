package com.test.myapplication;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.test.myapplication.CategoryNewsObject.CategoryNewsObject;
import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;
import com.test.myapplication.TrendingTopicsObject.Value;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnArticleSelectedListener, CustomRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private String TAG = "MainActivity";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DetailFragment detailFragment;
    RecyclerViewFragment recyclerViewFragment;
    Toolbar toolbar;
    private boolean isChecked = false;

    private String categoryName;
    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapter adapter;

    private String API_KEY = "0c6fd6e160ad457e9b8ae87389b75e44";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpDrawersandView();
        Log.i(TAG, "onCreate: Drawers and views set up");

        setRecycleFragment();
        Log.i(TAG, "onCreate: RecycleFragment set up");

        loadTrendingArticles();
        Log.i(TAG, "onCreate: loadTrendingArticles() run");


        initializeFacebookSDK();
        Log.i(TAG, "onCreate: Facebook SDK stuff initialized");


//        setUpBreakingNewsCheckJob();

//        setUpMorningNotificationJob();


    }

    @Override
    public void onItemClick(int position) {

    }

    private void loadTrendingArticles() {

        String TRENDING_BASE_URL = "https://api.cognitive.microsoft.com/bing/v5.0/news/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TRENDING_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(TAG, "loadTrendingArticles: Retrofit object made");

        BingAPIService request = retrofit.create(BingAPIService.class);

        Log.i(TAG, "loadTrendingArticles: BingAPIService request created");

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


                    fragmentTransaction.replace(R.id.fragment_container, mFrag);

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

    private void loadCategoryArticles(final String categoryName) {

        String CATEGORY_BASE_URL = "https://api.cognitive.microsoft.com/bing/v5.0/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CATEGORY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(TAG, "loadCategoryArticles: Retrofit object made");

        BingAPIService request = retrofit.create(BingAPIService.class);

        Log.i(TAG, "loadCategoryArticles: BingAPIService request created");

        Call<CategoryNewsObject> call = request.getSpecificTopicArticles(categoryName, API_KEY);

        call.enqueue(new Callback<CategoryNewsObject>() {
            @Override
            public void onResponse(Call<CategoryNewsObject> call, Response<CategoryNewsObject> response) {

                try {

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    CategoryNewsObject categoryNewsObject = response.body();

                    Log.i(TAG, "onResponse: CATEGORYNAME IS? " + categoryName);
                    Log.i(TAG, "onResponse: cat news object is" + categoryNewsObject.getValue().get(0).getName());
                    Log.i(TAG, "onResponse:  body gotten");

                    ArrayList<com.test.myapplication.CategoryNewsObject.Value> data =
                            new ArrayList<com.test.myapplication.CategoryNewsObject.Value>();


                    Log.i(TAG, "onResponse: data ArrayList of category' articles values created");

                    data.addAll(categoryNewsObject.getValue());

                    Log.i(TAG, "onResponse: all of category's articles gotten");

                    Bundle bundle = new Bundle();

                    Log.i(TAG, "onResponse: bundle created");

                    bundle.putSerializable(categoryName, data);

                    Log.i(TAG, "onResponse: data arraylist of category articles put in bundle");

                    RecyclerViewFragment mFrag = new RecyclerViewFragment();

                    Log.i(TAG, "onResponse: mFrag created");

                    mFrag.setArguments(bundle);

                    Log.i(TAG, "onResponse: bundle successfully passed through in mFrag.setArguments()");

                    Log.i(TAG, "onCreate: articles loaded");


                    fragmentTransaction.replace(R.id.fragment_container, mFrag);

                    fragmentTransaction.commit();

                    Log.i(TAG, "onCreate: fragmentTransaction committed");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(TAG, "onCreate: articles not loaded");

                }


            }

            @Override
            public void onFailure(Call<CategoryNewsObject> call, Throwable t) {
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

    @Override
    public void onCatArticleSelected(com.test.myapplication.CategoryNewsObject.Value catArticle) {
        detailFragment = new DetailFragment();

        detailFragment.setCatArticle(catArticle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detailFragment, null);
        fragmentTransaction.commit();
    }

    private void setUpDrawersandView() {

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(R.string.toolbar_name_trending_news);


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


        switch (item.getItemId()) {
            case R.id.button_heart_follow_topic:
                Log.i(TAG, "onOptionsItemSelected: heart clicked on");

                String toolbarName = toolbar.getTitle().toString();

                Log.i(TAG, "onOptionsItemSelected: toolbar name is: " + toolbarName);

                if (!toolbarName.equals("Trending News")) {


                } else {

                    isChecked = !item.isChecked();
                    item.setChecked(isChecked);

                    item.setIcon(R.drawable.ic_favorite_solid_red_heart_48dp);

                }
                return true;

            case R.id.action_settings:
                Log.i(TAG, "onOptionsItemSelected: 3-dot menu in right hand corner clicked on");

                return true;

            default:
                return false;
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_trending) {

            toolbar.setTitle(R.string.toolbar_name_trending);

            loadTrendingArticles();

        } else if (id == R.id.nav_followed) {

            toolbar.setTitle(R.string.toolbar_name_followed);


        } else if (id == R.id.nav_business) {
            categoryName = "Business";

            toolbar.setTitle(R.string.toolbar_name_business);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_entertainment) {
            categoryName = "Entertainment";

            toolbar.setTitle(R.string.toolbar_name_entertainment);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_health) {
            categoryName = "Health";

            toolbar.setTitle(R.string.toolbar_name_health);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_politics) {
            categoryName = "Politics";

            toolbar.setTitle(R.string.toolbar_name_politics);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_scienceandtech) {
            categoryName = "ScienceAndTechnology";

            toolbar.setTitle(R.string.toolbar_name_scienceandtech);

            loadCategoryArticles(categoryName);


        } else if (id == R.id.nav_sports) {
            categoryName = "Sports";

            toolbar.setTitle(R.string.toolbar_name_sports);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_US_and_UK) {
            categoryName = "US/UK";

            toolbar.setTitle(R.string.toolbar_name_usanduk);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_world) {
            categoryName = "World";

            toolbar.setTitle(R.string.toolbar_name_world);

            loadCategoryArticles(categoryName);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

     /*   @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
            MenuItem checkable = menu.findItem(R.id.button_heart_follow_topic);
            checkable.setChecked(isChecked);
            return true;
        }*/


}
