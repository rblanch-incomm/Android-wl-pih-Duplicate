package com.incomm.payithere.views.walkthrough;

import com.contentful.vault.Asset;
import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;

import java.util.List;

/**
 * Created by Jerome Davis on 9/25/17.
 */

public interface WalkThroughMVP {

    interface View extends BaseView<Presenter> {
        void setPageIndicator(int position);
        void displayTitle(String title);
        void displayWalkthrough(List<Asset> images);
        void showBottomButton();
        void hideBottomButton();
        void setupBottomButton(String buttonLabel);
        void showLoginView();
    }

    interface Presenter extends BasePresenter {
        String getName();
        String getTitle();
        String getChannel();
        List<Asset> getImages();
        List<Asset> getImagesAlternateResolution();
        String getContinueButtonTitle();
        void onButtonClicked();
        void setFirstLaunch();
        void displayWalkThrough();
    }

    interface WalkThroughRepository {
        String getName();
        String getTitle();
        String getChannel();
        List<Asset> getImages();
        List<Asset> getImagesAlternateResolution();
        String getContinueButtonTitle();
        String getAnalyticsId();
    }
}
