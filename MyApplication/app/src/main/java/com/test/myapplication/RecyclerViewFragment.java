package com.test.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import retrofit2.Call;


import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;
import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;
import com.test.myapplication.TrendingTopicsObject.Value;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Retrofit;

/**
 * Created by Jon Kim on 8/2/16.
 */
public class RecyclerViewFragment extends Fragment implements CustomRecyclerViewAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = "RecyclerViewFragment";
    protected RecyclerView mRecyclerView;
    protected CustomRecyclerViewAdapter rvAdapter;
    protected RecyclerView.LayoutManager rvLayoutManager;
    private ArrayList<Value> mDataSet;
    private ArrayList<com.test.myapplication.CategoryNewsObject.Value> catData;
    private ArrayList<com.test.myapplication.SearchNewsObject.Value> searchData;
    private Value mArticle;
    private com.test.myapplication.CategoryNewsObject.Value catArticle;
    private com.test.myapplication.SearchNewsObject.Value searchArticle;
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
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rvLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(rvLayoutManager);

        Bundle bundle = getArguments();
        Log.i(TAG, "onCreate: getArguments() run");

        if (bundle != null) {
            if(bundle.containsKey("ArrayList of articles")) {
                mDataSet = getArguments().getParcelableArrayList("ArrayList of articles");
                rvAdapter = new CustomRecyclerViewAdapter(mDataSet,this,getActivity(), null, null);
                mRecyclerView.setAdapter(rvAdapter);
                Log.i(TAG, "onCreate: getParcelableArrayList successfully run");
            }else if(bundle.containsKey("Search")){
                searchData = (ArrayList<com.test.myapplication.SearchNewsObject.Value>)getArguments().getSerializable("Search");
                rvAdapter = new CustomRecyclerViewAdapter(null,this,getActivity(),null,searchData);
                mRecyclerView.setAdapter(rvAdapter);
            }
            else if(bundle.containsKey("Business")) {
                setAdapter("Business");

            }else if(bundle.containsKey("Entertainment")) {
                setAdapter("Entertainment");

            }else if(bundle.containsKey("Health")) {
                setAdapter("Health");

            }else if(bundle.containsKey("Politics")) {
                setAdapter("Politics");

            }else if(bundle.containsKey("ScienceAndTechnology")) {
                setAdapter("ScienceAndTechnology");

            }else if(bundle.containsKey("Sports")) {
                setAdapter("Sports");

            }else if(bundle.containsKey("US/UK")) {
                setAdapter("US/UK");

            }else if(bundle.containsKey("World")) {
                setAdapter("World");

            }

        } else {
            mDataSet = new ArrayList<Value>();
            Log.i(TAG, "onCreate: bundle's null");
        }







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
        if(mDataSet!=null){
            mArticle = mDataSet.get(position);
            mListener.onArticleSelected(mArticle);
        }else if(catData!=null){
            catArticle = catData.get(position);
            mListener.onCatArticleSelected(catArticle);
        }
//        toolbar.setTitle(mArticle.getValue().get(0).getCategory());
    }

    private void setAdapter(String key){
        catData = (ArrayList< com.test.myapplication.CategoryNewsObject.Value>)getArguments().getSerializable(key);
        rvAdapter = new CustomRecyclerViewAdapter(null, this, getActivity(),catData, null);
        mRecyclerView.swapAdapter(rvAdapter,false);
    }
}
