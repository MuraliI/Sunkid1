package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductCategory;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.List;

public class ProductDataMapper extends BaseModelDataMapper<Product, DiscoverItemModel> {

    @NonNull
    @Override
    public DiscoverItemModel transform(Product item) {
        DiscoverItemModel discoverItemModel = new DiscoverItemModel();
        discoverItemModel.setDiscoverId(item.getProductId());
        discoverItemModel.setImageUrl(item.getProductMedia().getMediaItem().get(0).getMediaRefLink());

        List<ProductCategory> productCategory = item.getProductCategory();
        if (!productCategory.isEmpty()) {
            discoverItemModel.setCategory(productCategory.get(0).getCategoryDescription());
        }
        discoverItemModel.setTitle(item.getProductTitle());
        discoverItemModel.setDescription(item.getProductShortDescription());
        discoverItemModel.setType(item.getProductType().getProductTypeName());

        return discoverItemModel;
    }
}
