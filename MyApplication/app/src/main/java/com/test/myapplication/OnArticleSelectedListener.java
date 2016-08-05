package com.test.myapplication;

import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;
import com.test.myapplication.TrendingTopicsObject.Value;

/**
 * Created by Jon Kim on 8/2/16.
 */
public interface OnArticleSelectedListener {
    public void onArticleSelected(Value selectedArticle);
    public void onCatArticleSelected(com.test.myapplication.CategoryNewsObject.Value catArticle);
    public void onSearchArticleSelected(com.test.myapplication.SearchNewsObject.Value searchArticle);
}
