package com.incomm.payithere.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.cms.Feature;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jerome Davis on 4/13/17.
 */

public class FeatureSectionAdapter extends RecyclerView.Adapter<FeatureSectionAdapter.ViewHolder> {

    private List<Feature> features;
    private String globaltextColor,nextChevron;
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    public FeatureSectionAdapter(List<Feature> features, String globalTextColor, String nextChevron){
        this.features = features;
        this.globaltextColor = globalTextColor;
        this.nextChevron = nextChevron;

        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .showImageForEmptyUri(R.drawable.global_chevron_right)
                .imageScaleType(ImageScaleType.NONE)
                .build();
    }

    @Override
    public FeatureSectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_feature_row_no_icon ,parent, false);

        return new FeatureSectionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeatureSectionAdapter.ViewHolder holder, int position) {

        Log.d("TAG", "doc: " + features.get(position).toString());
        holder.documentRowTitle.setText(features.get(position).getTitle());
        holder.documentRowTitle.setTextColor(Color.parseColor(globaltextColor));
        imageManager.displayImage(nextChevron,holder.nextChevronImg, imageOptions);

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView documentRowTitle;
        ImageView nextChevronImg;

        public ViewHolder(View view){
            super(view);

            documentRowTitle = (TextView) view.findViewById(R.id.feature_row_title);
            nextChevronImg = (ImageView) view.findViewById(R.id.feature_row_glyph);
        }
    }
}