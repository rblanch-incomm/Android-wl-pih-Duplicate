package com.incomm.payithere.managers;

import com.incomm.payithere.models.services.response.StoresResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by agodambe on 11/17/2017.
 */

public interface LocationService {

    @GET("stores")
    Call<StoresResponse> getStoresByLocation(@Query("lat") String latitude, @Query("lng") String longitude, @Query("radius") String radius);

    @GET("stores")
    Call<StoresResponse> getStoresByAddress(@Query("address") String address, @Query("radius") String radius);

}
