package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Usuario;

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

    Comentario registrarComentario(Comentario c) throws  Exception;

    void actualizarComentario(Comentario c,int idComentario) throws  Exception;

    void eliminarComentario(int id) throws  Exception;

    void responderComentario(String respuesta, int idComentario) throws Exception;

    Comentario obtenerComentario(int id) throws  Exception;

    List<Comentario> listarComentarios();

    Reserva hacerReserva(Reserva r) throws Exception;

    void eliminarReserva(int id) throws Exception;

    Reserva modificarReserva() throws Exception;

    Reserva obtenerReserva(int id) throws Exception;

    List<Reserva> obtenerReservas(String emailU);

    List<Hotel> buscarHotelesCiudad(String nombreCiudad) throws Exception;



}
