package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.SingleCategoryResponse;
import com.incomm.payithere.views.selectBiller.SelectBillerMVP;
import com.incomm.payithere.views.selectBiller.SelectBillerPresenter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 9/13/2017.
 */

public class SingleCategoryService implements SelectBillerMVP.Service {
    private RetrofitManager retrofitManager;

    public SingleCategoryService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void getBillerCategory(SelectBillerPresenter billerPresenter, String id){
        final SelectBillerPresenter presenter = billerPresenter;
        retrofitManager.getPayItHereInstance().getSingleCategory(PayItHereApplication.getToken(),id)
                .enqueue(new Callback<SingleCategoryResponse>() {
                    @Override
                    public void onResponse(Call<SingleCategoryResponse> call, Response<SingleCategoryResponse> response) {
                        Log.i("Single Category service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                        List<BillerGroup> billerList = response.body().getCategory().getBillerGroups();
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.OnSingleCategoryCallSuccess(billerList);
                        }else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.OnSingleCategoryCallError(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnSingleCategoryCallError("894");
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<SingleCategoryResponse> call, Throwable t) {
                        presenter.OnSingleCategoryCallError("894");
                    }
                });

    }
}
