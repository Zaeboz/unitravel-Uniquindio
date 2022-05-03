package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Foto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private Habitacion habitacion;

    @ManyToOne
    private Hotel hotel;

    public Foto(String url) {
        this.url = url;
    }
}