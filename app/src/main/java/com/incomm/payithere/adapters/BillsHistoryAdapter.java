package com.incomm.payithere.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.BillsHistoryItem;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jayma on 4/20/2017.
 */

public class BillsHistoryAdapter extends RecyclerView.Adapter<BillsHistoryAdapter.ViewHolder> implements Filterable{
    private ArrayList<BillsHistoryItem> billsList;
    private ArrayList<BillsHistoryItem> mFilteredList;
    private String rightChevron;
    private int globalTextColor;

    public BillsHistoryAdapter(ArrayList<BillsHistoryItem> billsList, String rightChevron, int globalTextColor) {
        this.billsList = billsList;
        mFilteredList = billsList;
        this.rightChevron = rightChevron;
        this.globalTextColor = globalTextColor;
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

        /*if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }*/

        if (date.before(new Date()) && mFilteredList.get(position).getStatus().equals("pending")) {
            holder.paymentStatus.setText("Expired");
            holder.paymentStatus.setTextColor(Color.RED);
            return;
        }
        if (date.after(new Date()) && mFilteredList.get(position).getStatus().equals("pending")) {
            holder.paymentStatus.setText("Expires");
            holder.paymentStatus.setTextColor(Color.YELLOW);
            return;
        }
        holder.paymentStatus.setText("Redeemed");
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView billerName;
        public TextView paymentStatus;
        public TextView paymentDate;
        public TextView amount;
        //public ImageView billerRowIcon;

        public ViewHolder(View view) {
            super(view);

            billerName = (TextView) view.findViewById(R.id.question_tv);
            amount = (TextView) view.findViewById(R.id.payment_amount);
            paymentStatus = (TextView) view.findViewById(R.id.payment_status);
            paymentDate = (TextView) view.findViewById(R.id.payment_date);
            //billerRowIcon = (ImageView) view.findViewById(R.id.feature_row_glyph);
        }
    }
}
