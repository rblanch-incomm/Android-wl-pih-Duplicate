package com.incomm.payithere.views.billerCategory;

import android.app.Activity;

import com.incomm.payithere.models.BillerCategoryItem;
import com.incomm.payithere.models.cms.BillerCategoryIcon;
import com.incomm.payithere.models.services.response.BillerCategory;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.services.AllCategoriesService;
import com.incomm.payithere.services.SingleCategoryService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.CreatePaymentSlip.CreatePaymentSlipMVP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agodambe on 10/18/2017.
 */

public class BillerCategoryPresenter implements BillerCategoryMVP.Presenter{
    private BillerCategoryMVP.View view;
    private BillerCategoryMVP.Repository repository;
    private AllCategoriesService service;

    public BillerCategoryPresenter(BillerCategoryMVP.View view, BillerCategoryMVP.Repository repository) {
        this.view = view;
        this.repository = repository;
        service = new AllCategoriesService();
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    @Override
    public void getBillerCategories() {
        service.getAllCategories(this);
    }

    @Override
    public void OnCategoryListCallSuccess(List<BillerCategory> categoryList) {
        ArrayList<BillerCategoryItem> billerCategoryItemList = new ArrayList<>();
        List<BillerCategoryIcon> iconList = repository.getCategoryIcons();
        for(int i = 0;i < categoryList.size();i++){
            BillerCategoryItem item = new BillerCategoryItem();
            item.setId(categoryList.get(i).getId());
            item.setName(categoryList.get(i).getName());
            int num = 0;
            while (!categoryList.get(i).getId().equals(iconList.get(num).getCategoryId())){
                num++;
            }
            item.setIconUrl(repository.getCategoryIcons().get(num).getIcon().url());
            billerCategoryItemList.add(item);

        }
        view.displayCategoryList(billerCategoryItemList);
    }

    public String getGlobalTextColor() {
        return repository.getGeneralTextColor();
    }

    @Override
    public void OnCategoryListCallError(String error) {
        view.showError(error);
    }

    @Override
    public void getTitle() {
        String title = ActivityUtils.checkNull(repository.getTitle());
        view.displayTitle(title);
    }

    @Override
    public String getGeneralTextColor() {
        return repository.getGeneralTextColor();
    }

    @Override
    public String getThemePrimary() {
        return repository.getThemePrimary();
    }

    @Override
    public String getRightChevron() {
        return repository.getRightChevron();
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }
}
