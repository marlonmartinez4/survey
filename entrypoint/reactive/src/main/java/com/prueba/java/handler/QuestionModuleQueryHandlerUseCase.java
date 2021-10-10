package com.prueba.java.handler;

import com.prueba.java.dto.QuestionSurveyDto;
import com.prueba.java.usecase.handler.QuestionModuleHandlerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/robin-food/survey")
@AllArgsConstructor
public class QuestionModuleQueryHandlerUseCase {

    @Autowired
    private final QuestionModuleHandlerUseCase questionModuleHandlerUseCase;

    @GetMapping("/module-question-list")
    private Flux<QuestionSurveyDto> findAll(){
        return questionModuleHandlerUseCase.findAll();
    }

    @GetMapping("/by-id-module-question/{idModuleQuestion}")
    private Mono<QuestionSurveyDto> listModuleQuestionWithQuestion(@PathVariable("idModuleQuestion") String idModuleQuestion){
        return questionModuleHandlerUseCase.listModuleQuestionWithQuestion(Integer.parseInt(idModuleQuestion));
    }

}
