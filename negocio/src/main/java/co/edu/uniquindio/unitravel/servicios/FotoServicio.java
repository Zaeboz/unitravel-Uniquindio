package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Foto;

import java.util.List;

public interface FotoServicio {

    Foto registrarImagen(Foto f) throws Exception;

    void actualizarImagen(Foto f) throws Exception;

    void eliminarImagen (int id) throws Exception;

    Foto obtenerImagen(int id) throws Exception;

    List<Foto> listarImagenes() ;

}
