package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.MenuAdvisoryTagEntity;
import com.rcl.excalibur.data.entity.MenuEntity;
import com.rcl.excalibur.domain.Menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class MenuEntityDataMapperTest {
    MenuEntityDataMapper menuEntityDataMapper;
    MediaEntityDataMapper mediaEntityDataMapper;
    @Mock MenuEntity menuEntity;
    @Mock MenuAdvisoryTagEntity menuAdvisoryTagEntity1;
    @Mock MenuAdvisoryTagEntity menuAdvisoryTagEntity2;


    @Before
    public void setUp() throws Exception {
        mediaEntityDataMapper = new MediaEntityDataMapper();
        menuEntityDataMapper = new MenuEntityDataMapper(mediaEntityDataMapper);
        MockitoAnnotations.initMocks(this);
        when(menuEntity.getDayNumber()).thenReturn("1");
        when(menuEntity.getMenuName()).thenReturn("Lunch");
        when(menuAdvisoryTagEntity1.getTitle()).thenReturn("Lunch");
        when(menuAdvisoryTagEntity2.getTitle()).thenReturn("Dinner");
        List<MenuAdvisoryTagEntity> menuAdvisoryTagEntityList = new ArrayList<>();
        menuAdvisoryTagEntityList.add(menuAdvisoryTagEntity1);
        menuAdvisoryTagEntityList.add(menuAdvisoryTagEntity2);
        when(menuEntity.getMenuAdvisoryTag()).thenReturn(menuAdvisoryTagEntityList);


    }

    @After
    public void tearDown() throws Exception {
        Menu menu = menuEntityDataMapper.transform(menuEntity, null);
        assertNotNull(menu);
        assertEquals(menuEntity.getDayNumber(), menu.getDayNumber());
        assertEquals(menuEntity.getMenuName(), menu.getMenuName());
        assertNotNull(menu.getMenuAdvisoryTag());
        assertEquals(menuEntity.getMenuAdvisoryTag().size(), menu.getMenuAdvisoryTag().size());
        for (int i = 0; i < menu.getMenuAdvisoryTag().size(); i++) {
            assertEquals(menu.getMenuAdvisoryTag().get(i).getTitle(), menu.getMenuAdvisoryTag().get(i).getTitle());

        }
    }

    @Test
    public void transform() throws Exception {

    }

}