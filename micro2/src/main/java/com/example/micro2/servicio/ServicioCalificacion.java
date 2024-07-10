package com.example.micro2.servicio;

import com.example.micro2.modelo.Calificacion;
import com.example.micro2.modelo.Militar;
import com.example.micro2.repositorio.RepositorioCalificacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCalificacion {
    Logger logger = LoggerFactory.getLogger(RepositorioCalificacion.class);

    @Autowired
    private RepositorioCalificacion repositorioCalificacion;
    @Autowired
    private ServicioCursoMilitar servicioCursoMilitar;
    @Autowired
    private RestTemplate restTemplate;


    public Calificacion registrarCalificacion(Calificacion calificacion){
        try{

            logger.info("Registro "+calificacion.getId_calificacion());
            repositorioCalificacion.save(calificacion);
            System.out.println(calificacion);
           // servicioCursoMilitar.actualizarPromedio(calificacion.getMateria().getCurso().getId_curso(),calificacion.getId_militar());
            System.out.println("Se ejecuta ServicioCalificacion");
           return null;
        }catch (Exception e){
            logger.error("Error en el registro");
            throw e;
        }
    }
    public Double calcularPromedioCalificaciones(String id) {
        return repositorioCalificacion.findPromedioById_militar(id);
    }

    public List<Calificacion> listarCalificacion(){

        return repositorioCalificacion.findAll();
    }


    public Calificacion buscarCalificacion(Long id){
        logger.info("Registro con id"+id);

        Optional<Calificacion> optionalCalificacion = repositorioCalificacion.findById(id);
        if (optionalCalificacion.isPresent()) {
            logger.info("Calificacion encontrada: "+ optionalCalificacion.get());
            return optionalCalificacion.get();
        } else {
            logger.warn("No se encontró la calificacion con id "+ id);
            return null;
        }
    }

    public Calificacion editarCalificacion(Long id, Calificacion nuevaCalificacion) {
        Optional<Calificacion> optionalCalificacion = repositorioCalificacion.findById(id);

        if (optionalCalificacion.isPresent()) {
            Calificacion calificacionExistente = optionalCalificacion.get();
            calificacionExistente.setCalificacion(nuevaCalificacion.getCalificacion());


            try {
                logger.info("Editado registro con id " + id);
                repositorioCalificacion.save(calificacionExistente);
                //servicioCursoMilitar.actualizarPromedio(calificacionExistente.getMateria().getCurso().getId_curso(),calificacionExistente.getId_militar());
                return null;
            } catch (Exception e) {
                logger.error("Error al editar", e);
                throw e;
            }
        } else {
            logger.error("No se encontró la calificación con id " + id);
            return null;
        }
    }

    public boolean eliminarCalificacion(Long id) {
        try {
            if (repositorioCalificacion.existsById(id)) {
                repositorioCalificacion.deleteById(id);
                logger.info("Eliminado registro con id " + id);
                return true;
            } else {
                logger.error("No se encontró la calificación con id " + id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error al eliminar el registro", e);
            return false;
        }
    }


    /*
    public void obtenerIdsCalificacion() {
        List<Calificacion> calificaciones = listarCalificacion();
        System.out.println("calificaciones");
        System.out.println(calificaciones);


        for (Calificacion calificacion : calificaciones) {
            buscarMilitarPorId(calificacion.getId_militar());
            System.out.println("ids");
            System.out.println(calificacion.getId_militar());
        }

    }
    private Militar buscarMilitarPorId(String id){
        String url = "http://localhost:8082/api1/militar/" + id;
        return restTemplate.getForObject(url, Militar.class);
    }
    */



}
