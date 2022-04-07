package co.edu.uniquindio.unitravel.entidades;

import jdk.jfr.Timestamp;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 500, nullable = false)
    private String comentario;

    @FutureOrPresent(message = "La fecha debe ser mayor o igual a la actual")
    @Column(nullable = false)
    private LocalDate fecha_calificacion;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Hotel hotel;
}
