package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String direccion;

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
    @ToString.Exclude
    private List<Habitacion> habitaciones;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comentario> comentarios;

    @ManyToMany
    @ToString.Exclude
    private List<Caracteristica> caracteristicas;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> imagenes = new ArrayList<String>();

    @Lob
    private String descripcion;

    public Hotel(String nombre, String direccion, String telefono, @NonNull int numEstrellas, AdministradorHotel administradorHotel, Ciudad ciudad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numEstrellas = numEstrellas;
        this.administradorHotel = administradorHotel;
        this.ciudad = ciudad;
        this.habitaciones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.caracteristicas = new ArrayList<>();
        this.imagenes = new ArrayList<>();
    }

    public String getImagenPrincipal() {
        if (imagenes.size() > 0) {
            return imagenes.get(0);
        }
        return "predeterminada.png";
    }
}