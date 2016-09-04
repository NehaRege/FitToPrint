package com.test.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;
import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;
import com.test.myapplication.TrendingTopicsObject.Value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Langston Smith on 8/1/16.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>{
    private ArrayList<com.test.myapplication.CategoryNewsObject.Value> mCatData;
    private ArrayList<Value> mData;
    private ArrayList<com.test.myapplication.SearchNewsObject.Value> mSearchData;
    private Context mContext;
    private Resources res = Resources.getSystem();



    private static OnRecyclerViewItemClickListener onItemClickListener;

    public interface OnRecyclerViewItemClickListener{
        void onItemClick(int position);

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView rvTitleText, rvDateText;
        public ImageView rvImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            rvImageView = (ImageView) itemView.findViewById(R.id.rv_image);
            rvTitleText = (TextView) itemView.findViewById(R.id.rv_title);
            rvDateText = (TextView) itemView.findViewById(R.id.rv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onItemClick(getLayoutPosition());

                }
            });
        }
    }
    public CustomRecyclerViewAdapter(ArrayList<Value> mData,
                                     OnRecyclerViewItemClickListener listener, Context context,
                                     ArrayList<com.test.myapplication.CategoryNewsObject.Value> catData,
                                     ArrayList<com.test.myapplication.SearchNewsObject.Value> searchData) {
        this.mContext = context;
        if(listener!=null){
            this.onItemClickListener = listener;
        }

        if(mData!=null){
            this.mData = mData;
        }else{
            this.mData = new ArrayList<>();
        }

        if(catData!=null){
            this.mCatData = catData;
        }else{
            this.mCatData = new ArrayList<>();
        }

        if(searchData!=null){
            this.mSearchData = searchData;
        }else{
            this.mSearchData = new ArrayList<>();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View cardLayout = inflater.inflate(R.layout.rv_card_layout,parent,false);

        return new ViewHolder(cardLayout);
    }
    //if statements checking for object type an
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mData.size()>0) {
            Value value = mData.get(position);

            holder.rvTitleText.setText(value.getName());
            if(value.getImage()!=null){
                Picasso.with(mContext).load(value.getImage().getUrl()).into(holder.rvImageView);
            }
        }else if(mCatData.size()>0){
            com.test.myapplication.CategoryNewsObject.Value value = mCatData.get(position);

            holder.rvTitleText.setText(value.getName());
            if(value.getImage()!=null){
                Picasso.with(mContext).load(value.getImage().getThumbnail().getContentUrl()).into(holder.rvImageView);
            }
            if(value.getDatePublished()!=null) {
                String postedTime = value.getDatePublished();
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(postedTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long dateMilliseconds = date.getTime();
                long currentTime = Calendar.getInstance().getTimeInMillis();
                long howLongAgoPosted = dateMilliseconds - currentTime;
                if (howLongAgoPosted < 86400000) {
                    int hours = (int) ((howLongAgoPosted / (1000 * 60 * 60)) % 24);
                    holder.rvDateText.setText(String.valueOf(hours) + "hr ago");

                }
            }
        }else if(mSearchData.size()>0){
            com.test.myapplication.SearchNewsObject.Value value = mSearchData.get(position);

            holder.rvTitleText.setText(value.getName());
            if(value.getImage()!=null){
                Picasso.with(mContext).load(value.getImage().getThumbnail().getContentUrl()).into(holder.rvImageView);
            }
            if(value.getDatePublished()!=null) {
                String postedTime = value.getDatePublished();
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(postedTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long dateMilliseconds = date.getTime();
                long currentTime = Calendar.getInstance().getTimeInMillis();
                long howLongAgoPosted = dateMilliseconds - currentTime;
                if (howLongAgoPosted < 86400000) {
                    int hours = (int) ((howLongAgoPosted / (1000 * 60 * 60)) % 24);
                    holder.rvDateText.setText(String.valueOf(hours) + "hr ago");

                }
            }
        }

    }

    @Override
    public int getItemCount() {
        if(mData.size()>0){
            return mData.size();
        }else if(mCatData.size()>0){
            return mCatData.size();
        }else return mSearchData.size();

    }
    public void getTime(String publishedTime){

    }
}