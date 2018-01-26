package com.incomm.payithere.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.utils.ActivityUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jerome Davis on 4/13/17.
 */

public class MoreViewAdapter extends RecyclerView.Adapter<MoreViewAdapter.ViewHolder> {
    private List<Feature> features;

    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();
    private String globalTextColor,nextChevron;

    public MoreViewAdapter(List<Feature> features, String globalTextColor, String nextChevron){
        this.features = features;
        this.globalTextColor = globalTextColor;
        this.nextChevron = nextChevron;

        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .showImageForEmptyUri(R.drawable.global_chevron_right)
                .imageScaleType(ImageScaleType.NONE)
                .build();
    }

    @Override
    public MoreViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_feature_row ,parent, false);

        return new MoreViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreViewAdapter.ViewHolder holder, int position) {
        Feature feature = features.get(position);

        holder.featureRowTitle.setText(feature.getTitle());
        holder.featureRowTitle.setTextColor(Color.parseColor(globalTextColor));
        imageManager.displayImage(nextChevron,holder.nextChevron, imageOptions);
        if (feature.getIconPrimary() != null) {
            String imageUrl = feature.getIconPrimary().url();
            imageManager.displayImage(imageUrl, holder.featureIcon, imageOptions);
        } else {
            holder.featureIconContainer.setVisibility(View.GONE);
        }

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
        @BindView(R.id.feature_row_title)
        TextView featureRowTitle;
        @BindView(R.id.feature_icon_container)
        LinearLayout featureIconContainer;
        @BindView(R.id.feature_icon)
        ImageView featureIcon;
        @BindView(R.id.feature_row_glyph)
        ImageView nextChevron;

        public ViewHolder(View view){
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}


