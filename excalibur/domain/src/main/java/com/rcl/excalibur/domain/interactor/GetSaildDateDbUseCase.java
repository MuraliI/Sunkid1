package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.repository.SailDateRepository;

import java.util.List;

public class GetSaildDateDbUseCase extends UseCaseSync<SailDateRepository> {

    public GetSaildDateDbUseCase(SailDateRepository data) {
        super(data);
    }

    public List<SailDateInfo> getAll() {
        return getData().getAll();
    }

    public SailDateInfo get() {
        return getData().get();
    }

}
