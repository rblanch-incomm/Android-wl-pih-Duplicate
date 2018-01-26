package com.incomm.payithere.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.cms.TopBiller;
import com.incomm.payithere.utils.ActivityUtils;
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

public class TopBillerListAdapter extends RecyclerView.Adapter<TopBillerListAdapter.ViewHolder> implements Filterable{
    private List<TopBiller> billerList;
    private List<TopBiller> mFilteredList;
    private int globalTextColor;
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    public TopBillerListAdapter(List<TopBiller> billerList, int globalTextColor){
        this.billerList = billerList;
        mFilteredList = billerList;
        this.globalTextColor = globalTextColor;

        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .showImageForEmptyUri(R.drawable.global_chevron_right)
                .imageScaleType(ImageScaleType.NONE)
                .build();
    }

    @Override
    public TopBillerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_top_biller_row ,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopBillerListAdapter.ViewHolder holder, int position) {
        TopBiller billerGroupItem = mFilteredList.get(position);

        holder.billerRowTitle.setText(billerGroupItem.getName());
        holder.billerRowTitle.setTextColor(globalTextColor);

       /* if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }*/

        imageManager.displayImage(ActivityUtils.checkNull(billerGroupItem.getLogoImage()), holder.billerRowIcon, imageOptions);
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

                    ArrayList<TopBiller> filteredList = new ArrayList<>();

                    for (TopBiller item : billerList) {

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
                mFilteredList = (ArrayList<TopBiller>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.question_tv)
        TextView billerRowTitle;
        @BindView(R.id.biller_icon)
        ImageView billerRowIcon;

        public ViewHolder(View view){
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
