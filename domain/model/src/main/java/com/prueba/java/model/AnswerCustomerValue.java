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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerCustomerValue implements Serializable {

    @Serial
    private static final long serialVersionUID = 4769663628771565476L;

    @EqualsAndHashCode.Include
    private int idQuestion;
    private int idAnswer;
}
