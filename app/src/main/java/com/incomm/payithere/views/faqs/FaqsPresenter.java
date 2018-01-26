package com.incomm.payithere.views.faqs;


import android.app.Activity;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.FAQQuestionItem;
import com.incomm.payithere.models.cms.FAQView;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;

import java.util.List;


/**
 * Created by jayma on 4/20/2017.
 */

public class FaqsPresenter extends ViewPresenter implements FaqsMVP.Presenter{

    private FaqsMVP.View view;
    private FaqsMVP.Repository faqsRepository;
    //private FAQView cmsView;
    private List<FAQQuestionItem> questions;
    private String analyticId;

    public FaqsPresenter(FaqsMVP.View view,FaqsMVP.Repository faqsRepository){
        this.view = view;
        this.faqsRepository = faqsRepository;

    }

    public void getViewElements() {
        this.analyticId = faqsRepository.getAnalyticsId();
        view.setupUi();
    }

    public void getTitle(){
        String title = ActivityUtils.checkNull(faqsRepository.getTitle());
        view.setTitle(title);
    }

    public void getQuestions(){
        List<FAQQuestionItem> questionsList = faqsRepository.getQuestions();
        if(questionsList.isEmpty() || questionsList == null){
            view.displayNoQuestions();
        }else {
            view.displayQuestions(questionsList);
        }
    }




    public String getNavigationBar(){
        return GlobalImagesManager.getInstance().getNavigationBar().url();
    }

    public String getRightChevron(){
        String chevron = ActivityUtils.checkNull(faqsRepository.getRightChevron());
        return chevron;
    }

    public void getGlobalTextColor(){
        view.setGeneralTextColor(faqsRepository.getGeneralTextColor());
    }

    public String getAnalyticsId() {
        return faqsRepository.getAnalyticsId();
    }
    public void sendGoogleAnalytics(Activity activity) {
        GoogleAnalyticsUtils.getInstance().sendData(activity, faqsRepository.getAnalyticsId());
    }
}
