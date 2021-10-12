package com.prueba.java.usecase.handler;

import com.prueba.java.dto.QuestionSurveyDto;
import com.prueba.java.enums.SurveyEnum;
import com.prueba.java.exception.SurveyException;
import com.prueba.java.repository.QuestionModuleRepository;
import com.prueba.java.repository.QuestionSurveyRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
@Component
public class QuestionModuleHandlerUseCase {

    @Autowired
    private QuestionSurveyRepository questionSurveyRepository;

    @Autowired
    private QuestionModuleRepository questionModuleRepository;

    public Flux<QuestionSurveyDto> findAll(){
        return questionModuleRepository.findAll()
                .map(module -> QuestionSurveyDto.builder()
                        .id(module.getId())
                        .nameSurvey(module.getName())
                        .status(module.getStatus())
                        .build())
                .flatMap(module -> questionSurveyRepository.findAllByQuestionModuleId(module.getId())
                        .collectList()
                        .map(question -> module.toBuilder()
                                .descriptionQuestion(question)
                                .build())
                        .switchIfEmpty(Mono.defer(() -> Mono.justOrEmpty(module.toBuilder()
                                .descriptionQuestion(Collections.emptyList())
                                .build())))
                )
                .switchIfEmpty(Flux.defer(Flux::empty));
    }


    public Mono<QuestionSurveyDto> listModuleQuestionWithQuestion(int idModuleQuestion){

        return questionModuleRepository.findById(idModuleQuestion)
                .map(module -> QuestionSurveyDto.builder()
                        .id(module.getId())
                        .nameSurvey(module.getName())
                        .status(module.getStatus())
                        .build())
                .flatMap(module -> questionSurveyRepository.findAllByQuestionModuleId(module.getId())
                        .collectList()
                        .map(question -> module.toBuilder()
                                .descriptionQuestion(question)
                                .build())
                        .switchIfEmpty(Mono.defer(() -> Mono.justOrEmpty(module.toBuilder()
                                .descriptionQuestion(Collections.emptyList())
                                .build())))
                )
                .switchIfEmpty(Mono.defer(() -> Mono.error(new SurveyException(SurveyEnum.MODULE_QUESTION_NOT_FOUND))));

    }
}
