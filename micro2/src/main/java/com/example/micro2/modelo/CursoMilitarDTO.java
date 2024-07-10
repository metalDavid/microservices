package com.example.micro2.modelo;

import lombok.Data;

@Data
public class CursoMilitarDTO {
    private Long id;
    private Curso curso;
    private String id_militar;
    private double promedio=0.0;
    private Militar militar;

    public CursoMilitarDTO(CursoMilitar cursoMilitar, Militar militar) {
        this.id = cursoMilitar.getId();
        this.curso = cursoMilitar.getCurso();
        this.id_militar = cursoMilitar.getId_militar();
        this.promedio = cursoMilitar.getPromedio();
        this.militar = militar;
    }
}
