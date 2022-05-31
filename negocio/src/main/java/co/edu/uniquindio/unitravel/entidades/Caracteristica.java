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

    //Variable de tipo inte que solo almacena el numero 1 para hotel y el 2 para
    @Column(nullable = false, length = 1)
    private Integer tipo;

    @ManyToMany(mappedBy = "caracteristicas",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Hotel> hoteles = new ArrayList<>();

    @ManyToMany(mappedBy = "caracteristicas",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Habitacion> habitaciones = new ArrayList<>();

    public Caracteristica(String descripcion, Integer tipo) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.hoteles = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
    }
}
