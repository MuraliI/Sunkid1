package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;

import java.util.List;

import javax.inject.Inject;

public class GetDiscoverItemDbUseCase extends UseCaseSync {

    private final DiscoverItemRepository discoverItemRepository;

    @Inject
    GetDiscoverItemDbUseCase(DiscoverItemRepository discoverItemRepository) {
        this.discoverItemRepository = discoverItemRepository;
    }

    public List<DiscoverItem> getAllDiscoverItem(String type) {
        return discoverItemRepository.listAll(type);
    }
}
