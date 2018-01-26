package com.incomm.payithere.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.managers.ColorSchemeManager;

import java.util.List;

/**
 * Created by Jerome Davis on 10/9/17.
 */

public class NoBillsHistoryStepsAdapter extends RecyclerView.Adapter<NoBillsHistoryStepsAdapter.ViewHolder>{

    private List<String> listOfSteps;

    public NoBillsHistoryStepsAdapter(List<String> listOfSteps) {
        this.listOfSteps = listOfSteps;
    }

    @Override
    public NoBillsHistoryStepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_instructions_step_row ,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoBillsHistoryStepsAdapter.ViewHolder holder, int position) {
        holder.stepNumberTextView.setText(position+1+"");
        holder.stepNumberTextView.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getThemePrimary()));

        holder.stepInstructionTextView.setText(listOfSteps.get(position).toString());
        holder.stepInstructionTextView.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getGeneralText()));

//        holder.circleImageView.setColorFilter(Color.parseColor(ColorSchemeManager.getInstance().getThemePrimary()),
//                PorterDuff.Mode.ADD);
    }

    @Override
    public int getItemCount() {
        return listOfSteps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView stepNumberTextView;
        public TextView stepInstructionTextView;
        public ImageView circleImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            circleImageView = (ImageView) itemView.findViewById(R.id.circle_iv);
            stepInstructionTextView = (TextView) itemView.findViewById(R.id.step_text_tv);
            stepNumberTextView = (TextView) itemView.findViewById(R.id.step_number_tv);

        }
    }
}
