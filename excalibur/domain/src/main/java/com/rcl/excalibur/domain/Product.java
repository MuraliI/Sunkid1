package com.rcl.excalibur.domain;


import com.rcl.excalibur.domain.utils.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private static final int TIME_LENGTH = 4;
    private String productId;
    private List<ProductCategory> productCategory;
    private List<ProductAdvisement> advisements;
    private List<ProductPreference> preferences;
    private List<ProductRestriction> restrictions;
    private ProductActivityLevel activityLevel;
    private ProductLocation productLocation;
    private ProductDuration productDuration;
    private ProductCostType costType;
    private ProductType productType;
    private SellingPrice startingFromPrice;
    private String productTitle;
    private String productCode;
    private String productClass;
    private String productShortDescription;
    private String productLongDescription;
    private String productReservationInformation;
    private String experience;
    private Media productMedia;
    private int productRank;
    private int upcharge;
    private boolean isReservationRequired;
    private boolean isScheduable;
    private boolean isFeatured;
    private boolean isHighlighted;
    private List<Offering> offerings;

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

    public int getProductUpcharge() {
        return upcharge;
    }

    public void setProductUpcharge(int upchargeIcon) {
        this.upcharge = upchargeIcon;
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

    //TODO Improve this
    public String getProductLongDescription() {
        if (productLongDescription == null)
            return productShortDescription;
        else
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

    public Media getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(Media productMedia) {
        this.productMedia = productMedia;
    }

    public String getTimeFrame() {
        return convertToTime(productLocation.getOperatingHoursStart()) + " - " + convertToTime(productLocation.getOperatingHoursEnd());
    }

    private String convertToTime(String time) {
        if (time.length() == TIME_LENGTH) {
            return time.substring(0, 2) + ":" + time.substring(2);
        }
        return ConstantsUtil.DEFAULT_TIME;
    }

    public String getHeroImageRefLink() {
        if (productMedia != null) {
            List<MediaItem> mediaItems = productMedia.getMediaItem();
            if (!mediaItems.isEmpty()) {
                return mediaItems.get(0).getMediaRefLink();
            }
        }
        return ConstantsUtil.EMPTY;
    }

    public List<ProductAdvisement> getProductAdvisementsById(String advisementId) {
        List<ProductAdvisement> advisementList = new ArrayList<>();
        for (ProductAdvisement advisement : advisements) {
            String currentAdvisementId = advisement.getAdvisementId();
            if (currentAdvisementId != null && currentAdvisementId.equals(advisementId)) {
                advisementList.add(advisement);
            }
        }
        return advisementList;
    }

    public List<Offering> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<Offering> offerings) {
        this.offerings = offerings;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }
}
