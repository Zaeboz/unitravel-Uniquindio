package co.edu.uniquindio.unitravel.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Vuelo implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private String codigo;

    @Column(nullable = false)
    @ToString.Include
    private String estado;

    @Column(nullable = false)
    @ToString.Include
    private String aerolinea;

    @ManyToOne
    private Ciudad ciudadOrigen;

    @ManyToOne
    private Ciudad ciudadDestino;

    @OneToMany(mappedBy = "vuelo")
    private List<Silla> sillas;

}