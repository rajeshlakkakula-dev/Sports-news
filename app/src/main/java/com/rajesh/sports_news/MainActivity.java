package com.rajesh.sports_news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    ArrayList<Sport> mSportData;
    SportsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview = findViewById(R.id.recyclerView);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        mRecyclerview.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        mSportData = new ArrayList<>();

        mAdapter = new SportsAdapter(mSportData, this);

        mRecyclerview.setAdapter(mAdapter);

        initData();


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                        | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                Collections.swap(mSportData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                mSportData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });

        helper.attachToRecyclerView(mRecyclerview);


    }

    private void initData() {

        String[] sportsList = getResources().getStringArray(R.array.sports_titles);

        String[] sportsInfo = getResources().getStringArray(R.array.sports_info);

        TypedArray sportsImageResources = getResources().obtainTypedArray(R.array.sports_images);

        mSportData.clear();

        for (int i = 0; i < sportsList.length; i++) {

            mSportData.add(new Sport(sportsList[i], sportsInfo[i], sportsImageResources.getResourceId(i, 0)));

        }

        mAdapter.notifyDataSetChanged();

    }
}