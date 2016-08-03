package com.test.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;
import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;
import com.test.myapplication.TrendingTopicsObject.Value;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jon Kim on 8/2/16.
 */
public class RecyclerViewFragment extends Fragment implements CustomRecyclerViewAdapter.ViewHolder.OnRecyclerViewItemClickListener {
    private static final String TAG = "RecyclerViewFragment";
    protected RecyclerView mRecyclerView;
    protected CustomRecyclerViewAdapter rvAdapter;
    protected RecyclerView.LayoutManager rvLayoutManager;
    private ArrayList<Value> mDataSet;
    protected Value mArticle;
    Toolbar toolbar;
    OnArticleSelectedListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        rootView.setTag(TAG);

        Bundle bundle = this.getArguments();

        if (bundle == null) {
            mDataSet = new ArrayList<Value>();

        } else {

            mDataSet = bundle.getParcelableArrayList("ArrayList of articles");
        }
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rvLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(rvLayoutManager);


        rvAdapter = new CustomRecyclerViewAdapter(mDataSet, this);
        mRecyclerView.setAdapter(rvAdapter);


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnArticleSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onItemClick(int position) {
        mArticle = mDataSet.get(position);
        mListener.onArticleSelected(mArticle);
//        toolbar.setTitle(mArticle.getValue().get(0).getCategory());
    }
}
