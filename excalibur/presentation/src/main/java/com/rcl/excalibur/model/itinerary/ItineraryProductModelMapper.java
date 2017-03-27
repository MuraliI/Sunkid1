package com.rcl.excalibur.model.itinerary;

import android.content.res.Resources;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.utils.DateUtils;

import java.util.Calendar;


public class ItineraryProductModelMapper extends BaseDataMapper<ItineraryProductModel, ItineraryEvent> {

    private Resources resources;

    public ItineraryProductModelMapper(Resources resources) {
        this.resources = resources;
    }

    @Override
    public ItineraryProductModel transform(ItineraryEvent itineraryEvent) {
        ItineraryProductModel productModel = new ItineraryProductModel();
        productModel.setProductId(itineraryEvent.getId());
        productModel.setName(itineraryEvent.getName());

        StringBuilder builder = new StringBuilder();
        builder.append(DateUtils.getDateTime(itineraryEvent.getStartDate(), resources))
                .append(resources.getString(R.string.itinerary_product_view_dash))
                .append(DateUtils.getDateTime(itineraryEvent.getEndDate(), resources));


        Calendar startDate = Calendar.getInstance();
        startDate.setTime(itineraryEvent.getStartDate());
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(itineraryEvent.getEndDate());

        productModel.setStartDate(startDate);
        productModel.setEndDate(endDate);
        productModel.setOperatinghours(builder.toString());
        productModel.setLocationPointer(itineraryEvent.getLocation());
        productModel.setImageUrl(itineraryEvent.getThumbnail());
        //TODO Map other fields remaining on the ItineraryProductModel
        return productModel;
    }
}
