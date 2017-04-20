package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.SailDateInfoEntity;
import com.rcl.excalibur.domain.SailDateInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SaildDateInfoEntityDataMapperTest {

    private SaildDateInfoEntityDataMapper sailDateInfoDataMapper;
    @Mock SailDateInfoEntity sailDateInfoEntity;

    @Before
    public void setUp() throws Exception {
        sailDateInfoDataMapper = new SaildDateInfoEntityDataMapper();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void transform() throws Exception {

        SailDateInfo sailDateInfoEvent = sailDateInfoDataMapper.transform(sailDateInfoEntity, null);
        assertNotNull(sailDateInfoEvent);
        assertEquals(sailDateInfoEvent.getDuration(), sailDateInfoEntity.getDuration());
        assertEquals(sailDateInfoEvent.getShipCode(), sailDateInfoEntity.getShipCode());

    }

}