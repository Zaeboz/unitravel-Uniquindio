package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdministradorHotel extends Persona implements Serializable {

    @OneToMany(mappedBy = "administradorHotel")
    @ToString.Exclude
    private List<Hotel> hoteles;

    public AdministradorHotel(String cedula, String nombre, @Email String email, String password) {
        super(cedula, nombre, email, password);
        this.hoteles = new ArrayList<>();
    }
}
