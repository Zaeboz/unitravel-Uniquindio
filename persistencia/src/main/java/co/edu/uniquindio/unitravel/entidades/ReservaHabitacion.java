package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

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
    @NonNull
    private Float precio;

    @ManyToOne
    private Reserva reserva;

    @ManyToOne
    private Habitacion habitacion;

    public ReservaHabitacion(@NonNull Float precio, Reserva reserva, Habitacion habitacion) {
        this.precio = precio;
        this.reserva = reserva;
        this.habitacion = habitacion;
    }
}