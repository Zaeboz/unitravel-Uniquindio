package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    @Query("select c.hoteles from Ciudad c where c.nombre = :nombreCiudad")
    List<Hotel> obtenerHoteles(String nombreCiudad);

    @Query("select u from Ciudad c join c.usuarios u where c.nombre = :nombreCiudad")
    List<Usuario> obtenerCiudadUsuario(String nombreCiudad);

}
