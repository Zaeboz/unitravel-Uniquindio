package co.edu.uniquindio.unitravel.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Telefono> telefonos ;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<HistorialPuntos> historialPuntos ;

    @ManyToOne
    private Ciudad ciudad;

    public Usuario(String cedula, String nombre, @Email String email, String pasword) {
        super(cedula, nombre, email, pasword);
    }
}
