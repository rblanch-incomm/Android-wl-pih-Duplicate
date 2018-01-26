package com.incomm.payithere.views.signUp;

import com.incomm.payithere.services.SignUpService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by agodambe on 9/12/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpPresenterTest {

    @Mock
    SignUpMVP.SignUpRepository signUpRepository;
    @Mock
    SignUpMVP.View view;
    @Mock
    SignUpService signUpService;

    SignUpPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SignUpPresenter(view, signUpRepository,signUpService);
    }

    @Test
    public void showErrorWhenFirstNameEmpty() {
        when(view.getFirstName()).thenReturn("");
        presenter.signUpClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenLastNameEmpty() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("");
        presenter.signUpClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenEmailEmpty() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("");
        presenter.signUpClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenConfirmEmailEmpty() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("ahid@ff.com");
        when(view.getConfirmEmail()).thenReturn("");
        presenter.signUpClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenEmailMismatch() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("ahid@ff.com");
        when(view.getConfirmEmail()).thenReturn("abcde@11.com");
        presenter.signUpClicked();
        verify(view).showError("20312");
    }

    @Test
    public void showErrorWhenEmailInvalid() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("abc@111");
        when(view.getConfirmEmail()).thenReturn("abc@111");
        when(signUpRepository.isValidEmail("abc@111")).thenReturn(false);
        presenter.signUpClicked();
        verify(view).showError("20312");
    }

    @Test
    public void showErrorWhenPhoneEmpty() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("abcde@11.com");
        when(view.getConfirmEmail()).thenReturn("abcde@11.com");
        when(signUpRepository.isValidEmail("abcde@11.com")).thenReturn(true);
        when(view.getPhone()).thenReturn("");
        presenter.signUpClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenPhoneInvalid() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("abcde@11.com");
        when(view.getConfirmEmail()).thenReturn("abcde@11.com");
        when(signUpRepository.isValidEmail("abcde@11.com")).thenReturn(true);
        when(view.getPhone()).thenReturn("965896");
        presenter.signUpClicked();
        verify(view).showError("20312");
    }

    @Test
    public void showErrorWhenPasswordEmpty() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("abcde@11.com");
        when(view.getConfirmEmail()).thenReturn("abcde@11.com");
        when(signUpRepository.isValidEmail("abcde@11.com")).thenReturn(true);
        when(view.getPhone()).thenReturn("1234567890");
        when(view.getPassword()).thenReturn("");
        presenter.signUpClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenConfirmPasswordEmpty() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("abcde@11.com");
        when(view.getConfirmEmail()).thenReturn("abcde@11.com");
        when(signUpRepository.isValidEmail("abcde@11.com")).thenReturn(true);
        when(view.getPhone()).thenReturn("1234567890");
        when(view.getPassword()).thenReturn("");
        when(view.getConfirmPassword()).thenReturn("");
        presenter.signUpClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenPasswordMismatch() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("abcde@11.com");
        when(view.getConfirmEmail()).thenReturn("abcde@11.com");
        when(signUpRepository.isValidEmail("abcde@11.com")).thenReturn(true);
        when(view.getPhone()).thenReturn("1234567890");
        when(view.getPassword()).thenReturn("abc");
        when(view.getConfirmPassword()).thenReturn("def");
        presenter.signUpClicked();
        verify(view).showError("20309");
    }

    @Test
    public void showErrorWhenPasswordFormatInvalid() {
        when(view.getFirstName()).thenReturn("Jane");
        when(view.getLastName()).thenReturn("Doe");
        when(view.getEmail()).thenReturn("abcde@11.com");
        when(view.getConfirmEmail()).thenReturn("abcde@11.com");
        when(signUpRepository.isValidEmail("abcde@11.com")).thenReturn(true);
        when(view.getPhone()).thenReturn("1234567890");
        when(view.getPassword()).thenReturn("12345");
        when(view.getConfirmPassword()).thenReturn("12345");
        presenter.signUpClicked();
        verify(view).showError("20311");
    }


    @Test
    public void shouldPassFirstNameToView() {
        String name = "jane";
        when(signUpRepository.getFirstNameHint()).thenReturn(name);
        presenter.getFirstNameHint();
        verify(view).displayFirstNameHint(name);
    }

    @Test
    public void shouldPassEmptyStringIfNullFirstNameToView() {
        String name = null;
        when(signUpRepository.getFirstNameHint()).thenReturn(name);
        presenter.getFirstNameHint();
        verify(view).displayFirstNameHint("");
    }

    @Test
    public void shouldPassLastNameToView() {
        String name = "doe";
        when(signUpRepository.getLastNameHint()).thenReturn(name);
        presenter.getLastNameHint();
        verify(view).displayLastNameHint(name);
    }

    @Test
    public void shouldPassEmptyStringIfNullLastNameToView() {
        String name = null;
        when(signUpRepository.getLastNameHint()).thenReturn(name);
        presenter.getLastNameHint();
        verify(view).displayLastNameHint("");
    }

    @Test
    public void shouldPassEmailToView() {
        String email = "Email";
        when(signUpRepository.getEmailHint()).thenReturn(email);
        presenter.getEmailHint();
        verify(view).displayEmailHint(email);
    }

    @Test
    public void shouldPassEmptyStringIfNullEmailToView() {
        String email = null;
        when(signUpRepository.getEmailHint()).thenReturn(email);
        presenter.getEmailHint();
        verify(view).displayEmailHint("");
    }

    @Test
    public void shouldPassPhoneToView() {
        String phone = "Phone Num";
        when(signUpRepository.getPhoneHint()).thenReturn(phone);
        presenter.getPhoneHint();
        verify(view).displayPhoneHint(phone);
    }

    @Test
    public void shouldPassEmptyStringIfNullPhoneToView() {
        String phone = null;
        when(signUpRepository.getPhoneHint()).thenReturn(phone);
        presenter.getPhoneHint();
        verify(view).displayPhoneHint("");
    }

    @Test
    public void shouldPassPasswordToView() {
        String pass = "password";
        when(signUpRepository.getPasswordHint()).thenReturn(pass);
        presenter.getPasswordHint();
        verify(view).displayPasswordHint(pass);
    }

    @Test
    public void shouldPassEmptyStringIfNullPasswordToView() {
        String pass = null;
        when(signUpRepository.getPasswordHint()).thenReturn(pass);
        presenter.getPasswordHint();
        verify(view).displayPasswordHint("");
    }

    @Test
    public void shouldPassConfirmPassToView() {
        String confirmPass = "Con Pass";
        when(signUpRepository.getConfirmPasswordHint()).thenReturn(confirmPass);
        presenter.getConfirmPasswordHint();
        verify(view).displayConfirmPasswordHint(confirmPass);
    }

    @Test
    public void shouldPassEmptyStringIfNullConfirmPassToView() {
        String confirmPass = null;
        when(signUpRepository.getConfirmPasswordHint()).thenReturn(confirmPass);
        presenter.getConfirmPasswordHint();
        verify(view).displayConfirmPasswordHint("");
    }

    @Test
    public void shouldPassSignUpButtonTitleToView() {
        String signUpButton = "Click me";
        when(signUpRepository.getSignUpButton()).thenReturn(signUpButton);
        presenter.getSignUpButton();
        verify(view).displaySignUpButton(signUpButton);
    }

    @Test
    public void shouldPassEmptyStringIfNullSignUpButtonTitleToView() {
        String signUpButton = null;
        when(signUpRepository.getSignUpButton()).thenReturn(signUpButton);
        presenter.getSignUpButton();
        verify(view).displaySignUpButton("");
    }

    @Test
    public void shouldPassConfirmEmailToView() {
        String text = "con email";
        when(signUpRepository.getConfirmEmailHint()).thenReturn(text);
        presenter.getConfirmEmailHint();
        verify(view).displayConfirmEmail(text);
    }

    @Test
    public void shouldPassEmptyStringifNullConfirmEmailToView() {
        String text = null;
        when(signUpRepository.getConfirmEmailHint()).thenReturn(text);
        presenter.getConfirmEmailHint();
        verify(view).displayConfirmEmail("");
    }

    @Test
    public void shouldPassBodyTextToView() {
        String text = "pass requirements";
        when(signUpRepository.getBodyText()).thenReturn(text);
        presenter.getBodyText();
        verify(view).displayBodyText(text);
    }

    @Test
    public void shouldPassEmptyStringIfNullBodyTextToView() {
        String text = null;
        when(signUpRepository.getBodyText()).thenReturn(text);
        presenter.getBodyText();
        verify(view).displayBodyText("");
    }




}