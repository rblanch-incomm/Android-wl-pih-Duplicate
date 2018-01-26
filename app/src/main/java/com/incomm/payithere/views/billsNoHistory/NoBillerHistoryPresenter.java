package com.incomm.payithere.views.billsNoHistory;

import android.app.Activity;

import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;

import java.util.List;

/**
 * Created by Jerome Davis on 10/4/17.
 */

public class NoBillerHistoryPresenter  extends ViewPresenter implements NoBillerHistoryMVP.Presenter {

    private NoBillerHistoryMVP.View view;
    private NoBillerHistoryMVP.NoBillerHistoryRepository repository;

    private String name, title, channel, bodyText, instructionsHeaderText, createSlipButtonTitle;
    private List<String> stepsText;

    public NoBillerHistoryPresenter(NoBillerHistoryMVP.View view, NoBillerHistoryMVP.NoBillerHistoryRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getViewElements() {
        this.name = repository.getName();
        this.title = repository.getTitle();
        this.channel = repository.getChannel();
        this.bodyText = repository.getBodyText();
        this.instructionsHeaderText = repository.getInstructionsHeaderText();
        this.createSlipButtonTitle = repository.getCreateSlipButtonTitle();
        this.stepsText = repository.getStepsText();

        view.setupUi();
    }

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public String getBodyText() {
        return ActivityUtils.checkNull(bodyText);
    }

    public String getInstructionsHeaderText() {
        return ActivityUtils.checkNull(instructionsHeaderText);
    }

    public String getCreateSlipButtonTitle() {
        return ActivityUtils.checkNull(createSlipButtonTitle);
    }

    public List<String> getStepsText() {
        return ActivityUtils.checkNull(stepsText);
    }

    public void displayBodyText() {
        view.displayBodyText(getBodyText());
    }

    public void displayInstructionHeader() {
        view.displayInstructionHeader(getInstructionsHeaderText());
    }

    public void displayCreateSlipButtonTitle() {
        view.displayCreateSlipButtonTitle(getCreateSlipButtonTitle());
    }

    public void displaySteps() {
        view.displaySteps(getStepsText());
    }

    public void displayTitle() {
        view.displayTitle(getTitle());
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsId();
    }

    public void createSlipButtonClicked() {
        view.showCreateNewPaymentSlip();
    }

}
