package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.BarcodeSlipView;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.views.barcodeSlip.BarcodeSlipMVP;

import java.util.List;

/**
 * Created by agodambe on 9/28/2017.
 */

public class BarcodeSlipCMSRepository implements BarcodeSlipMVP.Repository {

    private BarcodeSlipView cmsView;
    public BarcodeSlipCMSRepository() {
        cmsView = CMSViewManager.getInstance().getBarcodeSlipView();
    }

    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
    }

    @Override
    public String getSaveLaterBtn() {
        return cmsView.getSaveLaterBtn();
    }

    @Override
    public String getAccountLabel() {
        return cmsView.getAccountLabel();
    }

    @Override
    public String getPaymentAmountLabel() {
        return cmsView.getPaymentAmountLabel();
    }

    @Override
    public String getFeeLabel() {
        return cmsView.getFeeLabel();
    }

    @Override
    public String getTotalLabel() {
        return cmsView.getTotalLabel();
    }

    @Override
    public String getBillDuelLabel() {
        return cmsView.getBillDuelLabel();
    }

    @Override
    public String getSlipExipresLabel() {
        return cmsView.getSlipExipresLabel();
    }

    @Override
    public String getScanBarCodeText() {
        return cmsView.getScanBarCodeText();
    }

    @Override
    public String getTermsAndConditions() {
        return cmsView.getTermsAndConditions();
    }

    @Override
    public FeatureContextualHelp getContextualHelp() {
        return cmsView.getContextualHelp();
    }

    @Override
    public String getPaymentLocationsBtn() {
        return cmsView.getPaymentLocationsBtn();
    }

    @Override
    public String getEmailPaymentSlipBtn() {
        return cmsView.getEmailPaymentSlipBtn();
    }

    @Override
    public String getVanillaLogo() {
        return cmsView.getVanillaLogo();
    }

    @Override
    public String getEmailSentAlertMessage() {return cmsView.getEmailSentAlertMessage();}

    @Override
    public String getEmailSentAlertButtonText() {return cmsView.getEmailSentAlertButtonText();}

    @Override
    public String getInstructionSteps() {
        return cmsView.getInstructionSteps();
    }

    @Override
    public List<String> getInstructionStepsSet() {
        return cmsView.getInstructionStepsSet();
    }

    @Override
    public String getFeeDisclaimer() {
        return cmsView.getFeeDisclaimer();
    }

    @Override
    public String getInstructionFooterInformation() {
        return cmsView.getInstructionFooterInformation();
    }
}
