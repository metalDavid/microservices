package com.example.micro2.repositorio;

import com.example.micro2.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCurso extends JpaRepository<Curso,Long> {
}
