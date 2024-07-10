package com.example.micro2.servicio;

import com.example.micro2.modelo.*;
import com.example.micro2.repositorio.RepositorioCalificacion;
import com.example.micro2.repositorio.RepositorioCursoMilitar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCursoMilitar {
    Logger logger = LoggerFactory.getLogger(RepositorioCursoMilitar.class);
    Double promedio=0.0;
    @Autowired
    RepositorioCursoMilitar repositorioCursoMilitar;

    @Autowired
    RepositorioCalificacion repositorioCalificacion;

    @Autowired
    private RestTemplate restTemplate;
/*
    @Transactional
    public void actualizarPromedio(Long cursoId, Long militarId) {
        List<Calificacion> calificaciones = repositorioCalificacion.findByCursoIdAndIdEstudiante(cursoId, militarId);
        System.out.println("Se ejecuta actualizar promedio");
        System.out.println(calificaciones);
        double promedio = calificaciones.stream().mapToDouble(Calificacion::getCalificacion).average().orElse(0);

        CursoMilitar cursoMilitar = repositorioCursoMilitar
                .findByCursoIdAndMilitarId(cursoId, militarId)
                .orElseThrow(() -> new RuntimeException("CursoMilitar no encontrado"));

        cursoMilitar.setPromedio(promedio);
        repositorioCursoMilitar.save(cursoMilitar);
    }
*/
    private String militarServiceUrl = "http://localhost:8080/api1/militar/";

    public List<CursoMilitarDTO> obtenerCursosMilitares() {
        List<CursoMilitar> cursosMilitares = repositorioCursoMilitar.findAll();
        List<CursoMilitarDTO> cursoMilitarDTOs = new ArrayList<>();

        for (CursoMilitar cursoMilitar : cursosMilitares) {

                Militar militar = restTemplate.getForObject(militarServiceUrl + cursoMilitar.getId_militar(), Militar.class);
                CursoMilitarDTO dto = new CursoMilitarDTO(cursoMilitar, militar);
                cursoMilitarDTOs.add(dto);
                obtenerPromedioPorIdMilitar(cursoMilitar.getId_militar());
        }

        return cursoMilitarDTOs;
    }
    private boolean aniosServicio(Date fechaGraduacion) {
        if (fechaGraduacion == null) {
            return false;
        }
        Date actual = new Date();
        actual.setYear(actual.getYear() - 10);
        return fechaGraduacion.before(actual);
    }


    public List<Militar> obtenerMilitaresMatriculados() {
        List<Militar> militaresMatriculados = new ArrayList<>();
        List<String> idMilitares = repositorioCursoMilitar.listarIdsMilitar();
        for (String idMilitar : idMilitares) {
            Militar militar = restTemplate.getForObject("http://localhost:8080/api1/militar/" + idMilitar, Militar.class);
            if (militar != null) {
                militaresMatriculados.add(militar);
            }
        }
        return militaresMatriculados;
    }
    public CursoMilitar registrarCursoMilitar(CursoMilitar cursoMilitar) {
        try {
            if("Curso Básico de Arma y Servicios".equals(cursoMilitar.getCurso().getCurso())){
                Militar militar = restTemplate.getForObject(militarServiceUrl + cursoMilitar.getId_militar(), Militar.class);
                if("TNTE.".equals(militar.getGrado()) && aniosServicio(militar.getFecha_graduacion())){
                    logger.info("Registrando cursoMilitar: "+ cursoMilitar);
                    return  repositorioCursoMilitar.save(cursoMilitar);
                }else{
                    logger.error("Error al registrar, no cumple grado o años de servicios: "+ cursoMilitar);
                    throw new IllegalArgumentException("Error al registrar, no cumple grado o años de servicios");
                }
            }else{
                logger.info("Registrando cursoMilitar: "+ cursoMilitar);
                return repositorioCursoMilitar.save(cursoMilitar);
            }


        } catch (Exception e) {
            logger.error("Error al registrar la cursoMilitar", e);
            throw e;
        }
    }
    public List<CursoMilitar> listarCursosMilitar() {
        logger.info("Listando todos las inscripciones");
        return repositorioCursoMilitar.findAll();
    }

    public double obtenerPromedioPorIdMilitar(String idMilitar) {
        System.out.println(idMilitar);
        System.out.println( repositorioCalificacion.findPromedioById_militar(idMilitar));
        if(repositorioCalificacion.findPromedioById_militar(idMilitar)==null){
           promedio=0.0;
        }else {
            promedio= repositorioCalificacion.findPromedioById_militar(idMilitar);
        }

        System.out.println("promedio");
        System.out.println(promedio);
        Optional<CursoMilitar> cursoMilitarOptional = repositorioCursoMilitar.findByMilitarId(idMilitar);
        System.out.println(cursoMilitarOptional);
        CursoMilitar cursoMilitarExistente = cursoMilitarOptional.get();
        System.out.println(cursoMilitarExistente);

        System.out.println(cursoMilitarExistente);
        cursoMilitarExistente.setPromedio(promedio);
        repositorioCursoMilitar.save(cursoMilitarExistente);

        return   repositorioCursoMilitar.obtenerPromedioPorIdMilitar(idMilitar);
    }

}
