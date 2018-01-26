package com.incomm.payithere.views.enteraccountinformation;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.request.ConfirmPaymentRequest;
import com.incomm.payithere.models.services.response.Form;
import com.incomm.payithere.models.services.response.PaymentPost;
import com.incomm.payithere.models.services.response.SpecificBillerInformation;

import java.util.List;

/**
 * Created by rrblanch-incomm on 11/6/17.
 */

public interface EnterAccountInformationMVP {
    interface View extends BaseView<Presenter> {
        void displayTitle(String title);
        void displayForm(List<Form> formFields);
        void displayContextualHelp(String text);
        void displayInstructions(String instructions);
        void displayBillerName(String name);
        void displaySubmitButton(String buttonText);
        void displayErrorDialog(String errorCode);
        void displayAccountValidationError(String error);
        void displayFormErrorDialog(String errorCode);
        void displayConfirmAccountDetailsView(PaymentPost paymentPost);
        void generateConfirmAccountBundle();
    }

    interface Presenter extends BasePresenter {
        void displayTitle();
        void displayBiller();
        void displayInstructionsText();
        void displaySubmitButton();
        void OnEnterAccountInformationCallSuccess(SpecificBillerInformation billerInformation);
        void OnEnterAccountInformationCallError(String error);
        void OnPaymentPostCallSuccess(PaymentPost paymentPost);
        void OnPaymentPostCallError(String error);
        void showErrorMessage(String errorCode);
        String getGeneralTextColor();
        String getGlobalTextColor();
    }

    interface Repository {
        String getTitle();
        String getConfirmPrefix();
        FeatureContextualHelp getContextualHelp();
        String getInstructionsText();
        String getSubmitButtonTitle();
        String getGeneralTextColor();
        String getAnalyticsID();
    }

    interface Service {
        void getAccountInformationFormFields(EnterAccountInformationPresenter presenter, String billerId);
        void getPaymentPost(EnterAccountInformationPresenter presenter, String billerId, ConfirmPaymentRequest confirmPaymentRequest);
    }
}
