package com.incomm.payithere.managers;


import com.incomm.payithere.models.services.request.ChangePasswordRequest;
import com.incomm.payithere.models.services.request.ConfirmPaymentRequest;
import com.incomm.payithere.models.services.request.EmailPaymentSlipRequest;
import com.incomm.payithere.models.services.request.EnterAmountRequest;
import com.incomm.payithere.models.services.request.PasswordResetRequest;
import com.incomm.payithere.models.services.request.SignInRequest;
import com.incomm.payithere.models.services.request.SignUpRequest;
import com.incomm.payithere.models.services.response.BillerByNameResponse;
import com.incomm.payithere.models.services.response.BillerCategoriesResponse;
import com.incomm.payithere.models.services.response.BillerHistoryResponse;
import com.incomm.payithere.models.services.response.ChangePasswordResponse;
import com.incomm.payithere.models.services.response.EnterAccountInformationResponse;
import com.incomm.payithere.models.services.response.PasswordResetResponse;
import com.incomm.payithere.models.services.response.PaymentPostResponse;
import com.incomm.payithere.models.services.response.PaymentWithEreceiptResponse;
import com.incomm.payithere.models.services.response.PostBillerResponse;
import com.incomm.payithere.models.services.response.SignInResponse;
import com.incomm.payithere.models.services.response.SignUpResponse;
import com.incomm.payithere.models.services.response.SingleCategoryResponse;
import com.incomm.payithere.models.services.response.SpecificBillerInformation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by agodambe on 3/17/2017.
 */

public interface PayItHereService {
// X-CURRENT-RETAILER - 7c0d0da84ff63578624e2e02f2586f53
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @POST("users/sign_in")
    Call<SignInResponse> postLogin(@Body SignInRequest signInRequest);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @POST("users/password")
    Call<PasswordResetResponse> postForgotPasswordReset(@Body PasswordResetRequest passwordResetRequest);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @PUT("user")
    Call<ChangePasswordResponse> putChangePassword(@Header("X-Auth-Token") String token,@Body ChangePasswordRequest changePasswordRequest);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @POST("users")
    Call<SignUpResponse> postSignUp(@Body SignUpRequest signUpRequest);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @GET("biller_accounts")
    Call<BillerHistoryResponse> getBillerHistory(@Header("X-Auth-Token") String token);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @GET("categories/{id}")
    Call<SingleCategoryResponse> getSingleCategory(@Header("X-Auth-Token") String token, @Path("id") String id);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @GET("biller/{id}")
    Call<SpecificBillerInformation> getBillerWithToken(@Header("X-Auth-Token") String token, @Path("id") String id);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @GET("categories")
    Call<BillerCategoriesResponse> getAllCategories(@Header("X-Auth-Token") String token);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @GET("search")
    Call<BillerByNameResponse> getBillerByName(@Header("X-Auth-Token") String token, @Query("q") String name);


    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @GET("payments/{paymentId}/ereceipt")
    Call<PaymentWithEreceiptResponse> getEreceipt(@Header("X-Auth-Token") String token, @Path("paymentId") String paymentId);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @PATCH("billers/{billerID}/payments/{paymentID}/confirm")
    Call<PostBillerResponse> confirmAccount(@Header("X-Auth-Token") String token,@Path("billerID") String billerId
            ,@Path("paymentID") String paymentId);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @PATCH("billers/{billerID}/payments/{paymentID}/amountize")
    Call<PostBillerResponse> enterAmount(@Header("X-Auth-Token") String token, @Path("billerID") String billerId
            , @Path("paymentID") String paymentId, @Body EnterAmountRequest request);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @GET("billers/{id}")
    Call<EnterAccountInformationResponse> getSpecificBillerInformation(@Header("X-Auth-Token") String token, @Path("id") String id);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @POST("billers/{id}/payments")
    Call<PaymentPostResponse> getPaymentPost(@Header("X-Auth-Token") String token, @Path("id") String id, @Body ConfirmPaymentRequest confirmPaymentRequest);

    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    @POST("billers/{billerId}/payments/{paymentId}/payment_slip_deliveries")
    Call<ResponseBody> emailPaymentSlip(@Header("X-Auth-Token") String token, @Path("billerId") String billerId, @Path("paymentId") String paymentId, @Body EmailPaymentSlipRequest emailPaymentSlipRequest);

    @POST("biller_accounts/{id}/pay_again")
    @Headers({"Accept: application/json",
            "Content-Type: application/json",
            "X-CURRENT-RETAILER: 7c0d0da84ff63578624e2e02f2586f53"})
    Call<PaymentPostResponse> getPaymentAgain(@Header("X-Auth-Token") String token, @Path("id") String id);
}
