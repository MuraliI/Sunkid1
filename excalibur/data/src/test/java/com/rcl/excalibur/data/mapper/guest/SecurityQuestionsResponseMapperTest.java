package com.rcl.excalibur.data.mapper.guest;

import android.support.annotation.NonNull;

import com.rcl.excalibur.data.service.response.guest.QuestionResponse;
import com.rcl.excalibur.data.service.response.guest.SecurityQuestionsResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class SecurityQuestionsResponseMapperTest {
    private static final String TEST_QUESTION = "Test question";
    private SecurityQuestionsResponseMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new SecurityQuestionsResponseMapper();
    }

    @Test
    public void testTransformWithResponseNull_returnsAnEmptyList() throws Exception {
        SecurityQuestionsResponse response = null;
        List<String> questions = mapper.transform(response, null);
        assertNotNull(questions);
        assertEquals(0, questions.size());
    }

    @Test
    public void testTransformWithAValidResponse_returnValidQuestions() throws Exception {
        SecurityQuestionsResponse response = getDummyResponse();
        List<String> questions = mapper.transform(response, null);
        assertNotNull(questions);
        assertEquals(1, questions.size());
        assertEquals(TEST_QUESTION, questions.get(0));
    }

    @NonNull
    private SecurityQuestionsResponse getDummyResponse() {
        SecurityQuestionsResponse response = new SecurityQuestionsResponse();
        List<QuestionResponse> questionResponses = new ArrayList<>();
        QuestionResponse questionResponse = new QuestionResponse(TEST_QUESTION);
        questionResponses.add(questionResponse);
        response.setQuestions(questionResponses);
        return response;
    }
}