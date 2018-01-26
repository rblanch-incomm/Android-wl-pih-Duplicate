package com.incomm.payithere.views.enterAmount;

import android.app.Activity;

import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.response.PaymentPost;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

/**
 * Created by agodambe on 10/18/2017.
 */

public class EnterAmountPresenter implements EnterAmountMVP.Presenter{
    private EnterAmountMVP.View view;
    private EnterAmountMVP.Repository repository;
    private EnterAmountMVP.Service service;

    public EnterAmountPresenter(EnterAmountMVP.View view, EnterAmountMVP.Repository repository,EnterAmountMVP.Service service) {
        this.view = view;
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    @Override
    public void setTitle() {
        String title = ActivityUtils.checkNull(repository.getTitle());
        view.displayTitle(title);
    }

    @Override
    public String getGeneralTextColor() {
        return repository.getGeneralTextColor();

    }

    @Override
    public void getAccountIdLabel() {
        String label = ActivityUtils.checkNull(repository.getAccountIdLabel());
        view.displayAccountIdLabel(label);
    }

    @Override
    public void getMaxPaymentLabelText() {
        String text = ActivityUtils.checkNull(repository.getMaxPaymentLabelText());
        view.displayMaxPaymentLabel(text);
    }

    @Override
    public void getPaymentAmountLabelText() {
        String text = ActivityUtils.checkNull(repository.getPaymentAmountLabelText());
        view.displayPaymentAmountLabel(text);
    }

    @Override
    public void getFeeLabelText() {
        String text = ActivityUtils.checkNull(repository.getFeeLabelText());
        view.displayFeeLabel(text);
    }

    @Override
    public void getTotalAmountLabelText() {
        String text = ActivityUtils.checkNull(repository.getTotalAmountLabelText());
        view.displayTotalAmountLabel(text);
    }

    @Override
    public void getDueDatePlaceholderText() {
        String text = ActivityUtils.checkNull(repository.getDueDatePlaceholderText());
        view.displayDueDatePlaceholder(text);
    }

    @Override
    public void getNotificationLabelText() {
        String text = ActivityUtils.checkNull(repository.getNotificationLabelText());
        view.displayNotificationText(text);
    }

    @Override
    public FeatureContextualHelp getContextHelpText() {
        return repository.getContextHelpText();
    }

    @Override
    public void getCreatePaymentButtonTitle() {
        String text = ActivityUtils.checkNull(repository.getCreatePaymentButtonTitle());
        view.displayCreatePaymentButton(text);
    }

    @Override
    public void getEnterAmountPlaceHolder() {
        String text = ActivityUtils.checkNull(repository.getEnterAmountPlaceHolder());
        view.displayEnterAmountPlaceHolder(text);
    }

    @Override
    public void enterAmountSuccess(PaymentPost payment) {
        view.displayPaymentSlip(payment);

    }

    @Override
    public void enterAmountFailure(String s) {
        view.showError(s);
    }

    @Override
    public void createPaymentSlip(String totalAmount,String billerId,String paymentId) {
        service.enterAmount(this,totalAmount,billerId,paymentId);
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }
}
