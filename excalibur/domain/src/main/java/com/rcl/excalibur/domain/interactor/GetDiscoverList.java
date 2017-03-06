package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.DiscoverRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link DiscoverItem}.
 */
public class GetDiscoverList extends UseCase<List<DiscoverItem>, GetDiscoverList.Params> {

    private final DiscoverRepository discoverRepository;

    @Inject
    GetDiscoverList(DiscoverRepository discoverRepository, ThreadExecutor threadExecutor,
                    PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.discoverRepository = discoverRepository;
    }

    @Override
    Observable<List<DiscoverItem>> buildUseCaseObservable(Params params) {
        return this.discoverRepository.listBy(params.type);
    }

    public static final class Params {

        private final String type;

        private Params(String type) {
            this.type = type;
        }

        public static Params create(String type) {
            return new Params(type);
        }
    }
}
