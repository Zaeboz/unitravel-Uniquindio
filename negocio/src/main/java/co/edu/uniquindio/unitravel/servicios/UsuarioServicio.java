package co.edu.uniquindio.unitravel.servicios;

<<<<<<< HEAD
import co.edu.uniquindio.unitravel.dto.ComentarioDTO;
import co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO;
=======
>>>>>>> 968f295acb75cbcbabc904f949c88660b9ec07a6
import co.edu.uniquindio.unitravel.entidades.*;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Usuario buscarPorEmail(String email);

    List<Usuario> listarPorNombre(String nombre);

    List<Reserva> listarReservas(String email) throws Exception;

    List<ComentarioDTO> obtenerComentarios();

    List<ReservasTotalesDTO> obtenerReservasTotales();

    List<Telefono> obtenerTelefonosU(String cedula) throws Exception;

<<<<<<< HEAD
    Usuario actualizarUsuario(Usuario usuario) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;
=======
    Usuario validarLogin(String email, String contrasena) throws Exception;

    void actualizarConstrasena(String email, String contrasena) throws Exception;

    Comentario registrarComentario(Comentario c) throws  Exception;

    public Comentario actualizarComentario(Comentario c, int idComentario) throws Exception;
>>>>>>> 968f295acb75cbcbabc904f949c88660b9ec07a6

    void eliminarUsuario(String codigo) throws Exception;

    List<Usuario> obtenerUsuarios();

    List<Usuario> obtenerUsuariosCiudad(String nombreCiudad);

    List<Reserva> listarReservasUsuario(String cedula) throws Exception;

    public Reserva hacerReserva(Reserva r) throws Exception;

    List<Habitacion> listarHabitacionesDisponibles (LocalDate fechaInicio, LocalDate fechaFin) throws Exception;

    void eliminarReserva(int id) throws Exception;

    Reserva modificarReserva() throws Exception;

    Reserva obtenerReserva(int id) throws Exception;

    List<Reserva> obtenerReservas(String emailU);

    List<Hotel> buscarHotelesCiudad(String nombreCiudad) throws Exception;


    List<Hotel> buscarHotelesNombre(String nombre) throws Exception;

    Habitacion buscarHabitacion(int codigo) throws Exception;
}
