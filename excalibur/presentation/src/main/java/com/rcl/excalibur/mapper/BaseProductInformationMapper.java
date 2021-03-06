package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.utils.LocationUtils;

import java.util.List;


abstract class BaseProductInformationMapper<T extends ProductInformationViewType> extends BaseModelDataMapper<Product, T> {
    @NonNull
    @Override
    public T transform(Product item) {
        Preconditions.notNull(item);
        T productBasicInformation = getProductInformationModel();
        productBasicInformation.setProductId(item.getProductId());
        productBasicInformation.setProductName(item.getProductTitle());
        productBasicInformation.setProductType(item.getProductType() != null ? item.getProductType().getProductType() : null);
        productBasicInformation.setReservationRequired(item.isReservationRequired());
        if (item.getProductLocation() != null) {
            ProductLocation location = item.getProductLocation();
            productBasicInformation.setVenue(location.getLocationTitle());
            productBasicInformation.setLocation(LocationUtils.getProductLocation(location));
        }
        productBasicInformation.setUpChargeLevel(item.getProductUpcharge());
        productBasicInformation.setProductMedia(
                extractProductMedia(item.getProductMedia() != null ? item.getProductMedia().getMediaItem()
                        : null));

        return productBasicInformation;
    }

    protected abstract T getProductInformationModel();

    private String[] extractProductMedia(List<MediaItem> mediaList) {
        if (mediaList != null) {
            String[] medias = new String[mediaList.size()];

            for (int i = 0; i < mediaList.size(); i++) {
                medias[i] = mediaList.get(i).getMediaRefLink();
            }

            return medias;
        }

        return new String[0];
    }
}
