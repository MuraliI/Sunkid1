package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.ProductResponseDataMapper;
import com.rcl.excalibur.data.service.api.DiscoverApi;
import com.rcl.excalibur.data.service.response.ActivitiesResponse;
import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.DiningsResponse;
import com.rcl.excalibur.data.service.response.EntertainmentsResponse;
import com.rcl.excalibur.data.service.response.ExcursionResponse;
import com.rcl.excalibur.data.service.response.GetProductsResponse;
import com.rcl.excalibur.data.service.response.MediaItemResponse;
import com.rcl.excalibur.data.service.response.MediaResponse;
import com.rcl.excalibur.data.service.response.ProductAdvisementResponse;
import com.rcl.excalibur.data.service.response.ProductLocationResponse;
import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.data.service.response.PromotionMessagesResponse;
import com.rcl.excalibur.data.service.response.SpasResponse;
import com.rcl.excalibur.data.utils.ServiceUtil;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.DiscoverServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DiscoverServicesImpl implements DiscoverServices {
    private static final String SAILING_ID = "AL20170430";
    private static final int MAX_COUNT = 50;

    private static final String SHOREX = "SHOREX";
    private static final String ACTIVITIES = "ACTIVITIES";
    private static final String ENTERTAINMENT = "ENTERTAINMENT";
    private static final String DINING = "DINING";
    private static final String SPA = "SPA";

    private final ProductRepository productRepository;
    private final ProductResponseDataMapper productResponseDataMapper;
    private final DiscoverApi discoverApi;

    public DiscoverServicesImpl(ProductRepository productRepository, ProductResponseDataMapper productResponseDataMapper,
                                DiscoverApi discoverApi) {
        this.productRepository = productRepository;
        this.productResponseDataMapper = productResponseDataMapper;
        this.discoverApi = discoverApi;
    }

    @Override
    public void getCategories() {

        Call<CategoriesResponse> call = discoverApi.getCategories();

        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                Timber.d("Succesfull", response.body().getGetCategoriesResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                //Handle failure
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getSpas() {
        Call<SpasResponse> call = discoverApi.getSpas();

        call.enqueue(new Callback<SpasResponse>() {
            @Override
            public void onResponse(Call<SpasResponse> call, Response<SpasResponse> response) {
                Timber.d("Succesfull", response.body().getGetSpasResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<SpasResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getExcursion() {

        Call<ExcursionResponse> call = discoverApi.getExcursion();

        call.enqueue(new Callback<ExcursionResponse>() {
            @Override
            public void onResponse(Call<ExcursionResponse> call, Response<ExcursionResponse> response) {
                Timber.d("Succesfull", response.body().getGetExcursionsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<ExcursionResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getDinings() {
        Call<DiningsResponse> call = discoverApi.getDinings();

        call.enqueue(new Callback<DiningsResponse>() {
            @Override
            public void onResponse(Call<DiningsResponse> call, Response<DiningsResponse> response) {
                Timber.d("Succesfull", response.body().getGetDiningsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<DiningsResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getActivities() {

        Call<ActivitiesResponse> call = discoverApi.getActivities();
        call.enqueue(new Callback<ActivitiesResponse>() {
            @Override
            public void onResponse(Call<ActivitiesResponse> call, Response<ActivitiesResponse> response) {
                Timber.d("Succesfull", response.body().getGetActivitiesResponse().getResponseStatus());

            }

            @Override
            public void onFailure(Call<ActivitiesResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getPromotionMessages() {
        Call<PromotionMessagesResponse> call = discoverApi.getPromotionMessages();
        call.enqueue(new Callback<PromotionMessagesResponse>() {
            @Override
            public void onResponse(Call<PromotionMessagesResponse> call, Response<PromotionMessagesResponse> response) {
                Timber.d("Succesfull", response.body().getGetPromotionMessages().getResponseStatus());

            }

            @Override
            public void onFailure(Call<PromotionMessagesResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getEntertainments() {

        Call<EntertainmentsResponse> call = discoverApi.getEntertainments();

        call.enqueue(new Callback<EntertainmentsResponse>() {
            @Override
            public void onResponse(Call<EntertainmentsResponse> call, Response<EntertainmentsResponse> response) {
                Timber.d("Succesfull", response.body().getGetEntertainmentsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<EntertainmentsResponse> call, Throwable t) {
                //Handle failure
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getProducts() {
        // TODO: This is a provisional implementation, once we have the final response from the serves. this must be changed.
        // As they changed the products call to multiple service we choosed to keep adding the Products from GetProductsResponse to an
        // array and after that call once the create method of the repository because this one is deleting the database everytime.

        List<Product> productList = new ArrayList<>();
        Call<GetProductsResponse> dinningCall = discoverApi.getProducts(SAILING_ID, DINING, MAX_COUNT);
        dinningCall.enqueue(new Callback<GetProductsResponse>() {
            @Override
            public void onResponse(Call<GetProductsResponse> call, Response<GetProductsResponse> response) {
                saveData(response, productList);

                Call<GetProductsResponse> shorexCall = discoverApi.getProducts(SAILING_ID, SHOREX, MAX_COUNT);
                shorexCall.enqueue(new Callback<GetProductsResponse>() {
                    @Override
                    public void onResponse(Call<GetProductsResponse> call, Response<GetProductsResponse> response) {
                        saveData(response, productList);

                        Call<GetProductsResponse> spaCall = discoverApi.getProducts(SAILING_ID, SPA, MAX_COUNT);
                        spaCall.enqueue(new Callback<GetProductsResponse>() {
                            @Override
                            public void onResponse(Call<GetProductsResponse> call, Response<GetProductsResponse> response) {
                                saveData(response, productList);

                                Call<GetProductsResponse> entertainmentCall = discoverApi.getProducts(SAILING_ID, ENTERTAINMENT, MAX_COUNT);
                                entertainmentCall.enqueue(new Callback<GetProductsResponse>() {
                                    @Override
                                    public void onResponse(Call<GetProductsResponse> call, Response<GetProductsResponse> response) {
                                        saveData(response, productList);

                                        Call<GetProductsResponse> activitiesCall = discoverApi.getProducts(SAILING_ID, ACTIVITIES, MAX_COUNT);
                                        activitiesCall.enqueue(new Callback<GetProductsResponse>() {
                                            @Override
                                            public void onResponse(Call<GetProductsResponse> call, Response<GetProductsResponse> response) {
                                                saveData(response, productList);

                                                productRepository.create(productList);
                                            }

                                            @Override
                                            public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                                                Timber.e("error", t.getMessage());
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                                        Timber.e("error", t.getMessage());
                                    }
                                });

                            }

                            @Override
                            public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                                Timber.e("error", t.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                        Timber.e("error", t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    private void saveData(Response<GetProductsResponse> response, List<Product> productList) {
        if (response.isSuccessful()) {
            GetProductsResponse getProductsResponse = response.body();
            if (ServiceUtil.isSuccess(getProductsResponse)) {
                for (ProductResponse productResponse : getProductsResponse.getProducts()) { // TODO: To be removed once the service provides this details
                    productResponse.setUpcharge(new Random().nextInt(4));
                    if (productResponse.getProductReservationInformation() == null) {
                        productResponse.setProductReservationInformation("Please Arrive 15 minutes early, Wear closedtoed shoes");
                    }
                    if (productResponse.getExperience() == null) {
                        productResponse.setExperience("Enjoy the travel!");
                    }
                    List<ProductAdvisementResponse> productAdvisementResponseList = productResponse.getAdvisements();
                   /* if (productAdvisementResponseList == null || productAdvisementResponseList.isEmpty()) {
                        productResponse.setAdvisements(getProductAdvisementResponseAttire());
                    }*/
                    productResponse.setAdvisements(getProductAdvisementResponseAttire());
                    setProductLocationExtraParameters(productResponse.getProductLocation());
                }
                productList.addAll(productResponseDataMapper.transform(getProductsResponse.getProducts()));
            }
        }
    }

    // TODO: Hardcoded method to be removed once the service provides this details
    private List<ProductAdvisementResponse> getProductAdvisementResponseAttire() {
        List<ProductAdvisementResponse> productAdvisementResponses = new ArrayList<>();

        MediaItemResponse mediaItemResponse = new MediaItemResponse();
        mediaItemResponse.setMediaType("icon");
        mediaItemResponse.setMediaRefLink("/royal/shared_assets/icons/advisement_meals_r.png");

        MediaResponse mediaResponse = new MediaResponse();
        List<MediaItemResponse> mediaItemResponseList = new ArrayList<>();
        mediaItemResponseList.add(mediaItemResponse);
        mediaResponse.setMediaItem(mediaItemResponseList);

        ProductAdvisementResponse advisementAttire = new ProductAdvisementResponse();
        advisementAttire.setAdvisementId(1L);
        advisementAttire.setAdvisementDescription("Casual");
        advisementAttire.setAdvisementTitle("Attire");
        advisementAttire.setAdvisementName("Attire");
        advisementAttire.setAdvisementType("ATTIRE");
        advisementAttire.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAttire);

        ProductAdvisementResponse advisementKnowBeforeYouGo = new ProductAdvisementResponse();
        advisementKnowBeforeYouGo.setAdvisementId(2L);
        advisementKnowBeforeYouGo.setAdvisementDescription("Arrive 15 minutes early, Wear closedtoed shoes");
        advisementKnowBeforeYouGo.setAdvisementTitle("Know Before You Go");
        advisementKnowBeforeYouGo.setAdvisementName("Know Before You Go");
        advisementKnowBeforeYouGo.setAdvisementType("KNOW_BEFORE_YOU_GO");
        advisementKnowBeforeYouGo.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementKnowBeforeYouGo);

        ProductAdvisementResponse advisementAccessibility = new ProductAdvisementResponse();
        advisementAccessibility.setAdvisementId(3L);
        advisementAccessibility.setAdvisementDescription("");
        advisementAccessibility.setAdvisementTitle("Wheelchair Accessible");
        advisementAccessibility.setAdvisementName("Wheelchair Accessible");
        advisementAccessibility.setAdvisementType("ACCESSIBILITY");
        advisementAccessibility.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAccessibility);
        //Test Gerardo
        ProductAdvisementResponse advisementAccessibility2 = new ProductAdvisementResponse();
        advisementAccessibility2.setAdvisementId(3L);
        advisementAccessibility2.setAdvisementDescription("This description is short enough to whet one's apetite but long enough to be meaningful.");
        advisementAccessibility2.setAdvisementTitle("Closed Caption");
        advisementAccessibility2.setAdvisementName("Closed Caption");
        advisementAccessibility2.setAdvisementType("ACCESSIBILITY");
        advisementAccessibility2.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAccessibility2);


        ProductAdvisementResponse advisementLegal = new ProductAdvisementResponse();
        advisementLegal.setAdvisementId(4L);
        advisementLegal.setAdvisementDescription("This legal information is short enough to comfort you but long enough  to be meaninful.");
        advisementLegal.setAdvisementTitle("Legal");
        advisementLegal.setAdvisementName("Legal");
        advisementLegal.setAdvisementType("LEGAL");
        advisementLegal.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementLegal);

        ProductAdvisementResponse advisementCuisine = new ProductAdvisementResponse();
        advisementCuisine.setAdvisementId(5L);
        advisementCuisine.setAdvisementDescription("Latin American");
        advisementCuisine.setAdvisementTitle("Cuisine");
        advisementCuisine.setAdvisementName("Cuisine");
        advisementCuisine.setAdvisementType("CUISINE");
        advisementCuisine.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementCuisine);

        return productAdvisementResponses;
    }

    // TODO: Hardcoded method to be removed once the service provides this details
    private void setProductLocationExtraParameters(ProductLocationResponse productLocationResponse) {
        productLocationResponse.setLocationVenue("Royale Theatre");
        productLocationResponse.setLocationPort("St. Martin");
        productLocationResponse.setLocationDirection("AFT");
        productLocationResponse.setLocationDeckNumber(12);
    }
}
