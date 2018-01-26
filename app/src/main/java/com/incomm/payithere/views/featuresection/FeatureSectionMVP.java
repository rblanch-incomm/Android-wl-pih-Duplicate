package com.incomm.payithere.views.featuresection;

import com.contentful.vault.Asset;
import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.Feature;

import java.util.List;

/**
 * Created by bjennings on 10/24/2017.
 */

public interface FeatureSectionMVP {
    interface Presenter extends BasePresenter {
        void setTitle();
        void onItemClicked(int position);
    }

    interface View extends BaseView<FeatureSectionMVP.Presenter> {
        void setTitle(String title);
        void createRecyclerView(List<Feature> features, String textColor, Asset chevron);
        void launchFragment(String key);
    }
}