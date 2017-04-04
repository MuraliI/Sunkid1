package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.utils.ConstantsUtil;

import java.util.List;


public class ProductInformationMapper extends BaseModelDataMapper<Product, ProductInformationViewType> {
    @NonNull
    @Override
    public ProductInformationViewType transform(Product item) {
        Preconditions.notNull(item);
        ProductInformationViewType productBasicInformation = new ProductInformationViewType();
        productBasicInformation.setProductId(String.valueOf(item.getProductId()));
        productBasicInformation.setProductName(item.getProductTitle());
        productBasicInformation.setVenue(item.getProductLocation().getLocationVenue());
        productBasicInformation.setReservationRequired(item.isReservationRequired());
        productBasicInformation.setLocation(
                String.valueOf(item.getProductLocation().getLocationDeckNumber())
                        + ConstantsUtil.WHITE_SPACE + item.getProductLocation().getLocationDirection());
        productBasicInformation.setPort(item.getProductLocation().getLocationPort());
        productBasicInformation.setUpChargeLevel(item.getProductUpcharge());
        productBasicInformation.setProductMedia(
                extractProductMedia(item.getProductMedia() != null ? item.getProductMedia().getMediaItem()
                        : null));

        return productBasicInformation;
    }

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
