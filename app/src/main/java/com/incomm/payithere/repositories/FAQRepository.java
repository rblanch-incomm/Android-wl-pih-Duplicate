package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.FAQQuestionItem;
import com.incomm.payithere.models.cms.FAQView;
import com.incomm.payithere.models.cms.GlobalImages;
import com.incomm.payithere.views.faqs.FaqsMVP;

import java.util.Collections;
import java.util.List;

/**
 * Created by agodambe on 9/19/2017.
 */

public class FAQRepository implements FaqsMVP.Repository {

    private FAQView cmsView;
    public FAQRepository() {
        cmsView = CMSViewManager.getInstance().getFaqView();
    }
    @Override
    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
    }
    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getRightChevron() {
        return GlobalImagesManager.getInstance().getChevronRight().url();
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    @Override
    public List<FAQQuestionItem> getQuestions() {
        return cmsView.getQuestions();
    }
}
