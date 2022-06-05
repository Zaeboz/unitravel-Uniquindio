package co.edu.uniquindio.unitravel.entidades;

import jdk.jfr.Timestamp;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 11, unique = true)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @Column(length = 500, nullable = false)
    @Size(max = 500, message = "El comentario no puede tener mas de 500 caracteres")
    @NotBlank(message = "El comentario no puede estar vacio")
    private String comentario;

    @Column(length = 500)
    @Size(max = 500, message = "La respuesta no puede tener mas de 500 caracteres")
    @NotBlank(message = "La respuesta no puede estar vacia")
    private String respuesta;

    @Column(nullable = false)
    private int calificacion;

    @FutureOrPresent(message = "La fecha debe ser mayor o igual a la actual")
    @Column(nullable = false)
    private LocalDateTime fecha_calificacion;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Hotel hotel;

    public Comentario( String comentario, int calificacion, Usuario usuario, Hotel hotel) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha_calificacion = LocalDateTime.now();
        this.usuario = usuario;
        this.hotel = hotel;
    }
}
