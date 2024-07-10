package com.example.micro1.servicio;

import com.example.micro1.modelo.Militar;
import com.example.micro1.repositorio.RepositorioMilitar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioMilitar {
    Logger logger = LoggerFactory.getLogger(RepositorioMilitar.class);

    @Autowired
    RepositorioMilitar repositorioMilitar;

    public List<Militar> listarMilitares() {
        logger.info("Listado todos los militares");
        return repositorioMilitar.findAll();
    }

    public Militar buscarMilitarPorId(String id) {
        logger.info("Buscando militar con id "+ id);
        Optional<Militar> optionalMilitar = repositorioMilitar.findById(id);
        if (optionalMilitar.isPresent()) {
            logger.info("Militar encontrado: "+ optionalMilitar.get());
            return optionalMilitar.get();
        } else {
            logger.warn("No se encontró el militar con id "+ id);
            return null;
        }
    }

    public Militar registrarMilitar(Militar militar) {
        try {

            if(validarCedula(militar.getCedula())){
                logger.info("Registrando militar: "+ militar);
                return repositorioMilitar.save(militar);

            }else {
                logger.error("Cédula no válida");
                throw new IllegalArgumentException("Cédula no válida");

            }


        } catch (Exception e) {
            logger.error("Error al registrar el militar", e);
            throw e;
        }
    }

    public static boolean validarCedula(String x) {
        int suma = 0;
        if (x.length() == 9) {
            System.out.println("Ingrese su cedula de 10 digitos");
            return false;
        } else {
            int a[] = new int[x.length() / 2];
            int b[] = new int[(x.length() / 2)];
            int c = 0;
            int d = 1;
            for (int i = 0; i < x.length() / 2; i++) {
                a[i] = Integer.parseInt(String.valueOf(x.charAt(c)));
                c = c + 2;
                if (i < (x.length() / 2) - 1) {
                    b[i] = Integer.parseInt(String.valueOf(x.charAt(d)));
                    d = d + 2;
                }
            }

            for (int i = 0; i < a.length; i++) {
                a[i] = a[i] * 2;
                if (a[i] > 9) {
                    a[i] = a[i] - 9;
                }
                suma = suma + a[i] + b[i];
            }
            int aux = suma / 10;
            int dec = (aux + 1) * 10;
            if ((dec - suma) == Integer.parseInt(String.valueOf(x.charAt(x.length() - 1))))
                return true;
            else if (suma % 10 == 0 && x.charAt(x.length() - 1) == '0') {
                return true;
            } else {
                return false;
            }

        }
    }

    public Militar editarMilitar(String id, Militar nuevoMilitar) {
        logger.info("Editando militar con id "+ id);
        Optional<Militar> optionalMilitar = repositorioMilitar.findById(id);

        if (optionalMilitar.isPresent()) {
            Militar militarExistente = optionalMilitar.get();
            militarExistente.setEstado_civil(nuevoMilitar.getEstado_civil());
            militarExistente.setUnidad_actual(nuevoMilitar.getUnidad_actual());
            militarExistente.setResidencia_actual(nuevoMilitar.getResidencia_actual());


            try {
                logger.info("Guardando cambios del militar con id "+ id);
                return repositorioMilitar.save(militarExistente);
            } catch (Exception e) {
                logger.error("Error al guardar los cambios del militar", e);
                throw e;
            }
        } else {
            logger.warn("No se encontró el militar con id "+ id);
            return null;
        }
    }

    public boolean eliminarMilitar(String id) {
        logger.info("Eliminando militar con id "+ id);
        if (repositorioMilitar.existsById(id)) {
            try {
                repositorioMilitar.deleteById(id);
                logger.info("Militar con id  eliminado exitosamente"+ id);
                return true;
            } catch (Exception e) {
                logger.error("Error al eliminar el militar con id "+ id, e);
                return false;
            }
        } else {
            logger.warn("No se encontró el militar con id {}"+ id);
            return false;
        }
    }




}
