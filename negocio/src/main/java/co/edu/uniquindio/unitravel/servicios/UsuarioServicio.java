package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.dto.ComentarioDTO;
import co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO;
import co.edu.uniquindio.unitravel.entidades.*;
import java.time.LocalDate;
import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Usuario buscarPorEmail(String email);

    List<Usuario> listarPorNombre(String nombre);

    List<Reserva> listarReservasCorreo(String email) throws Exception;

    List<ComentarioDTO> obtenerComentarios();

    List<ReservasTotalesDTO> obtenerReservasTotales();

    List<Telefono> obtenerTelefonosU(String cedula) throws Exception;

    Usuario actualizarUsuario(Usuario usuario) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;

    Comentario registrarComentario(Comentario c) throws  Exception;

    Comentario actualizarComentario(Comentario c, int idComentario) throws Exception;

    void eliminarComentario(int id) throws Exception;

    Comentario obtenerComentario(int id) throws Exception;

    List<Comentario> listarComentarios();

    void eliminarUsuario(String codigo) throws Exception;

    List<Usuario> obtenerUsuarios();

    List<Usuario> obtenerUsuariosCiudad(String nombreCiudad);

    List<Reserva> listarReservasUsuario(String cedula) throws Exception;

    Reserva hacerReserva(Reserva r) throws Exception;

    List<Habitacion> listarHabitacionesDisponibles (LocalDate fechaInicio, LocalDate fechaFin, String ciudad) throws Exception;

    void eliminarReserva(int id) throws Exception;

    Reserva modificarReserva(String cedula, Reserva r) throws Exception;

    Reserva obtenerReserva(int id) throws Exception;

    List<Reserva> obtenerReservas(String emailU);

    List<Hotel> buscarHotelesCiudad(String nombreCiudad) throws Exception;

    List<Hotel> buscarHotelesNombre(String nombre);

    Habitacion buscarHabitacion(int codigo) throws Exception;

    List<HistorialPuntos> listarPuntosUsuario(String cedula) throws Exception;

    List<HistorialPuntos> listarPuntosActivos(String cedula) throws Exception;

    List<HistorialPuntos> listarPuntosVencidos(String cedula) throws Exception;

    HistorialPuntos agregarPuntos(String cedula, Reserva r) throws Exception;

    ReservaHabitacion obtenerReservaHabitacion(int id) throws Exception;

    ReservaHabitacion reservarHabitacion(Reserva r, Habitacion h) throws Exception;

    void modificarReservaHabitacion(ReservaHabitacion rh, Habitacion h) throws Exception;

    ReservaSilla registrarReservaSilla(int idReserva,Silla silla,String idVuelo) throws Exception;

    List<Hotel> listarHoteles() ;
}
