package com.rcl.excalibur.data.mapper.guest;

import android.support.annotation.Nullable;

import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.data.service.response.guest.QuestionResponse;
import com.rcl.excalibur.data.service.response.guest.SecurityQuestionsResponse;

import java.util.ArrayList;
import java.util.List;

public class SecurityQuestionsResponseMapper extends BaseDataMapper<List<String>, SecurityQuestionsResponse> {

    @Nullable
    @Override
    public List<String> transform(SecurityQuestionsResponse entity, Object... additionalArgs) {
        List<String> questions = new ArrayList<>();
        if (entity != null) {
            for (QuestionResponse questionResponse : entity.getQuestions()) {
                questions.add(questionResponse.getQuestion());
            }
        }
        return questions;
    }
}
