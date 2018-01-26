package com.incomm.payithere.views.help;

import android.app.Activity;

import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.util.List;

/**
 * Created by bjennings on 10/11/2017.
 */

public class HelpPresenter implements HelpMVP.Presenter {
    private HelpMVP.View view;
    private HelpMVP.Repository repository;
    private GlobalImagesManager imagesManager;
    private ColorSchemeManager colorSchemeManager;
    private List<Feature> featureList;

    public HelpPresenter(HelpMVP.View view,
                         HelpMVP.Repository repository,
                         GlobalImagesManager imagesManager,
                         ColorSchemeManager colorSchemeManager) {
        this.view = view;
        this.repository = repository;
        this.imagesManager = imagesManager;
        this.colorSchemeManager = colorSchemeManager;
        this.featureList = repository.getFeatures();
    }

    @Override
    public void getViewElements() {
        view.createRecyclerView(featureList,
                                colorSchemeManager.getGeneralText(),
                                imagesManager.getChevronRight());
        view.setupUi();
    }

    @Override
    public void displayMailingAddress() {
        view.displayMailingAddress(repository.getMailingAddress());
    }

    @Override
    public void displayPhoneNumber() {
        view.displayPhoneNumber(repository.getPhoneNumber());
    }

    @Override
    public void displayDeviceId() {
        view.displayDeviceId();
    }

    @Override
    public void setFragmentTitle() {
        view.setFragmentTitle(repository.getTitle());
    }

    @Override
    public void displayCallLabel() {
        view.displayCallLabel(repository.getPhoneLabel());
    }

    @Override
    public void displayMailLabel() {
        view.displayMailLabel(repository.getAddressLabel());
    }

    @Override
    public void displayDeviceIdLabel() {
        view.displayDeviceIdLabel(repository.getDeviceIdLabel());
    }

    @Override
    public void onPhoneClick() {
        view.displayCallConfirmation(repository.getCallMessage(),
                                     repository.getPositivePhoneButton(),
                                     repository.getNegativeButton());
    }

    @Override
    public void confirmPhoneCall() {
        view.makePhoneCall(repository.getPhoneNumber());
    }

    @Override
    public void onItemClicked(int position) {
        view.launchFragment(featureList.get(position).getKey());
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsId();
    }
}
