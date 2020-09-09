package com.example.leaderboard.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboard.R;
import com.example.leaderboard.models.Hours;
import com.example.leaderboard.models.SkillIQ;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillIqLeaders extends RecyclerView.Adapter<SkillIqLeaders.SkillIqLeadersViewHolder> {
    private List<SkillIQ> mSkillIQS;
    Context mContext;
    String mName, mCountry, mBadgeUrl;
    int mHour;
    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 55;

    public SkillIqLeaders(Context ct, List<SkillIQ> skills){
        mSkillIQS = skills;
        mContext = ct;
    }

    @NonNull
    @Override
    public SkillIqLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_iq_leaders_list, parent, false);
        return new SkillIqLeadersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIqLeadersViewHolder holder, int position) {
        holder.bindSkills(mSkillIQS.get(position));
    }

    @Override
    public int getItemCount() {
        return mSkillIQS.size();
    }

    public class SkillIqLeadersViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.skill_iq_img)
        ImageView mImageView;
        @BindView(R.id.skills_leaders_name)
        TextView mSkillLeader;
        @BindView(R.id.scores_country) TextView mHoursCountry;

        public SkillIqLeadersViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            Context context = itemView.getContext();
        }

        @SuppressLint("SetTextI18n")
        public void bindSkills(SkillIQ skill) {
            Picasso.with(mContext).load(skill.getBadgeUrl()).resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mImageView);
            mSkillLeader.setText(skill.getName());
            mHoursCountry.setText(skill.getScore() + " skill IQ Score, " + skill.getCountry());

        }
    }
}
