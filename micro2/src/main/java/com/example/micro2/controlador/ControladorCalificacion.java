package com.example.micro2.controlador;

import com.example.micro2.modelo.Calificacion;
import com.example.micro2.servicio.ServicioCalificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path ="/api2")
public class ControladorCalificacion {

    @Autowired
    private ServicioCalificacion servicioCalificacion;

    @GetMapping("/calificacion")
    public List<Calificacion> listarCalificacion() {
        return servicioCalificacion.listarCalificacion();
    }

    @GetMapping("/calificacion/{id}")
    public Calificacion buscarCalificacionPorId(@PathVariable Long id) {
        return servicioCalificacion.buscarCalificacion(id);
    }

    @PostMapping("/calificacion")
    public  Calificacion registrarCalificacion(@RequestBody Calificacion calificacion) {

        return servicioCalificacion.registrarCalificacion(calificacion);
    }

    @PutMapping("/calificacion/{id}")
    public Calificacion editarCalificacion(@PathVariable Long id, @RequestBody Calificacion nuevaCalificacion) {
        return servicioCalificacion.editarCalificacion(id, nuevaCalificacion);
    }

    @DeleteMapping("/calificacion/{id}")
    public boolean eliminarCalificacion(@PathVariable Long id) {
        return servicioCalificacion.eliminarCalificacion(id);
    }

    @GetMapping("/calificacion/promedio/{id}")
    public Double obtenerPromedioCalificaciones(@PathVariable String id) {
        return servicioCalificacion.calcularPromedioCalificaciones(id);
    }




}
