package com.example.micro2.servicio;

import com.example.micro2.modelo.Materia;
import com.example.micro2.repositorio.RepositorioMateria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioMateria {
    Logger logger = LoggerFactory.getLogger(RepositorioMateria.class);

    @Autowired
    RepositorioMateria repositorioMateria;

    public List<Materia> listarMaterias() {
        logger.info("Listado las materias");
        return repositorioMateria.findAll();
    }

    public Materia buscarMateriaPorId(Long id) {
        logger.info("Buscando materia con id "+ id);
        Optional<Materia> optionalMateria = repositorioMateria.findById(id);
        if (optionalMateria.isPresent()) {
            logger.info("Materia encontrada: "+ optionalMateria.get());
            return optionalMateria.get();
        } else {
            logger.warn("No se encontró la materia con id "+ id);
            return null;
        }
    }

    public Materia registrarMateria(Materia materia) {
        try {
            logger.info("Registrando materia: "+ materia);
            return repositorioMateria.save(materia);
        } catch (Exception e) {
            logger.error("Error al registrar la materia", e);
            throw e;
        }
    }

    public Materia editarMateria(Long id, Materia nuevaMateria) {
        logger.info("Editando materia con id "+ id);
        Optional<Materia> optionalMateria = repositorioMateria.findById(id);

        if (optionalMateria.isPresent()) {
            Materia materiaExistente = optionalMateria.get();
            materiaExistente.setMateria(nuevaMateria.getMateria());
            materiaExistente.setCurso(nuevaMateria.getCurso());

            try {
                logger.info("Guardando cambios de la materia con id "+ id);
                return repositorioMateria.save(materiaExistente);
            } catch (Exception e) {
                logger.error("Error al guardar los cambios de la materia", e);
                throw e;
            }
        } else {
            logger.warn("No se encontró la materia con id "+ id);
            return null;
        }
    }

    public boolean eliminarMateria(Long id) {
        logger.info("Eliminando materia con id "+ id);
        if (repositorioMateria.existsById(id)) {
            try {
                repositorioMateria.deleteById(id);
                logger.info("Materia  eliminada exitosamente"+ id);
                return true;
            } catch (Exception e) {
                logger.error("Error al eliminar la materia", id, e);
                return false;
            }
        } else {
            logger.warn("No se encontró la materia con id "+ id);
            return false;
        }
    }
}
