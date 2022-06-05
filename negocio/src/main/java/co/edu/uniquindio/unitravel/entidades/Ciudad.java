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
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 11, unique = true)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 50)
    @EqualsAndHashCode.Include
    @Size(max = 50, message = "El nombre de la ciudad no puede tener mas de 50 caracteres")
    @NotBlank(message = "El nombre de la ciudad no puede estar vacio")
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Hotel> hoteles;

    @OneToMany(mappedBy = "ciudadOrigen",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Vuelo> vuelosOrigen;

    @OneToMany(mappedBy = "ciudadDestino",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Vuelo> vuelosDestino;

    @OneToMany(mappedBy = "ciudad",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Usuario> usuarios;

    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.hoteles = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.vuelosDestino = new ArrayList<>();
        this.vuelosOrigen = new ArrayList<>();
    }
}
