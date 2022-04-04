package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ciudad implements Serializable {

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 11, unique = true)
    @EqualsAndHashCode.Include
    private int codigo;

    @ToString.Include
    @Column(nullable = false, length = 50)
    @EqualsAndHashCode.Include
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Hotel> hoteles;

    @OneToMany(mappedBy = "ciudadOrigen")
    private List<Vuelo> vuelosOrigen;

    @OneToMany(mappedBy = "ciudadDestino")
    private List<Vuelo> vuelosDestino;

    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuarios;
}
