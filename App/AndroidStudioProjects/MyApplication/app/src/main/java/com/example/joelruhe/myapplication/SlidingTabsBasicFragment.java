/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.joelruhe.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.joelruhe.myapplication.activities.BroadcastTimerService;
import com.example.joelruhe.myapplication.activities.PlantActivity;
import com.example.joelruhe.myapplication.common.view.SlidingTabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class SlidingTabsBasicFragment extends Fragment {
    protected SlidingTabLayout mSlidingTabLayout;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    public static final String MY_PREFS_NAME = "MyPrefs";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ViewPager mViewPager;
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());

        mSlidingTabLayout = view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference userPinDatabase = mDatabase.child("Pins");
        SharedPreferences preferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String pinString = preferences.getString("pin", null);

        assert pinString != null;
        final DatabaseReference childUserPin = userPinDatabase.child(pinString);
        final DatabaseReference childUserPlants = childUserPin.child("Plants");

        adapter = new ArrayAdapter<>(getActivity(), R.layout.pager_item, arrayList);

        childUserPlants.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String, Object> string = (Map<String, Object>) dataSnapshot.getValue();
                //String string = dataSnapshot.getValue(String.class);
                //arrayList.add(string);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String string = dataSnapshot.getValue(String.class);
                arrayList.remove(string);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    class SamplePagerAdapter extends PagerAdapter {

        //private ArrayList<String> slide_headings = new ArrayList<>();
        //private ArrayList<Integer> slide_images = new ArrayList<>();

        private int[] slide_images = {
                R.drawable.flowerpot,
                R.drawable.flowerpot,
                R.drawable.flowerpot,
                R.drawable.flowerpot,
                R.drawable.flowerpot,
        };

        private String[] slide_headings = {
                "Coriander",
                "Strawberry",
                "Spinach",
                "Basil",
                "Spider plant"
        };

        @Override
        public int getCount() {
            //return slide_headings.size();
            return slide_headings.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return slide_headings.get(position);
            return slide_headings[position];
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @NonNull
        @Override
        public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
            // Inflate a new layout from our resources
            View view = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);

            ImageButton btn = view.findViewById(R.id.slide_image);
            TextView slideHeading = view.findViewById(R.id.slide_heading);

            Typeface myCustomFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/open_sans_bold.ttf");

            btn.setImageResource(slide_images[position]);
            slideHeading.setText(slide_headings[position]);
            slideHeading.setTypeface(myCustomFont);
            slideHeading.setTextColor(getResources().getColor(R.color.colorPrimary));

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        Intent intent = new Intent(getActivity(), PlantActivity.class);
                        intent.putExtra("DESCRIPTION", slide_headings[position]);
                        intent.putExtra("ID", position);
                        intent.putExtra("plantImage", slide_images[position]);
                        intent.putExtra("plantNameNoCapital", "coriander");
                        startActivity(intent);

                    }
                    if (position == 1) {
                        Intent intent = new Intent(getActivity(), PlantActivity.class);
                        intent.putExtra("DESCRIPTION", slide_headings[position]);
                        intent.putExtra("ID", position);
                        intent.putExtra("plantImage", slide_images[position]);
                        intent.putExtra("plantNameNoCapital", "strawberry");
                        startActivity(intent);

                    }
                    if (position == 2) {
                        Intent intent = new Intent(getActivity(), PlantActivity.class);
                        intent.putExtra("DESCRIPTION", slide_headings[position]);
                        intent.putExtra("ID", position);
                        intent.putExtra("plantImage", slide_images[position]);
                        intent.putExtra("plantNameNoCapital", "spinach");
                        startActivity(intent);
                    }
                    if (position == 3) {
                        Intent intent = new Intent(getActivity(), PlantActivity.class);
                        intent.putExtra("DESCRIPTION", slide_headings[position]);
                        intent.putExtra("ID", position);
                        intent.putExtra("plantImage", slide_images[position]);
                        intent.putExtra("plantNameNoCapital", "basil");
                        startActivity(intent);
                    }
                    if (position == 4) {
                        Intent intent = new Intent(getActivity(), PlantActivity.class);
                        intent.putExtra("DESCRIPTION", slide_headings[position]);
                        intent.putExtra("ID", position);
                        intent.putExtra("plantImage", slide_images[position]);
                        intent.putExtra("plantNameNoCapital", "spider plant");
                        startActivity(intent);
                    }
                }
            });

            // Return the View
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}

