package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.dto.ComentarioDTO;
import co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO;
import co.edu.uniquindio.unitravel.entidades.Persona;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Telefono;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {

    List<Usuario> findAllByNombre(String nombre);

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> findAll(Pageable pageable);

    @Query("select r from Usuario u join u.reservas r where u.email = :email")
    List<Reserva> obtenerListaReservas(String email);

    @Query("select new co.edu.uniquindio.unitravel.dto.ComentarioDTO(u.email, c) from Usuario u left join u.comentarios  c")
    List<ComentarioDTO> obtenerComentarios();

    @Query("select new co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO(u,r) from Usuario u left join u.reservas r")
    List<ReservasTotalesDTO> obtenerReservasTotales();

    @Query("SELECT t from Telefono t left join t.usuario u where u.cedula = :cedula")
    List<Telefono> obtenerTelefonosUsuario(String cedula);
    Persona findByEmailAndPassword(String correo, String password);
}
