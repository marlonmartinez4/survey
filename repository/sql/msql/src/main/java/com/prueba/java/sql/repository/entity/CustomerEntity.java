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
@Table(name = "cliente")
public class CustomerEntity {

    @Id
    private int id;

    @Column(name = "nombre")
    private String name;
    @Column(name = "apellidos")
    private String lastname;
    @Column(name = "numero_documento")
    private String documentNumber;
    @Column(name = "numero_celular")
    private String mobileNumber;
    @Column(name = "correo")
    private String email;
}
