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
    @NonNull
    private double precio;

    @Positive
    @NonNull
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

    public Habitacion(@NonNull double precio, @NonNull int capacidad, Hotel hotel) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.hotel = hotel;
        this.caracteristicas = new ArrayList<>();
        this.camas = new ArrayList<>();
        this.imagenes = new ArrayList<>();
    }
}