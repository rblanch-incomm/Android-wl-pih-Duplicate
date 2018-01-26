package com.incomm.payithere.views.featuresection;

import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.models.cms.FeatureSection;

import java.util.List;

/**
 * Created by bjennings on 10/24/2017.
 */

public class FeatureSectionPresenter implements FeatureSectionMVP.Presenter {
    private FeatureSectionMVP.View view;
    private ColorSchemeManager colors;
    private GlobalImagesManager images;
    private FeatureSection featureSection;
    private List<Feature> featureList;

    public FeatureSectionPresenter(FeatureSectionMVP.View view,
                                   FeatureSection featureSection,
                                   ColorSchemeManager colors,
                                   GlobalImagesManager images) {
        this.view = view;
        this.featureSection = featureSection;
        this.colors = colors;
        this.images = images;
    }

    @Override
    public void getViewElements() {
        featureList = featureSection.getFeatures();
        view.createRecyclerView(featureList, colors.getGeneralText(), images.getChevronRight());
        view.setupUi();
    }

    @Override
    public void setTitle() {
        view.setTitle(featureSection.getTitle());
    }

    @Override
    public void onItemClicked(int position) {
        view.launchFragment(featureList.get(position).getKey());
    }
}