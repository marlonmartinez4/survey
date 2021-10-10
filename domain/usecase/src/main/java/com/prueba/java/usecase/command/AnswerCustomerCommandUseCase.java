package com.prueba.java.usecase.command;

import com.google.gson.Gson;
import com.prueba.java.dto.AnswerCustomerDto;
import com.prueba.java.enums.SurveyEnum;
import com.prueba.java.exception.SurveyException;
import com.prueba.java.model.AnswerCustomer;
import com.prueba.java.model.Customer;
import com.prueba.java.repository.AnswerCustomerRepository;
import com.prueba.java.repository.AnswerSurveyRepository;
import com.prueba.java.repository.CustomerRepository;
import com.prueba.java.repository.QuestionSurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class AnswerCustomerCommandUseCase {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AnswerCustomerRepository answerCustomerRepository;
    @Autowired
    private AnswerSurveyRepository answerSurveyRepository;
    @Autowired
    private QuestionSurveyRepository questionSurveyRepository;


    public Mono<AnswerCustomerDto> saveCustomerAnswer(AnswerCustomerDto answerCustomerDto){

        return Mono.justOrEmpty(answerCustomerDto)
                .flatMap(this::validateDataJson)
                .filter(validateJson -> validateJson)
                .map(validate -> answerCustomerDto.getQuestionAndAnswerIds())
                .flatMap(question -> questionSurveyRepository.findById(question.iterator().next().getIdQuestion())
                        .map(dataQuestion -> answerCustomerDto)
                        .switchIfEmpty(Mono.defer(() -> Mono.error(new SurveyException(SurveyEnum.QUESTION_ID_DOES_NOT_EXIST)))))
                .map(validate -> answerCustomerDto.getQuestionAndAnswerIds())
                .flatMap(question -> answerSurveyRepository.findById(question.iterator().next().getIdAnswer())
                        .map(dataAnswer -> answerCustomerDto)
                        .switchIfEmpty(Mono.defer(() -> Mono.error(new SurveyException(SurveyEnum.ANSWER_ID_DOES_NOT_EXIST)))))
                .flatMap(answer -> customerRepository.save(Customer.builder()
                                    .documentNumber(answerCustomerDto.getDocumentNumber())
                                    .name(answerCustomerDto.getName())
                                    .lastname(answerCustomerDto.getLastname())
                                    .mobileNumber(answerCustomerDto.getMobileNumber())
                                    .email(answerCustomerDto.getEmail())
                                    .build()))
                .flatMap(customer -> answerCustomerRepository.save(AnswerCustomer.builder()
                        .customerId(customer.getId())
                        .questionAndAnswerIds(new Gson().toJson((answerCustomerDto.getQuestionAndAnswerIds())))
                        .build()))
                .map(answerCustomer -> answerCustomerDto)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new SurveyException(SurveyEnum.JSON_DOES_NOT_MEET_MINIMAL_VALIDATIONS))))
                ;
    }

    private Mono<Boolean> validateDataJson(AnswerCustomerDto answerCustomerDto) {
        return Mono.just(nonNull(answerCustomerDto.getDocumentNumber()) && !answerCustomerDto.getDocumentNumber().equals(Strings.EMPTY) &&
                nonNull(answerCustomerDto.getName()) && !answerCustomerDto.getName().equals(Strings.EMPTY) &&
                nonNull(answerCustomerDto.getEmail()) && !answerCustomerDto.getEmail().equals(Strings.EMPTY) &&
                nonNull(answerCustomerDto.getQuestionAndAnswerIds()) &&
                nonNull(answerCustomerDto.getMobileNumber()) && !answerCustomerDto.getMobileNumber().equals(Strings.EMPTY));
    }

}
