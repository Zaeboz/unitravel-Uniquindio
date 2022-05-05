package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    @Query("select c from Comentario c where c.calificacion > ?1")
    List<Comentario> obtenerListaPorCalificacion(int calificacion);

    @Query("select c.usuario from Comentario c where c.hotel.codigo = :idHotel")
    List<Usuario> usuariosComentarios(Integer idHotel);
}
