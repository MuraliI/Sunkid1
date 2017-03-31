package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ProductResponse {

    @SerializedName("productID")
    private long productId;
    private List<ProductCategoryResponse> productCategory;
    private List<ProductAdvisementResponse> advisements;
    private List<ProductPreferenceResponse> preferences;
    private List<ProductRestrictionResponse> restrictions;
    private ProductTypeResponse productType;
    private ProductActivityLevelResponse activityLevel;
    private ProductLocationResponse productLocation;
    private ProductDurationResponse productDuration;
    private ProductCostTypeResponse costType;
    private SellingPriceResponse startingFromPrice;
    private MediaResponse productMedia;
    private String productTitle;
    private String productCode;
    private String productClass;
    private String productShortDescription;
    private String productLongDescription;
    private String productReservationInformation;
    private String experience;
    private int productRank;
    private int upcharge;
    private boolean isReservationRequired;
    private boolean isScheduable;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public List<ProductCategoryResponse> getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(List<ProductCategoryResponse> productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductAdvisementResponse> getAdvisements() {
        return advisements;
    }

    public void setAdvisements(List<ProductAdvisementResponse> advisements) {
        this.advisements = advisements;
    }

    public List<ProductPreferenceResponse> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<ProductPreferenceResponse> preferences) {
        this.preferences = preferences;
    }

    public List<ProductRestrictionResponse> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<ProductRestrictionResponse> restrictions) {
        this.restrictions = restrictions;
    }

    public ProductTypeResponse getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeResponse productType) {
        this.productType = productType;
    }

    public ProductActivityLevelResponse getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ProductActivityLevelResponse activityLevel) {
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

    public ProductCostTypeResponse getCostType() {
        return costType;
    }

    public void setCostType(ProductCostTypeResponse costType) {
        this.costType = costType;
    }

    public SellingPriceResponse getStartingFromPrice() {
        return startingFromPrice;
    }

    public void setStartingFromPrice(SellingPriceResponse startingFromPrice) {
        this.startingFromPrice = startingFromPrice;
    }

    public MediaResponse getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(MediaResponse productMedia) {
        this.productMedia = productMedia;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
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

    public String getProductReservationInformation() {
        return productReservationInformation;
    }

    public void setProductReservationInformation(String productReservationInformation) {
        this.productReservationInformation = productReservationInformation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getProductRank() {
        return productRank;
    }

    public void setProductRank(int productRank) {
        this.productRank = productRank;
    }

    public int getUpcharge() {
        return upcharge;
    }

    public void setUpcharge(int upcharge) {
        this.upcharge = upcharge;
    }

    public boolean isReservationRequired() {
        return isReservationRequired;
    }

    public void setReservationRequired(boolean reservationRequired) {
        isReservationRequired = reservationRequired;
    }

    public boolean isScheduable() {
        return isScheduable;
    }

    public void setScheduable(boolean scheduable) {
        isScheduable = scheduable;
    }
}
