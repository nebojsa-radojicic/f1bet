package com.sporty.f1bet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "outcome")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutcomeEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String provider;
    private Integer event;
    private Integer driver;
    private String processed;

}