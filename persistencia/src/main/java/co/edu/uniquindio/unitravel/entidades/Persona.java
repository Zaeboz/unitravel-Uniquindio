package co.edu.uniquindio.unitravel.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Persona implements Serializable {

   @Id
   private String cedula;

   private String nombre;

   private String email;

   @ElementCollection
   private Map<String, String> telefono;

   public Persona() {
   }

   public Persona(String cedula, String nombre, String email, Map<String, String> telefono) {
      this.cedula = cedula;
      this.nombre = nombre;
      this.email = email;
      this.telefono = telefono;
   }

}
