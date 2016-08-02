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

import java.util.ArrayList;


/**
 * Created by Jon Kim on 8/1/16.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>{

    private ArrayList<ArticleWithDescriptionObject> mData;
    private static ViewHolder.OnRecyclerViewItemClickListener onItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView rvTitleText, rvDesText, rvTopicText, rvDateText;
        public ImageView rvImageView;
        public Button rvFollowButton;

        public interface OnRecyclerViewItemClickListener{
            void onItemClick(int position);

        }

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

    public CustomRecyclerViewAdapter(ArrayList<ArticleWithDescriptionObject> mData,
                                     ViewHolder.OnRecyclerViewItemClickListener listener) {
        this.onItemClickListener = listener;
//        if(mData!=null){
            this.mData = mData;
//        }else{
//            this.mData = new List<ArticleWithDescriptionObject>();
//        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardLayout = inflater.inflate(R.layout.rv_card_layout,parent,false);
//        ViewHolder viewHolder = new ViewHolder(cardLayout);

        return new ViewHolder(cardLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticleWithDescriptionObject topic = mData.get(position);

        holder.rvTitleText.setText(topic.getValue().get(0).getName());
        holder.rvDesText.setText(topic.getValue().get(0).getDescription());
        holder.rvDateText.setText(topic.getValue().get(0).getDatePublished());
        holder.rvTopicText.setText(topic.getValue().get(0).getCategory());
//        if(topic.getValue().get(0).getImage()!=null){
//            Picasso.with()
//        }
        holder.rvFollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //follow button
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
