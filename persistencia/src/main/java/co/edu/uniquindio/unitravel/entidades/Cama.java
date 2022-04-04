package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Cama implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private int codigo;

    @Column(nullable = false, length = 50)
    @ToString.Include
    private String tipo;

    @ManyToMany
    private List<Habitacion> habitaciones;
}