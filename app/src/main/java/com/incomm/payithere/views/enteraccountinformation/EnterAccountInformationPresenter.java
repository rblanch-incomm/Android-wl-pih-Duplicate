package com.incomm.payithere.views.enteraccountinformation;

import com.google.gson.JsonObject;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.request.ConfirmPaymentRequest;
import com.incomm.payithere.models.services.request.Payment;
import com.incomm.payithere.models.services.response.Form;
import com.incomm.payithere.models.services.response.PaymentPost;
import com.incomm.payithere.models.services.response.SpecificBillerInformation;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by rblanch on 11/6/17.
 */

public class EnterAccountInformationPresenter implements EnterAccountInformationMVP.Presenter {

    private EnterAccountInformationMVP.View view;
    private EnterAccountInformationMVP.Repository repository;
    private EnterAccountInformationMVP.Service service;
    private String billerName, billerId;
    private FeatureContextualHelp contextualHelp;
    private List<Form> formFields;
    private int anFormat;

    public EnterAccountInformationPresenter(EnterAccountInformationMVP.View view, EnterAccountInformationMVP.Repository repository, EnterAccountInformationMVP.Service service) {
        this.view = view;
        this.repository = repository;
        this.service = service;
    }

    public void getSpecificBiller(String id) {
        service.getAccountInformationFormFields(this, id);
    }

    public void getPaymentPost(String id, JsonObject userFields) {
        ConfirmPaymentRequest request = new ConfirmPaymentRequest();
        Payment payment = new Payment();
        payment.setCreated_in("mobile");
        payment.setUser_data(userFields);
        request.setPayment(payment);
        service.getPaymentPost(this, id, request);
    }

    public List<Form> getFormFields() {
        return formFields;
    }

    public String getBillerId() {
        return billerId;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getBillerName() {
        return billerName;
    }

    @Override
    public void getViewElements() {
        contextualHelp = repository.getContextualHelp();
        view.setupUi();
        view.setViewFeatures();
    }

    @Override
    public void OnEnterAccountInformationCallSuccess(SpecificBillerInformation billerInformation) {
        billerId = billerInformation.getId();
        billerName = billerInformation.getName();
        formFields = billerInformation.getForm();
        anFormat = billerInformation.getAnFormat();
        view.displayForm(formFields);
    }

    @Override
    public void OnEnterAccountInformationCallError(String error) {

    }

    public void OnPaymentPostCallSuccess(PaymentPost paymentPost) {
        view.displayConfirmAccountDetailsView(paymentPost);
    }

    public void displayAccountValidationError(String error) {
        view.displayAccountValidationError(error);
    }

    public void OnPaymentPostCallError(String error) {
        view.displayErrorDialog(error);
    }

    @Override
    public void showErrorMessage(String errorCode) {
        view.displayFormErrorDialog(errorCode);
    }

    @Override
    public String getGeneralTextColor() {
        return repository.getGeneralTextColor();
    }

    @Override
    public String getGlobalTextColor() {
        return null;
    }

    @Override
    public void displayTitle() {
        String title = ActivityUtils.checkNull(repository.getTitle());
        view.displayTitle(title);
    }

    public FeatureContextualHelp displayContextualHelp() {
        return contextualHelp;
    }

    public FeatureContextualHelp displayModifiedContextualHelp() {
        FeatureContextualHelp contextualHelpWithoutIcon = contextualHelp;
        contextualHelpWithoutIcon.getHelpViewAndroid().setHelpImage(null);
        return contextualHelpWithoutIcon;
    }

    @Override
    public void displayBiller() {
        view.displayBillerName(ActivityUtils.checkNull(getBillerName()));
    }

    @Override
    public void displayInstructionsText() {
        view.displayInstructions(ActivityUtils.checkNull(repository.getInstructionsText()));
    }

    @Override
    public void displaySubmitButton() {
        view.displaySubmitButton(ActivityUtils.checkNull(repository.getSubmitButtonTitle()));
    }

    public void displayConfirmAccountDetailsView() {
        view.generateConfirmAccountBundle();
    }

    public int getANFormat() {
        return anFormat;
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }
}
