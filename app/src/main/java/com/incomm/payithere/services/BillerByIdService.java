package com.incomm.payithere.services;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.request.ConfirmPaymentRequest;
import com.incomm.payithere.models.services.response.EnterAccountInformationResponse;
import com.incomm.payithere.models.services.response.PaymentPost;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.PaymentPostResponse;
import com.incomm.payithere.models.services.response.SpecificBillerInformation;
import com.incomm.payithere.views.enteraccountinformation.EnterAccountInformationMVP;
import com.incomm.payithere.views.enteraccountinformation.EnterAccountInformationPresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Brice Blanch on 11/6/17.
 */

public class BillerByIdService implements EnterAccountInformationMVP.Service {
    private RetrofitManager retrofitManager;

    public BillerByIdService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    @Override
    public void getAccountInformationFormFields(final EnterAccountInformationPresenter enterAccountInformationPresenter, String billerId) {
        final EnterAccountInformationPresenter presenter = enterAccountInformationPresenter;
        retrofitManager.getPayItHereInstance().getSpecificBillerInformation(PayItHereApplication.getToken(), billerId)
                .enqueue(new Callback<EnterAccountInformationResponse>() {
                    @Override
                    public void onResponse(Call<EnterAccountInformationResponse> call, Response<EnterAccountInformationResponse> response) {
                        SpecificBillerInformation biller = response.body().getBiller();
                        presenter.OnEnterAccountInformationCallSuccess(biller);
                    }

                    @Override
                    public void onFailure(Call<EnterAccountInformationResponse> call, Throwable t) {
                        presenter.OnEnterAccountInformationCallError("100");
                    }
                });
    }

    @Override
    public void getPaymentPost(final EnterAccountInformationPresenter enterAccountInformationPresenter, String billerId, ConfirmPaymentRequest confirmPaymentRequest) {
        final EnterAccountInformationPresenter presenter = enterAccountInformationPresenter;
        retrofitManager.getPayItHereInstance().getPaymentPost(PayItHereApplication.getToken(), billerId, confirmPaymentRequest)
                .enqueue(new Callback<PaymentPostResponse>() {
                    @Override
                    public void onResponse(Call<PaymentPostResponse> call, Response<PaymentPostResponse> response) {
                        if (response.code() == 200) {
                            PaymentPost paymentPost = response.body().getPaymentPost();
                            presenter.OnPaymentPostCallSuccess(paymentPost);
                        } else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.OnPaymentPostCallError(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnPaymentPostCallError("894");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PaymentPostResponse> call, Throwable t) {
                        presenter.OnPaymentPostCallError("894");
                    }
                });
    }
}
