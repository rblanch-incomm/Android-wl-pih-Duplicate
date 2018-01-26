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
import com.incomm.payithere.models.cms.FAQCategoryItem;
import com.incomm.payithere.models.cms.GlobalImages;
import com.incomm.payithere.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jayma on 4/20/2017.
 */

public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.ViewHolder>{
    private List<FAQCategoryItem> categories;
    private GlobalImages globalImages;
    private int globalTextColor;

    public FaqsAdapter(List<FAQCategoryItem> categories, GlobalImages globalImages, int globalTextColor){
        this.categories = categories;
        this.globalImages = globalImages;
        this.globalTextColor = globalTextColor;
    }

    @Override
    public FaqsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_feature_row_no_icon ,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FaqsAdapter.ViewHolder holder, int position) {
        FAQCategoryItem faqCategoryItem = categories.get(position);

        holder.categoryRowTitle.setText(faqCategoryItem.getName());
        holder.categoryRowTitle.setTextColor(globalTextColor);

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }

        //imageManager.displayImage(ActivityUtils.checkNull(globalImages.getChevronRight()), holder.billerRowIcon, imageOptions);
        Picasso.with(PayItHereApplication.getContext())
                .load(ActivityUtils.checkNull(globalImages.getChevronRight()))
                .placeholder(R.drawable.global_chevron_right)
                .into(holder.chevron);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.feature_row_title)
        TextView categoryRowTitle;

        @BindView(R.id.feature_row_glyph)
        ImageView chevron;

        public ViewHolder(View view){
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
