package com.incomm.payithere.views.importantinformation;

import android.app.Activity;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.adapters.ImportantInformationAdapter;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.FeatureLegalDocument;
import com.incomm.payithere.models.legaldocs.LegalDocument;

import java.util.List;

/**
 * Created by jayma on 4/3/2017.
 */

public interface ImportantInformationMVP {

    interface View extends BaseView<Presenter> { }

    interface Presenter extends BasePresenter {
        List<FeatureLegalDocument> getFeatureLegalDocuments();
        String getViewTitle();
        LegalDocument[] getLegalDocs();
        GlobalImagesManager getGlobalImages();
        String getGeneralText();
        ImportantInformationAdapter getAdapter();
        String getAnalyticsId();
    }
}
