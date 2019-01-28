package com.example.joelruhe.myapplication.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joelruhe.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMilestonesFriendsFragment extends Fragment {

    @BindView(R.id.post_title)
    TextView title;
    @BindView(R.id.post_date)
    TextView date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_milestones_friends, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Typeface myCustomFont2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/open_sans_regular.ttf");

        // set font to textViews
        title.setTypeface(myCustomFont2);
        date.setTypeface(myCustomFont2);

    }
}
