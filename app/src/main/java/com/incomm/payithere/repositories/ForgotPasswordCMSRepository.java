package com.incomm.payithere.repositories;

import android.text.TextUtils;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.ChangePasswordView;
import com.incomm.payithere.models.cms.ForgotPasswordView;
import com.incomm.payithere.views.changepassword.ChangePasswordMVP;
import com.incomm.payithere.views.forgotpassword.ForgotPasswordMVP;

/**
 * Created by agodambe on 9/12/2017.
 */

public class ForgotPasswordCMSRepository implements ForgotPasswordMVP.Repository{

    private ForgotPasswordView cmsView;
    public ForgotPasswordCMSRepository() {
        cmsView = CMSViewManager.getInstance().getForgotPasswordView();
    }
    @Override
    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
    }
    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getBodyText() {
        return cmsView.getBodyText();
    }

    @Override
    public String getEmailPlaceholderText() {
        return cmsView.getEmailPlaceholderText();
    }

    @Override
    public String getSubmitButtonTitle() {
        return cmsView.getSubmitButtonTitle();
    }

    @Override
    public String getButtonColor() {
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
