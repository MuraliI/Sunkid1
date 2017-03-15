package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GetDiscoverItemListTest {
    @Mock DiscoverItemRepository discoverItemRepository;
    @Mock ThreadExecutor threadExecutor;
    @Mock PostExecutionThread postExecutionThread;
    GetDiscoverItemBasicList useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetDiscoverItemBasicList(discoverItemRepository, threadExecutor, postExecutionThread);

    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        GetDiscoverItemBasicList.Params params = GetDiscoverItemBasicList.Params.create("type");
        useCase.buildUseCaseObservable(params);
        verify(discoverItemRepository).listAll("type");
    }


}