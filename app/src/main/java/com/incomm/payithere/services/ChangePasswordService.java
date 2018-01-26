package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.request.ChangePassUser;
import com.incomm.payithere.models.services.request.ChangePasswordRequest;
import com.incomm.payithere.models.services.response.ChangePasswordResponse;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.views.changepassword.ChangePasswordPresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 9/13/2017.
 */

public class ChangePasswordService {

    private RetrofitManager retrofitManager;
    private ChangePasswordPresenter presenter;
    public ChangePasswordService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void changePassword(final ChangePasswordPresenter presenter, String currentPassword, String newPassword, String confirmPassword){
        this.presenter = presenter;
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setUser(new ChangePassUser(currentPassword,newPassword,confirmPassword));

        retrofitManager.getPayItHereInstance().putChangePassword(PayItHereApplication.getToken(),request)
                .enqueue(new Callback<ChangePasswordResponse>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                        Log.i("ChangePassword service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.OnChangePasswordComplete(true,"");
                        }else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.OnChangePasswordComplete(false,errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnChangePasswordComplete(false,"894");
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                        presenter.OnChangePasswordComplete(false,"894");

                    }
                });

    }
}
