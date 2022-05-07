package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ReservaSilla implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @Positive
    private Double precio;

    @ManyToOne
    private Silla silla;

    @ManyToOne
    private Reserva reserva;

    public ReservaSilla(Double precio, Silla silla, Reserva reserva) {
        this.precio = precio;
        this.silla = silla;
        this.reserva = reserva;
    }
}