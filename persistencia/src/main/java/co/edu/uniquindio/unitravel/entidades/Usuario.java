package co.edu.uniquindio.unitravel.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Usuario extends Persona implements Serializable {

    @ToString.Include
    @ElementCollection
    private List<String> telefono;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    @ManyToOne
    private Ciudad ciudad;

    public Usuario(String cedula, String nombre, @Email String email, String pasword) {
        super(cedula, nombre, email, pasword);
    }
}
