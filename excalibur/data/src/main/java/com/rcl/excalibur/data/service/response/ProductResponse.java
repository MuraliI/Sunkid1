
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProductResponse {

    @SerializedName("productID")
    private String productId;
    private String productCode;
    private ProductTypeResponse productType;
    private String productClass;
    private List<ProductCategory> productCategory;
    private Long productRank;
    private Boolean isReservationRequired;
    private Boolean isScheduable;
    private ActivityLevelResponse activityLevel;
    private ProductLocationResponse productLocation;
    private ProductDurationResponse productDuration;
    private StartingFromPriceResponse startingFromPrice;
    private RestrictionResponse restrictionResponse;
    private String productTitle;
    private String productShortDescription;
    private String productLongDescription;
    private ProductMediaResponse productMedia;
    private List<AdvisementResponse> advisements;
    private List<PreferencesResponse> preferences;
    private List<RestrictionResponse> restrictions;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public ProductTypeResponse getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeResponse productType) {
        this.productType = productType;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public List<ProductCategory> getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(List<ProductCategory> productCategory) {
        this.productCategory = productCategory;
    }

    public Long getProductRank() {
        return productRank;
    }

    public void setProductRank(Long productRank) {
        this.productRank = productRank;
    }

    public Boolean getReservationRequired() {
        return isReservationRequired;
    }

    public void setReservationRequired(Boolean reservationRequired) {
        isReservationRequired = reservationRequired;
    }

    public Boolean getScheduable() {
        return isScheduable;
    }

    public void setScheduable(Boolean scheduable) {
        isScheduable = scheduable;
    }

    public ActivityLevelResponse getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevelResponse activityLevel) {
        this.activityLevel = activityLevel;
    }

    public ProductLocationResponse getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(ProductLocationResponse productLocation) {
        this.productLocation = productLocation;
    }

    public ProductDurationResponse getProductDuration() {
        return productDuration;
    }

    public void setProductDuration(ProductDurationResponse productDuration) {
        this.productDuration = productDuration;
    }

    public StartingFromPriceResponse getStartingFromPrice() {
        return startingFromPrice;
    }

    public void setStartingFromPrice(StartingFromPriceResponse startingFromPrice) {
        this.startingFromPrice = startingFromPrice;
    }

    public RestrictionResponse getRestrictionResponse() {
        return restrictionResponse;
    }

    public void setRestrictionResponse(RestrictionResponse restrictionResponse) {
        this.restrictionResponse = restrictionResponse;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductShortDescription() {
        return productShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        this.productShortDescription = productShortDescription;
    }

    public String getProductLongDescription() {
        return productLongDescription;
    }

    public void setProductLongDescription(String productLongDescription) {
        this.productLongDescription = productLongDescription;
    }

    public ProductMediaResponse getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(ProductMediaResponse productMedia) {
        this.productMedia = productMedia;
    }

    public List<AdvisementResponse> getAdvisements() {
        return advisements;
    }

    public void setAdvisements(List<AdvisementResponse> advisements) {
        this.advisements = advisements;
    }

    public List<PreferencesResponse> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<PreferencesResponse> preferences) {
        this.preferences = preferences;
    }


}
