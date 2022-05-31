package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    @NotBlank(message = "El nombre no puede estar vacío")
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
