package com.example.micro1.modelo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "militar")
public class Militar {
    @Id
    private String id;
    private String nombres;
    private byte[] foto;
    private String cedula;
    private String codigo_issfa;
    private String grado;
    private String arma;
    private String unidad_actual;
    private String situacion_militar;
    private String lugar_nacimiento;
    private Date fecha_nacimiento;
    private Date fecha_graduacion;
    private String sexo;
    private String estado_civil;
    private String residencia_actual;
    private String telefono;
    private String titulo_academico;
    private String email_institucional;




}
