package com.rcl.excalibur.mvp.view.guest;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.SecurityQuestionsActivity;
import com.rcl.excalibur.adapters.guest.SecurityQuestionsAdapter;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecurityQuestionsView extends ActivityView<SecurityQuestionsActivity, Void, String> {
    private static final String ERROR_MESSAGE = "Something went wrong, please try again";
    @BindView(R.id.recycler_questions) RecyclerView recyclerView;
    @BindView(R.id.progress_questions) ProgressBar progressBar;
    private SecurityQuestionsAdapter adapter;

    public SecurityQuestionsView(SecurityQuestionsActivity activity) {
        super(activity);
    }

    public void init() {
        ButterKnife.bind(this, getActivity());
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SecurityQuestionsAdapter(this.getAdapterObserver());
        recyclerView.setAdapter(adapter);
    }

    public void updateQuestions(List<String> questions) {
        adapter.addAll(questions);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.image_back_screen)
    void backClick() {
        getActivity().finish();
    }
}
