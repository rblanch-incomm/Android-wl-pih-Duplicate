package com.incomm.payithere.views.billsNoHistory;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;

import java.util.List;

/**
 * Created by Jerome Davis on 10/4/17.
 */

public interface NoBillerHistoryMVP {

    interface View extends BaseView<NoBillerHistoryMVP.Presenter> {
        void displayBodyText(String bodyText);
        void displayInstructionHeader(String headerText);
        void displayCreateSlipButtonTitle(String buttonTitle);
        void displayTitle(String title);
        void displaySteps(List<String> listOfSteps);
        void showCreateNewPaymentSlip();
    }

    interface Presenter extends BasePresenter {

    }

    interface NoBillerHistoryRepository {
        String getName();
        String getChannel();
        String getTitle();
        String getBodyText();
        String getInstructionsHeaderText();
        String getCreateSlipButtonTitle();
        List<String> getStepsText();
        String getAnalyticsId();
    }
}

