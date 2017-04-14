package com.rcl.excalibur.mapper;

import android.content.res.Resources;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.utils.DateUtils;

import java.util.Calendar;


public class PlannerProductModelMapper extends BaseDataMapper<PlannerProductModel, ItineraryEvent> {

    private Resources resources;

    public PlannerProductModelMapper(Resources resources) {
        this.resources = resources;
    }

    @Override
    public PlannerProductModel transform(ItineraryEvent itineraryEvent) {
        PlannerProductModel productModel = new PlannerProductModel();
        productModel.setProductId(itineraryEvent.getId());
        productModel.setName(itineraryEvent.getName());

        String builder = DateUtils.getDateTime(itineraryEvent.getStartDate(), resources)
                + resources.getString(R.string.itinerary_product_view_dash)
                + DateUtils.getDateTime(itineraryEvent.getEndDate(), resources);


        Calendar startDate = Calendar.getInstance();
        startDate.setTime(itineraryEvent.getStartDate());
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(itineraryEvent.getEndDate());

        productModel.setStartDate(startDate);
        productModel.setEndDate(endDate);
        productModel.setOperatinghours(builder);
        productModel.setLocationPointer(itineraryEvent.getLocation());
        productModel.setImageUrl(itineraryEvent.getThumbnail());
        //TODO Map other fields remaining on the PlannerProductModel
        return productModel;
    }
}
