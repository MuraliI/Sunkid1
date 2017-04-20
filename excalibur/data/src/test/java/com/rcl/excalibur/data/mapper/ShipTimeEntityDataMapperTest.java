package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.ShipTimeEntity;
import com.rcl.excalibur.domain.ShipTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class ShipTimeEntityDataMapperTest {
    ShipTimeEntityDataMapper shipTimeEntityDataMapper;
    @Mock ShipTimeEntity entity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(entity.getOffset()).thenReturn("-6");
        shipTimeEntityDataMapper = new ShipTimeEntityDataMapper();
    }

    @Test
    public void transform() throws Exception {
        ShipTime shipTime = shipTimeEntityDataMapper.transform(entity, null);
        Assert.assertNotNull(shipTime);
        Assert.assertEquals(entity.getOffset(), shipTime.getOffset());
    }

    @Test
    public void transformList() throws Exception {
        List<ShipTimeEntity> entities = new ArrayList<>();
        entities.add(entity);
        List<ShipTime> shipTimes = shipTimeEntityDataMapper.transform(entities, null);
        Assert.assertNotNull(shipTimes);
        Assert.assertFalse(shipTimes.isEmpty());
        for (int i = 0; i < shipTimes.size(); i++) {
            Assert.assertEquals(entities.get(i).getOffset(), shipTimes.get(i).getOffset());
        }
    }

}