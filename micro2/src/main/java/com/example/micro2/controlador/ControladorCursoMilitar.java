package com.example.micro2.controlador;

import com.example.micro2.modelo.Curso;
import com.example.micro2.modelo.CursoMilitar;
import com.example.micro2.modelo.CursoMilitarDTO;
import com.example.micro2.modelo.Militar;
import com.example.micro2.servicio.ServicioCursoMilitar;
import com.example.micro2.servicio.ServicioMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api2")
public class ControladorCursoMilitar {
    @Autowired
    private ServicioCursoMilitar servicioCursoMilitar;

    @PostMapping("/cursomilitar")
    public CursoMilitar registrarCursoMilitar(@RequestBody CursoMilitar cursoMilitar) {
        return servicioCursoMilitar.registrarCursoMilitar(cursoMilitar);
    }


    @GetMapping("/cursomilitar")
    public List<CursoMilitarDTO> listarCursosMilitar() {
        return servicioCursoMilitar.obtenerCursosMilitares();
    }
    @GetMapping("cursomilitar/promedio/{idMilitar}")
    public Double ObtenerPromedio(@PathVariable String idMilitar) {
        return servicioCursoMilitar.obtenerPromedioPorIdMilitar(idMilitar);
    }

    @GetMapping("/cursomilitar/militaresmatriculados")
    public List<Militar> obtenerMilitaresMatriculados() {
        return servicioCursoMilitar.obtenerMilitaresMatriculados();
    }
}
