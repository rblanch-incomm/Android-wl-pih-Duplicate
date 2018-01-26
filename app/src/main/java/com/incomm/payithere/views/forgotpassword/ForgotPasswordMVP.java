package com.incomm.payithere.views.forgotpassword;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;

/**
 * Created by Jerome Davis on 9/4/17.
 */

public interface ForgotPasswordMVP {
    interface View extends BaseView<Presenter> {
        void displayBodyText(String bodyText);
        void displayEmailPlaceholder(String email);
        void displaySubmitButton(String button);
        void setTitle(String title);
        void setGeneralTextColor(String generalTextColor);
        void setButtonTextColor(String buttonTextColor);
        void setButtonBackground(String buttonColor);
        String getEmail();
        void showError(String error);
        void displaySuccessFragment();
    }

    interface Presenter extends BasePresenter {
        void OnResetPasswordComplete(boolean status, String code);
    }

    interface Repository {
        String getEmailPlaceholderText();
        String getTitle();
        String getBodyText();
        String getButtonColor();
        String getButtonTextColor();
        String getGeneralTextColor();
        String getSubmitButtonTitle();
        boolean isValidEmail(CharSequence target);
        String getAnalyticsId();
    }
}
