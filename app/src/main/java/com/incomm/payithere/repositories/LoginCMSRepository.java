package com.incomm.payithere.repositories;

import com.contentful.vault.Asset;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.models.cms.LoginView;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 9/18/17.
 */

public class LoginCMSRepository {

    private LoginView cmsView;

    public LoginCMSRepository() {
        cmsView = (LoginView) DocumentsManager.getInstance().getCmsResource(LoginView.class);
    }

    public String getName() {
        return ActivityUtils.checkNull(cmsView.getName());
    }

    public String getTitle() {
        return ActivityUtils.checkNull(cmsView.getTitle());
    }

    public String getChannel() {
        return ActivityUtils.checkNull(cmsView.getChannel());
    }

    public String getUsernamePlaceholderText() {
        return ActivityUtils.checkNull(cmsView.getUsernamePlaceholderText());
    }

    public String getPasswordPlaceholderText() {
        return ActivityUtils.checkNull(cmsView.getPasswordPlaceholderText());
    }

    public String getRememberMeLabelText() {
        return ActivityUtils.checkNull(cmsView.getRememberMeLabelText());
    }

    public String getLoginButtonTitle() {
        return ActivityUtils.checkNull(cmsView.getLoginButtonTitle());
    }

    public String getForgotCredentialsButtonTitle() {
        return ActivityUtils.checkNull(cmsView.getForgotCredentialsButtonTitle());
    }

    public String getRegisterButtonTitle() {
        return ActivityUtils.checkNull(cmsView.getRegisterButtonTitle());
    }

    public List<Feature> getShortcutButtons() {
        return cmsView.getShortcutButtons();
    }

    public String getFooterText() {
        return ActivityUtils.checkNull(cmsView.getFooterText());
    }

    public Asset getFooterLogoImage() {
        return cmsView.getFooterLogoImage();
    }

}
