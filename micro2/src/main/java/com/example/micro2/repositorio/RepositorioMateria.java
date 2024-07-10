package com.example.micro2.repositorio;

import com.example.micro2.modelo.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioMateria extends JpaRepository<Materia,Long> {
}
