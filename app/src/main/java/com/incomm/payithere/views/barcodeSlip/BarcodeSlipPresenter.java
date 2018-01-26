package com.incomm.payithere.views.barcodeSlip;

import android.util.Log;

import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.request.EmailPaymentSlipRequest;
import com.incomm.payithere.models.services.request.PaymentSlipDelivery;
import com.incomm.payithere.models.services.response.PaymentWithEreceipt;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.barcodeSlip.BarcodeSlipMVP;

import java.util.List;

import static com.incomm.payithere.utils.Constants.IS_FROM_TAB;

/**
 * Created by agodambe on 10/18/2017.
 */

public class BarcodeSlipPresenter implements BarcodeSlipMVP.Presenter {
    private BarcodeSlipMVP.View view;
    private BarcodeSlipMVP.Repository repository;
    private BarcodeSlipMVP.Service service;

    private String accountNumber, accountName;

    public BarcodeSlipPresenter(BarcodeSlipMVP.View view, BarcodeSlipMVP.Repository repository,BarcodeSlipMVP.Service service) {
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
        String title = ActivityUtils.checkNull(accountName);
        String subtitleLabel = ActivityUtils.checkNull(repository.getAccountLabel());
        String subtitle = subtitleLabel + " " + accountNumber;
        if (!title.isEmpty()) {
            view.displayTitle(title, subtitle);
        }
    }

//    Hash all except last four of the account number
    public String hashAccountNumber(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < accountNumber.length() - 4; i++) {
            sb.append("*");
        }
        sb.append(accountNumber.substring(accountNumber.length() - 4));
        return sb.toString();
    }

    @Override
    public String getGeneralTextColor() {
        return repository.getGeneralTextColor();

    }

    @Override
    public void getSaveLaterBtn() {
        String text = ActivityUtils.checkNull(repository.getSaveLaterBtn());
        view.displaySaveLaterBtn(text);

    }

    @Override
    public void getAccountLabel() {
//        String text = ActivityUtils.checkNull(repository.getAccountLabel());
//        view.displayAccountLabel(text);
    }

    @Override
    public void getPaymentAmountLabel() {
        String text = ActivityUtils.checkNull(repository.getPaymentAmountLabel());
        view.displayPaymentAmountLabel(text);
    }

    @Override
    public void getPaymentSlipLabel() {
        String text = " " + ActivityUtils.checkNull(repository.getPaymentAmountLabel());
        view.displayPaymentSlip(text);
    }

    @Override
    public void getFeeLabel() {
        String text = ActivityUtils.checkNull(repository.getFeeLabel());
        view.displayFeeLabel(text);
    }

    @Override
    public void getTotalLabel() {
        String text = ActivityUtils.checkNull(repository.getTotalLabel());
        view.displayTotalLabel(text);
    }

    @Override
    public void getBillDuelLabel() {
        String text = ActivityUtils.checkNull(repository.getBillDuelLabel());
        view.displayBillDuelLabel(text);
    }

    @Override
    public void getSlipExipresLabel() {
        String text = ActivityUtils.checkNull(repository.getSlipExipresLabel()) + " ";
        view.displaySlipExipresLabel(text);
    }

    @Override
    public void getScanBarCodeText() {
        String text = ActivityUtils.checkNull(repository.getScanBarCodeText());
        view.displayScanBarCodeText(text);
    }

    @Override
    public void getTermsAndConditions() {
        String text = ActivityUtils.checkNull(repository.getTermsAndConditions());
        view.displayTermsAndConditions(text);
    }

    @Override
    public FeatureContextualHelp getContextualHelp() {
        return repository.getContextualHelp();
    }

    @Override
    public void getPaymentLocationsBtn() {
        String text = ActivityUtils.checkNull(repository.getPaymentLocationsBtn());
        view.displayPaymentLocationsBtn(text);
    }

    @Override
    public void getEmailPaymentSlipBtn() {
        String text = ActivityUtils.checkNull(repository.getEmailPaymentSlipBtn());
        view.displayEmailPaymentSlipBtn(text);
    }

    @Override
    public void getVanillaLogo() {
        String text = ActivityUtils.checkNull(repository.getVanillaLogo());
        view.displayVanillaLogo(text);
    }

    @Override
    public void getContextualHelpIcon() {
        String text = ActivityUtils.checkNull(ActivityUtils.checkNull(getContextualHelp().getIconPrimary()));
        view.displayContextualHelpIcon(text);
    }

    @Override
    public void paymentDetailSuccess(PaymentWithEreceipt payment) {
        if (payment.getUserData().get("account_number").length() > 4) {
            accountNumber = hashAccountNumber(payment.getUserData().get("account_number"));
        } else {
            accountNumber = payment.getUserData().get("account_number");
        }
        accountName = payment.getBiller().getName();
        view.displayViewElements(payment);
    }

    @Override
    public void paymentDetailFailure(String s) {
        view.showError(s);
    }

    @Override
    public void getPaymentDetails(String paymentId) {
        service.getPendingDetails(paymentId,this);
    }

    @Override
    public void getInstructions() {
        List<String> instructions = repository.getInstructionStepsSet();
        view.displayInstructions(instructions);
    }

    public void getFeeDisclaimer() {
        String disclaimer = ActivityUtils.checkNull(repository.getFeeDisclaimer());
        view.displayFeeDisclaimer(disclaimer);
    }

    @Override
    public void emailPaymentSlip(PaymentWithEreceipt payment) {
        String billerId = payment.getBiller().getId().toString();
        String paymentId = payment.getToken();
        EmailPaymentSlipRequest request = new EmailPaymentSlipRequest();
        PaymentSlipDelivery delivery = new PaymentSlipDelivery();
        delivery.setType("email");
        delivery.setBrandedFor("speedway");
        request.setPaymentSlipDelivery(delivery);
        service.sendPaymentSlipToEmail(billerId, paymentId, request, this);
    }

    @Override
    public void emailRequestSuccess() {
        String message = repository.getEmailSentAlertMessage();
        String buttonText = repository.getEmailSentAlertButtonText();
        view.displayEmailSuccessfullySentToast(message, buttonText);
    }

    @Override
    public void emailRequestFailure(String error) {
        view.showError(error);
    }

    @Override
    public void setIsFromTab(boolean fromTab) {
        PreferencesManager.getInstance().setSharedPreferences(IS_FROM_TAB, fromTab);
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }

    @Override
    public void getInstructionFooter() {
        String text = repository.getInstructionFooterInformation();
        view.displayInstructionFooterInformation(text);
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
