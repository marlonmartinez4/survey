package com.prueba.java.repository;

import com.prueba.java.model.QuestionSurvey;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionSurveyRepository {

    Flux<QuestionSurvey> findAllByQuestionModuleId(int id);
    Mono<QuestionSurvey> findById(int id);

}
