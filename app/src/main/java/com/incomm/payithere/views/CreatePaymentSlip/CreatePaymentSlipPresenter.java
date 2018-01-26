package com.incomm.payithere.views.CreatePaymentSlip;

import android.app.Activity;

import com.incomm.payithere.models.cms.TopBiller;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.SpecificBillerInformation;
import com.incomm.payithere.services.BillerByNameService;
import com.incomm.payithere.services.SingleCategoryService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.util.List;

/**
 * Created by agodambe on 10/18/2017.
 */

public class CreatePaymentSlipPresenter implements CreatePaymentSlipMVP.Presenter{
    private CreatePaymentSlipMVP.View view;
    private CreatePaymentSlipMVP.Repository repository;
    private BillerByNameService billerByNameService;

    public CreatePaymentSlipPresenter(CreatePaymentSlipMVP.View view, CreatePaymentSlipMVP.Repository repository,BillerByNameService billerByNameService) {
        this.view = view;
        this.repository = repository;
        this.billerByNameService = billerByNameService;
        //billerByNameService = new BillerByNameService();
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    @Override
    public void getTopBillers() {
        List<TopBiller> topBillers = repository.getTopBillers();
        if(topBillers.isEmpty()){
            view.showNoBillersFound();
        }else {
            view.displayTopBillers(topBillers);
        }
    }

    public String getGlobalTextColor() {
        return repository.getGeneralTextColor();
    }

    @Override
    public void getBillerByName(String query) {
        if(query.length() >0){
            billerByNameService.getBillerByName(this,query);
        }
    }

    @Override
    public void OnBillerByNameCallSuccess(List<BillerGroup> billerList) {
        if(billerList.isEmpty()){
            view.showNoBillersFound();
        }else {
            view.displayBillerList(billerList);
        }
    }

    public void onBillerByIdCallSuccess(SpecificBillerInformation biller) {
        if(biller == null) {
            view.showNoBillersFound();
        } else {

        }
    }

    @Override
    public void OnBillerByNameCallError(String error) {
        view.showError(error);
    }

    @Override
    public void getRightChevron() {
        view.setRightChevron(repository.getRightChevron());
    }

    @Override
    public void getTitle() {
        String title = ActivityUtils.checkNull(repository.getTitle());
        view.displayTitle(title);
    }

    @Override
    public void getThemePrimary() {
        String color = ActivityUtils.checkColorNull(repository.getThemePrimary());
        view.setThemePrimary(color);
    }

    @Override
    public void getSearchBarPlaceholder() {
        String placeHolder = ActivityUtils.checkNull(repository.getSearchBarPlaceholder());
        view.setSearchBarPlaceHolder(placeHolder);
    }

    @Override
    public void getCategoriesButtonText() {
        String placeHolder = ActivityUtils.checkNull(repository.getCategoriesButtonText());
        view.setCategoryButtonText(placeHolder);
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }

}
