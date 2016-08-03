package com.test.myapplication;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jon Kim on 8/1/16.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Value> mData;
    private Context mContext;

    private static OnRecyclerViewItemClickListener onItemClickListener;

    public interface OnRecyclerViewItemClickListener{
        void onItemClick(int position);

    }
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onItemClick(getLayoutPosition());


                }
            });
        }
    }
//add other types of articlelist data into parameters, null check other types to find out which on is sent.
    public CustomRecyclerViewAdapter(ArrayList<Value> mData,
                                     OnRecyclerViewItemClickListener listener, Context context) {
        this.mContext = context;
        if(listener!=null){
            this.onItemClickListener = listener;
        }
        if(mData!=null){
            this.mData = mData;
        }else{
            this.mData = new ArrayList<>();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardLayout = inflater.inflate(R.layout.rv_card_layout,parent,false);

        return new ViewHolder(cardLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Value value = mData.get(position);

        holder.rvTitleText.setText(value.getName());
        Picasso.with(mContext).load(value.getImage().getUrl()).into(holder.rvImageView);


//        holder.rvFollowButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //follow button
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
