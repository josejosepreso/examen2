package hn.unah.lenguajes.examen2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.lenguajes.examen2.modelos.Cuotas;
import hn.unah.lenguajes.examen2.modelos.Prestamo;

@Repository
public interface CuotasRepositorio extends JpaRepository<Cuotas, Long> {

    public List<Cuotas> getByPrestamo(Prestamo prestamo);
}
