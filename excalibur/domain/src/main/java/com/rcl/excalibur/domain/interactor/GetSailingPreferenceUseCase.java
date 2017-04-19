package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.preference.SailingPreferences;

public class GetSailingPreferenceUseCase extends UseCaseSync<SailingPreferences> {

    public GetSailingPreferenceUseCase(SailingPreferences data) {
        super(data);
    }

    public void putDay(String value) {
        getData().putDay(value);
    }

    public String getDay() {
        return getData().getDay();
    }
}
