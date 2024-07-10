package com.example.micro2.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_calificacion;

    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;

    private String id_militar;

    private Double calificacion;

}
