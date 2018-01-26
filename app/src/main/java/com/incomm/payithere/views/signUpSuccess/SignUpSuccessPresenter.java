package com.incomm.payithere.views.signUpSuccess;

import android.app.Activity;

import com.incomm.payithere.repositories.SignUpSuccessCMSRepository;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

/**
 * Created by agodambe on 9/14/2017.
 */

public class SignUpSuccessPresenter implements SignUpSucessMVP.Presenter {
    private SignUpSucessMVP.View view;
    private SignUpSucessMVP.Repository signUpSuccessCMSRepository;

    public SignUpSuccessPresenter(SignUpSucessMVP.View view, SignUpSucessMVP.Repository signUpSuccessCMSRepository) {
        this.view = view;
        this.signUpSuccessCMSRepository = signUpSuccessCMSRepository;
    }

    @Override
    public void getViewElements() {

    }

    public void getTitle() {
        String title = signUpSuccessCMSRepository.getTitle();
        if (null == title){
            view.displayTitle("");
        }else {
            view.displayTitle(title);
        }
    }

    public void getBodyText() {
        String bodyText = signUpSuccessCMSRepository.getBodyText();
        if (null == bodyText){
            view.displayBodyText("");
        }else {
            view.displayBodyText(bodyText);
        }
    }

    public void getButtonColor() {
        String buttonColor = signUpSuccessCMSRepository.getSignUpButtonColor();
        if(null == buttonColor || buttonColor.equals("")){
            view.setButtonColor("#000000");
        }else {
            view.setButtonColor(buttonColor);
        }
    }

    public void getButtonTextColor() {
        String buttonTextColor = signUpSuccessCMSRepository.getButtonTextColor();
        if(null == buttonTextColor || buttonTextColor.equals("")){
            view.setButtonTextColor("#000000");
        }else {
            view.setButtonTextColor(buttonTextColor);
        }
    }

    public void getGeneralTextColor() {
        String textColor = signUpSuccessCMSRepository.getGeneralTextColor();
        if(null == textColor || textColor.equals("")){
            view.setGeneralTextColor("#000000");
        }else {
            view.setGeneralTextColor(textColor);
        }
    }

    public void getContinueButton() {
        String buttonTitle = signUpSuccessCMSRepository.getContinueButton();
        if(null == buttonTitle || buttonTitle.equals("")){
            view.displayContinueButton("");
        }else {
            view.displayContinueButton(buttonTitle);
        }
    }

    public String getAnalyticsId() {
        return signUpSuccessCMSRepository.getAnalyticsId();
    }

}
