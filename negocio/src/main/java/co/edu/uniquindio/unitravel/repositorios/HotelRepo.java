package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.dto.HotelMayorCalificacionDTO;
import co.edu.uniquindio.unitravel.entidades.Cama;
import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE h.numEstrellas = :estrellas")
    List<Hotel> obtenerHotelesEstrellas(int estrellas);

    @Query("SELECT h.ciudad.nombre FROM Hotel h where h.codigo = :codigoH")
    String obtenerNombreCiudad(Integer codigoH);

    @Query("SELECT h FROM Hotel h WHERE h.ciudad.nombre = :nombreCiudad")
    List<Hotel> obtenerHotelesCiudad(String nombreCiudad);

    @Query("SELECT COUNT(c) FROM Hotel h join h.comentarios c where h.codigo=:codigoH")
    Integer obtenerCantidadComentarios(int codigoH);

    @Query("select avg(c.calificacion) from Hotel h join h.comentarios c where h.codigo=:codigoH")
    Integer obtenerCalificacionPromedio(int codigoH);

    @Query("select new co.edu.uniquindio.unitravel.dto.HotelMayorCalificacionDTO(h, avg(c.calificacion)) from Hotel h join h.comentarios c where h.ciudad.codigo =:idCiudad group by h.codigo order by avg(c.calificacion) desc ")
    List<HotelMayorCalificacionDTO> obtenerHotelMayorCalificacion(Integer idCiudad);

    @Query("select c from Hotel h join h.comentarios c where h.codigo = :idHotel")
    List<Comentario>obtenerComentariosHotel(int idHotel);

    @Query("select c from Cama c join c.habitaciones h where h.codigo =:idH")
    List<Cama> obtenerCamasPorHotel(int idH);

    @Query("select h from Habitacion h where h.hotel.codigo =:idHotel")
    List<Habitacion> obtenerHabitacionesHotel(int idHotel);

    @Query("select h from Hotel h where h.nombre like concat('%',:nombre,'%')")
    List<Hotel> obtenerHotelesPorNombre(String nombre);
}
