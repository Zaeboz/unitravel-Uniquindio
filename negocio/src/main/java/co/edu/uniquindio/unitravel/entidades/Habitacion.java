package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

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

    @ManyToMany(mappedBy = "habitaciones")
    @ToString.Exclude
    private List<Caracteristica> caracteristicas;

    @ManyToMany(mappedBy = "habitaciones")
    @ToString.Exclude
    private List<Cama> camas;

    @OneToMany(mappedBy = "habitacion")
    @ToString.Exclude
    private List<Foto> imagenes;

    public Habitacion(@NonNull double precio, @NonNull int capacidad, Hotel hotel) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.hotel = hotel;
        this.caracteristicas = new ArrayList<>();
        this.camas = new ArrayList<>();
        this.imagenes = new ArrayList<>();
    }
}