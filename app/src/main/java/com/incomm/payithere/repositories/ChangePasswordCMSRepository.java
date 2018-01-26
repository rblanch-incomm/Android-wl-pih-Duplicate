package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.ChangePasswordView;
import com.incomm.payithere.views.changepassword.ChangePasswordMVP;

/**
 * Created by agodambe on 9/12/2017.
 */

public class ChangePasswordCMSRepository implements ChangePasswordMVP.Repository{

    private ChangePasswordView cmsView;
    public ChangePasswordCMSRepository() {
        cmsView = CMSViewManager.getInstance().getChangePasswordView();
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
    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
    }

    @Override
    public String getCurrentPasswordTextFieldPlaceholder() {
        return cmsView.getCurrentPasswordTextFieldPlaceholder();
    }

    @Override
    public String getNewPasswordTextFieldPlaceholder() {
        return cmsView.getNewPasswordTextFieldPlaceholder();
    }

    @Override
    public String getConfirmPasswordTextFieldPlaceholder() {
        return cmsView.getConfirmPasswordTextFieldPlaceholder();
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


}
