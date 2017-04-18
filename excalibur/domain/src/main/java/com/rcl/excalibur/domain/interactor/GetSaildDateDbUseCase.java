package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.SailDateInfoEvent;
import com.rcl.excalibur.domain.repository.SailDateRepository;

public class GetSaildDateDbUseCase extends UseCaseSync<SailDateRepository> {

    protected GetSaildDateDbUseCase(SailDateRepository data) {
        super(data);
    }
    public SailDateInfoEvent get(String sailDateInfoId) {
        return getData().get(sailDateInfoId);
    }
}
