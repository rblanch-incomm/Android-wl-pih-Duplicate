package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.response.BillerCategoriesResponse;
import com.incomm.payithere.models.services.response.BillerCategory;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.views.billerCategory.BillerCategoryPresenter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 9/13/2017.
 */

public class AllCategoriesService {

    private RetrofitManager retrofitManager;
    private BillerCategoryPresenter presenter;
    public AllCategoriesService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void getAllCategories(final BillerCategoryPresenter presenter){
        this.presenter = presenter;

        retrofitManager.getPayItHereInstance().getAllCategories(PayItHereApplication.getToken())
                .enqueue(new Callback<BillerCategoriesResponse>() {
                    @Override
                    public void onResponse(Call<BillerCategoriesResponse> call, Response<BillerCategoriesResponse> response) {
                        Log.i("Biller History service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                        List<BillerCategory> categoryList = response.body().getCategories();
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.OnCategoryListCallSuccess(categoryList);
                        }else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.OnCategoryListCallError(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnCategoryListCallError("894");
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<BillerCategoriesResponse> call, Throwable t) {
                        presenter.OnCategoryListCallError("894");
                    }
                });

    }
}
