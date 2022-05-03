package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Cama implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 50)
    private String tipo;

    @ManyToMany
    @ToString.Exclude
    private List<Habitacion> habitaciones;

    public Cama(String tipo) {
        this.tipo = tipo;
        this.habitaciones = new ArrayList<>();
    }
}