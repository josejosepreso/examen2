package hn.unah.lenguajes.examen2.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes.examen2.modelos.Prestamo;
import hn.unah.lenguajes.examen2.servicios.PrestamoServicio;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoControlador {

    @Autowired
    private PrestamoServicio prestamoServicio;

    @PostMapping("/crear")
    public Prestamo crearPrestamo(@RequestBody Prestamo prestamo, @RequestParam String dni) {

        return this.prestamoServicio.crearPrestamo(dni, prestamo);
    }

}
