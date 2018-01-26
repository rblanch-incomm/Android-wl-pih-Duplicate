package com.incomm.payithere.views.login;

import android.app.Activity;

import com.contentful.vault.Asset;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.models.cms.LoginView;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.util.List;

import static com.incomm.payithere.utils.Constants.IS_FROM_TAB;
import static com.incomm.payithere.utils.Constants.IS_REMEMBER_LOGIN;
import static com.incomm.payithere.utils.Constants.REMEBERED_USERNAME;

/**
 * Created by Jerome Davis on 8/30/17.
 */

public class LoginPresenter implements LoginMVP.Presenter {

    private LoginMVP.View view;
    private LoginView loginView = new LoginView();

    public LoginPresenter(LoginMVP.View view) {
        this.view = view;
    }

    @Override
    public void getViewElements() {
        loginView = (LoginView) DocumentsManager.getInstance().getCmsResource(LoginView.class);
    }

    public String getName() {
        return loginView.getName();
    }

    public String getTitle() {
        return loginView.getTitle();
    }

    public String getUsernamePlaceholderText() {
        return loginView.getUsernamePlaceholderText();
    }

    public String getPasswordPlaceholderText() {
        return loginView.getPasswordPlaceholderText();
    }

    public String getRememberMeLabelText() {
        return loginView.getRememberMeLabelText();
    }

    public String getLoginButtonTitle() {
        return loginView.getLoginButtonTitle();
    }

    public String getForgotCredentialsButtonTitle() {
        return loginView.getForgotCredentialsButtonTitle();
    }

    public String getRegisterButtonTitle() {
        return loginView.getRegisterButtonTitle();
    }

    public List<Feature> getShortcutButtons() {
        return loginView.getShortcutButtons();
    }

    public String getFooterText() {
        return loginView.getFooterText();
    }

    public Asset getFooterLogoImage() {
        return loginView.getFooterLogoImage();
    }

    public ColorSchemeManager getColorSchemeManager() {
        return ColorSchemeManager.getInstance();
    }

    @Override
    public boolean isRememberLogin() {

        return PreferencesManager.getInstance().isRememberLogin();
    }

    @Override
    public void setRememberMe(boolean isRememberClicked) {
        PreferencesManager.getInstance().setSharedPreferences(IS_REMEMBER_LOGIN, isRememberClicked);
    }

    public void setIsFromTab(boolean isFromTab) {
        PreferencesManager.getInstance().setSharedPreferences(IS_FROM_TAB, isFromTab);
    }

    @Override
    public String getRememberedUserName() {
        return PreferencesManager.getInstance().getRememberedUsername();
    }

    @Override
    public void setRememberUserName(String userName) {
        PreferencesManager.getInstance().setSharedPreferences(REMEBERED_USERNAME, userName);
    }

    public void signUpClicked() {
    }

    public String getAnalyticsId() {
        return loginView.getAnalyticsId();
    }
}
