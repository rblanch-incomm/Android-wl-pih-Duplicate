package com.incomm.payithere.views.barcodeSlip;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.request.EmailPaymentSlipRequest;
import com.incomm.payithere.models.services.response.PaymentWithEreceipt;

import java.util.List;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface BarcodeSlipMVP {
    interface View extends BaseView<Presenter> {
        void showError(String error);
        void displayTitle(String title, String subtitle);
        void displaySaveLaterBtn(String text);
        void displayPaymentAmountLabel(String text);
        void displayFeeLabel(String text);
        void displayTotalLabel(String text);
        void displayBillDuelLabel(String text);
        void displaySlipExipresLabel(String text);
        void displaySlipExpiresDate(String text);
        void displayScanBarCodeText(String text);
        void displayTermsAndConditions(String text);
        void displayInstructionFooterInformation(String text);
        void displayInstructions(List<String> texts);
        void displayContextualHelpIcon(String text);
        void displayContextualHelp(FeatureContextualHelp feature);
        void displayFeeDisclaimer(String text);
        void displayPaymentLocationsBtn(String text);
        void displayEmailPaymentSlipBtn(String text);
        void displayVanillaLogo(String text);
        void displayViewElements(PaymentWithEreceipt payment);
        void displayContextualHelpLabel();
        void displayEmailSuccessfullySentToast(String message, String buttonText);
        void displayPaymentSlip(String text);
    }

    interface Presenter extends BasePresenter {
        void setTitle();
        String getGeneralTextColor();
        void getSaveLaterBtn();
        void getAccountLabel();
        void getPaymentAmountLabel();
        void getFeeLabel();
        void getTotalLabel();
        void getBillDuelLabel();
        void getSlipExipresLabel();
        void getScanBarCodeText();
        void getTermsAndConditions();
        void getInstructions();
        void getInstructionFooter();
        FeatureContextualHelp getContextualHelp();
        void getContextualHelpIcon();
        void getPaymentLocationsBtn();
        void getEmailPaymentSlipBtn();
        void getVanillaLogo();
        void paymentDetailSuccess(PaymentWithEreceipt payment);
        void paymentDetailFailure(String s);
        void getPaymentDetails(String paymentId);
        void setIsFromTab(boolean fromTab);
        void emailPaymentSlip(PaymentWithEreceipt payment);
        void emailRequestSuccess();
        void emailRequestFailure(String error);
        void getPaymentSlipLabel();
    }
    interface Repository{
        String getTitle();
        String getGeneralTextColor();
        String getSaveLaterBtn();
        String getAccountLabel();
        String getPaymentAmountLabel();
        String getFeeLabel();
        String getTotalLabel();
        String getBillDuelLabel();
        String getSlipExipresLabel();
        String getScanBarCodeText();
        String getInstructionSteps();
        String getTermsAndConditions();
        String getInstructionFooterInformation();
        FeatureContextualHelp getContextualHelp();
        String getPaymentLocationsBtn();
        String getEmailPaymentSlipBtn();
        String getVanillaLogo();
        String getEmailSentAlertButtonText();
        String getEmailSentAlertMessage();
        String getAnalyticsID();
        String getFeeDisclaimer();
        List<String> getInstructionStepsSet();
    }

    public interface Service {
        void getPendingDetails(String paymentId, final BarcodeSlipMVP.Presenter presenter);
        void sendPaymentSlipToEmail(String billerId, String paymentId, EmailPaymentSlipRequest request, Presenter presenter);
    }
}
