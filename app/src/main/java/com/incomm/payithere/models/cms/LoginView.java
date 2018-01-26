package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("loginView")
public class LoginView extends View {
    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    String usernamePlaceholderText;
    @Field
    String passwordPlaceholderText;
    @Field
    String rememberMeLabelText;
    @Field
    String loginButtonTitle;
    @Field
    String forgotCredentialsButtonTitle;
    @Field
    String registerButtonTitle;
    @Field
    List<Feature> shortcutButtons;
    @Field
    String footerText;
    @Field
    Asset footerLogoImage;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public String getUsernamePlaceholderText() {
        return ActivityUtils.checkNull(usernamePlaceholderText);
    }

    public String getPasswordPlaceholderText() {
        return ActivityUtils.checkNull(passwordPlaceholderText);
    }

    public String getRememberMeLabelText() {
        return ActivityUtils.checkNull(rememberMeLabelText);
    }

    public String getLoginButtonTitle() {
        return ActivityUtils.checkNull(loginButtonTitle);
    }

    public String getForgotCredentialsButtonTitle() {
        return ActivityUtils.checkNull(forgotCredentialsButtonTitle);
    }

    public String getRegisterButtonTitle() {
        return registerButtonTitle;
    }

    public List<Feature> getShortcutButtons() {
        return shortcutButtons;
    }

    public String getFooterText() {
        return footerText;
    }

    public Asset getFooterLogoImage() {
        return footerLogoImage;
    }
}
