package com.example.micro2.repositorio;

import com.example.micro2.modelo.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioCalificacion extends JpaRepository<Calificacion,Long> {

    @Query("SELECT AVG(c.calificacion) FROM Calificacion c WHERE c.id_militar = :id")
    Double findPromedioById_militar(@Param("id") String id);

    /*
    @Query(value = "SELECT * FROM calificacion c " +
            "JOIN materia m ON c.id_materia = m.id_materia " +
            "WHERE m.id_curso = :cursoId AND c.id_militar = :militarId", nativeQuery = true)
    List<Calificacion> findByCursoIdAndIdMilitar(@Param("cursoId") Long cursoId, @Param("militarId") Long militarId);

*/

}
