package com.incomm.payithere.views.login;

import com.contentful.vault.Asset;
import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.views.splash.SplashMVP;

import java.util.List;

/**
 * Created by Jerome Davis on 8/30/17.
 */

public interface LoginMVP  {

    interface View extends BaseView<SplashMVP.Presenter> {

    }

    interface Presenter extends BasePresenter {
        boolean isRememberLogin();
        void setRememberMe(boolean isRememberClicked);
        void setIsFromTab(boolean isFromLogin);
        String getRememberedUserName();
        void setRememberUserName(String userName);
    }

    interface LoginRepository {
        String getName();
        String getTitle();
        String getChannel();
        String getUsernamePlaceholderText();
        String getPasswordPlaceholderText();
        String getRememberMeLabelText();

        String getLoginButtonTitle();

        String getForgotCredentialsButtonTitle();

        String getRegisterButtonTitle();
        List<Feature> getShortcutButtons();
        String getFooterText();
        Asset getFooterLogoImage();
    }
}
