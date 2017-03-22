package com.rcl.excalibur.domain;


import java.util.List;


public class Product {

    private String productId;
    private String productCode;
    private ProductType productType;
    private String productClass;
    private List<ProductCategory> productCategory;
    private int productRank;
    private boolean isReservationRequired;
    private boolean isScheduable;
    private ProductActivityLevel activityLevel;
    private ProductLocation productLocation;
    private ProductDuration productDuration;
    private ProductCostType costType;
    private SellingPrice startingFromPrice;
    private List<ProductAdvisement> advisements;
    private List<ProductPreference> preferences;
    private List<ProductRestriction> restrictions;
    private String productTitle;
    private String productShortDescription;
    private String productLongDescription;
    private Media productMedia;

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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
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

    public int getProductRank() {
        return productRank;
    }

    public void setProductRank(int productRank) {
        this.productRank = productRank;
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

    public ProductActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ProductActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public ProductLocation getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(ProductLocation productLocation) {
        this.productLocation = productLocation;
    }

    public ProductDuration getProductDuration() {
        return productDuration;
    }

    public void setProductDuration(ProductDuration productDuration) {
        this.productDuration = productDuration;
    }

    public ProductCostType getCostType() {
        return costType;
    }

    public void setCostType(ProductCostType costType) {
        this.costType = costType;
    }

    public SellingPrice getStartingFromPrice() {
        return startingFromPrice;
    }

    public void setStartingFromPrice(SellingPrice startingFromPrice) {
        this.startingFromPrice = startingFromPrice;
    }

    public List<ProductAdvisement> getAdvisements() {
        return advisements;
    }

    public void setAdvisements(List<ProductAdvisement> advisements) {
        this.advisements = advisements;
    }

    public List<ProductPreference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<ProductPreference> preferences) {
        this.preferences = preferences;
    }

    public List<ProductRestriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<ProductRestriction> restrictions) {
        this.restrictions = restrictions;
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

    public Media getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(Media productMedia) {
        this.productMedia = productMedia;
    }
}
