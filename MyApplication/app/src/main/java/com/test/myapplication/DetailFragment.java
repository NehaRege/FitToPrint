package com.test.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;
import com.test.myapplication.TrendingTopicsObject.Value;

/**
 * Created by NehaRege on 8/1/16.
 */
public class DetailFragment extends Fragment {

    FloatingActionMenu fam;
    FloatingActionButton fab1Fb, fab2Twt, fab3Messenger, fab4;

    WebView webView;

    CallbackManager callbackManager;

    ShareDialog shareDialog;
    MessageDialog messageDialog;

    private View view;

    ArticleWithDescriptionObject article;
    String articleName;
    String articleUrl;
    String articleDescription;

    String catArticalName;
    String catArticalUrl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail,container,false);

        initializeViews();

        callbackManager = CallbackManager.Factory.create();

        shareDialog = new ShareDialog(this);

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
            articleUrl=null;
        }else if(catArticalUrl!=null){
            webView.loadUrl(catArticalUrl);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(catArticalUrl);
                    return true;

                }
            });
            catArticalUrl=null;
        }


        // fb

        fab1Fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(articleName)
                            .setContentDescription(articleDescription)
                            .setContentUrl(Uri.parse(articleUrl))
                            .build();

                    shareDialog.show(linkContent);
                }
            }
        });

        fab2Twt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MessageDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(articleName)
                            .setContentDescription(articleDescription)
                            .setContentUrl(Uri.parse(articleUrl))
                            .build();

                    messageDialog.show(linkContent);

                }

            }
        });

        // fb messenger

        fab3Messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MessageDialog.canShow(ShareLinkContent.class)) {

                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(articleName)
                            .setContentDescription(articleDescription)
                            .setContentUrl(Uri.parse(articleUrl))
                            .build();

                    messageDialog.show(linkContent);

                }
            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MessageDialog.canShow(ShareLinkContent.class)) {

                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(articleName)
                            .setContentDescription(articleDescription)
                            .setContentUrl(Uri.parse(articleUrl))
                            .build();

                    messageDialog.show(linkContent);
                }
            }
        });


        return view;
    }


    public void setDetailArticle(Value article){
        this.articleUrl = article.getWebSearchUrl();
        this.articleName = article.getName();
    }

    public void setCatArticle(com.test.myapplication.CategoryNewsObject.Value catArticle){
        this.catArticalUrl = catArticle.getUrl();
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
        fab2Twt = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item2);
        fab3Messenger = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item3_messenger);
        fab4 =(FloatingActionButton) view.findViewById(R.id.floating_action_menu_item4);

    }

}
