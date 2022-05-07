package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {

     /*
    @Query("select new co.edu.uniquindio.unitravel.dto.ReservaDTO(r.usuario.nombre, r.fechaReserva, h.habitacion) from Reserva r join r.reservaHabitaciones h where h.habitacion.hotel.codigo = :idHotel and r.fechaInicio < :fecha")
    List<ReservaDTO> obtenerReservasPorHotel(Integer idHotel, LocalDate fecha);
      */

    @Query("select distinct rh.habitacion from ReservaHabitacion rh where not (:fecha1 > rh.fechaInicio and :fecha2 < rh.fechaFin) and rh.habitacion.hotel.ciudad.nombre = :ciudad")
    List<Habitacion> obtenerReservaHabitaciones(LocalDate fecha1, LocalDate fecha2, String ciudad);

    @Query("select (select sum(rh.precio) from ReservaHabitacion rh where rh.reserva = r group by r), (select sum(rs.precio) from ReservaSilla rs where rs.reserva = r group by r) from Reserva r  where r.usuario.cedula = :cedula and r.codigo = :codigoReserva")
    Object[] obtenerTotalPorReserva(String cedula, Integer codigoReserva);

}
