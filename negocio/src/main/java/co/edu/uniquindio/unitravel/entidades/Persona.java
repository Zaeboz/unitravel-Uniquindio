package co.edu.uniquindio.unitravel.entidades;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
   @Cascade(org.hibernate.annotations.CascadeType.DELETE)
   private String cedula;

   @Column(length = 100, nullable = false)
   private String nombre;

   @Email
   @Column(length = 150, nullable = false, unique = true)
   private String email;

   @Column(length = 50, nullable = false)
   private String password;

}
