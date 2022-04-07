package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer codigo;

    @FutureOrPresent
    @Column(nullable = false)
    @ToString.Include
    private Date fechaReserva;

    @FutureOrPresent
    @Column(nullable = false)
    @ToString.Include
    private Date fechaInicio;

    @Future
    @Column(nullable = false)
    @ToString.Include
    private LocalDate fechaFin;

    @Column(nullable = false)
    @NonNull
    @Positive
    @ToString.Include
    private double precioTotal;

    @Column(nullable = false)
    @ToString.Include
    private String estado;

    @Column(nullable = false)
    @NonNull
    @Positive
    @ToString.Include
    private int cantidadPersonas;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "reserva")
    private List<ReservaHabitacion> reservaHabitaciones;

    @OneToMany(mappedBy = "reserva")
    private List<ReservaSilla> reservaSillas;
}
