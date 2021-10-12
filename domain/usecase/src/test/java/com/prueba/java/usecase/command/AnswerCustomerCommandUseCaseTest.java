package com.prueba.java.usecase.command;

import com.google.gson.Gson;
import com.prueba.java.dto.AnswerCustomerDto;
import com.prueba.java.model.*;
import com.prueba.java.repository.AnswerCustomerRepository;
import com.prueba.java.repository.AnswerSurveyRepository;
import com.prueba.java.repository.CustomerRepository;
import com.prueba.java.repository.QuestionSurveyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerCustomerCommandUseCaseTest {

    @InjectMocks
    private AnswerCustomerCommandUseCase answerCustomerCommandUseCase;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AnswerCustomerRepository answerCustomerRepository;
    @Mock
    private AnswerSurveyRepository answerSurveyRepository;
    @Mock
    private QuestionSurveyRepository questionSurveyRepository;

    @Before
    public void init() {
        answerCustomerCommandUseCase = new AnswerCustomerCommandUseCase(customerRepository, answerCustomerRepository, answerSurveyRepository,questionSurveyRepository);
    }

    @Test
    public void shouldCreateGetHeaderByDate() {
        when(customerRepository.save(any())).thenReturn(Mono.just(customer));
        when(answerCustomerRepository.save(any())).thenReturn(Mono.just(answerCustomer));
        when(answerSurveyRepository.findById(1)).thenReturn(Mono.just(answerSurvey));
        when(questionSurveyRepository.findById(1)).thenReturn(Mono.just(questionSurvey));

        StepVerifier.withVirtualTime(() -> answerCustomerCommandUseCase.saveCustomerAnswer(answerCustomerDto))
                .expectNext(answerCustomerDto)
                .verifyComplete();
    }

    final Customer customer = Customer.builder()
            .id(1)
            .documentNumber("12345")
            .name("marlon")
            .lastname("martinez")
            .mobileNumber("3117063738")
            .email("marlon@hotmail.com")
            .build();

    final AnswerCustomer answerCustomer = AnswerCustomer.builder()
            .customerId(customer.getId())
            .questionAndAnswerIds(new Gson().toJson((AnswerCustomerValue.builder().idAnswer(1).idQuestion(1).build())))
            .build();

    private final QuestionSurvey questionSurvey = QuestionSurvey.builder()
            .id(1)
            .questionModuleId(1)
            .description("Te gusto la forma de antender")
            .build();

    private final AnswerSurvey answerSurvey = AnswerSurvey.builder()
            .id(1)
            .name("probablemente")
            .build();

    private final AnswerCustomerDto answerCustomerDto = AnswerCustomerDto.builder()
            .name("marlon")
            .lastname("martinez")
            .documentNumber("12345")
            .mobileNumber("3117063738")
            .email("marlon@hotmail.com")
            .questionAndAnswerIds(List.of(AnswerCustomerValue.builder().idAnswer(1).idQuestion(1).build()))
            .build();

}