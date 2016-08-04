package com.test.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;
import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;


import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.test.myapplication.TrendingTopicsObject.Value;

/**
 * Created by NehaRege on 8/1/16.
 */
public class DetailFragment extends Fragment {

    String TAG = "DetailFragment";

    FloatingActionMenu fam;
    FloatingActionButton fab1Fb, fab2, fab3Messenger;

    WebView webView;

    CallbackManager callbackManager;

    ShareDialog shareDialog;
    MessageDialog messageDialog;

    private View view;

//    ArticleWithDescriptionObject article;


    Value article;
    String articleName;
    String articleUrl;
    String articleDescription;

    com.test.myapplication.CategoryNewsObject.Value catArticle;
    String catArticalName;
    String catArticalUrl;
    String catArticleDescription;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail,container,false);

        initializeViews();

        callbackManager = CallbackManager.Factory.create();

        shareDialog = new ShareDialog(this);

        Log.i(TAG, "onCreateView: shareDialog instance created");

        messageDialog = new MessageDialog(this);


        if(articleUrl!=null){

            webView.loadUrl(articleUrl);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(articleUrl);
                    return true;

                }
            });

//            articleUrl=null;

        } else if(catArticalUrl!=null){

            Log.i(TAG, "onCreateView: Cat Url is NOT null");

            Log.i(TAG, "onCreateView: cat URL is: "+catArticalUrl);

            webView.loadUrl(catArticalUrl);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(catArticalUrl);
                    return true;

                }
            });

//            catArticalUrl=null;

        }

        // fb

        fab1Fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(TAG, "*******onClick:FB ********");


                Log.i(TAG, "onClick: cat url: "+catArticalUrl);

                if (catArticalUrl != null) {

                    Log.i(TAG, "onClick: inside if(catUrl not null) method");

                    Log.i(TAG, "onClick: cat Url: "+catArticalUrl);

                    Log.i(TAG, "onClick: cat Title: "+catArticalName);
                    

                if (ShareDialog.canShow(ShareLinkContent.class)) {

                    Log.i(TAG, "onClick: inside shareDialog.canShow() method");


                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(catArticalName)
//                            .setContentDescription()
                                .setContentUrl(Uri.parse(catArticalUrl))
                                .build();

                        shareDialog.show(linkContent);

                    Log.i(TAG, "onClick: ShareDialog should show");
                    }
                    articleUrl = null;

                    Log.i(TAG, "onClick: article url set to null");

                    Log.i(TAG, "onClick: END");
                    
                    
                } else if ( articleUrl != null) {

                    if (ShareDialog.canShow(ShareLinkContent.class)) {


                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(articleName)
                                .setContentDescription(articleDescription)
                                .setContentUrl(Uri.parse(articleUrl))
                                .build();

                        shareDialog.show(linkContent);
                    }
                    catArticalUrl = null;
                }
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // fb messenger

        fab3Messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (catArticalUrl != null) {

                    if (MessageDialog.canShow(ShareLinkContent.class)) {

                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(catArticalName)
                                .setContentDescription(catArticleDescription)
                                .setContentUrl(Uri.parse(catArticalUrl))
                                .build();

                        messageDialog.show(linkContent);

                    }

                    articleUrl = null;

                } else if (articleUrl != null) {

                    if (MessageDialog.canShow(ShareLinkContent.class)) {

                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(articleName)
                                .setContentUrl(Uri.parse(articleUrl))
                                .build();

                        messageDialog.show(linkContent);

                    }
                    catArticalUrl = null;
                }
            }
        });





        return view;
    }


    public void setDetailArticle(Value article){
        this.article = article;
        this.articleUrl = article.getWebSearchUrl();
        this.articleName = article.getName();


    }

    public void setCatArticle(com.test.myapplication.CategoryNewsObject.Value catArticle){
        this.catArticle = catArticle;
        this.catArticalUrl = catArticle.getUrl();
        this.catArticleDescription = catArticle.getDescription();
        this.catArticalName = catArticle.getName();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode,resultCode,data);

    }

    private void initializeViews() {

        webView = (WebView) view.findViewById(R.id.web_view);

        fam = (FloatingActionMenu) view.findViewById(R.id.floating_action_menu);
        fab1Fb = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item1_fb);
        fab2 = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item2);
        fab3Messenger = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item3_messenger);

    }

}
