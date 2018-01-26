package com.incomm.payithere.managers;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.incomm.payithere.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aashish on 10/2/16.
 */
public class RetrofitManager {

    Context context;
    public static String baseUrl = Constants.BASE_URL;

    public RetrofitManager(Context context) {
        this.context = context;
    }

    //
    public RetrofitManager(Context context,String url) {
        this.context = context;
        baseUrl = url;
    }

    private static PayItHereService instance = null;
    private static LocationService locationServiceInstance = null;


    public PayItHereService getPayItHereInstance() {
        if (null == instance) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        MockServiceClient mockInterceptor = new MockServiceClient(context);
//        OkHttpClient mockClient = new OkHttpClient.Builder().addInterceptor(mockInterceptor).build();
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Content-Type", "application/json")
                                    .addHeader("Accept", "application/json").build();
                            return chain.proceed(request);
                        }
                    }).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

            PayItHereService service = retrofit.create(PayItHereService.class);
            instance = service;
        }
        return instance;
    }

    public LocationService getLocationInstance() {
        if (null == locationServiceInstance) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        MockServiceClient mockInterceptor = new MockServiceClient(context);
//        OkHttpClient mockClient = new OkHttpClient.Builder().addInterceptor(mockInterceptor).build();
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Authorization", "Token token=586f2612dae3c2ad21ee5dd09b350b39")
                                    .addHeader("Accept","application/vnd.cashtie.com+json;version=1").build();
                            return chain.proceed(request);
                        }
                    }).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

            LocationService service = retrofit.create(LocationService.class);
            locationServiceInstance = service;
        }
        return locationServiceInstance;
    }

}
