package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;

import java.util.List;

public interface ComentarioServicio {

    void ingresarComentario(Comentario c, Hotel hotel, Usuario u);

    void actualizarComentario(Comentario c,int codigoComentario) throws  Exception;

    void eliminarComentario(int id) throws  Exception;

    Comentario obtenerComentario(int id) throws  Exception;

    List<Comentario> obtenerListaPorCalificacion(int calificacion);

    List<Comentario> listarComentarios();

    List<Comentario> obtenerComentariosHotel(int idHotel) throws  Exception;
}
