package com.prueba.java.usecase.handler;

import com.prueba.java.dto.QuestionSurveyDto;
import com.prueba.java.model.QuestionModule;
import com.prueba.java.model.QuestionSurvey;
import com.prueba.java.repository.QuestionModuleRepository;
import com.prueba.java.repository.QuestionSurveyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class QuestionModuleHandlerUseCaseTest {

    @InjectMocks
    private QuestionModuleHandlerUseCase questionModuleHandlerUseCase;

    @Mock
    private QuestionSurveyRepository questionSurveyRepository;

    @Mock
    private QuestionModuleRepository questionModuleRepository;

    @Before
    public void init() {
        when(questionModuleRepository.findAll()).thenReturn(Flux.just(questionModule));
        when(questionSurveyRepository.findAllByQuestionModuleId(1)).thenReturn(Flux.just(questionSurvey));
        when(questionModuleRepository.findById(1)).thenReturn(Mono.just(questionModule));
        when(questionSurveyRepository.findAllByQuestionModuleId(1)).thenReturn(Flux.just(questionSurvey));
    }


    @Test
    public void shouldGetAllQuestionModule() {
        StepVerifier.create(questionModuleHandlerUseCase.findAll())
                .expectNext(questionSurveyDto)
                .verifyComplete();
    }

    @Test
    public void shouldGetQuestionModuleById() {
        StepVerifier.create(questionModuleHandlerUseCase.listModuleQuestionWithQuestion(1))
                .expectNext(questionSurveyDto)
                .verifyComplete();
    }

    private final QuestionSurvey questionSurvey = QuestionSurvey.builder()
            .id(1)
            .questionModuleId(1)
            .description("Te gusto la forma de antender")
            .build();

    private final QuestionModule questionModule = QuestionModule.builder()
            .id(1)
            .name("Sobre el restaurante")
            .status("ACTIVO")
            .build();

    private final QuestionSurveyDto questionSurveyDto = QuestionSurveyDto.builder()
            .id(1)
            .nameSurvey("Sobre el restaurante")
            .descriptionQuestion(List.of(questionSurvey))
            .status("ACTIVO")
            .build();

}