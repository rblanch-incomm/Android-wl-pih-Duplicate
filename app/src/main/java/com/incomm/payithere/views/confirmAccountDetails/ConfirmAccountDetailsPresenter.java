package com.incomm.payithere.views.confirmAccountDetails;

import android.app.Activity;
import android.widget.Toast;

import com.incomm.payithere.models.services.response.PaymentPost;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.template.TemplateMVP;

/**
 * Created by agodambe on 10/18/2017.
 */

public class ConfirmAccountDetailsPresenter implements ConfirmAccountDetailsMVP.Presenter{
    private ConfirmAccountDetailsMVP.View view;
    private ConfirmAccountDetailsMVP.Repository repository;
    private ConfirmAccountDetailsMVP.Service service;

    public ConfirmAccountDetailsPresenter(ConfirmAccountDetailsMVP.View view, ConfirmAccountDetailsMVP.Repository repository,
                                          ConfirmAccountDetailsMVP.Service service) {
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
    public void confirmAccount(String billerId, String paymentId) {
        service.confirmAccount(this,billerId,paymentId);
    }

    @Override
    public void confirmAccountSuccess(PaymentPost payment) {
        view.displayNextPage(payment);

    }

    @Override
    public void confirmAccountFailure(String s) {
        view.showError(s);

    }

    public void setQuestionLabel() {
        String question = ActivityUtils.checkNull(repository.getConfirmAccountQuestion());
        view.displayQuestion(question);
    }

    public void setConfirmButtonText() {
        String confirmText = ActivityUtils.checkNull(repository.getConfirmAccountButton());
        view.displayConfirmButton(confirmText);
    }

    public void setDeclineButtonText() {
        String decline = ActivityUtils.checkNull(repository.getDeclineButtonTitle());
        view.displayDeclineButton(decline);
    }

    @Override
    public String getPositiveButtonBackground() {
        return repository.getPositiveButtonBackground();
    }

    @Override
    public String getNegativeButtonBackground() {
        return repository.getNegativeButtonBackground();
    }

    @Override
    public String getPositiveBtnTextColor() {
        return repository.getPositiveBtnTextColor();
    }

    @Override
    public String getNegativeBtnTextColor() {
        return repository.getNegativeBtnTextColor();
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }

}
