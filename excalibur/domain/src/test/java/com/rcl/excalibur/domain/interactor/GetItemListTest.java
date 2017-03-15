package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.ItemRepository;

import org.junit.Ignore;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

@Deprecated
public class GetItemListTest {

    @Mock ItemRepository itemRepository;
    @Mock ThreadExecutor threadExecutor;
    @Mock PostExecutionThread postExecutionThread;
    GetItemList useCase;

    //    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetItemList(itemRepository);

    }

    @Ignore
    public void buildUseCaseObservable() throws Exception {
        useCase.buildUseCaseObservable(null, null);
        verify(itemRepository).items();
    }

}