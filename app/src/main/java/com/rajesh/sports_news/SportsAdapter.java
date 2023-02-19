package com.rajesh.sports_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {


    ArrayList<Sport> mSportsData;
    private static Context mContext;

    public SportsAdapter(ArrayList<Sport> mSportsData, Context mContext) {
        this.mSportsData = mSportsData;
        this.mContext = mContext;
    }


    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SportsAdapter.ViewHolder holder, int position) {

        Sport currentSport = mSportsData.get(position);
        holder.bindTo(currentSport);

    }


    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleText;
        TextView mInfoText;

        ImageView mSportsImages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.heading);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImages = itemView.findViewById(R.id.sportsImages);
        }

        void bindTo(Sport currentSport) {

            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());

            Glide.with(mContext).load(currentSport.getImageResource()).into(mSportsImages);


        }


    }
}
