package hn.unah.lenguajes.examen2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes.examen2.modelos.Cliente;
import hn.unah.lenguajes.examen2.servicios.ClienteServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("/crear")
    public Cliente crearCliente(@RequestBody Cliente cliente) {

        return this.clienteServicio.crearCliente(cliente);
    }

    @GetMapping("/obtener")
    public List<Cliente> obtener() {

        return this.clienteServicio.obtenerTodos();
    }

}
