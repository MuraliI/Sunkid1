package com.rcl.excalibur.domain.interactor;


import android.content.Context;
import android.content.Loader;
import android.database.Cursor;

import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;

import java.util.List;

import javax.inject.Inject;

public class DiscoverItemDbUseCase extends UseCaseSync {

    private final DiscoverItemRepository discoverItemRepository;

    @Inject
    DiscoverItemDbUseCase(DiscoverItemRepository discoverItemRepository) {
        this.discoverItemRepository = discoverItemRepository;
    }

    public void create() {
        //   TODO only for example     discoverItemRepository.create();
    }

    public void update() {
        //   TODO only for example     discoverItemRepository.update();
    }

    public DiscoverItem getDiscoverItem(final int id) {
        return discoverItemRepository.get(id);
    }

    public List<DiscoverItem> getAllDiscoverItem(final String type) {
        return discoverItemRepository.listAll(type);
    }

    public Loader<Cursor> getAllDiscoverItemLoader(Context context, final String type) {
        return null;
    }

    public void create(List<DiscoverItem> discoverItems) {
        discoverItemRepository.create(discoverItems);
    }
}
