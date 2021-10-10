package com.prueba.java.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.java.model.AnswerCustomerValue;
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
public class AnswerCustomerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 237348423395167834L;

    private List<AnswerCustomerValue> questionAndAnswerIds;
    private String name;
    private String lastname;
    private String documentNumber;
    private String mobileNumber;
    private String email;
}
