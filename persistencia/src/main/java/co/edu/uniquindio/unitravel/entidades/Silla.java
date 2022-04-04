package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Silla implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private String codigo;

    @Column(nullable = false)
    @ToString.Include
    private String posicion;

    @Column(nullable = false)
    @Positive
    @NonNull
    @ToString.Include
    private Double precio;

    @ManyToOne
    private Vuelo vuelo;

    @OneToMany(mappedBy = "silla")
    private List<ReservaSilla> reservasSillas;
}