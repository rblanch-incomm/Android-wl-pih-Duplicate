package com.incomm.payithere.models.cms;


import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.DocumentsManager;

import java.util.List;

/**
 * Created by Jerome Davis on 4/28/17.
 */

public class TextMesaageAcceptanceView {
    //TODO: remover this is testing
    private ImportantInformationView importantInformationView;

    private String name;
    private String acceptanceText;
    List<FeatureLegalDocument> legalDocuments;

    private String declineButtonText;
    private String acceptButtonText;

    public String getName() {
        return "Text Message Agreement";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcceptanceText() {
        return "I agree to receive autodialed text messages about MyVanilla Card at the phone number provided when certain activity occurs on my account,  like my account balance falls below an amount I have identified. Standard text message and data rates apply. Not all carriers covered. You can always text STOP to 66521 to stop getting text messages (you will be sent a confirmation message), or call 1-855-686-9513. Text HELP to 66521 for help. I further understand that my information will be used as described here and in The Bancorp Bank\'s Privacy Policy and I agree to the Terms of Service.";
    }

    public void setAcceptanceText(String acceptanceText) {
        this.acceptanceText = acceptanceText;
    }

    public List<FeatureLegalDocument> getLegalDocuments() {
        DocumentsManager documentsManager = new DocumentsManager(PayItHereApplication.getContext());
        importantInformationView = (ImportantInformationView)
                documentsManager.getCmsResourceWhereChannel(ImportantInformationView.class, ImportantInformationView$Fields.CHANNEL);
        legalDocuments = importantInformationView.getLegalDocumentFeatures();

        return legalDocuments;
    }

    public void setLegalDocuments(List<FeatureLegalDocument> legalDocuments) {
        this.legalDocuments = legalDocuments;
    }

    public String getDeclineButtonText() {
        return "Decline";
    }

    public void setDeclineButtonText(String declineButtonText) {
        this.declineButtonText = declineButtonText;
    }

    public String getAcceptButtonText() {
        return "Accept";
    }

    public void setAcceptButtonText(String acceptButtonText) {
        this.acceptButtonText = acceptButtonText;
    }
}
