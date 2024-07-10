package com.example.micro2.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    private String curso;
    private String estado;
    private String ogeneral;

    private Date fechaInicio;
    private Date fechaFin;
    private String unidad;
    private String observacion;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Materia> materias;

    @JsonIgnore
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CursoMilitar> cursoMilitar;

}
