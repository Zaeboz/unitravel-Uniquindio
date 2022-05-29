package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    @Query("select c.hoteles from Ciudad c where c.nombre = :nombreCiudad")
    List<Hotel> obtenerHoteles(String nombreCiudad);

    @Query("select u from Ciudad c join c.usuarios u where c.nombre = :nombreCiudad")
    List<Usuario> obtenerCiudadUsuario(String nombreCiudad);

    Ciudad findByNombre(String nombre);

    //agrupen por ciudad
    @Query("select c from Ciudad c join c.hoteles h join h.habitaciones hh join ReservaHabitacion rh where (rh.fechaFin > :fechaInicio and rh.fechaInicio < :fechaFin) order by count(rh) desc")
    List<Ciudad> obtenerCiudadesMasReservadas(LocalDate fechaInicio, LocalDate fechaFin);


}
