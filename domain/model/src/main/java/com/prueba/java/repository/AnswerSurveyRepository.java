package com.prueba.java.repository;


import com.prueba.java.model.AnswerSurvey;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AnswerSurveyRepository {

    Mono<AnswerSurvey> findById(int id);
}
