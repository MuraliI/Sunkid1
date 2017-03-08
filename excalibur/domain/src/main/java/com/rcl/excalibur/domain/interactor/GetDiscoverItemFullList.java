package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link DiscoverItem}.
 */
public class GetDiscoverItemFullList extends UseCase<DiscoverItem, GetDiscoverItemFullList.Params> {

    private final DiscoverItemRepository discoverItemRepository;

    @Inject
    GetDiscoverItemFullList(DiscoverItemRepository discoverItemRepository, ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.discoverItemRepository = discoverItemRepository;
    }

    @Override
    Observable<DiscoverItem> buildUseCaseObservable(Params params) {
        return this.discoverItemRepository.getFullBy(params.discoverItemId);
    }

    public static final class Params {

        private final String discoverItemId;

        private Params(String discoverItemId) {
            this.discoverItemId = discoverItemId;
        }

        public static Params create(String discoverItemId) {
            return new Params(discoverItemId);
        }
    }
}
