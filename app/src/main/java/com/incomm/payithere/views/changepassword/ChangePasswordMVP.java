package com.incomm.payithere.views.changepassword;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;

/**
 * Created by Jerome Davis on 9/5/17.
 */

public interface ChangePasswordMVP {
    interface View extends BaseView<Presenter> {
        void displayTitle(String title);
        void displayCurrentPasswordHint(String currentHint);
        void displayNewPasswordHint(String newHint);
        void displayConfirmPasswordHint(String confirm);
        void displayBodyText(String bodyText);
        void displaySubmitButton(String submit);
        String getCurrentPassword();
        void showError(String error);
        String getNewPassword();
        String getConfirmPassword();
        void displaySuccessFragment();
        void setButtonTextColor(String buttonTextColor);
        void setButtonBackground(String buttonColor);
        void setGeneralTextColor(String generalTextColor);
    }

    interface Presenter extends BasePresenter {
        void OnChangePasswordComplete(boolean isSuccess,String code);
    }
    interface Repository{
        String getTitle();
        String getBodyText();
        String getCurrentPasswordTextFieldPlaceholder();
        String getNewPasswordTextFieldPlaceholder();
        String getConfirmPasswordTextFieldPlaceholder();
        String getSubmitButtonTitle();
        String getButtonColor();
        String getButtonTextColor();
        String getGeneralTextColor();
        String getAnalyticsID();
    }
}
