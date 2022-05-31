package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ToString
@AllArgsConstructor
public class Persona implements Serializable {

   @Id
   @EqualsAndHashCode.Include
   @Column(length = 11)
   @Size(max = 11, message = "El n�mero de documento debe tener 11 d�gitos")
   @Cascade(org.hibernate.annotations.CascadeType.DELETE)
   @NotBlank(message = "Debe de escribir su cedula")
   private String cedula;

   @Column(length = 100, nullable = false)
   @Size(max = 100, message = "El nombre debe tener m�ximo 100 caracteres")
   @NotBlank(message = "Debe de escribir su nombre")
   private String nombre;

   @Email(message = "El correo electr�nico no es v�lido")
   @Column(length = 150, nullable = false, unique = true)
   @Size(max = 150, message = "El correo electr�nico debe tener m�ximo 150 caracteres")
   @NotBlank(message = "Debe de escribir su correo electr�nico")
   private String email;

   @Column(length = 50, nullable = false)
   @Size(max = 50, message = "El password debe tener m�ximo 50 caracteres")
   @NotBlank(message = "Debe de escribir su password")
   private String password;

}
