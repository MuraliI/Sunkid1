package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.mapper.guest.SecurityQuestionsResponseMapper;
import com.rcl.excalibur.data.service.api.GuestApi;
import com.rcl.excalibur.data.service.request.guest.CreateAccountRequest;
import com.rcl.excalibur.data.service.request.guest.SecurityQuestionRequest;
import com.rcl.excalibur.data.service.request.guest.TermsAndConditionsAgreementRequest;
import com.rcl.excalibur.data.service.response.guest.CreateAccountResponse;
import com.rcl.excalibur.data.service.response.guest.SecurityQuestionsResponse;
import com.rcl.excalibur.data.service.response.guest.ValidateEmailResponse;
import com.rcl.excalibur.domain.guest.CreateAccountEvent;
import com.rcl.excalibur.domain.guest.ValidateEmailEvent;
import com.rcl.excalibur.domain.service.GuestServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

public class GuestServicesImpl implements GuestServices {
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private final GuestApi guestApi;
    private final SecurityQuestionsResponseMapper securityQuestionsResponseMapper;

    public GuestServicesImpl(GuestApi guestApi, SecurityQuestionsResponseMapper securityQuestionsResponseMapper) {
        this.guestApi = guestApi;
        this.securityQuestionsResponseMapper = securityQuestionsResponseMapper;
    }

    @Override
    public void getSecurityQuestions(Observer<List<String>> observer) {
        Observable<List<String>> observable = Observable.create(e -> {
            Call<SecurityQuestionsResponse> call = guestApi.getSecurityQuestions();
            try {
                Response<SecurityQuestionsResponse> response = call.execute();
                SecurityQuestionsResponse securityQuestionsResponse = response.body();
                if (securityQuestionsResponse != null) {
                    e.onNext(securityQuestionsResponseMapper.transform(securityQuestionsResponse));
                } else {
                    e.onError(new RuntimeException("Invalid Response"));
                }
            } catch (IOException ex) {
                e.onError(ex);
            }
            e.onComplete();
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void createAccount(Observer<CreateAccountEvent> observer) {

        //TODO improve this
        CreateAccountRequest request = new CreateAccountRequest();
        request.setFirstName("Brad");
        request.setLastName("Pitt");
        request.setEmail("lawn@order.com");
        request.setPassword("insecurepassword");
        request.setBrand("r");

        TermsAndConditionsAgreementRequest terms = new TermsAndConditionsAgreementRequest();
        //review Integer exacple to large for INTEGER -> 1490108091640
        terms.setAcceptTime(1490108091);
        terms.setVersion("1.0");
        request.setTermsAndConditionsAgreement(terms);

        SecurityQuestionRequest question = new SecurityQuestionRequest();
        question.setQuestion("What is your mother's phone number?");
        question.setAnswer("8675309");
        ArrayList<SecurityQuestionRequest> questions = new ArrayList<>();
        questions.add(question);
        request.setSecurityQuestions(questions);
        //


        Observable<CreateAccountEvent> observable = Observable.create(e -> {
            Call<CreateAccountResponse> call = guestApi.createAccount(CONTENT_TYPE_APPLICATION_JSON, request);
            try {
                Response<CreateAccountResponse> response = call.execute();
                CreateAccountEvent responseEvent = new CreateAccountEvent();
                CreateAccountResponse createAccountResponse = response.body();
                if (createAccountResponse != null) {
                    responseEvent.setSuccesfull(true);
                    responseEvent.setMessage(response.body().getAccountId());
                } else {
                    responseEvent.setSuccesfull(false);
                    responseEvent.setMessage("Invalid Response");
                }
                e.onNext(responseEvent);
            } catch (IOException ex) {
                e.onError(ex);
            }
            e.onComplete();
        });

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void validateEmail(Observer<ValidateEmailEvent> observer, String email) {
        Observable<ValidateEmailEvent> observable = Observable.create(e -> {
            Call<ValidateEmailResponse> call = guestApi.validateEmail(email);
            try {
                Response<ValidateEmailResponse> response = call.execute();
                ValidateEmailResponse validateEmailResponse = response.body();
                if (validateEmailResponse != null) {
                    e.onNext(new ValidateEmailEvent(response.isSuccessful(), validateEmailResponse.getStatus()));
                } else {
                    e.onError(new RuntimeException("Invalid Response"));
                }
            } catch (IOException ex) {
                e.onError(ex);
            }
            e.onComplete();
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
