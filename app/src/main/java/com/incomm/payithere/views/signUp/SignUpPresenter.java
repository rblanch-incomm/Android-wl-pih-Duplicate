package com.incomm.payithere.views.signUp;

import android.app.Activity;
import android.util.Log;

import com.incomm.payithere.services.SignUpService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

/**
 * Created by agodambe on 9/12/2017.
 */

public class SignUpPresenter implements SignUpMVP.Presenter {

    private SignUpMVP.View view;
    private SignUpMVP.SignUpRepository signUpRepository;
    private SignUpService signUpService;

    public SignUpPresenter(SignUpMVP.View view, SignUpMVP.SignUpRepository signUpRepository, SignUpService signUpService) {
        this.view = view;
        this.signUpRepository = signUpRepository;
        this.signUpService = signUpService;
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    public void getTitle() {
        String title = ActivityUtils.checkNull(signUpRepository.getTitle());
        view.displayTitle(title);
    }

    public void getFirstNameHint() {
        String firstName = ActivityUtils.checkNull(signUpRepository.getFirstNameHint());
        view.displayFirstNameHint(firstName);
    }

    public void getLastNameHint() {
        String lastName = ActivityUtils.checkNull(signUpRepository.getLastNameHint());
        view.displayLastNameHint(lastName);
    }

    public void getEmailHint() {
        String email = ActivityUtils.checkNull(signUpRepository.getEmailHint());
        view.displayEmailHint(email);
    }

    public void getConfirmEmailHint() {
        String confirmEmail = ActivityUtils.checkNull(signUpRepository.getConfirmEmailHint());
        view.displayConfirmEmail(confirmEmail);
    }

    public void getPhoneHint() {
        String phone = ActivityUtils.checkNull(signUpRepository.getPhoneHint());
        view.displayPhoneHint(phone);
    }

    public void getPasswordHint() {
        String password = ActivityUtils.checkNull(signUpRepository.getPasswordHint());
        view.displayPasswordHint(password);
    }

    public void getConfirmPasswordHint() {
        String confirmPass = ActivityUtils.checkNull(signUpRepository.getConfirmPasswordHint());
        view.displayConfirmPasswordHint(confirmPass);
    }

    public void getBodyText() {
        String bodytext = ActivityUtils.checkNull(signUpRepository.getBodyText());
        view.displayBodyText(bodytext);
    }

    public void getSignUpButton() {
        String signUpText = ActivityUtils.checkNull(signUpRepository.getSignUpButton());
        view.displaySignUpButton(signUpText);
    }

    public void getButtonColor() {
        String buttonColor = ActivityUtils.checkNull(signUpRepository.getSignUpButtonColor());
        view.setButtonColor(buttonColor);
    }

    public void getButtonTextColor() {
        String buttonTextColor = ActivityUtils.checkNull(signUpRepository.getButtonTextColor());
        view.setButtonTextColor(buttonTextColor);
    }

    public void getGeneralTextColor() {
        String textColor = ActivityUtils.checkNull(signUpRepository.getGeneralTextColor());
        view.setGeneralTextColor(textColor);
    }

    public void signUpClicked() {
        String firstName = view.getFirstName();
        if (firstName.isEmpty()) {
            view.showError("20308");
            return;
        }
        String lastName = view.getLastName();
        if (lastName.isEmpty()) {
            view.showError("20308");
            return;
        }
        String email = view.getEmail();
        if (email.isEmpty()) {
            view.showError("20308");
            return;
        }
        String confirmEmail = view.getConfirmEmail();
        if (confirmEmail.isEmpty()) {
            view.showError("20308");
            return;
        }
        if (!confirmEmail.equals(email)) {
            view.showError("20312");
            return;
        }
        if (!signUpRepository.isValidEmail(email)) {
            view.showError("20312");
            return;
        }
        String phone = view.getPhone();
        if (phone.isEmpty()) {
            view.showError("20308");
            return;
        }
        phone = phone.replaceAll("[()\\s-]+", "");
        if (phone.length() < 10) {
            view.showError("20312");
            return;
        }
        String password = view.getPassword();
        if (password.isEmpty()) {
            view.showError("20308");
            return;
        }
        String confirmPassword = view.getConfirmPassword();
        if (confirmPassword.isEmpty()) {
            view.showError("20308");
            return;
        }
        if (!password.equals(confirmPassword)) {
            view.showError("20306");
            return;
        }
        if (password.length() < 8 || password.length() > 50) {
            view.showError("20311");
            return;
        }
        signUpService.signUp(this, firstName, lastName, email, phone, password, confirmPassword);

    }

    @Override
    public void signUpCallback(boolean success, String code) {
        Log.i("SignUp Presenter", "Status = " + success + "  " + "Code = " + code);
        if (success) {
            view.displaySuccessFragment();
        } else {
            view.showError(code);
        }
    }

    public String getAnalyticsId() {
        return signUpRepository.getAnalyticsId();
    }

}
