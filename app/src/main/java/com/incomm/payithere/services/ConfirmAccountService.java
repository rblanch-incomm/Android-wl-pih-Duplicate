package com.incomm.payithere.services;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.PostBillerResponse;
import com.incomm.payithere.views.confirmAccountDetails.ConfirmAccountDetailsMVP;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 10/16/2017.
 */

public class ConfirmAccountService implements ConfirmAccountDetailsMVP.Service {
    private RetrofitManager retrofitManager;

    public ConfirmAccountService() {
        this.retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void confirmAccount(final ConfirmAccountDetailsMVP.Presenter presenter,String billerId,String paymentId) {
        this.retrofitManager.getPayItHereInstance().confirmAccount(PayItHereApplication.getToken(),billerId,paymentId)
                .enqueue(new Callback<PostBillerResponse>() {
                    @Override
                    public void onResponse(Call<PostBillerResponse> call, Response<PostBillerResponse> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.confirmAccountSuccess(response.body().getPayment());
                        } else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.confirmAccountFailure(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.confirmAccountFailure("894");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PostBillerResponse> call, Throwable t) {
                        presenter.confirmAccountFailure("894");
                    }
                });
    }
}
