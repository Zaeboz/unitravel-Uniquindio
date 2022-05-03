package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vuelo implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String aerolinea;

    @ManyToOne
    private Ciudad ciudadOrigen;

    @ManyToOne
    private Ciudad ciudadDestino;

    @OneToMany(mappedBy = "vuelo")
    @ToString.Exclude
    private List<Silla> sillas;

    public Vuelo(String codigo, String estado, String aerolinea, Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        this.codigo = codigo;
        this.estado = estado;
        this.aerolinea = aerolinea;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.sillas = new ArrayList<>();
    }
}