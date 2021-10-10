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
public class QuestionModule implements Serializable {

    @Serial
    private static final long serialVersionUID = -2640692624446830519L;

    private int id;
    private String name;
    private String status;
}
