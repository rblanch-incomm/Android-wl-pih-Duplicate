package com.incomm.payithere.views.walkthrough;

import android.app.Activity;

import com.contentful.vault.Asset;
import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;

import java.util.List;

/**
 * Created by Jerome Davis on 9/25/17.
 */

public class WalkThroughPresenter extends ViewPresenter implements WalkThroughMVP.Presenter {

    private WalkThroughMVP.View view;
    private WalkThroughMVP.WalkThroughRepository repository;

    private String name, title, channel, continueButtonTitle, analyticId;
    private List<Asset> images, imagesAlternateResolution;

    public WalkThroughPresenter(WalkThroughMVP.View view, WalkThroughMVP.WalkThroughRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getViewElements() {
        this.name = repository.getName();
        this.title = repository.getTitle();
        this.channel = repository.getChannel();
        this.continueButtonTitle = repository.getContinueButtonTitle();
        this.images = repository.getImages();
        this.imagesAlternateResolution = repository.getImagesAlternateResolution();
        this.analyticId = repository.getAnalyticsId();
    }
    public String getAnalyticId() {
        return analyticId;
    }

    @Override
    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    @Override
    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    @Override
    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    @Override
    public String getContinueButtonTitle() {
        return ActivityUtils.checkNull(continueButtonTitle);
    }

    @Override
    public List<Asset> getImages() {
        return ActivityUtils.checkNull(images);
    }

    @Override
    public List<Asset> getImagesAlternateResolution() {

        return ActivityUtils.checkNull(imagesAlternateResolution);
    }

    @Override
    public void onButtonClicked() {
        view.showLoginView();
    }
    @Override
    public void displayWalkThrough() {
        view.displayWalkthrough(getImages());
    }

    public void setupBottomButton() {
        view.setupBottomButton(getContinueButtonTitle());
    }

    public void displayBottomButton(boolean showButton) {
        if (showButton) {
            view.showBottomButton();
        } else {
            view.hideBottomButton();
        }
    }

    public void displayTitle() {
        if (getTitle() != null) {
            view.displayTitle(getTitle());
        } else {
            view.displayTitle("");
        }
    }
    public void setPageIndicator(int position) {
        view.setPageIndicator(position);
    }

    @Override
    public void setFirstLaunch() {
        PreferencesManager.getInstance().setSharedPreferences(Constants.IS_FIRST_LAUNCH, true);
    }

}
