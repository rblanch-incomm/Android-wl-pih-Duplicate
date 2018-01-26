package com.incomm.payithere.views.educational;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.EducationalSlide;

import java.util.List;

/**
 * Created by Jerome Davis on 10/23/17.
 */

public interface EducationalMVP  {
    interface View extends BaseView<Presenter> {
        void displaySkipButton(String buttonLabel);
        void displayHelpMedia(List<EducationalSlide> educationalSlideList);
        void displayBottomButton(String buttonLabel);
        void setPageIndicator(int position);
        void hideBottomButton();
        void showBottomButton();
        void hideSkipButton();
        void showSkipButton();
        void showLoginView();
    }

    interface Presenter extends BasePresenter {
        EducationalSlide getEducationalSlide(int index);
        void setPageIndicator(int position);
        void onButtonClicked();
        void setFirstLaunch();
        void checkIfButtonShown(int position);
    }
    interface Repository {
        String getName();
        String getTitle();
        String getSkipButtonLabel();
        String getCreatePaymentButtonLabel();
        List<EducationalSlide> getEducationalSlides();
        String getAnalyticsID();
    }
}
