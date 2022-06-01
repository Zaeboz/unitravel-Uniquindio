package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
    @Size(max = 100, message = "El nombre del hotel debe tener entre 5 y 100 caracteres")
    @NotBlank(message = "El nombre del hotel no puede estar vacio")
    private String nombre;

    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "La direccion del hotel debe tener entre 10 y 100 caracteres")
    @NotBlank(message = "La direccion del hotel no puede estar vacia")
    private String direccion;

    @Column(nullable = false, length = 10)
    @Size(max = 10, message = "El telefono del hotel debe tener entre 7 y 10 caracteres")
    @NotBlank(message = "El telefono del hotel no puede estar vacio")
    private String telefono;

    @Positive
    @Max(value = 5, message = "El numero de estrellas debe ser menor o igual a 5")
    @ToString.Include
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
    @Column(nullable = false)
    @Size(max = 10000, message = "La descripcion del hotel debe tener entre 10 y 10000 caracteres")
    @NotBlank(message = "La descripcion del hotel no puede estar vacia")
    private String descripcion;

    public Hotel(String nombre, String direccion, String telefono, int numEstrellas, AdministradorHotel administradorHotel, Ciudad ciudad) {
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