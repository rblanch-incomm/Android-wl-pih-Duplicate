package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.request.EmailPaymentSlipRequest;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.PaymentPostResponse;
import com.incomm.payithere.models.services.response.PaymentWithEreceiptResponse;
import com.incomm.payithere.views.barcodeSlip.BarcodeSlipMVP;
import com.incomm.payithere.views.billHistoryDetail.BillHistoryDetailMVP;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bjennings on 10/16/2017.
 */

public class PaymentDetailService implements BarcodeSlipMVP.Service {
    private RetrofitManager retrofitManager;

    public PaymentDetailService() {
        this.retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void getPaymentDetails(String paymentId, final BillHistoryDetailMVP.Presenter presenter) {
        this.retrofitManager.getPayItHereInstance().getEreceipt(PayItHereApplication.getToken(), paymentId)
                .enqueue(new Callback<PaymentWithEreceiptResponse>() {
                    @Override
                    public void onResponse(Call<PaymentWithEreceiptResponse> call, Response<PaymentWithEreceiptResponse> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.paymentDetailSuccess(response.body().getPayment());
                        } else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000 + "";
                                    presenter.paymentDetailFailure(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.paymentDetailFailure("894");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PaymentWithEreceiptResponse> call, Throwable t) {
                        presenter.paymentDetailFailure("894");
                    }
                });
    }

    public void getPendingDetails(String paymentId, final BarcodeSlipMVP.Presenter presenter) {
        this.retrofitManager.getPayItHereInstance().getEreceipt(PayItHereApplication.getToken(), paymentId)
                .enqueue(new Callback<PaymentWithEreceiptResponse>() {
                    @Override
                    public void onResponse(Call<PaymentWithEreceiptResponse> call, Response<PaymentWithEreceiptResponse> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.paymentDetailSuccess(response.body().getPayment());
                        } else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.paymentDetailFailure(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.paymentDetailFailure("100");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PaymentWithEreceiptResponse> call, Throwable t) {
                        presenter.paymentDetailFailure("894");
                    }
                });
    }

    public void sendPaymentSlipToEmail(String billerId, String paymentId, EmailPaymentSlipRequest request, final BarcodeSlipMVP.Presenter presenter) {
        this.retrofitManager.getPayItHereInstance().emailPaymentSlip(PayItHereApplication.getToken(),billerId, paymentId, request)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful() && response.code() == 204) {
                            presenter.emailRequestSuccess();
                        } else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000 + "";
                                    presenter.emailRequestFailure(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.emailRequestFailure("100");
                                }
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        presenter.paymentDetailFailure("894");
                    }
                });
    }

    public void getPayAgain(String id, final BillHistoryDetailMVP.Presenter presenter) {
        this.retrofitManager.getPayItHereInstance().getPaymentAgain(PayItHereApplication.getToken(), id)
                .enqueue(new Callback<PaymentPostResponse>() {
                    @Override
                    public void onResponse(Call<PaymentPostResponse> call, Response<PaymentPostResponse> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Log.d("TAG", response.body().getPaymentPost().getBillerAccountToken());
                            presenter.payAgainCallback(response.body());
                        } else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000 + "";
                                    presenter.paymentDetailFailure(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.paymentDetailFailure("894");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PaymentPostResponse> call, Throwable t) {
                        presenter.paymentDetailFailure("894");
                    }
                });
    }

}
