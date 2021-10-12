package com.prueba.java.handler;

import com.prueba.java.dto.QuestionSurveyDto;
import com.prueba.java.model.QuestionSurvey;
import com.prueba.java.usecase.handler.QuestionModuleHandlerUseCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@WebFluxTest(QuestionModuleQueryHandlerUseCase.class)
@ExtendWith({SpringExtension.class})
public class QuestionModuleQueryHandlerUseCaseTest {

    @Autowired
    private WebTestClient testClient;

    @InjectMocks
    private QuestionModuleQueryHandlerUseCase useCase;

    @MockBean
    private  QuestionModuleHandlerUseCase questionModuleHandlerUseCase;

    private final QuestionSurveyDto questionSurveyDto = QuestionSurveyDto
            .builder()
            .id(1)
            .nameSurvey("nameSurvey1")
            .descriptionQuestion(List.of(QuestionSurvey.builder().id(1).description("servicio").questionModuleId(1).build()))
            .status("ACTIVO")
            .build();

    @Before
    public void init() {
        useCase = new QuestionModuleQueryHandlerUseCase(questionModuleHandlerUseCase);

    }

    @Test
    public void shouldListModelSurvey() {

        final Flux<QuestionSurveyDto> surveys = Flux.just(questionSurveyDto);
        when(useCase.findAll()).thenReturn(surveys);

        final WebTestClient.ResponseSpec spec = testClient.get().uri("/api/robin-food/survey/module-question-list")
                .exchange();

        spec.expectBodyList(QuestionSurveyDTO.class).consumeWith(res -> {
            final HttpStatus status = res.getStatus();
            final List<QuestionSurveyDTO> body = res.getResponseBody();
            assertThat(status.is2xxSuccessful()).isTrue();
            assertThat(body).hasSize(1).extracting(QuestionSurveyDTO::getId)
                    .containsExactly(1);
        });
    }

    @Test
    public void shouldListAllDetailSuccess() {
        final Mono<QuestionSurveyDto> surveyMono = Mono.just(questionSurveyDto);
        when(useCase.listModuleQuestionWithQuestion("1")).thenReturn(surveyMono);
        final WebTestClient.ResponseSpec spec = testClient
                .get()
                .uri("/api/robin-food/survey/by-id-module-question/1")
                .exchange();

        spec.expectBody(QuestionSurveyDTO.class).consumeWith(consumer -> {
            final HttpStatus status = consumer.getStatus();
            final QuestionSurveyDTO body = consumer.getResponseBody();
            assertThat(status.is2xxSuccessful()).isTrue();
            assertThat(body).isNotNull();
            assertThat(body.getId()).isEqualTo(1);
        });
    }


    @Data
    @NoArgsConstructor
    private static class QuestionSurveyDTO {
        private int id;
        private String nameSurvey;
        private String status;
        private List<QuestionSurvey> descriptionQuestion;
    }

}