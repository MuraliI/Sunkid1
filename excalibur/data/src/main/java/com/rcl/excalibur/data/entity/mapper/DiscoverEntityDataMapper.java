package com.rcl.excalibur.data.entity.mapper;


import com.rcl.excalibur.data.entity.AccessibilityEntity;
import com.rcl.excalibur.data.entity.DiscoverItemEntity;
import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.data.entity.PropertyEntity;
import com.rcl.excalibur.data.entity.StandardTimeEntity;
import com.rcl.excalibur.data.entity.TimeEntity;
import com.rcl.excalibur.domain.Accessibility;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.Price;
import com.rcl.excalibur.domain.Property;
import com.rcl.excalibur.domain.StandardTime;
import com.rcl.excalibur.domain.Time;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link DiscoverItemEntity} (in the data layer) to {@link DiscoverItem} in the
 * domain layer.
 */
@Singleton
public class DiscoverEntityDataMapper extends BaseEntityDataMapper<DiscoverItem, DiscoverItemEntity> {

    @Inject
    DiscoverEntityDataMapper() {
    }

    @Override
    public DiscoverItem transform(DiscoverItemEntity entity) {
        DiscoverItem discoverItem = null;
        if (entity != null) {
            discoverItem = new DiscoverItem();
            discoverItem.setImageUrl(entity.getImageUrl());
            discoverItem.setCategory(entity.getCategory());
            discoverItem.setHours(entity.getHours());
            discoverItem.setTitle(entity.getTitle());
            discoverItem.setType(entity.getType());
            discoverItem.setSubTitle(entity.getSubTitle());
            discoverItem.setReservationRequired(entity.getReservationRequired());
            discoverItem.setDescription(entity.getDescription());
            discoverItem.setPromotionTitle(entity.getPromotionTitle());
            discoverItem.setPromotionDescription(entity.getPromotionDescription());
            discoverItem.setLegal(entity.getLegal());
            discoverItem.setStandardTime(create(entity.getStandardTimeEntity()));
            discoverItem.setAccessibilities(createAccessibilities(entity.getAccessibilities()));
            discoverItem.setPrices(createPrices(entity.getPrices()));
            discoverItem.setProperties(createProperties(entity.getProperties()));
        }
        return discoverItem;
    }

    private List<Price> createPrices(List<PriceEntity> entities) {
        final List<Price> prices = new ArrayList<>();
        if (entities == null || entities.isEmpty()) {
            return prices;
        }
        for (PriceEntity entity : entities) {
            final Price price = new Price();
            price.setTitle(entity.getTitle());
            price.setValue(entity.getValue());
            prices.add(price);
        }
        return prices;
    }

    private List<Property> createProperties(List<PropertyEntity> entities) {
        final List<Property> properties = new ArrayList<>();
        if (entities == null || entities.isEmpty()) {
            return properties;
        }
        for (PropertyEntity entity : entities) {
            final Property property = new Property();
            property.setKey(entity.getKey());
            property.setValue(entity.getValue());
            properties.add(property);
        }
        return properties;
    }


    private List<Accessibility> createAccessibilities(List<AccessibilityEntity> entities) {
        final List<Accessibility> accessibilities = new ArrayList<>();
        if (entities == null || entities.isEmpty()) {
            return accessibilities;
        }
        for (AccessibilityEntity accessibilityEntity : entities) {
            final Accessibility accessibility = new Accessibility();
            accessibility.setText(accessibilityEntity.getText());
            accessibilities.add(accessibility);
        }
        return accessibilities;
    }

    private StandardTime create(StandardTimeEntity entity) {
        if (entity == null) {
            return null;
        }
        final StandardTime standardTime = new StandardTime();
        standardTime.setTitle(entity.getTitle());
        final List<Time> times = new ArrayList<>();
        for (TimeEntity timeEntity : entity.getTimes()) {
            Time time = new Time();
            time.setDay(timeEntity.getDay());
            time.setTime(timeEntity.getTime());
            times.add(time);
        }
        standardTime.setTimes(times);
        return standardTime;
    }
}
