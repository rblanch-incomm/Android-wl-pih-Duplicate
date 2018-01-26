package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;

import java.util.List;

/**
 * Created by Aashish Godambe on 10/24/17.
 */
@ContentType("billPendingDetailView")
public class BarcodeSlipView extends View {
    @Field
    String accountLabel;
    @Field
    String paymentAmountLabel;
    @Field
    String feeLabel;
    @Field
    String totalLabel;
    @Field
    String billDuelLabel;
    @Field
    String slipExipresLabel;
    @Field
    String scanBarCodeText;
    @Field
    String termsAndConditions;
    @Field
    FeatureContextualHelp contextualHelp;
    @Field
    String paymentLocationsBtn;
    @Field
    String emailPaymentSlipBtn;
    @Field
    String saveLaterBtn;
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    Asset vanillaLogo;
    @Field
    String emailSentAlertMessage;
    @Field
    String emailSentAlertButtonText;
    @Field
    String analyticsId;
    @Field
    String instructionSteps;
    @Field
    List<String> instructionStepsSet;
    @Field
    String feeDisclaimer;
    @Field
    String instructionFooterInformation;
    @Field
    String paymentAmountType;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getSaveLaterBtn() {
        return saveLaterBtn;
    }

    public String getAccountLabel() {
        return accountLabel;
    }

    public String getPaymentAmountLabel() {
        return paymentAmountLabel;
    }

    public String getFeeLabel() {
        return feeLabel;
    }

    public String getTotalLabel() {
        return totalLabel;
    }

    public String getBillDuelLabel() {
        return billDuelLabel;
    }

    public String getSlipExipresLabel() {
        return slipExipresLabel;
    }

    public String getScanBarCodeText() {
        return scanBarCodeText;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public FeatureContextualHelp getContextualHelp() {
        return contextualHelp;
    }

    public String getPaymentLocationsBtn() {
        return paymentLocationsBtn;
    }

    public String getEmailPaymentSlipBtn() {
        return emailPaymentSlipBtn;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getChannel() {
        return channel;
    }

    public String getVanillaLogo() {
        return vanillaLogo.url();
    }

    public String getEmailSentAlertMessage() {
        return emailSentAlertMessage;
    }

    public String getEmailSentAlertButtonText() {
        return emailSentAlertButtonText;
    }

    public String getInstructionSteps() {
        return instructionSteps;
    }

    public String getFeeDisclaimer() {
        return feeDisclaimer;
    }

    public List<String> getInstructionStepsSet() {
        return instructionStepsSet;
    }

    public String getInstructionFooterInformation() {
        return instructionFooterInformation;
    }

    public String getPaymentAmountType() {
        return paymentAmountType;
    }
}
