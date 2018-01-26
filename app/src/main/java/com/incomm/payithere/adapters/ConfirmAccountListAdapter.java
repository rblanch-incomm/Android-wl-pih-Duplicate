package com.incomm.payithere.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.ConfirmAccountItem;
import com.incomm.payithere.models.cms.GlobalImages;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jayma on 4/20/2017.
 */

public class ConfirmAccountListAdapter extends RecyclerView.Adapter<ConfirmAccountListAdapter.ViewHolder>{
    private List<ConfirmAccountItem> itemsList;
    private GlobalImages globalImages;
    private int globalTextColor;

    public ConfirmAccountListAdapter(List<ConfirmAccountItem> itemsList, int globalTextColor){
        this.itemsList = itemsList;
        this.globalTextColor = globalTextColor;
    }

    @Override
    public ConfirmAccountListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_confirm_account_row,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConfirmAccountListAdapter.ViewHolder holder, int position) {
        ConfirmAccountItem item = itemsList.get(position);

        holder.itemLabel.setText(item.getItemLabel());

        holder.itemValue.setText(item.getItemValue());
        holder.itemValue.setTextColor(globalTextColor);

       /* if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }*/

        //imageManager.displayImage(ActivityUtils.checkNull(globalImages.getChevronRight()), holder.billerRowIcon, imageOptions);
       /* Picasso.with(PayItHereApplication.getContext())
                .load(ActivityUtils.checkNull(globalImages.getChevronRight()))
                .placeholder(R.drawable.global_chevron_right)
                .into(holder.chevron);*/
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.acc_detail_label)
        TextView itemLabel;

        @BindView(R.id.acc_detail_value)
        TextView itemValue;

        public ViewHolder(View view){
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
