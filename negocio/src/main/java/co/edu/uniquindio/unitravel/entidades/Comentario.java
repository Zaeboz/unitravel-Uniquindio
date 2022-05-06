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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 500, nullable = false)
    private String comentario;

    @Column(length = 500)
    private String respuesta;

    @Column(nullable = false)
    private int calificacion;

    @FutureOrPresent(message = "La fecha debe ser mayor o igual a la actual")
    @Column(nullable = false)
    private LocalDate fecha_calificacion;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Hotel hotel;

    public Comentario( String comentario, int calificacion, LocalDate fecha_calificacion, Usuario usuario, Hotel hotel) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha_calificacion = fecha_calificacion;
        this.usuario = usuario;
        this.hotel = hotel;
    }
}
