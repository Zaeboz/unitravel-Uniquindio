package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Habitacion implements Serializable{

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private String numero;

    @Positive
    @NonNull
    @ToString.Include
    private double precio;

    @Positive
    @NonNull
    @ToString.Include
    private int capacidad;

    @ManyToOne
    private Hotel hotel;

    @ManyToMany(mappedBy = "habitaciones")
    private List<Caracteristica> caracteristicas;

    @ManyToMany(mappedBy = "habitaciones")
    private List<Cama> camas;

    @OneToMany(mappedBy = "habitacion")
    private List<Foto> fotosHabitacion;

}