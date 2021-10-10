package com.prueba.java.command;

import com.prueba.java.dto.AnswerCustomerDto;
import com.prueba.java.usecase.command.AnswerCustomerCommandUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/robin-food/survey")
@AllArgsConstructor
public class AnswerCustomerCommand {

    @Autowired
    private final AnswerCustomerCommandUseCase answerCustomerCommandUseCase;

    @PostMapping(path = "/save/customer-answer")
    public Mono<AnswerCustomerDto> saveCustomerAnswer(@RequestBody AnswerCustomerDto answerCustomerDto){
        return answerCustomerCommandUseCase.saveCustomerAnswer(answerCustomerDto);
    }

}
