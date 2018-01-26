package com.incomm.payithere.views.educational;

import android.app.Activity;

import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.models.cms.EducationalSlide;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 10/23/17.
 */

public class EducationalPresenter implements EducationalMVP.Presenter {

    private EducationalMVP.View view;
    private EducationalMVP.Repository repository;

    private String name, title, skipButtonLabel, bottomButtonLabel;
    private List<EducationalSlide> educationalSlideList;

    private int numberOfSlides;

    public EducationalPresenter(EducationalMVP.View view, EducationalMVP.Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getViewElements() {
        this.name = repository.getName();
        this.title = repository.getTitle();
        this.educationalSlideList = repository.getEducationalSlides();
        this.skipButtonLabel = repository.getSkipButtonLabel();
        this.bottomButtonLabel = repository.getCreatePaymentButtonLabel();

        numberOfSlides = educationalSlideList.size();

        view.setupUi();
    }

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getSkipButtonLabel() {
        return ActivityUtils.checkNull(skipButtonLabel);
    }

    public String getBottomButtonLabel() {
        return ActivityUtils.checkNull(bottomButtonLabel);
    }

    public List<EducationalSlide> getEducationalSlideList() {
        return ActivityUtils.checkNull(educationalSlideList);
    }

    @Override
    public EducationalSlide getEducationalSlide(int index) {
        EducationalSlide educationalSlide = new EducationalSlide();
        if (educationalSlideList.get(index) != null) {
            educationalSlide = educationalSlideList.get(index);
        }

        return educationalSlide;
    }

    @Override
    public void setPageIndicator(int position) {
        view.setPageIndicator(position);
    }

    public void checkIfButtonShown(int position) {
        if (getFirstLaunch()) {
            if (position + 1 == numberOfSlides) {
                showBottomButton();
                hideSkipButton();
            } else {
                showSkipButton();
                hideButtomButton();
            }
        } else {
            hideButtomButton();
            hideSkipButton();
        }
    }

    public void displaySlides() {
        view.displayHelpMedia(getEducationalSlideList());
    }

    public void displayBottomButton() {
        view.displayBottomButton(getBottomButtonLabel());
    }

    public void showBottomButton() {
        view.showBottomButton();
    }

    public void hideButtomButton() {
        view.hideBottomButton();
    }

    public void displaySkipButton() {
        view.displaySkipButton(getSkipButtonLabel());
    }

    public void showSkipButton() {
        view.showSkipButton();
    }

    public void hideSkipButton() {
        view.hideSkipButton();
    }

    @Override
    public void onButtonClicked() {
        view.showLoginView();
    }

    @Override
    public void setFirstLaunch() {
        PreferencesManager.getInstance().setSharedPreferences(Constants.IS_FIRST_LAUNCH, true);
    }

    public boolean getFirstLaunch() {
        boolean isFirstLaunch = PreferencesManager.getInstance().getIsFirstLaunch();
        return isFirstLaunch;
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }

}
