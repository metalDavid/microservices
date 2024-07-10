package com.example.micro2.repositorio;

import com.example.micro2.modelo.CursoMilitar;
import com.example.micro2.modelo.Militar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositorioCursoMilitar extends JpaRepository<CursoMilitar, Long> {
/*
    @Query(value = "SELECT * FROM curso_militar ce WHERE ce.id_curso = :cursoId AND ce.id_militar = :militarId", nativeQuery = true)
    Optional<CursoMilitar> findByCursoIdAndMilitarId(@Param("cursoId") Long cursoId, @Param("militarId") Long militarId);
*/
    @Query(value = "SELECT * FROM curso_militar ce WHERE ce.id_militar = :militarId", nativeQuery = true)
    Optional<CursoMilitar> findByMilitarId(@Param("militarId") String militarId);
    @Query(value = "SELECT AVG(promedio) FROM curso_militar WHERE id_militar = :idMilitar", nativeQuery = true)
    Double obtenerPromedioPorIdMilitar(@Param("idMilitar") String idMilitar);

    @Query("SELECT cm.id_militar FROM CursoMilitar cm")
    List<String> listarIdsMilitar();

}
