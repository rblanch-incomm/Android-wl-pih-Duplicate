package com.incomm.payithere.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.GlobalImages;
import com.incomm.payithere.models.services.response.Option;
import com.incomm.payithere.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rblanch on 11/1/2017.
 */

public class SelectPaymentTypeAdapter extends RecyclerView.Adapter<SelectPaymentTypeAdapter.ViewHolder> {

    private ArrayList<Option> paymentTypeOptions;
    private Context context;
    private String feeLabelText, postingTimeLabel;


    public SelectPaymentTypeAdapter(ArrayList<Option> paymentTypeOptions, Context context, String feeLabelText, String postingTimeLabel) {
        this.paymentTypeOptions = paymentTypeOptions;
        this.context = context;
        this.feeLabelText = feeLabelText;
        this.postingTimeLabel = postingTimeLabel;
    }

    @Override
    public SelectPaymentTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_select_payment ,parent, false);

        return new SelectPaymentTypeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Option option = paymentTypeOptions.get(position);

        if (position % 2 == 0) {
            holder.container.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.container.setBackgroundColor(Color.TRANSPARENT);
        }

        // TODO: add presenter to constructor
        holder.feeRowLabel.setText(feeLabelText + " ");
        holder.feeRow.setText(ActivityUtils.strCleanMoneyValueString(option.getFee()));

        holder.postingTimeLabel.setText(postingTimeLabel + " ");
        holder.postingTime.setText(option.getPostingTime());

        //imageManager.displayImage(ActivityUtils.checkNull(globalImages.getChevronRight()), holder.billerRowIcon, imageOptions);;

        Picasso.with(PayItHereApplication.getContext())
                .load(ActivityUtils.checkNull(GlobalImagesManager.getInstance().getChevronRight()))
                .placeholder(R.drawable.global_chevron_right)
                .into(holder.chevron);
    }

    @Override
    public int getItemCount() {
        return paymentTypeOptions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout container;
        public TextView feeRowLabel, feeRow, postingTimeLabel, postingTime;
        public ImageView chevron;

        public ViewHolder(View view){
            super(view);

            container = (RelativeLayout) view.findViewById(R.id.select_payment_fee_container);
            feeRowLabel = (TextView) view.findViewById(R.id.select_payment_fee_label_tv);
            feeRow = (TextView) view.findViewById(R.id.select_payment_fee_tv);
            postingTimeLabel = (TextView) view.findViewById(R.id.select_payment_posting_time_label_tv);
            postingTime = (TextView) view.findViewById(R.id.select_payment_posting_time_tv);
            chevron = (ImageView) view.findViewById(R.id.feature_row_glyph);
        }
    }
}