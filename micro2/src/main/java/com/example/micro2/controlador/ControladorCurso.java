package com.example.micro2.controlador;

import com.example.micro2.modelo.Curso;
import com.example.micro2.servicio.ServicioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api2")
//@CrossOrigin(origins = "*")
public class ControladorCurso {
    @Autowired
    private ServicioCurso servicioCurso;

    @GetMapping("/curso")
    public List<Curso> listarCursos() {
        return servicioCurso.listarCursos();
    }

    @GetMapping("/curso/{id}")
    public Curso buscarCursoPorId(@PathVariable Long id) {
        return servicioCurso.buscarCursoPorId(id);
    }

    @PostMapping("/curso")
    public Curso registrarCurso(@RequestBody Curso curso) {
        return servicioCurso.registrarCurso(curso);
    }

    @PutMapping("/curso/{id}")
    public Curso editarCurso(@PathVariable Long id, @RequestBody Curso nuevoCurso) {
        return servicioCurso.editarCurso(id, nuevoCurso);
    }

    @DeleteMapping("/curso/{id}")
    public boolean eliminarCurso(@PathVariable Long id) {
        return servicioCurso.eliminarCurso(id);
    }
}
