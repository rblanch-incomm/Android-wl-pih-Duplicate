package com.incomm.payithere.views.changepassword;

import com.incomm.payithere.repositories.ChangePasswordCMSRepository;
import com.incomm.payithere.services.ChangePasswordService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by agodambe on 9/15/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordPresenterTest {

    @Mock
    ChangePasswordMVP.View view;
    @Mock
    ChangePasswordCMSRepository repository;
    @Mock
    ChangePasswordService service;
    ChangePasswordPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ChangePasswordPresenter(view,repository,service);
    }

    @Test
    public void showErrorWhenCurrentPasswordEmpty() {
        when(view.getCurrentPassword()).thenReturn("");
        presenter.submitClicked();
        verify(view).showError("20310");
    }

    @Test
    public void showErrorWhenNewPasswordEmpty() {
        when(view.getCurrentPassword()).thenReturn("123456");
        when(view.getNewPassword()).thenReturn("");
        presenter.submitClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenConfirmPasswordEmpty() {
        when(view.getCurrentPassword()).thenReturn("123456");
        when(view.getNewPassword()).thenReturn("12345678");
        when(view.getConfirmPassword()).thenReturn("");
        presenter.submitClicked();
        verify(view).showError("20308");
    }

    @Test
    public void showErrorWhenPasswordMismatch() {
        when(view.getCurrentPassword()).thenReturn("123456");
        when(view.getNewPassword()).thenReturn("12345678");
        when(view.getConfirmPassword()).thenReturn("12345");
        presenter.submitClicked();
        verify(view).showError("20309");
    }

    @Test
    public void showErrorWhenPasswordFormatInvalid() {
        when(view.getCurrentPassword()).thenReturn("123456");
        when(view.getNewPassword()).thenReturn("123456");
        when(view.getConfirmPassword()).thenReturn("123456");
        presenter.submitClicked();
        verify(view).showError("20311");
    }

    @Test
    public void passTitleToView() throws Exception {
        when(repository.getTitle()).thenReturn("title");
        presenter.getTitle();
        verify(view).displayTitle("title");
    }

    @Test
    public void passEmptyStringIfTitleNullToView() throws Exception {
        when(repository.getTitle()).thenReturn(null);
        presenter.getTitle();
        verify(view).displayTitle("");
    }

    @Test
    public void passCurrentPasswordHintToView() throws Exception {
        when(repository.getCurrentPasswordTextFieldPlaceholder()).thenReturn("current");
        presenter.getCurrentPasswordTextFieldPlaceholder();
        verify(view).displayCurrentPasswordHint("current");
    }

    @Test
    public void passEmptyStringIfCurrentPasswordHintNullToView() throws Exception {
        when(repository.getCurrentPasswordTextFieldPlaceholder()).thenReturn(null);
        presenter.getCurrentPasswordTextFieldPlaceholder();
        verify(view).displayCurrentPasswordHint("");
    }

    @Test
    public void passNewPasswordHintToView() throws Exception {
        when(repository.getNewPasswordTextFieldPlaceholder()).thenReturn("new");
        presenter.getNewPasswordTextFieldPlaceholder();
        verify(view).displayNewPasswordHint("new");
    }

    @Test
    public void passEmptyStringIfNewPasswordHintNullToView() throws Exception {
        when(repository.getNewPasswordTextFieldPlaceholder()).thenReturn(null);
        presenter.getNewPasswordTextFieldPlaceholder();
        verify(view).displayNewPasswordHint("");
    }

    @Test
    public void passConfirmPasswordHintToView() throws Exception {
        when(repository.getConfirmPasswordTextFieldPlaceholder()).thenReturn("confirm");
        presenter.getConfirmPasswordTextFieldPlaceholder();
        verify(view).displayConfirmPasswordHint("confirm");
    }

    @Test
    public void passEmptyStringIfConfirmPasswordHintNullToView() throws Exception {
        when(repository.getConfirmPasswordTextFieldPlaceholder()).thenReturn(null);
        presenter.getConfirmPasswordTextFieldPlaceholder();
        verify(view).displayConfirmPasswordHint("");
    }

    @Test
    public void passBodyTextToView() throws Exception {
        when(repository.getBodyText()).thenReturn("body text");
        presenter.getBodyText();
        verify(view).displayBodyText("body text");
    }

    @Test
    public void passEmptyStringIfBodyTextNullToView() throws Exception {
        when(repository.getBodyText()).thenReturn(null);
        presenter.getBodyText();
        verify(view).displayBodyText("");
    }

    @Test
    public void passSubmitButtonTextToView() throws Exception {
        when(repository.getSubmitButtonTitle()).thenReturn("next");
        presenter.getSubmitButtonTitle();
        verify(view).displaySubmitButton("next");
    }

    @Test
    public void passEmptyStringIfSubmitButtonTextNullToView() throws Exception {
        when(repository.getSubmitButtonTitle()).thenReturn(null);
        presenter.getSubmitButtonTitle();
        verify(view).displaySubmitButton("");
    }

    @Test public void displaySuccessFragmentIfCallSuccessful() throws Exception{
        presenter.OnChangePasswordComplete(true,"");
        verify(view).displaySuccessFragment();
    }

    @Test public void displayErrorIfCallUnSuccessful() throws Exception{
        presenter.OnChangePasswordComplete(false,"10401");
        verify(view).showError("10401");
    }
}