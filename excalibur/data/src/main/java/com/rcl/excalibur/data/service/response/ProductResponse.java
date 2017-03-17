
package com.rcl.excalibur.data.service.response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

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
    //??@SerializedName("costType")
    private Object mCostType;
    private StartingFromPriceResponse startingFromPrice;
    private RestrictionResponse restrictionResponse;
    private String productTitle;
    private String productShortDescription;
    private String productLongDescription;
    private ProductMediaResponse productMedia;




    @SerializedName("advisements")
    private List<Object> mAdvisements;

    @SerializedName("preferences")
    private List<Object> mPreferences;

    @SerializedName("productDuration")
    private ProductDurationResponse mProductDuration;




    @SerializedName("restrictions")
    private List<Object> mRestrictions;


}
