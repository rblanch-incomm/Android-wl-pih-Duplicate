package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.response.BillerByNameResponse;
import com.incomm.payithere.models.services.response.BillerGroup;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.SpecificBillerInformation;
import com.incomm.payithere.views.CreatePaymentSlip.CreatePaymentSlipPresenter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 9/13/2017.
 */

public class BillerByNameService {

    private RetrofitManager retrofitManager;
    private CreatePaymentSlipPresenter presenter;
    public BillerByNameService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void getBillerByName(final CreatePaymentSlipPresenter presenter,String name){
        this.presenter = presenter;

        retrofitManager.getPayItHereInstance().getBillerByName(PayItHereApplication.getToken(),name)
                .enqueue(new Callback<BillerByNameResponse>() {
                    @Override
                    public void onResponse(Call<BillerByNameResponse> call, Response<BillerByNameResponse> response) {
                        Log.i("Biller History service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                        List<BillerGroup> billersList = null;
                        billersList = response.body().getBillerGroups();
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.OnBillerByNameCallSuccess(billersList);
                        }else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.OnBillerByNameCallError(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnBillerByNameCallError("894");
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<BillerByNameResponse> call, Throwable t) {
                        presenter.OnBillerByNameCallError("894");
                    }
                });

    }

}
