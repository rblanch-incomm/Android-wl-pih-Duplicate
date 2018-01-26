package com.incomm.payithere.services;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.request.EnterAmountRequest;
import com.incomm.payithere.models.services.request.PaymentBody;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.PostBillerResponse;
import com.incomm.payithere.views.enterAmount.EnterAmountMVP;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 10/16/2017.
 */

public class EnterAmountService implements EnterAmountMVP.Service {
    private RetrofitManager retrofitManager;

    public EnterAmountService() {
        this.retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void enterAmount(final EnterAmountMVP.Presenter presenter,String totalAmount, String billerId, String paymentId) {
        EnterAmountRequest request = new EnterAmountRequest();
        request.setPayment(new PaymentBody(totalAmount));
        this.retrofitManager.getPayItHereInstance().enterAmount(PayItHereApplication.getToken(),billerId,paymentId,request)
                .enqueue(new Callback<PostBillerResponse>() {
                    @Override
                    public void onResponse(Call<PostBillerResponse> call, Response<PostBillerResponse> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.enterAmountSuccess(response.body().getPayment());
                        } else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000 + "";
                                    presenter.enterAmountFailure(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.enterAmountFailure("100");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PostBillerResponse> call, Throwable t) {
                        presenter.enterAmountFailure("894");
                    }
                });
    }
}
