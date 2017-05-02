package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.MediaEntity;
import com.rcl.excalibur.data.entity.MenuAdvisoryTagEntity;
import com.rcl.excalibur.data.entity.MenuEntity;
import com.rcl.excalibur.data.entity.MenuItemAdvisoryTagEntity;
import com.rcl.excalibur.data.entity.MenuItemEntity;
import com.rcl.excalibur.data.entity.MenuSectionEntity;
import com.rcl.excalibur.domain.Media;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.MenuAdvisoryTag;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.domain.MenuItemAdvisoryTag;
import com.rcl.excalibur.domain.MenuSection;

import java.util.ArrayList;
import java.util.List;

public class MenuEntityDataMapper extends BaseDataMapper<Menu, MenuEntity, Void> {

    MediaEntityDataMapper mediaItemDataMapper;

    public MenuEntityDataMapper(MediaEntityDataMapper mediaItemDataMapper) {
        this.mediaItemDataMapper = mediaItemDataMapper;
    }

    @Nullable
    @Override
    public Menu transform(MenuEntity input, Void additionalArg) {
        Menu output = new Menu();
        if (input == null) {
            return output;
        }
        output.setDayNumber(input.getDayNumber());
        output.setMenuName(input.getMenuName());
        output.setMenuAdvisoryTag(transformMenuAdvisoryTag(input.getMenuAdvisoryTag()));
        output.setMenuSections(transformMenuSection(input.getMenuSection()));
        output.setMenuMedia(transform(input.getMenuMedia()));
        return output;
    }

    private MenuAdvisoryTag transform(MenuAdvisoryTagEntity input) {
        MenuAdvisoryTag output = new MenuAdvisoryTag();
        if (input == null) {
            return output;
        }
        output.setTitle(input.getTitle());
        return output;
    }

    private MenuSection transform(MenuSectionEntity input) {
        MenuSection output = new MenuSection();
        if (input == null) {
            return output;
        }
        output.setSectionName(input.getSectionName());
        output.setSectiondescription(input.getSectionDescription());
        output.setSectionMedia(transform(input.getSectionMediaEntity()));
        output.setMenuItem(transformMenuItem(input.getMenuItemEntity()));
        return output;
    }

    private Media transform(MediaEntity entity) {
        Media output = new Media();
        if (entity == null) {
            return output;
        }
        output.setMediaItem(mediaItemDataMapper.transform(entity.getValues(), null));
        return output;

    }

    public MenuItem transform(MenuItemEntity entity) {
        MenuItem output = new MenuItem();
        if (entity == null) {
            return output;
        }
        output.setMenuItemTitle(entity.getMenuItemTitle());
        output.setMenuItemDescription(entity.getMenuItemDescription());
        output.setMenuItemAdvisoryTags(transformMenuItemAdvisoryTag(entity.getMenuItemAdvisoryTags()));
        output.setMenuItemMedia(transform(entity.getMediaEntity()));
        return output;

    }

    private MenuItemAdvisoryTag transform(MenuItemAdvisoryTagEntity input) {
        MenuItemAdvisoryTag output = new MenuItemAdvisoryTag();
        if (input == null) {
            return output;
        }
        output.setTitle(input.getTitle());
        return output;
    }

    private List<MenuItemAdvisoryTag> transformMenuItemAdvisoryTag(List<MenuItemAdvisoryTagEntity> entity) {

        ArrayList<MenuItemAdvisoryTag> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        for (MenuItemAdvisoryTagEntity menuItemAdvisoryTagEntity : entity) {
            if (entity == null) {
                continue;
            }

            items.add(transform(menuItemAdvisoryTagEntity));

        }

        return items;
    }

    private List<MenuAdvisoryTag> transformMenuAdvisoryTag(List<MenuAdvisoryTagEntity> entity) {

        ArrayList<MenuAdvisoryTag> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        for (MenuAdvisoryTagEntity menuAdvisoryTagEntity : entity) {
            if (entity == null) {
                continue;
            }

            items.add(transform(menuAdvisoryTagEntity));

        }

        return items;
    }

    private List<MenuSection> transformMenuSection(List<MenuSectionEntity> entity) {

        ArrayList<MenuSection> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        for (MenuSectionEntity menuSectionEntity : entity) {
            if (entity == null) {
                continue;
            }

            items.add(transform(menuSectionEntity));
        }

        return items;
    }

    private List<MenuItem> transformMenuItem(List<MenuItemEntity> entity) {

        ArrayList<MenuItem> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        for (MenuItemEntity menuSectionEntity : entity) {
            if (entity == null) {
                continue;
            }

            items.add(transform(menuSectionEntity));
        }

        return items;
    }
}
