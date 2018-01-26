package com.incomm.payithere.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.legaldocs.LegalDocument;
import com.incomm.payithere.utils.ActivityUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bjennings on 10/20/2017.
 */

public class ImportantInformationAdapter extends RecyclerView.Adapter<ImportantInformationAdapter.ViewHolder> {
    private LegalDocument[] documents;
    private String color;
    private ImageLoader imageManager;
    private DisplayImageOptions imageOptions;
    private GlobalImagesManager globalImages;

    public ImportantInformationAdapter(LegalDocument[] documents, String color, GlobalImagesManager globalImages) {
        this.documents = documents;
        this.color = color;
        this.globalImages = globalImages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.incl_legal_document_row, parent, false);

        imageManager = ImageLoader.getInstance();
        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .build();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LegalDocument legalDocument = documents[position];

        holder.documentRowTitle.setText(legalDocument.getTitle());
        holder.documentRowTitle.setTextColor(Color.parseColor(color));

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }

        imageManager.displayImage(ActivityUtils.checkNull(globalImages.getChevronRight()), holder.chevron, imageOptions);
    }

    @Override
    public int getItemCount() {
        return documents.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.document_row_title)
        TextView documentRowTitle;
        @BindView(R.id.document_row_glyph)
        ImageView chevron;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
