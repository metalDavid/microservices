package com.example.micro1.controlador;

import com.example.micro1.modelo.Militar;
import com.example.micro1.modelo.MilitarDTO;
import com.example.micro1.servicio.ServicioMilitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api1")
//@CrossOrigin(origins = "*")
public class ControladorMilitar {
    @Autowired
    private ServicioMilitar servicioMilitar;

    @GetMapping("/militar")
    public List<Militar> listarMilitares() {
        return servicioMilitar.listarMilitares();
    }

    @GetMapping("/militar/{id}")
    public Militar buscarMilitarPorId(@PathVariable String id) {
        return servicioMilitar.buscarMilitarPorId(id);
    }

    @PostMapping("/militar")
    public Militar registrarMilitar(@RequestBody Militar militar) {
        /*
        Militar militar = new Militar();
        militar.setNombres(militarDTO.getNombres());
        militar.setFoto(militarDTO.getFoto());
        militar.setCedula(militarDTO.getCedula());
        militar.setCodigo_issfa(militarDTO.getCodigo_issfa());
*/
        return servicioMilitar.registrarMilitar(militar);
    }

    @PutMapping("/militar/{id}")
    public Militar editarMilitar(@PathVariable String id, @RequestBody Militar militar) {
        return servicioMilitar.editarMilitar(id, militar);
    }

    @DeleteMapping("/militar/{id}")
    public boolean eliminarMilitar(@PathVariable String id) {
        return servicioMilitar.eliminarMilitar(id);
    }
}
