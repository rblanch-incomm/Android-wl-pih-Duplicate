package com.incomm.payithere.views.selectBiller;

import android.app.Activity;
import android.util.Log;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.SingleCategoryResponse;
import com.incomm.payithere.services.SingleCategoryService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 10/18/2017.
 */

public class SelectBillerPresenter implements SelectBillerMVP.Presenter{
    private SelectBillerMVP.View view;
    private SelectBillerMVP.Repository repository;
    private SelectBillerMVP.Service service;

    public SelectBillerPresenter(SelectBillerMVP.View view, SelectBillerMVP.Repository repository,SelectBillerMVP.Service service) {
        this.view = view;
        this.repository = repository;
        this.service = service;
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
    public void setBillersList(String category) {
        service.getBillerCategory(this,category);
    }

    @Override
    public void OnSingleCategoryCallSuccess(List<BillerGroup> billerList) {
        if(billerList.isEmpty()){
            view.showWhenListEmpty();
        }else {
            view.showBillersList(billerList);
        }

    }

    @Override
    public void OnSingleCategoryCallError(String error) {
        view.showError(error);

    }

    @Override
    public String getGlobalTextColor() {
        return repository.getGeneralTextColor();
    }

    @Override
    public void getRightChevron() {
        view.setRightChevron(repository.getRightChevron());
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsId();
    }
}
