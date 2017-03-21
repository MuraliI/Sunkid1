package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.domain.Product;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ProductEntity} (in the data layer) to {@link Product} in the
 * domain layer.
 */
@Singleton
public class ProductEntityDataMapper extends BaseDataMapper<Product, ProductEntity> {

    @Inject
    ProductEntityDataMapper() {
    }

    @Override
    public Product transform(final ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        final Product product = new Product();
//   TODO waiting
//     category.setCategoryId(entity.getCategoryId());
//        category.setDescription(entity.getDescription());
//        category.setTags(Arrays.asList(entity.getTags()));
        return product;
    }
}
