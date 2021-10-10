package com.prueba.java.sql.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "respuesta_clientes")
public class AnswerCustomerEntity {

    @Id
    private int id;

    @Column(name = "pregunta_and_respuesta_ids",columnDefinition = "JSON")
    private String questionAndAnswerIds;
    @Column(name = "id_cliente")
    private int customerId;
}
