package com.example.micro2.servicio;


import com.example.micro2.modelo.Curso;
import com.example.micro2.modelo.Militar;
import com.example.micro2.repositorio.RepositorioCurso;
import com.example.micro2.repositorio.RepositorioMateria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioCurso {
    Logger logger = LoggerFactory.getLogger(RepositorioCurso.class);

    @Autowired
    RepositorioCurso repositorioCurso;

    @Autowired
    private RestTemplate restTemplate;

    public List<Curso> listarCursos() {
        logger.info("Listando todos los cursos");
        return repositorioCurso.findAll();
    }

    public Curso buscarCursoPorId(Long id) {
        logger.info("Buscando curso con id "+ id);
        Optional<Curso> optionalCurso = repositorioCurso.findById(id);
        if (optionalCurso.isPresent()) {
            logger.info("Curso encontrado: "+ optionalCurso.get());
            return optionalCurso.get();
        } else {
            logger.warn("No se encontró el curso con id "+ id);
            return null;
        }
    }
    @Transactional
    public Curso registrarCurso(Curso curso) {
        try {
            /*
            List<String> idmilitar = curso.getId_militar();

            System.out.println(idmilitar);
            List<Militar> militares = idmilitar.stream()
                    .map(this::buscarMilitarPorId)
                    .filter(militar -> "CAPT".equals(militar.getGrado()) && aniosServicio(militar.getFecha_graduacion()))
                    .collect(Collectors.toList());
            System.out.println("hplflñads");
            System.out.println(militares);
            // Actualizar la lista de IDs de militares en el curso
            curso.setId_militar(militares.stream().map(Militar::getId).collect(Collectors.toList()));
*/
            logger.info("Registrando curso: "+ curso);
            return repositorioCurso.save(curso);
        } catch (Exception e) {
            logger.error("Error al registrar el curso "+ e);
            throw e;
        }
    }

    private Militar buscarMilitarPorId(String id){
        String url = "http://localhost:8082/api1/militar/" + id;
        return restTemplate.getForObject(url, Militar.class);
    }

    private boolean aniosServicio(Date fechaGraduacion) {
        if (fechaGraduacion == null) {
            return false;
        }
        Date actual = new Date();
        actual.setYear(actual.getYear() - 10);
        return fechaGraduacion.before(actual);
    }

    public Curso editarCurso(Long id, Curso nuevoCurso) {
        logger.info("Editando curso con id "+ id);
        Optional<Curso> optionalCurso = repositorioCurso.findById(id);

        if (optionalCurso.isPresent()) {
            Curso cursoExistente = optionalCurso.get();
            cursoExistente.setCurso(nuevoCurso.getCurso());
            cursoExistente.setEstado(nuevoCurso.getEstado());
            cursoExistente.setOgeneral(nuevoCurso.getOgeneral());
            cursoExistente.setFechaInicio(nuevoCurso.getFechaInicio());
            cursoExistente.setFechaFin(nuevoCurso.getFechaFin());
            cursoExistente.setUnidad(nuevoCurso.getUnidad());
            cursoExistente.setObservacion(nuevoCurso.getObservacion());
            cursoExistente.setMaterias(nuevoCurso.getMaterias());
           // cursoExistente.setId_militar(nuevoCurso.getId_militar());

            try {
                logger.info("Guardando cambios del curso con id "+ id);
                return repositorioCurso.save(cursoExistente);
            } catch (Exception e) {
                logger.error("Error al guardar los cambios del curso "+ e);
                throw e;
            }
        } else {
            logger.warn("No se encontró el curso con id "+ id);
            return null;
        }
    }

    public boolean eliminarCurso(Long id) {
        logger.info("Eliminando curso con id "+ id);
        if (repositorioCurso.existsById(id)) {
            try {
                repositorioCurso.deleteById(id);
                logger.info("Curso con eliminado exitosamente"+ id);
                return true;
            } catch (Exception e) {
                logger.error("Error al eliminar el curso con id "+ id, e);
                return false;
            }
        } else {
            logger.warn("No se encontró el curso con id "+ id);
            return false;
        }
    }
}
