package com.rcl.excalibur.mapper;

import com.rcl.excalibur.domain.Accessibility;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.Price;
import com.rcl.excalibur.domain.Property;
import com.rcl.excalibur.domain.StandardTime;
import com.rcl.excalibur.domain.Time;
import com.rcl.excalibur.model.AccessibilityModel;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.model.PriceModel;
import com.rcl.excalibur.model.PropertyModel;
import com.rcl.excalibur.model.StandardTimeModel;
import com.rcl.excalibur.model.TimeModel;

import java.util.ArrayList;
import java.util.List;

public class DiscoverModelDataMapper extends BaseModelDataMapper<DiscoverItemModel, DiscoverItem> {

    public DiscoverModelDataMapper() {
    }

    @Override
    public DiscoverItemModel transform(DiscoverItem discoverItem) {
        if (discoverItem == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final DiscoverItemModel discoverItemModel = new DiscoverItemModel();
        discoverItemModel.setImageUrl(discoverItem.getImageUrl());
        discoverItemModel.setCategory(discoverItem.getCategory());
        discoverItemModel.setHours(discoverItem.getHours());
        discoverItemModel.setTitle(discoverItem.getTitle());
        discoverItemModel.setType(discoverItem.getType());
        discoverItemModel.setSubTitle(discoverItem.getSubTitle());
//        discoverItemModel.setReservationRequired(discoverItem.getReservationRequired());
        discoverItemModel.setDescription(discoverItem.getDescription());
        discoverItemModel.setPromotionTitle(discoverItem.getPromotionTitle());
        discoverItemModel.setPromotionDescription(discoverItem.getPromotionDescription());
        discoverItemModel.setLegal(discoverItem.getLegal());
//        discoverItemModel.setStandardTime(create(discoverItem.getStandardTime()));
//        discoverItemModel.setAccessibilities(createAccessibilities(discoverItem.getAccessibilities()));
//        discoverItemModel.setPrices(createPrices(discoverItem.getPrices()));
//        discoverItemModel.setProperties(createProperties(discoverItem.getProperties()));

        return discoverItemModel;
    }

    private List<PriceModel> createPrices(List<Price> prices) {
        final List<PriceModel> priceModels = new ArrayList<>();
        if (prices == null || prices.isEmpty()) {
            return priceModels;
        }
        for (Price price : prices) {
            final PriceModel priceModel = new PriceModel();
            priceModel.setTitle(price.getTitle());
            priceModel.setValue(price.getValue());
            priceModels.add(priceModel);
        }
        return priceModels;
    }

    private List<PropertyModel> createProperties(List<Property> properties) {
        final List<PropertyModel> propertyModels = new ArrayList<>();
        if (properties == null || properties.isEmpty()) {
            return propertyModels;
        }
        for (Property property : properties) {
            final PropertyModel propertyModel = new PropertyModel();
            propertyModel.setKey(property.getKey());
            propertyModel.setValue(property.getValue());
            propertyModels.add(propertyModel);
        }
        return propertyModels;
    }


    private List<AccessibilityModel> createAccessibilities(List<Accessibility> accessibilities) {
        final List<AccessibilityModel> accessibilityModels = new ArrayList<>();
        if (accessibilities == null || accessibilities.isEmpty()) {
            return accessibilityModels;
        }
        for (Accessibility accessibility : accessibilities) {
            final AccessibilityModel accessibilityModel = new AccessibilityModel();
            accessibilityModel.setText(accessibility.getText());
            accessibilityModels.add(accessibilityModel);
        }
        return accessibilityModels;
    }

    private StandardTimeModel create(StandardTime standardTime) {
        if (standardTime == null) {
            return null;
        }
        final StandardTimeModel standardTimeModel = new StandardTimeModel();
        standardTimeModel.setTitle(standardTime.getTitle());
        final List<TimeModel> timeModels = new ArrayList<>();
        for (Time time : standardTime.getTimes()) {
            TimeModel timeModel = new TimeModel();
            timeModel.setDay(time.getDay());
            timeModel.setTime(time.getTime());
            timeModels.add(timeModel);
        }
        standardTimeModel.setTimes(timeModels);
        return standardTimeModel;
    }
}
