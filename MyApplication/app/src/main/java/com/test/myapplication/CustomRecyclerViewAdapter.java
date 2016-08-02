package com.test.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jon Kim on 8/1/16.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>{

    private List<TrendingTopics> mData;
    private TextView cvTitleText, cvDesText, cvTopicText, cvDateText;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView rvTitleText, rvDesText, rvTopicText, rvDateText;
        public ImageView rvImageView;
        public Button rvFollowButton;

        public ViewHolder(View itemView) {
            super(itemView);

            rvTitleText = (TextView) itemView.findViewById(R.id.rv_title);
            rvDesText = (TextView) itemView.findViewById(R.id.rv_description);
            rvTopicText = (TextView) itemView.findViewById(R.id.rv_topic);
            rvDateText = (TextView) itemView.findViewById(R.id.rv_date);
            rvImageView = (ImageView) itemView.findViewById(R.id.rv_image);
            rvFollowButton = (Button) itemView.findViewById(R.id.rv_follow_button);

        }

    }

    public CustomRecyclerViewAdapter(List<TrendingTopics> mData) {
//        if(mData!=null){
            this.mData = mData;
//        }else{
//            this.mData = new List<TrendingTopics>();
//        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardLayout = inflater.inflate(R.layout.rv_card_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(cardLayout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrendingTopics topic = mData.get(position);

        cvTitleText = holder.rvTitleText;
        cvDesText = holder.rvDesText;
        cvDateText = holder.rvDateText;
        cvTopicText = holder.rvTopicText;



    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
