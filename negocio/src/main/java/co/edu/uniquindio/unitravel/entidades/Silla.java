package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@ToString
public class Silla implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false)
    private String posicion;

    @Column(nullable = false)
    @Positive
    @NonNull
    private Double precio;

    @ManyToOne
    private Vuelo vuelo;

    @Column
    private boolean reservada;

    @OneToMany(mappedBy = "silla")
    @ToString.Exclude
    private List<ReservaSilla> reservasSillas;

    public Silla(String codigo, String posicion, @NonNull Double precio,Vuelo vuelo) {
        this.codigo = codigo;
        this.posicion = posicion;
        this.precio = precio;
        this.vuelo = new Vuelo();
        this.reservasSillas = new ArrayList<>();
    }
}