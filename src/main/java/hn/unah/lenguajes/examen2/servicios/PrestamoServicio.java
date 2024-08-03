package hn.unah.lenguajes.examen2.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes.examen2.modelos.Cliente;
import hn.unah.lenguajes.examen2.modelos.Cuotas;
import hn.unah.lenguajes.examen2.modelos.Prestamo;
import hn.unah.lenguajes.examen2.repositorios.ClienteRepositorio;
import hn.unah.lenguajes.examen2.repositorios.CuotasRepositorio;
import hn.unah.lenguajes.examen2.repositorios.PrestamoRepositorio;

@Service
public class PrestamoServicio {

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CuotasRepositorio cuotasRepositorio;

    public Prestamo crearPrestamo(String dni, Prestamo prestamo) {

        if (!this.clienteRepositorio.existsById(dni)
                || this.clienteRepositorio.findById(dni).get().getPrestamo().size() > 2) {

            return null;
        }

        Cliente cliente = this.clienteRepositorio.findById(dni).get();

        prestamo.setCliente(cliente);

        double monto = prestamo.getMonto();
        int n = prestamo.getPlazo();
        double i = 0.09 / 12;

        double cuota = (monto * i * Math.pow(1 + i, n * 12)) / (Math.pow(1 + i, n * 12) - 1);
        prestamo.setCuota(cuota);

        double saldo = monto;
        double interes = 0;
        double capital = 0;

        for (int j = 0; j <= n * 12 + 1; j++) {

            Cuotas cuotas = new Cuotas();
            cuotas.setPrestamo(prestamo);
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

        return this.prestamoRepositorio.save(prestamo);
    }
}
