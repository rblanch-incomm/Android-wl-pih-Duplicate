package com.incomm.payithere.views.forgotpassword;

import android.app.Activity;

import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.models.cms.ForgotPasswordView;
import com.incomm.payithere.services.ForgotPasswordService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

/**
 * Created by Jerome Davis on 9/4/17.
 */

public class ForgotPasswordPresenter implements ForgotPasswordMVP.Presenter {

    private ForgotPasswordMVP.View view;
    private ForgotPasswordMVP.Repository repository;
    private ForgotPasswordService service;

    public ForgotPasswordPresenter(ForgotPasswordMVP.View view,ForgotPasswordMVP.Repository repository) {
        this.view = view;
        this.repository = repository;
        service = new ForgotPasswordService();
    }

    @Override
    public void getViewElements() {
    view.setupUi();
    }

    public void submitClicked() {
        String email = view.getEmail();
        if (email.isEmpty()) {
            view.showError("20308");
            return;
        }
        if (!repository.isValidEmail(email)) {
            view.showError("20304");
            return;
        }
        service.resetPassword(this,email);
    }

    public void getBodyText() {
        String bodyText = ActivityUtils.checkNull(repository.getBodyText());
        view.displayBodyText(bodyText);
    }

    public void getEmailPlaceholderText() {
        String email = ActivityUtils.checkNull(repository.getEmailPlaceholderText());
        view.displayEmailPlaceholder(email);
    }

    public void getSubmitButtonTitle() {
        String button = ActivityUtils.checkNull(repository.getSubmitButtonTitle());
        view.displaySubmitButton(button);
    }

    public void getTitle() {
        String title = ActivityUtils.checkNull(repository.getTitle());
        view.setTitle(title);
    }

    public void getLabelColor(){
        view.setGeneralTextColor(repository.getGeneralTextColor());
    }

    public void getButtonTitleColor(){
        view.setButtonTextColor(repository.getButtonTextColor());
    }

    public void getButtonColor(){
        view.setButtonBackground(repository.getButtonColor());
    }


    @Override
    public void OnResetPasswordComplete(boolean isSuccess, String code) {
        if (isSuccess) {
            view.displaySuccessFragment();
        } else {
            view.showError(code);
        }
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsId();
    }

}
