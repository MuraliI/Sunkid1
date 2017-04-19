package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.OfferingResponseMapper;
import com.rcl.excalibur.data.mapper.ProductResponseDataMapper;
import com.rcl.excalibur.data.mapper.SubCategoryResponseDataMapper;
import com.rcl.excalibur.data.repository.OfferingDataRepository;
import com.rcl.excalibur.data.service.response.ActivitiesResponse;
import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.DiningsResponse;
import com.rcl.excalibur.data.service.response.EntertainmentsResponse;
import com.rcl.excalibur.data.service.response.ExcursionResponse;
import com.rcl.excalibur.data.service.response.GetProductsResponse;
import com.rcl.excalibur.data.service.response.GetSubCategoriesResponse;
import com.rcl.excalibur.data.service.response.MediaItemResponse;
import com.rcl.excalibur.data.service.response.MediaResponse;
import com.rcl.excalibur.data.service.response.ProductAdvisementResponse;
import com.rcl.excalibur.data.service.response.ProductLocationResponse;
import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.data.service.response.ProductRestrictionResponse;
import com.rcl.excalibur.data.service.response.PromotionMessagesResponse;
import com.rcl.excalibur.data.service.response.SpasResponse;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.repository.OfferingRepository;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.repository.SubCategoryRepository;
import com.rcl.excalibur.domain.service.DiscoverServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getDiscoverApi;
import static com.rcl.excalibur.data.utils.ServiceUtil.isSuccess;
import static com.rcl.excalibur.domain.ProductType.ACTIVITIES_TYPE;
import static com.rcl.excalibur.domain.ProductType.DINING_TYPE;
import static com.rcl.excalibur.domain.ProductType.ENTERTAINMENT_TYPE;
import static com.rcl.excalibur.domain.ProductType.SHOPPING_TYPE;
import static com.rcl.excalibur.domain.ProductType.SHOREX_TYPE;
import static com.rcl.excalibur.domain.ProductType.SPA_TYPE;

public class DiscoverServicesImpl extends BaseDataService<Product, ProductResponse, Void> implements DiscoverServices {
    private static final String SAILING_ID = "AL20170430";
    private static final int MAX_COUNT = 50;

    private final ProductRepository productRepository;
    private SubCategoryRepository subCategoryRepository;
    private SubCategoryResponseDataMapper subCategoryResponseDataMapper;

    private final OfferingRepository offeringRepository;
    private final OfferingResponseMapper offeringResponseMapper;

    public DiscoverServicesImpl(ProductRepository productRepository) {
        super(new ProductResponseDataMapper());
        this.productRepository = productRepository;
        offeringResponseMapper = new OfferingResponseMapper((ProductResponseDataMapper) getMapper());
        offeringRepository = new OfferingDataRepository();
    }


    public void setSubCategoryRepository(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public void setSubCategoryResponseDataMapper(SubCategoryResponseDataMapper subCategoryResponseDataMapper) {
        this.subCategoryResponseDataMapper = subCategoryResponseDataMapper;
    }

    @Override
    public void getSubCategories() {
        List<SubCategory> subCategories = new ArrayList<>();

        subCategoryRepository.deleteAll();

        Call<GetSubCategoriesResponse> subCategoriesCall = getDiscoverApi().getSubCategories(SAILING_ID);

        subCategoriesCall.enqueue(new Callback<GetSubCategoriesResponse>() {

            @Override
            public void onResponse(Call<GetSubCategoriesResponse> call, Response<GetSubCategoriesResponse> response) {
                mapSubCategories(response, subCategories);
                subCategoryRepository.create(subCategories);
            }

            @Override
            public void onFailure(Call<GetSubCategoriesResponse> call, Throwable t) {
                logOnFailureError(t, "");
            }
        });
    }

    private void mapSubCategories(Response<GetSubCategoriesResponse> response, List<SubCategory> subCategories) {
        if (response.isSuccessful()) {
            GetSubCategoriesResponse getGetSubCategoriesResponse = response.body();
            if (isSuccess(getGetSubCategoriesResponse)) {
                subCategories.addAll(subCategoryResponseDataMapper.transform(getGetSubCategoriesResponse.getCategory(), null));
            }
        }
    }

    @Override
    public void getCategories() {

        Call<CategoriesResponse> call = getDiscoverApi().getCategories();

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
        Call<SpasResponse> call = getDiscoverApi().getSpas();

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

        Call<ExcursionResponse> call = getDiscoverApi().getExcursion();

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
        Call<DiningsResponse> call = getDiscoverApi().getDinings();

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

        Call<ActivitiesResponse> call = getDiscoverApi().getActivities();
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
        Call<PromotionMessagesResponse> call = getDiscoverApi().getPromotionMessages();
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

        Call<EntertainmentsResponse> call = getDiscoverApi().getEntertainments();

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
        // TODO: This is a provisional implementation, once we have the final response from the serves.
        // This must be changed to consume all products with pagination support
        new Thread(() -> {
            offeringRepository.deleteAll();
            productRepository.deleteAll();

            Call<GetProductsResponse> dinningCall = getDiscoverApi().getProducts(SAILING_ID, DINING_TYPE, MAX_COUNT);
            Call<GetProductsResponse> shorexCall = getDiscoverApi().getProducts(SAILING_ID, SHOREX_TYPE, MAX_COUNT);
            Call<GetProductsResponse> activitiesCall = getDiscoverApi().getProducts(SAILING_ID, ACTIVITIES_TYPE, MAX_COUNT);
            Call<GetProductsResponse> entertainmentCall = getDiscoverApi().getProducts(SAILING_ID, ENTERTAINMENT_TYPE, MAX_COUNT);
            Call<GetProductsResponse> spaCall = getDiscoverApi().getProducts(SAILING_ID, SPA_TYPE, MAX_COUNT);
            Call<GetProductsResponse> shoppingCall = getDiscoverApi().getProducts(SAILING_ID, SHOPPING_TYPE, MAX_COUNT);

            ProductProcessor productProcessor = new ProductProcessor();

            try {
                productProcessor.onResponse(dinningCall.execute(), DINING_TYPE);
                productProcessor.onResponse(shorexCall.execute(), SHOREX_TYPE);
                productProcessor.onResponse(activitiesCall.execute(), ACTIVITIES_TYPE);
                productProcessor.onResponse(entertainmentCall.execute(), ENTERTAINMENT_TYPE);
                productProcessor.onResponse(spaCall.execute(), SPA_TYPE);
                productProcessor.onResponse(shoppingCall.execute(), SHOPPING_TYPE);
            } catch (Exception e) {
                productProcessor.onFailure(e);
            }
        }).start();
    }

    private void logOnFailureError(Throwable t, String category) {
        Timber.e(t, "Error on %s call, message = %s", category, t.getMessage());
    }

    private List<ProductRestrictionResponse> getProductRestrictionResponse() {
        List<ProductRestrictionResponse> productRestrictionResponses = new ArrayList<>();

        ProductRestrictionResponse ageRestriction = new ProductRestrictionResponse();
        ageRestriction.setRestrictionId("1");
        ageRestriction.setRestrictionType(ProductRestriction.AGE);
        ageRestriction.setRestrictionTitle("Age Restritions");
        ageRestriction.setRestrictionDisplayText("12+");
        ageRestriction.setRestrictionDescription("12+");

        productRestrictionResponses.add(ageRestriction);

        ProductRestrictionResponse heigthRestriction = new ProductRestrictionResponse();
        heigthRestriction.setRestrictionId("2");
        heigthRestriction.setRestrictionType(ProductRestriction.HEIGHT);
        heigthRestriction.setRestrictionTitle("Height Restrictions");
        heigthRestriction.setRestrictionDisplayText("None");
        heigthRestriction.setRestrictionDescription("None");

        productRestrictionResponses.add(heigthRestriction);

        return productRestrictionResponses;
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
        advisementAttire.setAdvisementId(ProductAdvisement.ATTIRE);
        advisementAttire.setAdvisementDescription("Casual");
        advisementAttire.setAdvisementTitle("Attire");
        advisementAttire.setAdvisementName("Attire");
        advisementAttire.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAttire);

        ProductAdvisementResponse advisementKnowBeforeYouGo = new ProductAdvisementResponse();
        advisementKnowBeforeYouGo.setAdvisementId(ProductAdvisement.KNOW_BEFORE_YOU_GO);
        advisementKnowBeforeYouGo.setAdvisementDescription("Arrive 15 minutes early, Wear closedtoed shoes");
        advisementKnowBeforeYouGo.setAdvisementTitle("Know Before You Go");
        advisementKnowBeforeYouGo.setAdvisementName("Know Before You Go");
        advisementKnowBeforeYouGo.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementKnowBeforeYouGo);

        ProductAdvisementResponse advisementAccessibility = new ProductAdvisementResponse();
        advisementAccessibility.setAdvisementId(ProductAdvisement.ACCESSIBILITY);
        advisementAccessibility.setAdvisementDescription("");
        advisementAccessibility.setAdvisementTitle("Wheelchair Accessible");
        advisementAccessibility.setAdvisementName("Wheelchair Accessible");
        advisementAccessibility.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAccessibility);
        ProductAdvisementResponse advisementAccessibility2 = new ProductAdvisementResponse();
        advisementAccessibility2.setAdvisementId(ProductAdvisement.ACCESSIBILITY);
        advisementAccessibility2.setAdvisementDescription("This description is short enough to whet one's apetite "
                + "but long enough to be meaningful.");
        advisementAccessibility2.setAdvisementTitle("Closed Caption");
        advisementAccessibility2.setAdvisementName("Closed Caption");
        advisementAccessibility2.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAccessibility2);


        ProductAdvisementResponse advisementLegal = new ProductAdvisementResponse();
        advisementLegal.setAdvisementId(ProductAdvisement.LEGAL);
        advisementLegal.setAdvisementDescription("This legal information is short enough to comfort you but long enough  to be meaninful.");
        advisementLegal.setAdvisementTitle("Legal");
        advisementLegal.setAdvisementName("Legal");
        advisementLegal.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementLegal);

        ProductAdvisementResponse advisementCuisine = new ProductAdvisementResponse();
        advisementCuisine.setAdvisementId(ProductAdvisement.CUISINE);
        advisementCuisine.setAdvisementDescription("Latin American");
        advisementCuisine.setAdvisementTitle("Cuisine");
        advisementCuisine.setAdvisementName("Cuisine");
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

    private class ProductProcessor {

        private List<Product> productList = new ArrayList<>();
        private List<Offering> offeringList = new ArrayList<>();
        private String productType;

        void onResponse(Response<GetProductsResponse> response, String productType) {
            this.productType = productType;
            mapDataProducts(response, productList, offeringList);
            productRepository.create(productList);
            offeringRepository.create(offeringList);
        }

        void onFailure(Throwable t) {
            logOnFailureError(t, productType);
        }

        private void mapDataProducts(Response<GetProductsResponse> response, List<Product> productList, List<Offering> offeringList) {
            if (response.isSuccessful()) {
                GetProductsResponse getProductsResponse = response.body();
                if (isSuccess(getProductsResponse)) {
                    for (ProductResponse productResponse : getProductsResponse.getProducts()) {
                        // TODO: To be removed once the service provides this details
                        productResponse.setUpcharge(2);
                        if (productResponse.getProductReservationInformation() == null) {
                            productResponse.setProductReservationInformation("Please Arrive 15 minutes early, Wear closedtoed shoes");
                        }
                        if (productResponse.getExperience() == null) {
                            productResponse.setExperience("Enjoy the travel!");
                        }
                        List<ProductAdvisementResponse> productAdvisementResponseList = productResponse.getAdvisements();
                        if (productAdvisementResponseList == null || productAdvisementResponseList.isEmpty()) {
                            productResponse.setAdvisements(getProductAdvisementResponseAttire());
                        }
                        setProductLocationExtraParameters(productResponse.getProductLocation());
                        offeringList.addAll(offeringResponseMapper.transform(productResponse.getOffering(), productResponse));
                    }
                    productList.addAll(getMapper().transform(getProductsResponse.getProducts(), null));
                }
            }
        }
    }
}
