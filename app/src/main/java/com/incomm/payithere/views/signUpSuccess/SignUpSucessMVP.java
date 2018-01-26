package com.incomm.payithere.views.signUpSuccess;


import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;

/**
 * Created Aashish on 4/13/17.
 */

public interface SignUpSucessMVP {
    interface View extends BaseView<Presenter> {
        void displayBodyText(String bodyText);
        void setButtonColor(String color);
        void setButtonTextColor(String color);
        void setGeneralTextColor(String color);
        void displayTitle(String title);
        void displayContinueButton(String s);
    }

    interface Presenter extends BasePresenter {
    }
    interface Repository {
        String getTitle();
        String getContinueButton();
        String getBodyText();
        String getButtonTextColor();
        String getGeneralTextColor();
        String getSignUpButtonColor();
        String getAnalyticsId();
    }
}
