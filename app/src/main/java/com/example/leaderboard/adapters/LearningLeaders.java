package com.example.leaderboard.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboard.R;
import com.example.leaderboard.models.Hours;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearningLeaders extends RecyclerView.Adapter<LearningLeaders.LearningLeadersViewHolder> {
    private List<Hours> mHours;
    Context mContext;
    String mName, mCountry, mBadgeUrl;
    int mHour;
    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 100;


    public LearningLeaders(Context ct, List<Hours> hours){
        mHours = hours;
        mContext = ct;
    }

    @NonNull
    @Override
    public LearningLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_leaders_list, parent, false);
        return new LearningLeadersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeadersViewHolder holder, int position) {
        holder.bindHours(mHours.get(position));
    }

    @Override
    public int getItemCount() {

        return mHours.size();
    }

    public class LearningLeadersViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.top_learner_img)
        ImageView mImageView;
        @BindView(R.id.learning_leaders_name)
        TextView mLearningLeader;
        @BindView(R.id.hours_country) TextView mHoursCountry;

        public LearningLeadersViewHolder(@NonNull View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
            Context context = itemView.getContext();
        }

        @SuppressLint("SetTextI18n")
        public void bindHours(Hours hour) {
            Picasso.with(mContext).load(hour.getBadgeUrl()).resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mImageView);
            mLearningLeader.setText(hour.getName());
            mHoursCountry.setText(hour.getHours() + " learning hours, " + hour.getCountry());

        }
    }
}
