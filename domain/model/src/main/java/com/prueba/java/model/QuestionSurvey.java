package com.prueba.java.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class QuestionSurvey implements Serializable {

    @Serial
    private static final long serialVersionUID = 3780127420068949737L;

    private int id;
    private int questionModuleId;
    private String description;
}
