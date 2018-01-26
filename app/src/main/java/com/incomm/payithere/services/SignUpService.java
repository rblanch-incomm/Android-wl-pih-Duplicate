package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.request.SignUpRequest;
import com.incomm.payithere.models.services.request.SignUpUser;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.SignUpResponse;
import com.incomm.payithere.views.signUp.SignUpPresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 9/13/2017.
 */

public class SignUpService {

    private RetrofitManager retrofitManager;
    private SignUpPresenter presenter;
    public SignUpService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext());
    }

    public void signUp(final SignUpPresenter presenter, String firstName, String lastName, String email, String phone, String password, String confirmPassword){
        this.presenter = presenter;
        SignUpRequest request = new SignUpRequest();
        request.setUser(new SignUpUser(firstName,lastName,email,phone,password,confirmPassword));

        retrofitManager.getPayItHereInstance().postSignUp(request).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.i("SignUp service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                if (response.isSuccessful() && response.code() == 200) {
                    PayItHereApplication.setToken(response.body().getUser().getToken());
                    presenter.signUpCallback(true,"");
                }else if (response.code() == 522) {
                    presenter.signUpCallback(false,"10401");
                } else {
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            ErrorBody error = gson.fromJson(response.errorBody().string(),ErrorBody.class);
                            String errorCode = error.getError().getCode() + 40000+"";
                            presenter.signUpCallback(false, errorCode);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            presenter.signUpCallback(false,"894");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                presenter.signUpCallback(false,"894");
            }
        });

    }
}
