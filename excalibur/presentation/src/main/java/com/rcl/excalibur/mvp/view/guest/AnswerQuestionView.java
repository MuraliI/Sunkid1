package com.rcl.excalibur.mvp.view.guest;


import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.AnswerQuestionActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerQuestionView extends ActivityView<AnswerQuestionActivity, Void> {
    @Bind(R.id.edit_answer) EditText editTextAnswer;
    @Bind(R.id.button_done_answer) Button buttonDoneAnswer;
    @Bind(R.id.text_title_question) TextView textViewTitleQuestion;

    public AnswerQuestionView(AnswerQuestionActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public String getAnswer() {
        return editTextAnswer.getText().toString();
    }

    public void setEnableDone(boolean isEnable) {
        buttonDoneAnswer.setEnabled(isEnable);
    }

    public void setTitleQuestion(String titleQuestion) {
        textViewTitleQuestion.setText(titleQuestion);
    }

    @OnClick(R.id.button_done_answer)
    void onClick() {
        //TODO navigate to next Activity
    }
}
