package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;

/**
 * Created by Jerome Davis on 9/5/17.
 */
@ContentType("changePasswordView")
public class ChangePasswordView extends View {
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    String currentPasswordTextFieldPlaceholder;
    @Field
    String newPasswordTextFieldPlaceholder;
    @Field
    String confirmPasswordTextFieldPlaceholder;
    @Field
    String bodyText;
    @Field
    String submitButtonTitle;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCurrentPasswordTextFieldPlaceholder() {
        return currentPasswordTextFieldPlaceholder;
    }

    public void setCurrentPasswordTextFieldPlaceholder(String currentPasswordTextFieldPlaceholder) {
        this.currentPasswordTextFieldPlaceholder = currentPasswordTextFieldPlaceholder;
    }

    public String getNewPasswordTextFieldPlaceholder() {
        return newPasswordTextFieldPlaceholder;
    }

    public void setNewPasswordTextFieldPlaceholder(String newPasswordTextFieldPlaceholder) {
        this.newPasswordTextFieldPlaceholder = newPasswordTextFieldPlaceholder;
    }

    public String getConfirmPasswordTextFieldPlaceholder() {
        return confirmPasswordTextFieldPlaceholder;
    }

    public void setConfirmPasswordTextFieldPlaceholder(String confirmPasswordTextFieldPlaceholder) {
        this.confirmPasswordTextFieldPlaceholder = confirmPasswordTextFieldPlaceholder;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getSubmitButtonTitle() {
        return submitButtonTitle;
    }

    public void setSubmitButtonTitle(String submitButtonTitle) {
        this.submitButtonTitle = submitButtonTitle;
    }
}
