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
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = -4744732184669602672L;

    private int id;
    private String name;
    private String lastname;
    private String documentNumber;
    private String mobileNumber;
    private String email;
}
