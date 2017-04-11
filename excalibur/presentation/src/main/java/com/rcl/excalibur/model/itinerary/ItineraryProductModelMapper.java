package com.rcl.excalibur.model.itinerary;

import android.content.res.Resources;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.utils.DateUtils;

import java.util.Calendar;
import java.util.Random;


public class ItineraryProductModelMapper extends BaseDataMapper<ItineraryProductModel, ItineraryEvent> {

    private static final String SPACE_STRING = " ";
    private Resources resources;

    public ItineraryProductModelMapper(Resources resources) {
        this.resources = resources;
    }

    @Override
    public ItineraryProductModel transform(ItineraryEvent itineraryEvent) {
        ItineraryProductModel productModel = new ItineraryProductModel();
        productModel.setProductId(itineraryEvent.getId());
        productModel.setName(itineraryEvent.getName());

        String dateBuilder = DateUtils.getDateTime(itineraryEvent.getStartDate(), resources)
                + SPACE_STRING
                + resources.getString(R.string.itinerary_product_view_dash)
                + SPACE_STRING
                + DateUtils.getDateTime(itineraryEvent.getEndDate(), resources);

        // TODO: these are mock values
        String deckAndDirectionValue = "Deck 12 " + "AFT";
        int resourceIdIcon = R.drawable.ic_blue_checkbox;
        boolean isFavorite = new Random().nextBoolean();
        boolean isCostVisible = new Random().nextBoolean();
        int priceRange = new Random().nextInt(5);

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(itineraryEvent.getStartDate());
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(itineraryEvent.getEndDate());

        productModel.setResourceIdIcon(resourceIdIcon);
        productModel.setFavorite(isFavorite);
        productModel.setCostVisible(isCostVisible);
        productModel.setPriceRange(priceRange);
        productModel.setStartDate(startDate);
        productModel.setEndDate(endDate);
        productModel.setOperatingHours(dateBuilder);
        productModel.setLocationPointer(itineraryEvent.getLocation());
        productModel.setImageUrl(itineraryEvent.getThumbnail());
        productModel.setDeckAndDirection(deckAndDirectionValue);
        //TODO Map other fields remaining on the ItineraryProductModel
        return productModel;
    }
}
