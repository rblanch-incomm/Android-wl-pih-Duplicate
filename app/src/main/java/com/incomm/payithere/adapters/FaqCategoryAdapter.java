package com.incomm.payithere.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.models.cms.FAQQuestionItem;
import com.incomm.payithere.models.cms.GlobalImages;
import com.incomm.payithere.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jayma on 4/20/2017.
 */

public class FaqCategoryAdapter extends RecyclerView.Adapter<FaqCategoryAdapter.ViewHolder>{
    private List<FAQQuestionItem> questions;
    private String rightChevron;
    private int globalTextColor;

    public FaqCategoryAdapter(List<FAQQuestionItem> questions, String rightChevron, int globalTextColor) {
        this.questions = questions;
        this.rightChevron = rightChevron;
        this.globalTextColor = globalTextColor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_feature_row_no_icon ,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FAQQuestionItem question = questions.get(position);

        holder.questionRowTitle.setText(question.getQuestion());
        holder.questionRowTitle.setTextColor(globalTextColor);

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }

        Picasso.with(PayItHereApplication.getContext()).load(rightChevron).into(holder.chevron);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionRowTitle;
        public ImageView chevron;

        public ViewHolder(View view){
            super(view);

            questionRowTitle = (TextView) view.findViewById(R.id.feature_row_title);
            chevron = (ImageView) view.findViewById(R.id.feature_row_glyph);
        }
    }
}
