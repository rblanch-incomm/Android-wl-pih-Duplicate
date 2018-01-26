package com.incomm.payithere.repositories;

import android.text.TextUtils;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.ColorScheme;
import com.incomm.payithere.models.cms.SignUpView;
import com.incomm.payithere.views.signUp.SignUpMVP;

/**
 * Created by agodambe on 9/12/2017.
 */

public class SignUpCMSRepository implements SignUpMVP.SignUpRepository {

    private SignUpView cmsView;
    public SignUpCMSRepository() {
        cmsView = CMSViewManager.getInstance().getSignUpView();
    }

    @Override
    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
    }
    @Override
    public String getFirstNameHint() {
        return cmsView.getFirstName();
    }

    @Override
    public String getLastNameHint() {
        return cmsView.getLastName();
    }

    @Override
    public String getEmailHint() {
        return cmsView.getEmail();
    }

    @Override
    public String getPhoneHint() {
        return cmsView.getPhone();
    }

    @Override
    public String getPasswordHint() {
        return cmsView.getPassword();
    }

    @Override
    public String getConfirmPasswordHint() {
        return cmsView.getConfirmPassword();
    }

    @Override
    public String getConfirmEmailHint() {
        return cmsView.getConfirmEmail();
    }

    @Override
    public String getZipcodeHint() {
        return null;
    }

    @Override
    public String getBodyText() {
        return cmsView.getBodyText();
    }

    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getSignUpButton() {
        return cmsView.getSignUpButton();
    }

    @Override
    public String getSignUpButtonColor() {
        return ColorSchemeManager.getInstance().getPositiveButton();
    }

    @Override
    public String getButtonTextColor() {
        return ColorSchemeManager.getInstance().getPositiveButtonTitle();
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    @Override
    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
