package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Usuario buscarPorEmail(String email);

    Usuario actualizarUsuario(Usuario usuario) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;

    List<Usuario> obtenerUsuarios() throws Exception;

    void eliminarUsuario(String codigo) throws Exception;

    Usuario validarLogin(String email, String contrasena) throws Exception;

    List<Reserva> listarReservasUsuario(String cedula) throws Exception;

    String recuperarContrasena(String email) throws Exception;

    void cambiarContrasena(String email, String contrasena) throws Exception;
}
