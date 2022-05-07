package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.HistorialPuntos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialPuntosRepo extends JpaRepository<HistorialPuntos,Integer> {
    @Query("select h from HistorialPuntos h where h.usuario.cedula = :cedula")
    List<HistorialPuntos> findByCedula(String cedula);

    @Query("select h from HistorialPuntos h where h.usuario.cedula = :cedula and h.estado = 'Activo'")
    List<HistorialPuntos> puntosDiponibles(String cedula);

    @Query("select h from HistorialPuntos h where h.usuario.cedula = :cedula and h.estado = 'Vencido'")
    List<HistorialPuntos> puntosVencidos(String cedula);
}