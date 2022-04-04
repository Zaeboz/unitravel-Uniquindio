package co.edu.uniquindio.unitravel.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class AdministradorHotel extends Persona implements Serializable {

    @OneToMany(mappedBy = "administradorHotel")
    private List<Hotel> hoteles;
}
