package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ReservaHabitacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(nullable = false)
    @Positive
    private Double precio;

    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Future
    @Column(nullable = false)
    private LocalDate fechaFin;

    @ManyToOne
    private Reserva reserva;

    @ManyToOne
    private Habitacion habitacion;

    public ReservaHabitacion(@NonNull Double precio, LocalDate fechaInicio, LocalDate fechaFin, Reserva reserva, Habitacion habitacion) {
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.reserva = reserva;
        this.habitacion = habitacion;
    }
}