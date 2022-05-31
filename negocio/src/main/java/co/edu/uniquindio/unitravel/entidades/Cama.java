package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(max = 50, message = "El nombre de la cama no puede tener mas de 50 caracteres")
    private String tipo;

    @ManyToMany(mappedBy = "camas")
    @ToString.Exclude
    private List<Habitacion> habitaciones = new ArrayList<>();

    public Cama(String tipo) {
        this.tipo = tipo;
        this.habitaciones = new ArrayList<>();
    }
}