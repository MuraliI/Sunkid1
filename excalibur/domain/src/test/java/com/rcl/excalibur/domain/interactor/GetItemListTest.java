package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.interactor.GetItemList;
import com.rcl.excalibur.domain.repository.ItemRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GetItemListTest {

    @Mock ItemRepository itemRepository;
    @Mock ThreadExecutor threadExecutor;
    @Mock PostExecutionThread postExecutionThread;
    GetItemList useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetItemList(itemRepository, threadExecutor, postExecutionThread);

    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        useCase.buildUseCaseObservable(null);
        verify(itemRepository).items();
    }

}