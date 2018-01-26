package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.request.PasswordResetRequest;
import com.incomm.payithere.models.services.request.User;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.PasswordResetResponse;
import com.incomm.payithere.views.forgotpassword.ForgotPasswordPresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 9/13/2017.
 */

public class ForgotPasswordService {

    private RetrofitManager retrofitManager;
    private ForgotPasswordPresenter presenter;
    public ForgotPasswordService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void resetPassword(final ForgotPasswordPresenter presenter, String email){
        this.presenter = presenter;
        PasswordResetRequest request = new PasswordResetRequest();
        User user = new User();
        user.setEmail(email);
        request.setUser(user);

        retrofitManager.getPayItHereInstance().postForgotPasswordReset(request)
                .enqueue(new Callback<PasswordResetResponse>() {
                    @Override
                    public void onResponse(Call<PasswordResetResponse> call, Response<PasswordResetResponse> response) {
                        Log.i("ResetPassword service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.OnResetPasswordComplete(true,"");
                        }else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000+"";
                                    presenter.OnResetPasswordComplete(false,errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnResetPasswordComplete(false,"894");
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<PasswordResetResponse> call, Throwable t) {
                        presenter.OnResetPasswordComplete(false,"894");

                    }
                });

    }
}
