package com.prueba.java.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AnswerCustomer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1007352899644889893L;

    private int id;
    private String questionAndAnswerIds;
    private int customerId;
}
