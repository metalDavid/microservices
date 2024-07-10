package com.example.micro2.controlador;

import com.example.micro2.modelo.Materia;
import com.example.micro2.servicio.ServicioCurso;
import com.example.micro2.servicio.ServicioMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api2/materia")
//@CrossOrigin(origins = "*")
public class ControladorMateria {
    @Autowired
    private ServicioMateria servicioMateria;

    @GetMapping
    public List<Materia> listarMaterias() {
        return servicioMateria.listarMaterias();
    }

    @GetMapping("/{id}")
    public Materia buscarMateriaPorId(@PathVariable Long id) {
        return servicioMateria.buscarMateriaPorId(id);
    }

    @PostMapping
    public Materia registrarMateria(@RequestBody Materia materia) {
        return servicioMateria.registrarMateria(materia);
    }

    @PutMapping("/{id}")
    public Materia editarMateria(@PathVariable Long id, @RequestBody Materia nuevaMateria) {
        return servicioMateria.editarMateria(id, nuevaMateria);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarMateria(@PathVariable Long id) {
        return servicioMateria.eliminarMateria(id);
    }

}
