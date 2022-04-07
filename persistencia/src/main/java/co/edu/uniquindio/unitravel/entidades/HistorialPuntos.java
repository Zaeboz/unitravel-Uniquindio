package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class HistorialPuntos implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer codigo;

    @Positive
    @NonNull
    @Column(nullable = false)
    @ToString.Include
    private int puntos;

    @FutureOrPresent
    @NonNull
    @Column(nullable = false)
    @ToString.Include
    private LocalDate fechaPuntos;

    @Future
    @NonNull
    @Column(nullable = false)
    @ToString.Include
    private LocalDate fechaVencimiento;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Hotel hotel;
}
