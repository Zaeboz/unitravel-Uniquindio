package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicaRepo extends JpaRepository<Caracteristica, Integer> {

    @Query("SELECT c FROM Caracteristica c WHERE c.tipo = 2")
    List<Caracteristica> listarCaracteristicasHabitaciones();

    @Query("SELECT c FROM Caracteristica c WHERE c.tipo = 1")
    List<Caracteristica> listarCaracteristicasHoteles();

}
