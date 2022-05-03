package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepo extends JpaRepository<Telefono,Integer> {

    //Eliminar los telefonos de un usuario
    @Modifying
    @Query("delete from Telefono t where t.usuario.cedula = :idUsuario")
    void eliminarTelefonos(String idUsuario);
}
