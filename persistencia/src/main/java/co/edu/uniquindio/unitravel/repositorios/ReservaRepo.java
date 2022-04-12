package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {

    @Query("select r.usuario, r.fechaReserva, h.habitacion from Reserva r join r.reservaHabitaciones h where h.habitacion.hotel.codigo = :idHotel and r.fechaInicio < :fechaFin")
    List<Object[]> obtenerReservasPorHotel(Integer idHotel, LocalDate fecha);

}
