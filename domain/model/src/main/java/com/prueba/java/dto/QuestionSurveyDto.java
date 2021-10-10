package com.prueba.java.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.java.model.QuestionSurvey;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class QuestionSurveyDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -2640692624446830519L;

    private int id;
    private String nameSurvey;
    private String status;
    private List<QuestionSurvey> descriptionQuestion;
}
