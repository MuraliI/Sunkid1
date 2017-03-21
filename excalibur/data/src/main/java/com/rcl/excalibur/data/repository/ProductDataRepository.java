package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.entity.TypeEntity;
import com.rcl.excalibur.data.mapper.ProductEntityDataMapper;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

@Singleton
public class ProductDataRepository extends BaseDataRepository<Product, ProductEntity> implements ProductRepository {

    private final ProductEntityDataMapper productEntityDataMapper;

    @Inject
    ProductDataRepository(ProductEntityDataMapper productEntityDataMapper) {
        super(productEntityDataMapper, ProductEntity.class);
        this.productEntityDataMapper = productEntityDataMapper;
    }

    @Override
    public void create(Product product) {
        final ProductEntity entity = new ProductEntity();
// TODO waiting
//       entity.setCategoryId(category.getCategoryId());
//        entity.setDescription(category.getDescription());
//        entity.setTags(category.getTags());
        entity.save();
    }

    @Override
    public Product get(long id) {
        return get(ProductEntity.COLUMN_PRODUCT_ID, id);
    }


    public List<Product> getAll(@NonNull final String type) {
        final TypeEntity typeEntity = new Select()
                .from(TypeEntity.class)
                .where(eq(ProductEntity.COLUMN_TYPE, type))
                .executeSingle();
        if (typeEntity == null) {
            return null;
        }
        final List<ProductEntity> entities = new Select()
                .from(ProductEntity.class)
                .where(eq(ProductEntity.COLUMN_TYPE, type))
                .execute();
        return productEntityDataMapper.transform(entities);
    }
}
