package com.rcl.excalibur.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.preference.SailingPreferenceImpl;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.preference.SailingPreferences;
import com.rcl.excalibur.mvp.presenter.DayPickerPresenter;
import com.rcl.excalibur.mvp.view.DayPickerView;

public class DayPickerFragment extends Fragment {


    private DayPickerPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_day_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SailingPreferences sailingPreferences = new SailingPreferenceImpl(getContext());
        presenter = new DayPickerPresenter(new DayPickerView(this),
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(new SailDateDataRepository()));
        presenter.init();
    }
}
