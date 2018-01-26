package com.incomm.payithere.services;

import android.util.Log;

import com.google.gson.Gson;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.RetrofitManager;
import com.incomm.payithere.models.services.response.ErrorBody;
import com.incomm.payithere.models.services.response.Store;
import com.incomm.payithere.models.services.response.StoresResponse;
import com.incomm.payithere.utils.Constants;
import com.incomm.payithere.views.locations.LocationsMVP;
import com.incomm.payithere.views.locations.LocationsPresenter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agodambe on 9/13/2017.
 */

public class GetStoresService implements LocationsMVP.Service {

    private RetrofitManager retrofitManager;
    private LocationsPresenter presenter;
    private Call<StoresResponse> storesByLocationCall;
    public GetStoresService() {
        retrofitManager = new RetrofitManager(PayItHereApplication.getContext(), Constants.LOCATION_URL);

    }

    public void cancelAll(){
        if (storesByLocationCall !=  null){
            if(storesByLocationCall.isExecuted()){
            storesByLocationCall.cancel();
        }}
    }

    public void getStoresByLocation(final LocationsPresenter presenter, String latitude,String longitude,String radius){
        this.presenter = presenter;

        storesByLocationCall = retrofitManager.getLocationInstance().getStoresByLocation(latitude, longitude, radius);
                storesByLocationCall.enqueue(new Callback<StoresResponse>() {
                    @Override
                    public void onResponse(Call<StoresResponse> call, Response<StoresResponse> response) {
                        Log.i("Location service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                        List<Store> storesList = null;
                        storesList = response.body().getStores();
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.OnStoresByLocationSuccess(storesList);
                        }else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000 + "";
                                    presenter.OnStoresByLocationError(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnStoresByLocationError("100");
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<StoresResponse> call, Throwable t) {
                        presenter.OnStoresByLocationError("100");
                    }
                });

    }

    public void getStoresByAddress(final LocationsPresenter presenter, String address,String radius){
        this.presenter = presenter;

        retrofitManager.getLocationInstance().getStoresByAddress(address, radius)
                .enqueue(new Callback<StoresResponse>() {
                    @Override
                    public void onResponse(Call<StoresResponse> call, Response<StoresResponse> response) {
                        Log.i("Location service", "Response Code: " + response.code() + "\n" + "Response: " + response.raw());
                        List<Store> storesList = null;
                        storesList = response.body().getStores();
                        if (response.isSuccessful() && response.code() == 200) {
                            presenter.OnStoresByLocationSuccess(storesList);
                        }else {
                            if (response.errorBody() != null) {
                                try {
                                    Gson gson = new Gson();
                                    ErrorBody error = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                                    String errorCode = error.getError().getCode() + 40000 + "";
                                    presenter.OnStoresByLocationError(errorCode);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    presenter.OnStoresByLocationError("894");
                                }
                            }

                        }
                    }
                    @Override
                    public void onFailure(Call<StoresResponse> call, Throwable t) {
                        presenter.OnStoresByLocationError("894");
                    }
                });

    }
}
