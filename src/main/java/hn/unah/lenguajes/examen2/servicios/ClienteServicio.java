package hn.unah.lenguajes.examen2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes.examen2.modelos.Cliente;
import hn.unah.lenguajes.examen2.modelos.Cuotas;
import hn.unah.lenguajes.examen2.modelos.Prestamo;
import hn.unah.lenguajes.examen2.repositorios.ClienteRepositorio;
import hn.unah.lenguajes.examen2.repositorios.CuotasRepositorio;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CuotasRepositorio cuotasRepositorio;

    public List<Cliente> obtenerTodos() {

        return this.clienteRepositorio.findAll();
    }

    public Cliente crearCliente(Cliente cliente) {

        if (this.clienteRepositorio.existsById(cliente.getDni())) {

            return null;
        }

        List<Prestamo> prestamo = cliente.getPrestamo();

        if (prestamo != null) {

            prestamo.get(0).setCliente(cliente);

            double monto = prestamo.get(0).getMonto();
            int n = prestamo.get(0).getPlazo();
            double i = 0.09 / 12;

            double cuota = (monto * i * Math.pow(1 + i, n * 12)) / (Math.pow(1 + i, n * 12) - 1);
            prestamo.get(0).setCuota(cuota);

            double saldo = monto;
            double interes = 0;
            double capital = 0;

            for (int j = 0; j <= n * 12 + 1; j++) {

                Cuotas cuotas = new Cuotas();
                cuotas.setPrestamo(prestamo.get(0));
                cuotas.setMes(j);

                if (j == 0) {

                    cuotas.setInteres(interes);
                    cuotas.setCapital(capital);
                    cuotas.setSaldo(saldo);
                    this.cuotasRepositorio.save(cuotas);

                    continue;
                }

                interes = (saldo * i) / 12;
                capital = cuota - interes;
                saldo = saldo - capital;

                cuotas.setInteres(interes);
                cuotas.setCapital(capital);
                cuotas.setSaldo(saldo);

                this.cuotasRepositorio.save(cuotas);
            }

        }

        return this.clienteRepositorio.save(cliente);
    }
}
