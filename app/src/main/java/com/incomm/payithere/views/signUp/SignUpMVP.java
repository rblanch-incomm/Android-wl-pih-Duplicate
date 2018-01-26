package com.incomm.payithere.views.signUp;


import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;

/**
 * Created by Jerome Davis on 4/13/17.
 */

public interface SignUpMVP {
    interface View extends BaseView<Presenter> {
        void displayTitle(String title);
        void displayFirstNameHint(String firstName);
        void displayLastNameHint(String lastName);
        void displayEmailHint(String email);
        void displayPhoneHint(String phone);
        void displayPasswordHint(String password);
        void displayConfirmPasswordHint(String confirmPass);
        void displaySignUpButton(String signUp);
        void setButtonColor(String color);
        void setButtonTextColor(String color);
        void setGeneralTextColor(String color);
        void showError(String error);
        String getFirstName();
        String getLastName();
        String getEmail();
        String getPhone();
        String getPassword();
        String getConfirmPassword();
        void displaySuccessFragment();
        void displayConfirmEmail(String email);
        void displayZipcode(String zipcode);
        void displayBodyText(String bodyText);
        String getConfirmEmail();
    }

    interface Presenter extends BasePresenter {
        void signUpCallback(boolean status,String code);
    }
    interface SignUpRepository{
         String getFirstNameHint();
        String getLastNameHint();
        String getEmailHint();
        String getPhoneHint();
        String getPasswordHint();
        String getConfirmPasswordHint();
        String getTitle();
        String getSignUpButton();
        String getSignUpButtonColor();
        String getButtonTextColor();
        String getGeneralTextColor();
        boolean isValidEmail(CharSequence target);
        String getConfirmEmailHint();
        String getZipcodeHint();
        String getBodyText();
        String getAnalyticsId();
    }
}
