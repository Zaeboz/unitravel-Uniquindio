package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Reserva;
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

    Optional<Usuario> findByEmailAndPassword(String correo, String password);

    Page<Usuario> findAll(Pageable pageable);

    @Query("select r from Usuario u, IN (u.reservas) r where u.email = :email")
    List<Reserva> obtenerListaReservas(String email);

    @Query("select u.email, c from Usuario u left join u.comentarios  c")
    List<Object[]> obtenerComentarios();

    @Query("select u from Usuario u left join u.reservas r")
    List<Object[]> obtenerReservasTotales();

    @Query("select distinct t from Usuario u join u.telefono t ")
    List<String> obtenerUsuariosTelefono();
}
