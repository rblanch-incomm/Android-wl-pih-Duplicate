package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Aashish Godambe on 9/12/17.
 */
@ContentType("signUpView")
public class SignUpView extends View {
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    String firstName;
    @Field
    String lastName;
    @Field
    String phone;
    @Field
    String email;
    @Field
    String confirmEmail;
    @Field
    String password;
    @Field
    String confirmPassword;
    @Field
    String signupButton;
    @Field
    String informationalText;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getFirstName() {
        return ActivityUtils.checkNull(firstName);
    }

    public String getLastName() {
        return ActivityUtils.checkNull(lastName);
    }

    public String getPhone() {
        return ActivityUtils.checkNull(phone);
    }

    public String getEmail() {
        return ActivityUtils.checkNull(email);
    }

    @Override
    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    @Override
    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getPassword() {
        return ActivityUtils.checkNull(password);
    }

    public String getConfirmPassword() {
        return ActivityUtils.checkNull(confirmPassword);
    }

    public String getSignUpButton() {
        return ActivityUtils.checkNull(signupButton);
    }

    public String getConfirmEmail() {
        return ActivityUtils.checkNull(confirmEmail);
    }

    public String getBodyText() {
        return ActivityUtils.checkNull(informationalText);
    }

}
