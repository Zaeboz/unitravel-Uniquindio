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
@ToString(onlyExplicitlyIncluded = true)
public class ReservaHabitacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer codigo;

    @Column(nullable = false)
    @Positive
    @NonNull
    @ToString.Include
    private Float precio;

    @ManyToOne
    private Reserva reserva;

    @ManyToOne
    private Habitacion habitacion;
}