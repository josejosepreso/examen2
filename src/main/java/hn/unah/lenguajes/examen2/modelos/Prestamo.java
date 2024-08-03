package hn.unah.lenguajes.examen2.modelos;

import java.time.LocalDate;
// import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @Column(name = "codigoprestamo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoPrestamo;

    @Column(name = "fechaapertura")
    private LocalDate fechaApertura;

    private double monto;

    private double cuota;

    private int plazo;

    private int interes;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Cliente cliente;

    // @OneToMany(mappedBy = "codigoPrestamo", cascade = CascadeType.ALL)
    // private List<Cuotas> cuotas;
}
