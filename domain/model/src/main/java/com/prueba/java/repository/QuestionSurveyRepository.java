package com.prueba.java.repository;

import com.prueba.java.model.QuestionSurvey;
import reactor.core.publisher.Flux;

public interface QuestionSurveyRepository {

    Flux<QuestionSurvey> findAll();
    Flux<QuestionSurvey> findAllByQuestionModuleId(int id);

}
