package com.test.myapplication;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;
import com.test.myapplication.CategoryNewsObject.CategoryNewsObject;
import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;
import com.test.myapplication.TrendingTopicsObject.Value;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnArticleSelectedListener, CustomRecyclerViewAdapter.OnRecyclerViewItemClickListener {


    private String TAG = "MainActivity";
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DetailFragment detailFragment;
    RecyclerViewFragment recyclerViewFragment;
    Toolbar toolbar;
    boolean categoryIsFollowed;
    String categoryName;
    SearchManager searchManager;
    SearchableInfo searchableInfo;
    SearchView searchView;
    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapter adapter;
    private String API_KEY = "0c6fd6e160ad457e9b8ae87389b75e44";
    Context context;
    MenuInflater inflater;
    public static final String MyPREFERENCES = "com.example.myapplication.FOLLOWED_CATEGORIES";


    MenuItem followHeart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpDrawersandView();
        Log.i(TAG, "onCreate: Drawers and views set up");

        setRecycleFragment();
        Log.i(TAG, "onCreate: RecycleFragment set up");



        handleIntent(getIntent());

        initializeFacebookSDK();
        Log.i(TAG, "onCreate: Facebook SDK stuff initialized");

        loadTrendingArticles();
        Log.i(TAG, "onCreate: loadTrendingArticles() run");

//        setUpBreakingNewsCheckJob();


//        setUpMorningNotificationJob();

    }

    @Override
    protected void onNewIntent(Intent intent) {

        Log.i(TAG, "onNewIntent: entered");
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {
        Log.i(TAG, "handleIntent: intent action = "+Intent.ACTION_SEARCH);
        Log.i(TAG, "handleIntent: intent getaction = "+intent.getAction());
        Log.i(TAG, "handleIntent: intent getaction = "+intent.getDataString());


        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            Log.i(TAG, "handleIntent: just entered");

            String query = intent.getStringExtra(SearchManager.QUERY);

            Log.i(TAG, "handleIntent: got the query: "+query);
            
            


            loadSearchedItems(query);


            Toast.makeText(MainActivity.this, "searched " + query, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemClick(int position) {

    }


    private void loadTrendingArticles() {

//        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            // the connection is available
//        } else {
//            // the connection is not available
//        }

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {

            // connection available !

            String TRENDING_BASE_URL = "https://api.cognitive.microsoft.com/bing/v5.0/news/";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TRENDING_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Log.i(TAG, "loadTrendingArticles: Retrofit object made");

            final BingAPIService request = retrofit.create(BingAPIService.class);

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

        }  else  {

            Toast.makeText(this,"No network connectivity !",Toast.LENGTH_LONG).show();

            // connection not available !




        }




    }

    private void loadSearchedItems(String query) {

        Log.i(TAG, "loadSearchedItems: just entered loadsearcheditems method");
        

//        https://api.cognitive.microsoft.com/bing/v5.0/news/search[?q][&count][&offset][&mkt][&safeSearch]

//        @GET("/search?q")
//        Call<ArticleWithDescriptionObject> getArticlesBasedOnSearchQuery(
//                @Header("Ocp-Apim-Subscription-Key") String apiKey,
//                @Path("?q") String searchQuery);

        String SEARCH_BASE_URL = "https://api.cognitive.microsoft.com/bing/v5.0/news/";
        
        Log.i(TAG, "loadSearchedItems: base url: "+SEARCH_BASE_URL);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SEARCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BingAPIService request = retrofit.create(BingAPIService.class);

        String count = "";
        String offset = "";
        String mkt = "";

        Call<ArticleWithDescriptionObject> callForSearch = request.getArticlesBasedOnSearchQuery(
                API_KEY, query
        );

        callForSearch.enqueue(new Callback<ArticleWithDescriptionObject>() {
            @Override
            public void onResponse(Call<ArticleWithDescriptionObject> call, Response<ArticleWithDescriptionObject> response) {
                try {

                    Log.i(TAG, "onResponse: inside onresponse");
                    
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    ArticleWithDescriptionObject articleWithDescriptionObject = response.body();

                    ArrayList<com.test.myapplication.ArticleWithDescriptionObject.Value> data = new ArrayList<>();

                    data.addAll(articleWithDescriptionObject.getValue());

                    Log.i(TAG, "onResponse: data is: "+data);

                    Bundle bundle = new Bundle();

                    bundle.putSerializable("ArrayList of searched items", data);

                    RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();

                    recyclerViewFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.fragment_container, recyclerViewFragment);

                    fragmentTransaction.commit();

                    Log.i(TAG, "onResponse: end of on response");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(TAG, "onCreate: articles not loaded");
                }
            }

            @Override
            public void onFailure(Call<ArticleWithDescriptionObject> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }

    private void loadCategoryArticles(final String categoryName) {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // the connection is available

            String CATEGORY_BASE_URL = "https://api.cognitive.microsoft.com/bing/v5.0/";

//        https://api.cognitive.microsoft.com/bing/v5.0/news/[?Category]

//        @GET("news?category")
//        Call<CategoryNewsObject> getSpecificTopicArticles(
//                @Query("categoryName") String categoryName, @Header("Ocp-Apim-Subscription-Key") String apiKey);

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
                        Log.i(TAG, "onResponse: CATEGORYNAME IS? "+categoryName);
                        Log.i(TAG, "onResponse: cat news object is"+ categoryNewsObject.getValue().get(0).getName());
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

        } else {

            // the connection is not available

            Toast.makeText(this,"No network connectivity !",Toast.LENGTH_LONG).show();

        }

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


        // Find searchManager and searchableInfo
//        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchableInfo = searchManager.getSearchableInfo(getComponentName());


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

        inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        followHeart = (MenuItem) menu.findItem(R.id.button_heart_follow_topic);

        followHeart.setVisible(false);


        return true;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.button_heart_follow_topic:

                String toolbarName = toolbar.getTitle().toString();

                addCategoryToSharedPreferences(toolbarName, true);

                return true;

            case R.id.action_settings:
                Log.i(TAG, "onOptionsItemSelected: 3-dot menu in right hand corner clicked on");

                return true;

            default:
                return false;
        }

    }

    private void addCategoryToSharedPreferences(String keyName, boolean categoryFollowed) {

        context = getApplicationContext();

        SharedPreferences sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(keyName, categoryFollowed);

        editor.commit();

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        inflater = getMenuInflater();

        if (id == R.id.nav_trending) {

            toolbar.setTitle(R.string.toolbar_name_trending);

            followHeart.setVisible(false);


            loadTrendingArticles();

        } else if (id == R.id.nav_followed) {

            toolbar.setTitle(R.string.toolbar_name_followed);

        } else if (id == R.id.search) {

            Log.i(TAG, "onNavigationItemSelected: onclick of search");

////             Find searchManager and searchableInfo
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

            // Associate searchable info with the SearchView
            searchView = (SearchView) item.getActionView();
            searchView.setSearchableInfo(searchableInfo);

//            toolbar.setTitle("Search");

        } else if (id == R.id.nav_business) {


            categoryName = "Business";

            toolbar.setTitle(R.string.toolbar_name_business);

            SharedPreferences sharedPreferences = getSharedPreferences
                    (MyPREFERENCES,Context.MODE_PRIVATE);

            boolean alreadyFollowedOrNot = sharedPreferences.getBoolean(categoryName,false);

            if (alreadyFollowedOrNot) {

                followHeart.setIcon(R.drawable.ic_favorite_solid_red_heart_48dp);

            } else {
                followHeart.setIcon(R.drawable.ic_favorite_border_white_48dp);

            }


            followHeart.setVisible(true);


            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_entertainment)

        {

            categoryName = "Entertainment";

            toolbar.setTitle(R.string.toolbar_name_entertainment);

            SharedPreferences sharedPreferences = getSharedPreferences
                    (MyPREFERENCES,Context.MODE_PRIVATE);

            boolean alreadyFollowedOrNot = sharedPreferences.getBoolean(categoryName,false);

            if (alreadyFollowedOrNot) {

                followHeart.setIcon(R.drawable.ic_favorite_solid_red_heart_48dp);

            } else {
                followHeart.setIcon(R.drawable.ic_favorite_border_white_48dp);

            }

            followHeart.setVisible(true);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_health)

        {

            categoryName = "Health";

            toolbar.setTitle(R.string.toolbar_name_health);

            followHeart.setVisible(true);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_politics)

        {

            categoryName = "Politics";

            toolbar.setTitle(R.string.toolbar_name_politics);

            followHeart.setVisible(true);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_scienceandtech)

        {

            categoryName = "ScienceAndTechnology";

            toolbar.setTitle(R.string.toolbar_name_scienceandtech);

            followHeart.setVisible(true);

            loadCategoryArticles(categoryName);


        } else if (id == R.id.nav_sports)

        {

            categoryName = "Sports";

            toolbar.setTitle(R.string.toolbar_name_sports);

            followHeart.setVisible(true);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_US)

        {

            categoryName = "US";

            toolbar.setTitle(R.string.toolbar_name_us);

            followHeart.setVisible(true);

            loadCategoryArticles(categoryName);

        } else if (id == R.id.nav_world)

        {

            categoryName = "World";

            toolbar.setTitle(R.string.toolbar_name_world);

            followHeart.setVisible(true);

            loadCategoryArticles(categoryName);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
