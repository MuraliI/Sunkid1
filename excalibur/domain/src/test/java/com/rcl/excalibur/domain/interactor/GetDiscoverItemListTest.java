package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.DiscoverRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GetDiscoverItemListTest {
    @Mock DiscoverRepository discoverRepository;
    @Mock ThreadExecutor threadExecutor;
    @Mock PostExecutionThread postExecutionThread;
    GetDiscoverList useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetDiscoverList(discoverRepository, threadExecutor, postExecutionThread);

    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        GetDiscoverList.Params params = GetDiscoverList.Params.create("type");
        useCase.buildUseCaseObservable(params);
        verify(discoverRepository).listBy("type");
    }


}