package com.rcl.excalibur.mapper;


import com.rcl.excalibur.model.PlannerProductModel;

public class PlannerProductMapper extends BaseProductInformationMapper<PlannerProductModel> {
    @Override
    protected PlannerProductModel getProductInformationModel() {
        return new PlannerProductModel();
    }
}
