package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Telefono implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(length = 200)
    private String numero;

    @ManyToOne
    private Usuario usuario;

    public Telefono(String numero, Usuario usuario) {
        this.numero = numero;
        this.usuario = usuario;
    }
}
