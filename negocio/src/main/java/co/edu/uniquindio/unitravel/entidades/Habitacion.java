package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Habitacion implements Serializable{

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Positive
    @Min(50000)
    private double precio;

    @Positive
    @Min(value = 1, message = "La capacidad de la habitacion debe ser mayor a 0")
    private int capacidad;

    @ManyToOne
    private Hotel hotel;

    @ManyToMany
    @ToString.Exclude
    private List<Caracteristica> caracteristicas;

    @ManyToMany
    @ToString.Exclude
    private List<Cama> camas;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> imagenes = new ArrayList<String>();

    public Habitacion( double precio, int capacidad, Hotel hotel) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.hotel = hotel;
        this.caracteristicas = new ArrayList<>();
        this.camas = new ArrayList<>();
        this.imagenes = new ArrayList<>();
    }

    public String getImagenPrincipal() {
        if (imagenes.size() > 0) {
            return imagenes.get(0);
        }
        return "predeterminada.png";
    }
}