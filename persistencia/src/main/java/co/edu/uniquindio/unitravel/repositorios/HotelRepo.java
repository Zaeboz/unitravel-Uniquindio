package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE h.numEstrellas = :estrellas")
    List<Hotel> obtenerHotelesEstrellas(int estrellas);

    List<Hotel> findAllByNumEstrellas(int estrellas);

    @Query("SELECT h.ciudad.nombre FROM Hotel h where h.codigo = :codigoH")
    String obtenerNombreCiudad(Integer codigoH);

    @Query("SELECT h FROM Hotel h WHERE h.ciudad.nombre = :nombreCiudad")
    List<Hotel> obtenerHotelesCiudad(String nombreCiudad);
}
