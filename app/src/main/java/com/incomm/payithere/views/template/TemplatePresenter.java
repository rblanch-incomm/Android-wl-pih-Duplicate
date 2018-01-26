package com.incomm.payithere.views.template;

import android.app.Activity;

import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

/**
 * Created by agodambe on 10/18/2017.
 */

public class TemplatePresenter implements TemplateMVP.Presenter{
    private TemplateMVP.View view;
    private TemplateMVP.Repository repository;

    public TemplatePresenter(TemplateMVP.View view, TemplateMVP.Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    @Override
    public void setTitle() {
        String title = ActivityUtils.checkNull(repository.getTitle());
        view.displayTitle(title);
    }

    @Override
    public String getGeneralTextColor() {
        return repository.getGeneralTextColor();

    }

    @Override
    public String getPositiveButtonBackground() {
        return repository.getPositiveButtonBackground();
    }

    @Override
    public String getNegativeButtonBackground() {
        return repository.getNegativeButtonBackground();
    }

    @Override
    public String getPositiveBtnTextColor() {
        return repository.getPositiveBtnTextColor();
    }

    @Override
    public String getNegativeBtnTextColor() {
        return repository.getNegativeBtnTextColor();
    }

    public void sendGoogleAnalytics(Activity activity) {
        GoogleAnalyticsUtils.getInstance().sendData(activity, repository.getAnalyticsId());
    }
}
