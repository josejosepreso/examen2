package hn.unah.lenguajes.examen2.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes.examen2.modelos.Prestamo;
import hn.unah.lenguajes.examen2.servicios.PrestamoServicio;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoControlador {

    @Autowired
    private PrestamoServicio prestamoServicio;

    @PostMapping("/crear")
    public Prestamo crearPrestamo(@RequestBody Prestamo prestamo, @RequestParam String dni) {

        return this.prestamoServicio.crearPrestamo(dni, prestamo);
    }

    @GetMapping("/obtener/{id}")
    public Prestamo getMethodName(@PathVariable long id) {

        return this.prestamoServicio.obtenerPorId(id);
    }

}
