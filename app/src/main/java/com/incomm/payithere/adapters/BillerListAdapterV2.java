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

public class BillerListAdapterV2 extends RecyclerView.Adapter<BillerListAdapterV2.ViewHolder> implements Filterable{
    private SelectBillerMVP.View view;
    private List<BillerGroup> billerList;
    private List<BillerGroup> mFilteredList;
    private int globalTextColor;
    private String rightChevron;
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    public BillerListAdapterV2(SelectBillerMVP.View view, List<BillerGroup> billerList, int globalTextColor, String rightChevron){
        this.view = view;
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
    public BillerListAdapterV2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_feature_row_no_icon ,parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillerListAdapterV2.ViewHolder holder, int position) {

        BillerGroup billerGroupItem = mFilteredList.get(position);
        holder.categoryRowTitle.setText(billerGroupItem.getName());
        holder.categoryRowTitle.setTextColor(globalTextColor);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                view.displayPaymentFragment(mFilteredList.get(pos));
            }
        });


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
                view.displayQueryData(mFilteredList);
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.feature_row_title)
        TextView categoryRowTitle;

        @BindView(R.id.feature_row_glyph)
        ImageView rightChevronIV;

        ItemClickListener itemClickListener;

        public ViewHolder(View view){
            super(view);

            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }
    }

    public interface ItemClickListener {
        void onItemClick(View v,int pos);
    }
}
