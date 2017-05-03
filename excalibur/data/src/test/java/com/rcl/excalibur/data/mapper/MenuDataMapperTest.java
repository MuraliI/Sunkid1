package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.mockobjects.MockMediaitemResponse;
import com.rcl.excalibur.data.service.response.MediaResponse;
import com.rcl.excalibur.data.service.response.MenuAdvisoryTagResponse;
import com.rcl.excalibur.data.service.response.MenuItemAdvisoryTagResponse;
import com.rcl.excalibur.data.service.response.MenuItemResponse;
import com.rcl.excalibur.data.service.response.MenuResponse;
import com.rcl.excalibur.data.service.response.MenuSectionResponse;
import com.rcl.excalibur.domain.Menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuDataMapperTest {

    MenuDataMapper menuDataMapper;
    MediaItemDataMapper mediaItemDataMapper;
    MenuResponse menuResponse;
    List<MenuAdvisoryTagResponse> menuAdvisoryTagResponseList;
    MenuAdvisoryTagResponse menuAdvisoryTagResponse1, menuAdvisoryTagResponse2;
    List<MenuSectionResponse> menuSectionResponseList;
    MenuSectionResponse menuSectionResponse1, menuSectionResponse2;
    MediaResponse menuMediaResponse;
    MediaResponse sectionMediaResponse;

    List<MenuItemAdvisoryTagResponse> menuItemAdvisoryTagResponseList;
    MenuItemAdvisoryTagResponse menuItemAdvisoryTagResponse1;
    MediaResponse menuItemMediaResponse;
    List<MenuItemResponse> menuItemResponses;
    MenuItemResponse menuItemResponse;


    @Before
    public void setUp() throws Exception {
        mediaItemDataMapper =new MediaItemDataMapper();
        menuDataMapper = new MenuDataMapper(mediaItemDataMapper);
        menuResponse = new MenuResponse();
        menuAdvisoryTagResponseList = new ArrayList<>();
        menuAdvisoryTagResponse1 = new MenuAdvisoryTagResponse();
        menuAdvisoryTagResponse2 = new MenuAdvisoryTagResponse();

        menuSectionResponseList = new ArrayList<>();
        menuSectionResponse1 = new MenuSectionResponse();
        menuSectionResponse2 = new MenuSectionResponse();

        sectionMediaResponse = new MediaResponse();
        menuItemAdvisoryTagResponseList =new ArrayList<>();
        menuItemAdvisoryTagResponse1 = new MenuItemAdvisoryTagResponse();
        menuItemResponse = new MenuItemResponse();
        menuItemMediaResponse= new MediaResponse();

        menuMediaResponse = new MediaResponse();



        menuResponse.setDayNumber("1");
        menuResponse.setMenuName("Lunch");
        menuAdvisoryTagResponse1.setTitle("Lunch");
        menuAdvisoryTagResponse2.setTitle("Dinner");
        menuAdvisoryTagResponseList.add(menuAdvisoryTagResponse1);
        menuAdvisoryTagResponseList.add(menuAdvisoryTagResponse2);
        menuResponse.setMenuAdvisoryTag(menuAdvisoryTagResponseList);

        menuSectionResponse1.setSectionName("APPETIZERS");
        menuSectionResponse1.setSectionDescription("APPETIZERS");
        sectionMediaResponse.setMediaItem(MockMediaitemResponse.getMockMockMediaitemResponse());
        menuSectionResponse1.setSectionMedia(sectionMediaResponse);
        menuItemResponse.setMenuItemTitle("WILD MUSHROOM SOUP");
        menuItemResponse.setMenuItemDescription("Scented with white truffle oil, chives");

        menuItemAdvisoryTagResponse1.setTitle("WILD MUSHROOM SOUP");
        menuItemAdvisoryTagResponseList.add(menuItemAdvisoryTagResponse1);
        menuItemResponse.setMenuItemAdvisoryTags(menuItemAdvisoryTagResponseList);
        menuItemMediaResponse.setMediaItem(MockMediaitemResponse.getMockMockMediaitemResponse());
        menuItemResponse.setMenuItemMedia(menuItemMediaResponse);
        menuItemResponses = new ArrayList<>();
        menuItemResponses.add(menuItemResponse);
        menuSectionResponse1.setMenuItem(menuItemResponses);

        menuSectionResponseList.add(menuSectionResponse1);

        menuSectionResponse2.setSectionName("ENTRÉES");
        menuSectionResponse2.setSectionDescription("ENTRÉES");
        menuSectionResponse2.setSectionMedia(sectionMediaResponse);
        menuSectionResponse2.setMenuItem(menuItemResponses);
        menuSectionResponseList.add(menuSectionResponse2);
        menuResponse.setMenuSections(menuSectionResponseList);

        menuMediaResponse.setMediaItem(MockMediaitemResponse.getMockMockMediaitemResponse());

        menuResponse.setMenuMedia(menuMediaResponse);


    }

    @Test
    public void transform() throws Exception {
        Menu menu = menuDataMapper.transform(menuResponse, null);
        assertNotNull(menu);
        assertEquals(menuResponse.getDayNumber(), menu.getDayNumber());
        assertEquals(menuResponse.getMenuName(), menu.getMenuName());
        assertNotNull(menu.getMenuAdvisoryTag());
        assertEquals(menuResponse.getMenuAdvisoryTag().size(), menu.getMenuAdvisoryTag().size());
        for (int i = 0; i < menu.getMenuAdvisoryTag().size(); i++) {
            assertEquals(menu.getMenuAdvisoryTag().get(i).getTitle(), menu.getMenuAdvisoryTag().get(i).getTitle());

        }
        assertNotNull(menu.getMenuSections());
        assertEquals(menuResponse.getMenuSections().size(), menu.getMenuSections().size());
        for (int i = 0; i < menu.getMenuSections().size(); i++) {
            assertEquals(menu.getMenuSections().get(i).getSectionName(), menu.getMenuSections().get(i).getSectionName());
            assertEquals(menu.getMenuSections().get(i).getSectiondescription(), menu.getMenuSections().get(i).getSectiondescription());
            assertNotNull(menu.getMenuSections().get(i).getMenuItem());
            assertEquals(menu.getMenuSections().get(i).getMenuItem().size(), menu.getMenuSections().get(i).getMenuItem().size());


        }
        assertNotNull(menu.getMenuMedia());
        assertEquals(menuResponse.getMenuMedia().getMediaItem().size(), menu.getMenuSections().size());
        for (int i = 0; i < menu.getMenuMedia().getMediaItem().size(); i++) {
            assertEquals(menu.getMenuMedia().getMediaItem().get(i).getMediaRefLink(), menu.getMenuMedia().getMediaItem().get(i).getMediaRefLink());
            assertEquals(menu.getMenuMedia().getMediaItem().get(i).getMediaType(), menu.getMenuMedia().getMediaItem().get(i).getMediaType());

        }

    }

    @After
    public void tearDown() throws Exception {
        menuResponse = null;
        menuDataMapper = null;
        menuAdvisoryTagResponseList = null;
        menuAdvisoryTagResponse1 = null;
        menuAdvisoryTagResponse2 = null;
        menuSectionResponseList = null;
        menuSectionResponse1 = null;
        menuSectionResponse2 = null;
    }
}