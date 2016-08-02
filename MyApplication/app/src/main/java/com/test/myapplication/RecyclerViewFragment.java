package com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;

import java.util.ArrayList;

/**
 * Created by Jon Kim on 8/2/16.
 */
public class RecyclerViewFragment extends Fragment implements CustomRecyclerViewAdapter.ViewHolder.OnRecyclerViewItemClickListener{
    private static final String TAG = "RecyclerViewFragment";
    protected RecyclerView mRecyclerView;
    protected CustomRecyclerViewAdapter rvAdapter;
    protected RecyclerView.LayoutManager rvLayoutManager;
    protected ArrayList<ArticleWithDescriptionObject> mDataSet = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rvLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(rvLayoutManager);
        rvAdapter = new CustomRecyclerViewAdapter(mDataSet,this);
        mRecyclerView.setAdapter(rvAdapter);

        return rootView;
    }

    @Override
    public void onItemClick(int position) {

    }
}
