package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @Column(length = 10)
    @NotBlank
    @Size(max = 10, min = 7, message = "El numero debe tener entre 7 y 10 digitos")
    private String numero;

    @ManyToOne
    private Usuario usuario;

    public Telefono(String numero, Usuario usuario) {
        this.numero = numero;
        this.usuario = usuario;
    }
}