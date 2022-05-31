package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class HistorialPuntos implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    @Min(value = 1, message = "El puntaje debe ser mayor a 0")
    private int puntos;

    @Column(nullable = false, length = 20)
    private String estado;

    @FutureOrPresent
    @NonNull
    @Column(nullable = false)
    private LocalDate fechaPuntos;

    @Future
    @NonNull
    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Reserva reserva;

    public HistorialPuntos(@NonNull int puntos, @NonNull LocalDate fechaPuntos, @NonNull LocalDate fechaVencimiento, Usuario usuario, Reserva reserva) {
        this.puntos = puntos;
        this.fechaPuntos = fechaPuntos;
        this.fechaVencimiento = fechaVencimiento;
        this.usuario = usuario;
        this.reserva = reserva;
    }
}
