package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.response.BillerAccount;
import com.incomm.payithere.models.services.response.BillerHistoryResponse;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.views.billsHistory.BillerHistoryPresenter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 9/13/2017.
 */

public class BillerHistoryService {

    private RetrofitManager retrofitManager;
    private BillerHistoryPresenter presenter;
    public BillerHistoryService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void getBillerHistory(final BillerHistoryPresenter presenter){
        this.presenter = presenter;

        retrofitManager.getPayItHereInstance().getBillerHistory(PayItHereApplication.getToken())
                .enqueue(new Callback<BillerHistoryResponse>() {
                    @Override
                    public void onResponse(Call<BillerHistoryResponse> call, Response<BillerHistoryResponse> response) {
                        Log.i("Biller History service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                        List<BillerAccount> billsList = response.body().getBillerAccounts();
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.OnBillerHistoryCallSuccess(billsList);
                        }else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.OnBillerHistoryCallError(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnBillerHistoryCallError("894");
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<BillerHistoryResponse> call, Throwable t) {
                        presenter.OnBillerHistoryCallError("894");

                    }
                });

    }
}
