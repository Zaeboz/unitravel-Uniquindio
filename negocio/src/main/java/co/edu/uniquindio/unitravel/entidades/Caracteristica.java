package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Caracteristica implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @ManyToMany
    @ToString.Exclude
    private List<Hotel> hoteles;

    @ManyToMany
    @ToString.Exclude
    private List<Habitacion> habitaciones;

    public Caracteristica(String descripcion) {
        this.descripcion = descripcion;
        this.hoteles = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
    }
}
