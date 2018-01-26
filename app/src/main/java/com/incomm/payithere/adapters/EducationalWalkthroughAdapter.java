package com.incomm.payithere.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.models.cms.EducationalSlide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Jerome Davis on 10/25/17.
 */

public class EducationalWalkthroughAdapter extends PagerAdapter {

    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    private Context context;
    private LayoutInflater layoutInflater;
    private String[] mediaUrlArray;
    private String[] helpTextArray, skipButtonLabelArray, createPaymentLabelArray;

    public EducationalWalkthroughAdapter(Context context, List<EducationalSlide> educationalSlideList) {
        this.context = context;
        int educationalSlideListSize = educationalSlideList.size();
        mediaUrlArray = new String[educationalSlideListSize];
        skipButtonLabelArray = new String[educationalSlideListSize];
        helpTextArray = new String[educationalSlideListSize];
        createPaymentLabelArray = new String[educationalSlideListSize];

        for (int x = 0; x < educationalSlideListSize; x++) {
            mediaUrlArray[x] = educationalSlideList.get(x).getImage().url();
            helpTextArray[x] = educationalSlideList.get(x).getHelpText();
            //skipButtonLabelArray[x] = educationalSlideList.get(x).getSkipButtonLabel();
            //createPaymentLabelArray[x] = educationalSlideList.get(x).getCreatePaymentLabel();
        }
    }

    @Override
    public int getCount() {
        return mediaUrlArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.incl_educational_swipe_item, container,false);

        TextView helpText = (TextView) item_view.findViewById(R.id.help_tv);
        helpText.setText(helpTextArray[position]);

        ImageView imageView = (ImageView) item_view.findViewById(R.id.educational_swipe_imageview);
        imageManager.displayImage(mediaUrlArray[position], imageView, imageOptions);

        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}