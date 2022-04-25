package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepo extends JpaRepository<Foto, Integer> {

    @Query("select f.url from Foto f where f.hotel.codigo=:idHotel")
    String obtenerUrlImagenHotel(Integer idHotel);

    @Query("select f.url from Foto f where f.habitacion.codigo=:idHabitacion")
    String obtenerUrlImagenHabitacion(Integer idHabitacion);

}
