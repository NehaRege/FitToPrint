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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
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

import retrofit2.http.HEAD;

/**
 * Created by NehaRege on 8/1/16.
 */
public class DetailFragment extends Fragment {

    String TAG = "DetailFragment";

    FloatingActionMenu fam;
    FloatingActionButton fab2FB, fab3Messenger, fab1;

    WebView webView;

    CallbackManager callbackManager;

    ShareDialog shareDialog;
    MessageDialog messageDialog;

    private View view;

    Value article;
    String articleName;
    String articleUrl;
    String articleDescription;

    com.test.myapplication.CategoryNewsObject.Value catArticle;
    String catArticalName;
    String catArticalUrl;
    String catArticleDescription;

    com.test.myapplication.SearchNewsObject.Value searchArticle;
    String searchArticleName;
    String searchArticleUrl;
    String searchArticleDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        initializeViews();

        callbackManager = CallbackManager.Factory.create();

        shareDialog = new ShareDialog(this);

        Log.i(TAG, "onCreateView: shareDialog instance created");

        messageDialog = new MessageDialog(this);

        //if statements checking for which data type that's coming in. same thing happens for fab buttons.

        if (articleUrl != null) {

            webView.loadUrl(articleUrl);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(articleUrl);
                    return true;
                }
            });

        } else if (catArticalUrl != null) {

            webView.loadUrl(catArticalUrl);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(catArticalUrl);
                    return true;
                }
            });

        } else if (searchArticleUrl != null) {
            webView.loadUrl(searchArticleUrl);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(searchArticleUrl);
                    return true;
                }
            });
        }

        fab1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (catArticalUrl != null) {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, catArticalUrl);
                    startActivity(Intent.createChooser(shareIntent, "Share"));

                    articleUrl = null;
                    searchArticle = null;

                }  else if (articleUrl != null)  {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, articleUrl);
                    startActivity(Intent.createChooser(shareIntent, "Share"));

                    catArticalUrl = null;
                    searchArticleUrl = null;

                }   else if (searchArticle != null) {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, searchArticleUrl);
                    startActivity(Intent.createChooser(shareIntent, "Share"));

                    catArticalUrl = null;
                    articleUrl = null;
                }

            }
        });

        fab2FB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (catArticalUrl != null) {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, catArticalUrl);
                    startActivity(Intent.createChooser(shareIntent, "Share"));

                    articleUrl = null;
                    searchArticle = null;

                }  else if (articleUrl != null)  {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, articleUrl);
                    startActivity(Intent.createChooser(shareIntent, "Share"));

                    catArticalUrl = null;
                    searchArticleUrl = null;

                }  else if (searchArticle != null) {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, searchArticleUrl);
                    startActivity(Intent.createChooser(shareIntent, "Share"));

                    catArticalUrl = null;
                    articleUrl = null;
                }

            }
        });

        fab2FB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (catArticalUrl != null) {

                    if (ShareDialog.canShow(ShareLinkContent.class)) {

                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(catArticalName)
                                .setContentUrl(Uri.parse(catArticalUrl))
                                .build();

                        shareDialog.show(linkContent);

                    }

                    articleUrl = null;
                    searchArticleUrl = null;

                } else if (articleUrl != null) {

                    if (ShareDialog.canShow(ShareLinkContent.class)) {

                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(articleName)
                                .setContentDescription(articleDescription)
                                .setContentUrl(Uri.parse(articleUrl))
                                .build();

                        shareDialog.show(linkContent);
                    }

                    catArticalUrl = null;
                    searchArticleUrl = null;

                } else if (searchArticleUrl != null) {

                    if (ShareDialog.canShow(ShareLinkContent.class)) {

                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(searchArticleName)
                                .setContentDescription(searchArticleDescription)
                                .setContentUrl(Uri.parse(searchArticleUrl))
                                .build();

                        shareDialog.show(linkContent);
                    }

                    catArticalUrl = null;
                    articleUrl = null;

                }
            }
        });

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

                    searchArticleUrl = null;
                    articleUrl = null;

                } else if (articleUrl != null) {

                    if (MessageDialog.canShow(ShareLinkContent.class)) {

                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(articleName)
                                .setContentUrl(Uri.parse(articleUrl))
                                .build();

                        messageDialog.show(linkContent);

                    }

                    searchArticleUrl = null;
                    catArticalUrl = null;

                }  else if (searchArticleUrl != null) {


                    Log.i(TAG, "onClick: inside search");

                    Log.i(TAG, "onClick: search URL: "+searchArticleUrl);

                    if (ShareDialog.canShow(ShareLinkContent.class)) {

                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(searchArticleName)
                                .setContentDescription(searchArticleDescription)
                                .setContentUrl(Uri.parse(searchArticleUrl))
                                .build();

                        shareDialog.show(linkContent);
                    }

                    catArticalUrl = null;
                    articleUrl = null;

                }
            }
        });

        return view;
    }

    public void setSearchArticle(com.test.myapplication.SearchNewsObject.Value searchArticle) {
        this.searchArticle = searchArticle;
        this.searchArticleUrl = searchArticle.getUrl();
        this.searchArticleDescription = searchArticle.getDescription();
        this.searchArticleName = searchArticle.getName();
    }

    public void setDetailArticle(Value article) {
        this.article = article;
        this.articleUrl = article.getWebSearchUrl();
        this.articleName = article.getName();
    }

    public void setCatArticle(com.test.myapplication.CategoryNewsObject.Value catArticle) {
        this.catArticle = catArticle;
        this.catArticalUrl = catArticle.getUrl();
        this.catArticleDescription = catArticle.getDescription();
        this.catArticalName = catArticle.getName();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void initializeViews() {
        webView = (WebView) view.findViewById(R.id.web_view);
        fam = (FloatingActionMenu) view.findViewById(R.id.floating_action_menu);
        fab1 = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item1);
        fab2FB = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item2_fb);
        fab3Messenger = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item3_messenger);
    }

}
