package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.dto.ComentarioDTO;
import co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO;
import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Usuario buscarPorEmail(String email);

    List<Usuario> listarPorNombre(String nombre);

    List<Reserva> listarReservas(String email) throws Exception;

    List<ComentarioDTO> obtenerComentarios();

    List<ReservasTotalesDTO> obtenerReservasTotales();

    List<Telefono> obtenerTelefonosU(String cedula) throws Exception;

    Usuario actualizarUsuario(Usuario usuario) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;

    void eliminarUsuario(String codigo) throws Exception;

    List<Usuario> obtenerUsuarios();

    List<Usuario> obtenerUsuariosCiudad(String nombreCiudad);

    List<Reserva> listarReservasUsuario(String cedula) throws Exception;

    Reserva hacerReserva(Reserva r) throws Exception;

    void eliminarReserva(int id) throws Exception;

    Reserva modificarReserva() throws Exception;

    Reserva obtenerReserva(int id) throws Exception;

    List<Reserva> obtenerReservas(String emailU);

    List<Hotel> buscarHotelesCiudad(String nombreCiudad) throws Exception;



}
