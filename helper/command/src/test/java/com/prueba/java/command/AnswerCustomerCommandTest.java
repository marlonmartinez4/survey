package com.prueba.java.command;

import com.prueba.java.dto.AnswerCustomerDto;
import com.prueba.java.model.AnswerCustomerValue;
import com.prueba.java.usecase.command.AnswerCustomerCommandUseCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@WebFluxTest(AnswerCustomerCommand.class)
@ExtendWith({SpringExtension.class})
public class AnswerCustomerCommandTest {

    @Autowired
    private WebTestClient testClient;

    @InjectMocks
    private AnswerCustomerCommand useCase;

    @MockBean
    private  AnswerCustomerCommandUseCase answerCustomerCommandUseCase;

    private final  AnswerCustomerDto answerCustomerDto = AnswerCustomerDto.builder()
            .name("marlon")
            .lastname("martinez")
            .documentNumber("12345")
            .mobileNumber("3117063738")
            .email("marlon@hotmail.com")
            .questionAndAnswerIds(List.of(AnswerCustomerValue.builder().idAnswer(1).idQuestion(1).build()))
            .build();


    @Before
    public void init() {
        useCase = new AnswerCustomerCommand(answerCustomerCommandUseCase);

    }

    @Test
    public void shouldSaveSurvey() {
        AnswerCustomerDto answerCustomerDto1 = Mockito.mock(AnswerCustomerDto.class);
        when(useCase.saveCustomerAnswer(answerCustomerDto1))
                .thenAnswer(i -> just(answerCustomerDto1));

        testClient.post().uri("/api/robin-food/survey/save/customer-answer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(answerCustomerDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommandResponse.class)
                .returnResult();

    }

    @Data
    @NoArgsConstructor
    private static class CommandResponse {
        private List<AnswerCustomerValue> questionAndAnswerIds;
        private String name;
        private String lastname;
        private String documentNumber;
        private String mobileNumber;
        private String email;
    }

}