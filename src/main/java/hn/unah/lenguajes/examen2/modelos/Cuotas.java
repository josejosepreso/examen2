package hn.unah.lenguajes.examen2.modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cuotas")
public class Cuotas {

    @Id
    @Column(name = "codigocuota")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoCuota;

    private int mes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigoprestamo", referencedColumnName = "codigoprestamo")
    private Prestamo prestamo;

    private double interes;

    private double capital;

    private double saldo;
}
