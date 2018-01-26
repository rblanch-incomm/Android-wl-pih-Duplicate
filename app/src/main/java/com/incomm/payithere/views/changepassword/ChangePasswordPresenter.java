package com.incomm.payithere.views.changepassword;

import android.app.Activity;

import com.incomm.payithere.services.ChangePasswordService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;

/**
 * Created by Jerome Davis on 9/5/17.
 */

public class ChangePasswordPresenter extends ViewPresenter implements ChangePasswordMVP.Presenter {

    private ChangePasswordMVP.View view;
    private ChangePasswordMVP.Repository changePasswordRepository;
    private ChangePasswordService changePasswordService;


    public ChangePasswordPresenter(ChangePasswordMVP.View view, ChangePasswordMVP.Repository changePasswordRepository,
                                   ChangePasswordService changePasswordService) {
        this.view = view;
        this.changePasswordRepository = changePasswordRepository;
        this.changePasswordService = changePasswordService;
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    public void submitClicked() {
        String current = view.getCurrentPassword();
        if(current.isEmpty()){
            view.showError("20310");
            return;
        }
        String newpassword = view.getNewPassword();
        if (newpassword.isEmpty()) {
            view.showError("20308");
            return;
        }
        String confirmPassword = view.getConfirmPassword();
        if (confirmPassword.isEmpty()) {
            view.showError("20308");
            return;
        }
        if (!newpassword.equals(confirmPassword)) {
            view.showError("20309");
            return;
        }
        if (newpassword.length() < 8 || newpassword.length() > 50) {
            view.showError("20311");
            return;
        }
        changePasswordService.changePassword(this,current,newpassword,confirmPassword);
    }

    public void getTitle() {
        String title = ActivityUtils.checkNull(changePasswordRepository.getTitle());
        view.displayTitle(title);
    }

    public void getCurrentPasswordTextFieldPlaceholder() {
        String currentHint = ActivityUtils.checkNull(changePasswordRepository.getCurrentPasswordTextFieldPlaceholder());
        view.displayCurrentPasswordHint(currentHint);
    }

    public void getNewPasswordTextFieldPlaceholder() {
        String newHint = ActivityUtils.checkNull(changePasswordRepository.getNewPasswordTextFieldPlaceholder());
        view.displayNewPasswordHint(newHint);
    }

    public void getConfirmPasswordTextFieldPlaceholder() {
        String confirmHint = ActivityUtils.checkNull(changePasswordRepository.getConfirmPasswordTextFieldPlaceholder());
        view.displayConfirmPasswordHint(confirmHint);
    }

    public void getBodyText() {
        String bodyText = ActivityUtils.checkNull(changePasswordRepository.getBodyText());
        view.displayBodyText(bodyText);
    }

    public void getSubmitButtonTitle() {
        String submit = ActivityUtils.checkNull(changePasswordRepository.getSubmitButtonTitle());
        view.displaySubmitButton(submit);
    }

    public void getLabelColor(){
        view.setGeneralTextColor(changePasswordRepository.getGeneralTextColor());
    }

    public void getButtonTitleColor(){
        view.setButtonTextColor(changePasswordRepository.getButtonTextColor());
    }

    public void getButtonColor(){
        view.setButtonBackground(changePasswordRepository.getButtonColor());
    }


    @Override
    public void OnChangePasswordComplete(boolean isSuccess, String code) {
        if (isSuccess) {
            view.displaySuccessFragment();
        } else {
            view.showError(code);
        }
    }

    public String getAnalyticsId() {
        return changePasswordRepository.getAnalyticsID();
    }

}
