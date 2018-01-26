package com.incomm.payithere.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.views.selectBiller.SelectBillerMVP;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jayma on 4/20/2017.
 */

public class CreatePaymentBillerListAdapter extends RecyclerView.Adapter<CreatePaymentBillerListAdapter.ViewHolder> implements Filterable{
    private List<BillerGroup> billerList;
    private List<BillerGroup> mFilteredList;
    private int globalTextColor;
    private String rightChevron;
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    public CreatePaymentBillerListAdapter(List<BillerGroup> billerList, int globalTextColor, String rightChevron){
        this.billerList = billerList;
        mFilteredList = billerList;
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
    public CreatePaymentBillerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_feature_row_no_icon ,parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreatePaymentBillerListAdapter.ViewHolder holder, int position) {

        BillerGroup billerGroupItem = mFilteredList.get(position);
        holder.categoryRowTitle.setText(billerGroupItem.getName());
        holder.categoryRowTitle.setTextColor(globalTextColor);
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        imageManager.displayImage(ActivityUtils.checkNull(rightChevron), holder.rightChevronIV, imageOptions);
        /*Picasso.with(PayItHereApplication.getContext())
                .load(ActivityUtils.checkNull(globalImages.getChevronRight()))
                .placeholder(R.drawable.global_chevron_right)
                .into(holder.billerRowIcon);*/
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

               if (charString.isEmpty()) {
                    mFilteredList = billerList;
                } else {

                    ArrayList<BillerGroup> filteredList = new ArrayList<>();

                    for (BillerGroup item : billerList) {

                        if (item.getName().toLowerCase().contains(charString)) {
                            filteredList.add(item);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<BillerGroup>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.feature_row_title)
        TextView categoryRowTitle;

        @BindView(R.id.feature_row_glyph)
        ImageView rightChevronIV;

        public ViewHolder(View view){
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
