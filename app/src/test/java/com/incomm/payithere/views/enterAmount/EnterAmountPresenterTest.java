package com.incomm.payithere.views.enterAmount;

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
public class EnterAmountPresenterTest {

    @Mock
    EnterAmountMVP.View view;
    @Mock
    EnterAmountMVP.Repository repository;
    @Mock
    EnterAmountMVP.Service service;
    EnterAmountPresenter presenter;
    @Before
    public void setUp() throws Exception {
        presenter = new EnterAmountPresenter(view,repository,service);
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
    public void passAccountIdToView() throws Exception {
        when(repository.getAccountIdLabel()).thenReturn("Account id");
        presenter.getAccountIdLabel();
        verify(view).displayAccountIdLabel("Account id");
    }

    @Test
    public void passEmptyStringIfAccountIdNullToView() throws Exception {
        when(repository.getAccountIdLabel()).thenReturn(null);
        presenter.getAccountIdLabel();
        verify(view).displayAccountIdLabel("");
    }

    @Test
    public void passMaxPayLabelToView() throws Exception {
        when(repository.getMaxPaymentLabelText()).thenReturn("Max Payment");
        presenter.getMaxPaymentLabelText();
        verify(view).displayMaxPaymentLabel("Max Payment");
    }

    @Test
    public void passEmptyStringIfMaxPayLabelNullToView() throws Exception {
        when(repository.getMaxPaymentLabelText()).thenReturn(null);
        presenter.getMaxPaymentLabelText();
        verify(view).displayMaxPaymentLabel("");
    }

    @Test
    public void passPayAmtLabelToView() throws Exception {
        when(repository.getPaymentAmountLabelText()).thenReturn("Payment");
        presenter.getPaymentAmountLabelText();
        verify(view).displayPaymentAmountLabel("Payment");
    }

    @Test
    public void passEmptyStringIfPayAmtLabelNullToView() throws Exception {
        when(repository.getPaymentAmountLabelText()).thenReturn(null);
        presenter.getPaymentAmountLabelText();
        verify(view).displayPaymentAmountLabel("");
    }

    @Test
    public void passPayFeeLabelToView() throws Exception {
        when(repository.getFeeLabelText()).thenReturn("Fee");
        presenter.getFeeLabelText();
        verify(view).displayFeeLabel("Fee");
    }

    @Test
    public void passEmptyStringIfPayFeeLabelNullToView() throws Exception {
        when(repository.getFeeLabelText()).thenReturn(null);
        presenter.getFeeLabelText();
        verify(view).displayFeeLabel("");
    }

    @Test
    public void passPayTotalLabelToView() throws Exception {
        when(repository.getTotalAmountLabelText()).thenReturn("Total");
        presenter.getTotalAmountLabelText();
        verify(view).displayTotalAmountLabel("Total");
    }

    @Test
    public void passEmptyStringIfPayTotalLabelNullToView() throws Exception {
        when(repository.getTotalAmountLabelText()).thenReturn(null);
        presenter.getTotalAmountLabelText();
        verify(view).displayTotalAmountLabel("");
    }

    @Test
    public void passDueDateTextToView() throws Exception {
        when(repository.getDueDatePlaceholderText()).thenReturn("Date");
        presenter.getDueDatePlaceholderText();
        verify(view).displayDueDatePlaceholder("Date");
    }

    @Test
    public void passEmptyStringIfDueDateTextNullToView() throws Exception {
        when(repository.getDueDatePlaceholderText()).thenReturn(null);
        presenter.getDueDatePlaceholderText();
        verify(view).displayDueDatePlaceholder("");
    }

    @Test
    public void passNotificationLabelToView() throws Exception {
        when(repository.getNotificationLabelText()).thenReturn("notify");
        presenter.getNotificationLabelText();
        verify(view).displayNotificationText("notify");
    }

    @Test
    public void passEmptyStringIfNotificationLabelNullToView() throws Exception {
        when(repository.getNotificationLabelText()).thenReturn(null);
        presenter.getNotificationLabelText();
        verify(view).displayNotificationText("");
    }

    @Test
    public void getContextHelpText() throws Exception {
    }

    @Test
    public void passPaymentButtonTextToView() throws Exception {
        when(repository.getCreatePaymentButtonTitle()).thenReturn("create");
        presenter.getCreatePaymentButtonTitle();
        verify(view).displayCreatePaymentButton("create");
    }

    @Test
    public void passEmptyStringIfPaymentButtonTextNullToView() throws Exception {
        when(repository.getCreatePaymentButtonTitle()).thenReturn(null);
        presenter.getCreatePaymentButtonTitle();
        verify(view).displayCreatePaymentButton("");
    }

    @Test
    public void passEnterAmtPlaceholderToView() throws Exception {
        when(repository.getEnterAmountPlaceHolder()).thenReturn("enter amount");
        presenter.getEnterAmountPlaceHolder();
        verify(view).displayEnterAmountPlaceHolder("enter amount");
    }

    @Test
    public void passEmptyStringIfEnterAmtPlaceholderNullToView() throws Exception {
        when(repository.getEnterAmountPlaceHolder()).thenReturn(null);
        presenter.getEnterAmountPlaceHolder();
        verify(view).displayEnterAmountPlaceHolder("");
    }

    @Test
    public void enterAmountSuccess() throws Exception {
        PaymentPost paymentPost = new PaymentPost();
        presenter.enterAmountSuccess(paymentPost);
        verify(view).displayPaymentSlip(paymentPost);
    }

    @Test
    public void enterAmountFailure() throws Exception {
        presenter.enterAmountFailure("30303");
        verify(view).showError("30303");

    }

}