package com.incomm.payithere.views.confirmAccountDetails;

import com.incomm.payithere.models.services.response.PaymentPost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by agodambe on 11/6/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfirmAccountDetailsPresenterTest {

    @Mock
    ConfirmAccountDetailsMVP.View view;
    @Mock
    ConfirmAccountDetailsMVP.Repository repository;
    @Mock
    ConfirmAccountDetailsMVP.Service service;
    ConfirmAccountDetailsPresenter presenter;
    @Before
    public void setUp() throws Exception {
        presenter = new ConfirmAccountDetailsPresenter(view,repository,service);
    }

    @Test
    public void passTitleToView() throws Exception {
        when(repository.getTitle()).thenReturn("title");
        presenter.setTitle();
        verify(view).displayTitle("title");
    }

    @Test
    public void passEmptyStringIfTitleNullToView() throws Exception {
        when(repository.getTitle()).thenReturn(null);
        presenter.setTitle();
        verify(view).displayTitle("");
    }

    @Test
    public void confirmAccount() throws Exception {
        presenter.confirmAccount("123","456");
        service.confirmAccount(presenter,"123","456");
    }

    @Test
    public void confirmAccountSuccess() throws Exception {
        PaymentPost payment = new PaymentPost();
        presenter.confirmAccountSuccess(payment);
        verify(view).displayNextPage(payment);
    }

    @Test
    public void confirmAccountFailure() throws Exception {

        presenter.confirmAccountFailure("30303");
        verify(view).showError("30303");
    }

    @Test
    public void passQuestionLabelToView() throws Exception {
        when(repository.getConfirmAccountQuestion()).thenReturn("is this correct ?");
        presenter.setQuestionLabel();
        verify(view).displayQuestion("is this correct ?");
    }

    @Test
    public void passEmptyStringIfQuestionNullToView() throws Exception {
        when(repository.getConfirmAccountQuestion()).thenReturn(null);
        presenter.setQuestionLabel();
        verify(view).displayQuestion("");
    }

    @Test
    public void passConfirmButtonToView() throws Exception {
        when(repository.getConfirmAccountButton()).thenReturn("Submit");
        presenter.setConfirmButtonText();
        verify(view).displayConfirmButton("Submit");
    }

    @Test
    public void passEmptyStringIfConfirmButtonNullToView() throws Exception {
        when(repository.getConfirmAccountButton()).thenReturn(null);
        presenter.setConfirmButtonText();
        verify(view).displayConfirmButton("");
    }

    @Test
    public void passDeclineButtonToView() throws Exception {
        when(repository.getDeclineButtonTitle()).thenReturn("Decline");
        presenter.setDeclineButtonText();
        verify(view).displayDeclineButton("Decline");
    }

    @Test
    public void passEmptyStringIfDeclineButtonNullToView() throws Exception {
        when(repository.getDeclineButtonTitle()).thenReturn(null);
        presenter.setDeclineButtonText();
        verify(view).displayDeclineButton("");
    }

}