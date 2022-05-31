package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre de la aerolinea es obligatorio")
    @Size(max = 50, message = "El nombre de la aerolinea no puede tener mas de 50 caracteres")
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