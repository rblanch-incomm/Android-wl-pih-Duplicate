package com.incomm.payithere.views.selectPayment;

import android.app.Activity;

import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.Option;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.util.ArrayList;

/**
 * Created by agodambe on 10/18/2017.
 */

public class SelectPaymentPresenter implements SelectPaymentMVP.Presenter{
    private SelectPaymentMVP.View view;
    private SelectPaymentMVP.Repository repository;
    private String feeLabel, postingTimeLabel;
    private BillerGroup biller;
    private FeatureContextualHelp contextualHelp;

    public SelectPaymentPresenter(SelectPaymentMVP.View view, SelectPaymentMVP.Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    public FeatureContextualHelp getContextualHelp() {
        return contextualHelp;
    }

    public String getFeeLabel() {
        return ActivityUtils.checkNull(feeLabel);
    }

    public String getPostingTimeLabel() {
        return ActivityUtils.checkNull(postingTimeLabel);
    }

    public BillerGroup getBiller() {
        return biller;
    }

    public void setBiller(BillerGroup biller) {
        this.biller = biller;
    }

    public void displayTitle() {
        view.displayTitle(getBillerNameForTitle());
    }

    public String getBillerNameForTitle() {
        return ActivityUtils.checkNull(biller.getName());
    }

    public String getContextualHelpLabelColor() {
        return ColorSchemeManager.getInstance().getThemePrimary();
    }

    @Override
    public void retrieveFeeLabel() {
        feeLabel = repository.getFeeLabel();
    }

    @Override
    public void retrievePostingTimeLabel() {
        postingTimeLabel = repository.getPostingTimeLabel();
    }

    @Override
    public void retrieveContextualHelpLabel() {
        contextualHelp = repository.getContextualHelpLabel();
        view.setViewFeatures();
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    @Override
    public void displayPaymentType() {
        ArrayList<Option> paymentTypes;
        if (getBiller() != null && getBiller().getOptions() != null) {
            paymentTypes = (ArrayList) getBiller().getOptions();
        } else {
            paymentTypes = new ArrayList<>();
        }
        view.displayPaymentType(paymentTypes);
    }

    @Override
    public String getGeneralTextColor() {
        return repository.getGeneralTextColor();
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsId();
    }
}