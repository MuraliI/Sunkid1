package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.MenuAdvisoryTagResponse;
import com.rcl.excalibur.data.service.response.MenuItemAdvisoryTagResponse;
import com.rcl.excalibur.data.service.response.MenuItemMediaResponse;
import com.rcl.excalibur.data.service.response.MenuItemResponse;
import com.rcl.excalibur.data.service.response.MenuMediaResponse;
import com.rcl.excalibur.data.service.response.MenuResponse;
import com.rcl.excalibur.data.service.response.MenuSectionResponse;
import com.rcl.excalibur.data.service.response.SectionMediaResponse;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.MenuAdvisoryTag;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.domain.MenuItemAdvisoryTag;
import com.rcl.excalibur.domain.MenuItemMedia;
import com.rcl.excalibur.domain.MenuMedia;
import com.rcl.excalibur.domain.MenuSection;
import com.rcl.excalibur.domain.SectionMedia;

import java.util.ArrayList;
import java.util.List;

public class MenuDataMapper extends BaseDataMapper<Menu, MenuResponse, Void> {

    MediaItemDataMapper mediaItemDataMapper;

    public MenuDataMapper(MediaItemDataMapper mediaItemDataMapper) {
        this.mediaItemDataMapper = mediaItemDataMapper;
    }

    @Nullable
    @Override
    public Menu transform(MenuResponse input, Void additionalArg) {

        Menu output = new Menu();
        if (input == null) {
            return output;
        }
        output.setDayNumber(input.getDayNumber());
        output.setMenuName(input.getMenuName());
        output.setMenuAdvisoryTag(transformMenuAdvisoryTag(input.getMenuAdvisoryTag()));
        output.setMenuSections(transformMenuSection(input.getMenuSections()));
        output.setMenuMedia(transform(input.getMenuMedia()));
        return output;
    }


    private MenuAdvisoryTag transform(MenuAdvisoryTagResponse input) {
        MenuAdvisoryTag output = new MenuAdvisoryTag();
        if (input == null) {
            return output;
        }
        output.setTitle(input.getTitle());
        return output;
    }

    private MenuSection transform(MenuSectionResponse input) {
        MenuSection output = new MenuSection();
        if (input == null) {
            return output;
        }
        output.setSectionName(input.getSectionName());
        output.setSectiondescription(input.getSectionDescription());
        output.setSectionMedia(transform(input.getSectionMedia()));
        output.setMenuItem(transformMenuItem(input.getMenuItem()));
        return output;
    }

    private SectionMedia transform(SectionMediaResponse input) {
        SectionMedia output = new SectionMedia();
        if (input == null) {
            return output;
        }
        output.setMediaItem(mediaItemDataMapper.transform(input.getMediaItem(), null));
        return output;
    }

    private MenuMedia transform(MenuMediaResponse entity) {
        MenuMedia output = new MenuMedia();
        if (entity == null) {
            return output;
        }
        output.setMediaItem(new MediaItemDataMapper().transform(entity.getMediaItem(), null));
        return output;

    }

    private MenuItemMedia transform(MenuItemMediaResponse entity) {
        MenuItemMedia output = new MenuItemMedia();
        if (entity == null) {
            return output;
        }
        output.setMediaItem(mediaItemDataMapper.transform(entity.getMediaItem(), null));
        return output;

    }

    private MenuItem transform(MenuItemResponse entity) {
        MenuItem output = new MenuItem();
        if (entity == null) {
            return output;
        }
        output.setMenuItemTitle(entity.getMenuItemTitle());
        output.setMenuItemDescription(entity.getMenuItemDescription());
        output.setMenuItemAdvisoryTags(transformMenuItemAdvisoryTag(entity.getMenuItemAdvisoryTags()));
        output.setMenuItemMedia(transform(entity.getMenuItemMedia()));
        return output;

    }

    private MenuItemAdvisoryTag transform(MenuItemAdvisoryTagResponse input) {
        MenuItemAdvisoryTag output = new MenuItemAdvisoryTag();
        if (input == null) {
            return output;
        }
        output.setTitle(input.getTitle());
        return output;
    }

    private List<MenuItemAdvisoryTag> transformMenuItemAdvisoryTag(List<MenuItemAdvisoryTagResponse> entity) {

        ArrayList<MenuItemAdvisoryTag> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        for (MenuItemAdvisoryTagResponse menuItemAdvisoryTagResponse : entity) {
            if (entity == null) {
                continue;
            }

            items.add(transform(menuItemAdvisoryTagResponse));

        }

        return items;
    }

    private List<MenuAdvisoryTag> transformMenuAdvisoryTag(List<MenuAdvisoryTagResponse> entity) {

        ArrayList<MenuAdvisoryTag> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        for (MenuAdvisoryTagResponse menuAdvisoryTagResponse : entity) {
            if (entity == null) {
                continue;
            }

            items.add(transform(menuAdvisoryTagResponse));

        }

        return items;
    }

    private List<MenuSection> transformMenuSection(List<MenuSectionResponse> entity) {

        ArrayList<MenuSection> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        for (MenuSectionResponse menuSectionResponse : entity) {
            if (entity == null) {
                continue;
            }

            items.add(transform(menuSectionResponse));
        }

        return items;
    }

    private List<MenuItem> transformMenuItem(List<MenuItemResponse> entity) {

        ArrayList<MenuItem> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        for (MenuItemResponse menuSectionResponse : entity) {
            if (entity == null) {
                continue;
            }

            items.add(transform(menuSectionResponse));
        }

        return items;
    }
}
