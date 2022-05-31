package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Comentario;

import java.util.List;

public interface ComentarioServicio {

    Comentario registrarComentario(Comentario c);

    void actualizarComentario(Comentario c,int codigoComentario) throws  Exception;

    void eliminarComentario(int id) throws  Exception;

    Comentario obtenerComentario(int id) throws  Exception;

    List<Comentario> obtenerListaPorCalificacion(int calificacion);

    List<Comentario> listarComentarios();

    List<Comentario> obtenerComentariosHotel(int idHotel) throws  Exception;
}
