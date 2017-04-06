package com.rcl.excalibur.data.service.api;

import com.rcl.excalibur.data.service.request.guest.CreateAccountRequest;
import com.rcl.excalibur.data.service.response.guest.CreateAccountResponse;
import com.rcl.excalibur.data.service.response.guest.SecurityQuestionsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GuestApi {

    @GET("securityQuestions")
    Call<SecurityQuestionsResponse> getSecurityQuestions();

    @GET("securityQuestions")
    Call<SecurityQuestionsResponse> getSecurityQuestionByEmail(@Query("emailId") String email);

    @POST(" ")
    Call<CreateAccountResponse> createAccount(@Header("Content-Type") String contentType, @Body CreateAccountRequest requestBody);
}
