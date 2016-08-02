package com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by NehaRege on 8/1/16.
 */
public class DetailFragment extends Fragment {

    ImageView imageView;
    TextView textViewTopic;
    TextView textViewTitle;
    TextView textViewText;


    FloatingActionMenu fam;
    FloatingActionButton fab1, fab2, fab3;

    private View view;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail,container,false);

        initializeViews();



        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void initializeViews() {

        imageView = (ImageView) view.findViewById(R.id.fragment_detail_image);
        textViewText = (TextView) view.findViewById(R.id.fragment_detail_text);
        textViewTitle = (TextView) view.findViewById(R.id.fragment_detail_title);
        textViewTopic = (TextView) view.findViewById(R.id.fragment_detail_topic);

        fam = (FloatingActionMenu) view.findViewById(R.id.floating_action_menu);
        fab1 = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.floating_action_menu_item3);


    }




}
