package com.incomm.payithere.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.BillerCategoryItem;
import com.incomm.payithere.models.cms.BillerCategoryIcon;
import com.incomm.payithere.models.services.response.BillerCategory;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.utils.ActivityUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jayma on 4/20/2017.
 */

public class BillerCategoryListAdapter extends RecyclerView.Adapter<BillerCategoryListAdapter.ViewHolder>{
    private List<BillerCategoryItem> categoryList;
    private int globalTextColor;
    private String rightChevron;
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    public BillerCategoryListAdapter(List<BillerCategoryItem> categoryList,
                                     int globalTextColor, String rightChevron){
        this.categoryList = categoryList;
        this.globalTextColor = globalTextColor;
        this.rightChevron = rightChevron;

        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .showImageForEmptyUri(R.drawable.global_chevron_right)
                .imageScaleType(ImageScaleType.NONE)
                .build();
    }

    @Override
    public BillerCategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_category_row ,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillerCategoryListAdapter.ViewHolder holder, int position) {
        BillerCategoryItem category = categoryList.get(position);

        holder.categoryRowTitle.setText(category.getName());
        holder.categoryRowTitle.setTextColor(globalTextColor);

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        imageManager.displayImage(ActivityUtils.checkNull(rightChevron), holder.rightChevron, imageOptions);
        imageManager.displayImage(ActivityUtils.checkNull(categoryList.get(position).getIconUrl()),
                holder.categoryIcon,imageOptions);
        /*Picasso.with(PayItHereApplication.getContext())
                .load(ActivityUtils.checkNull(globalImages.getChevronRight()))
                .placeholder(R.drawable.global_chevron_right)
                .into(holder.billerRowIcon);*/
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.feature_row_title)
        TextView categoryRowTitle;
        @BindView(R.id.feature_row_glyph)
        ImageView rightChevron;
        @BindView(R.id.feature_icon)
        ImageView categoryIcon;



        public ViewHolder(View view){
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
