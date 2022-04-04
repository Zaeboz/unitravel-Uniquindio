package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Hotel implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @ToString.Include
    @Column(nullable = false, length = 100)
    private String nombre;

    @ToString.Include
    @Column(nullable = false, length = 100)
    private String direccion;

    @ToString.Include
    @Column(nullable = false, length = 100)
    private String telefono;

    @Positive
    @ToString.Include
    @NonNull
    @Column(nullable = false)
    private int numEstrellas;

    @ManyToOne
    private AdministradorHotel administradorHotel;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "hotel")
    private List<Habitacion> habitaciones;

    @OneToMany(mappedBy = "hotel")
    private List<Comentario> comentarios;

    @ManyToMany(mappedBy = "hoteles")
    private List<Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "hotel")
    private List<Foto> fotosHotel;
}