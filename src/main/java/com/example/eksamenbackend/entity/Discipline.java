package com.example.eksamenbackend.entity;

import com.example.eksamenbackend.enums.ResultType;
import jakarta.persistence.*;

@Entity
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    public Discipline() {
    }

    public Discipline(String name, ResultType resultType) {
        this.name = name;
        this.resultType = resultType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }
}
