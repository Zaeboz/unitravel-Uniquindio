package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Usuario buscarPorEmail(String email);

    Usuario actualizarUsuario(Usuario usuario) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;

    void eliminarUsuario(String codigo) throws Exception;

    List<Usuario> obtenerUsuarios();

    List<Reserva> listarReservasUsuario(String cedula) throws Exception;

    String recuperarContrasena(String email) throws Exception;

    Usuario validarLogin(String email, String contrasena) throws Exception;

    void actualizarConstrasena(String email, String contrasena) throws Exception;

    Comentario registrarComentario(Comentario c) throws  Exception;

    public Comentario actualizarComentario(Comentario c, int idComentario) throws Exception;

    void eliminarComentario(int id) throws  Exception;

    void responderComentario(String respuesta, int idComentario) throws Exception;

    Comentario obtenerComentario(int id) throws  Exception;

    List<Comentario> listarComentarios();

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
