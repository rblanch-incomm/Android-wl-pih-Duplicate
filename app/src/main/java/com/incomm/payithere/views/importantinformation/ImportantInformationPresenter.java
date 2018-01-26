package com.incomm.payithere.views.importantinformation;

import android.app.Activity;
import android.graphics.Color;

import com.incomm.payithere.adapters.ImportantInformationAdapter;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.FeatureLegalDocument;
import com.incomm.payithere.models.cms.ImportantInformationView;
import com.incomm.payithere.models.legaldocs.LegalDocument;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.util.List;

/**
 * Created by jayma on 4/3/2017.
 */

public class ImportantInformationPresenter implements ImportantInformationMVP.Presenter {
    private ImportantInformationMVP.View view;
    private ColorSchemeManager colorSchemeManager;
    private ImportantInformationView cmsView;
    private GlobalImagesManager globalImages;
    private ImportantInformationAdapter adapter;

    public ImportantInformationPresenter(ColorSchemeManager colorSchemeManager,
                                         ImportantInformationView cmsView,
                                         ImportantInformationFragment view,
                                         GlobalImagesManager globalImages){
        this.colorSchemeManager = colorSchemeManager;
        this.cmsView = cmsView;
        this.globalImages = globalImages;
        this.view = view;
    }

    @Override
    public void getViewElements() {
        adapter = new ImportantInformationAdapter(getLegalDocs(), getGeneralText(), globalImages);

        view.setupUi();
        view.setViewFeatures();
    }

    @Override
    public ImportantInformationAdapter getAdapter() { return adapter; }

    @Override
    public List<FeatureLegalDocument> getFeatureLegalDocuments() {
        return cmsView.getLegalDocumentFeatures();
    }

    public String getViewTitle() {
        return cmsView.getTitle();
    }

    public LegalDocument[] getLegalDocs(){
        return cmsView.getLegalDocuments();
    }

    @Override
    public GlobalImagesManager getGlobalImages() {
        return globalImages;
    }

    @Override
    public String getGeneralText() {
        return colorSchemeManager.getGeneralText();
    }

    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
    }
}
