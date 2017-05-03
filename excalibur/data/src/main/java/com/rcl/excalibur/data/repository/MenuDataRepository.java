package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.MediaEntity;
import com.rcl.excalibur.data.entity.MediaValueEntity;
import com.rcl.excalibur.data.entity.MenuAdvisoryTagEntity;
import com.rcl.excalibur.data.entity.MenuEntity;
import com.rcl.excalibur.data.entity.MenuItemAdvisoryTagEntity;
import com.rcl.excalibur.data.entity.MenuItemEntity;
import com.rcl.excalibur.data.entity.MenuSectionEntity;
import com.rcl.excalibur.data.mapper.MediaEntityDataMapper;
import com.rcl.excalibur.data.mapper.MenuEntityDataMapper;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.data.utils.DBUtil;
import com.rcl.excalibur.domain.Media;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.MenuAdvisoryTag;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.domain.MenuItemAdvisoryTag;
import com.rcl.excalibur.domain.MenuSection;
import com.rcl.excalibur.domain.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

public class MenuDataRepository extends BaseDataRepository<Menu, MenuEntity, Void, MenuEntityDataMapper>
        implements MenuRepository {

    public MenuDataRepository() {
        super(new MenuEntityDataMapper(new MediaEntityDataMapper()), MenuEntity.class);

    }

    @Override
    public void create(@NonNull Menu input) {
        final MenuEntity entity = new MenuEntity();
        entity.setDayNumber(input.getDayNumber());
        entity.setMenuName(input.getMenuName());
        entity.setMenuMedia(create(input.getMenuMedia()));
        entity.save();
        //MenuAdvisoryTag
        createMenuAdvisoryTag(entity, input.getMenuAdvisoryTag());
        //MenuSections
        createMenuSections(entity, input.getMenuSections());

    }

    private void createMenuAdvisoryTag(final MenuEntity entity, final List<MenuAdvisoryTag> menuAdvisoryTags) {
        if (CollectionUtils.isEmpty(menuAdvisoryTags)) {
            return;
        }
        for (MenuAdvisoryTag menuAdvisoryTag : menuAdvisoryTags) {
            final MenuAdvisoryTagEntity menuAdvisoryTagEntity = new MenuAdvisoryTagEntity();
            menuAdvisoryTagEntity.setTitle(menuAdvisoryTag.getTitle());
            menuAdvisoryTagEntity.setMenuEntity(entity);
            menuAdvisoryTagEntity.save();
        }

    }

    private void createMenuSections(final MenuEntity entity, final List<MenuSection> menuSectionList) {
        if (CollectionUtils.isEmpty(menuSectionList)) {
            return;
        }
        for (MenuSection menuSection : menuSectionList) {
            final MenuSectionEntity menuSectionEntity = new MenuSectionEntity();
            menuSectionEntity.setSectionName(menuSection.getSectionName());
            menuSectionEntity.setSectionDescription(menuSection.getSectiondescription());
            menuSectionEntity.setMenuEntity(entity);
            menuSectionEntity.save();
            createMenuItemEntity(menuSectionEntity, menuSection.getMenuItem());
        }

    }

    private void createMenuItemEntity(final MenuSectionEntity entity, final List<MenuItem> menuItems) {
        if (CollectionUtils.isEmpty(menuItems)) {
            return;
        }
        for (MenuItem menuItem : menuItems) {
            final MenuItemEntity menuItemEntity = new MenuItemEntity();
            menuItemEntity.setMenuItemTitle(menuItem.getMenuItemTitle());
            menuItemEntity.setMenuItemDescription(menuItem.getMenuItemDescription());
            menuItemEntity.setMenuSectionEntity(entity);
            menuItemEntity.save();
            createMenuItemAdvisoryTag(menuItemEntity, menuItem.getMenuItemAdvisoryTags());
        }

    }

    private void createMenuItemAdvisoryTag(final MenuItemEntity entity, final List<MenuItemAdvisoryTag> menuItemAdvisoryTags) {
        if (CollectionUtils.isEmpty(menuItemAdvisoryTags)) {
            return;
        }
        for (MenuItemAdvisoryTag menuItemAdvisoryTag : menuItemAdvisoryTags) {
            final MenuItemAdvisoryTagEntity menuItemAdvisoryTagEntity = new MenuItemAdvisoryTagEntity();
            menuItemAdvisoryTagEntity.setTitle(menuItemAdvisoryTag.getTitle());
            menuItemAdvisoryTagEntity.setMenuItemEntity(entity);
            menuItemAdvisoryTagEntity.save();
        }

    }
    private MediaEntity create(final Media media) {

        if (media == null || CollectionUtils.isEmpty(media.getMediaItem())) {
            return null;
        }
        final MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.save();
        for (MediaItem mediaItem : media.getMediaItem()) {
            final MediaValueEntity mediaValueEntity = new MediaValueEntity();
            mediaValueEntity.setType(mediaItem.getMediaType());
            mediaValueEntity.setLink(mediaItem.getMediaRefLink());
            mediaValueEntity.setMedia(mediaEntity);
            mediaValueEntity.save();
        }
        return mediaEntity;

    }
    @Override
    public List<Menu> getAll() {
        final MenuEntity entity = new Select()
                .all()
                .from(MenuEntity.class)
                .executeSingle();
        if (entity == null) {
            return new ArrayList<>();
        }
        final List<MenuEntity> entities = new Select()
                .from(MenuEntity.class)
                .execute();
        return getMapper().transform(entities, null);
    }

    @Override
    public List<MenuItem> getAllMenuItemByMenuName(String menuName) {

        List<MenuItem> menuItems = new ArrayList<>();
        final List<MenuEntity> entities = new Select()
                .from(MenuEntity.class)
                .where(eq(MenuEntity.COLUMN_MENU_NAME, menuName))
                .execute();
        List<Menu> menu = getMapper().transform(entities, null);
        for (Menu menuEntity : menu) {
            if (menuEntity != null) {
                for (MenuSection menuSection : menuEntity.getMenuSections()) {
                    if (menuSection != null) {
                        for (MenuItem menuItemEntity : menuSection.getMenuItem()) {
                            if (menuItemEntity != null) {
                                menuItems.add(menuItemEntity);
                            }
                        }
                    }
                }
            }
        }

        return menuItems;
    }

    @Override
    public List<String> getAllMenuName() {
        List<String> menuNames = new ArrayList<>();
        for (Menu menu : getAll()) {
            menuNames.add(menu.getMenuName());
        }
        return menuNames;
    }

    @Override
    public void deleteAll() {
        final List<MenuEntity> entities = new Select()
                .from(MenuEntity.class)
                .execute();
        new Delete().from(MenuEntity.class).execute();
        for (MenuEntity menuEntity : entities) {
            new Delete().from(MediaEntity.class).where(DBUtil.eqId(menuEntity.getMenuMedia().getId())).execute();
        }
    }
}
