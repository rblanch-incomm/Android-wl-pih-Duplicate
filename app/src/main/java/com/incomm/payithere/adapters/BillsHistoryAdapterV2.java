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
import com.incomm.payithere.models.BillsHistoryItem;
import com.incomm.payithere.views.billsHistory.BillerHistoryMVP;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jayma on 4/20/2017.
 */

public class BillsHistoryAdapterV2 extends RecyclerView.Adapter<BillsHistoryAdapterV2.ViewHolder> implements Filterable{
    private BillerHistoryMVP.View view;
    private ArrayList<BillsHistoryItem> billsList;
    private ArrayList<BillsHistoryItem> mFilteredList;
    private String rightChevron;
    private int globalTextColor;
    private ImageLoader imageManager = ImageLoader.getInstance();
    private DisplayImageOptions imageOptions;


    public BillsHistoryAdapterV2(BillerHistoryMVP.View view,ArrayList<BillsHistoryItem> billsList, String rightChevron, int globalTextColor) {
        this.view = view;
        this.billsList = billsList;
        mFilteredList = billsList;
        this.rightChevron = rightChevron;
        this.globalTextColor = globalTextColor;
        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .showImageForEmptyUri(R.drawable.global_chevron_right)
                .imageScaleType(ImageScaleType.NONE)
                .build();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_bill_history_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.billerName.setText(mFilteredList.get(position).getBillerName());
        holder.billerName.setTextColor(globalTextColor);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                view.displayDetailFragment(mFilteredList.get(pos));
            }
        });

        String amount = mFilteredList.get(position).getAmount();
        if (amount != null && !amount.equals("")) {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            //holder.amount.setText("$ " + amount);
            holder.amount.setText(format.format(Float.parseFloat(amount)));
            holder.amount.setTextColor(globalTextColor);
        }

        Date date = mFilteredList.get(position).getTimestamp();
        SimpleDateFormat output = new SimpleDateFormat("MM/dd/yyyy");
        holder.paymentDate.setText(output.format(date));

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }

        imageManager.displayImage(rightChevron,holder.chevron, imageOptions);


        holder.paymentStatus.setText(mFilteredList.get(position).getText());
        holder.paymentStatus.setTextColor(Color.parseColor(mFilteredList.get(position).getTextColor()));
        return;
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
                    mFilteredList = billsList;
                } else {

                    ArrayList<BillsHistoryItem> filteredList = new ArrayList<>();

                    for (BillsHistoryItem item : billsList) {

                        if (item.getBillerName().toLowerCase().contains(charString) ||
                                item.getStatus().toLowerCase().contains(charString) //||
                                //item.getTimestamp().toString().toLowerCase().contains(charString)
                                //|| item.getAmount().contains(charString)
                                ) {

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
                mFilteredList = (ArrayList<BillsHistoryItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView billerName;
        public TextView paymentStatus;
        public TextView paymentDate;
        public TextView amount;
        public ImageView chevron;
        ItemClickListener itemClickListener;
        //public ImageView billerRowIcon;

        public ViewHolder(View view) {
            super(view);

            billerName = (TextView) view.findViewById(R.id.question_tv);
            amount = (TextView) view.findViewById(R.id.payment_amount);
            paymentStatus = (TextView) view.findViewById(R.id.payment_status);
            paymentDate = (TextView) view.findViewById(R.id.payment_date);
            chevron = (ImageView) view.findViewById(R.id.chevron_iv);
            view.setOnClickListener(this);
            //billerRowIcon = (ImageView) view.findViewById(R.id.feature_row_glyph);
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
