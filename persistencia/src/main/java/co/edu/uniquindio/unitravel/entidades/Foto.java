package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Foto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private String codigo;

    @Column(nullable = false)
    @ToString.Include
    private String url;

    @ManyToOne
    private Habitacion habitacion;

    @ManyToOne
    private Hotel hotel;
}