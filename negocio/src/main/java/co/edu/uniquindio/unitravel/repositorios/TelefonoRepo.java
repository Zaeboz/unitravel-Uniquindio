package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefonoRepo extends JpaRepository<Telefono,Integer> {

    @Query("select t from Telefono t where t.usuario.cedula =:cedula")
    List<Telefono> obtenerTelefonosUsuarios(String cedula);

}
