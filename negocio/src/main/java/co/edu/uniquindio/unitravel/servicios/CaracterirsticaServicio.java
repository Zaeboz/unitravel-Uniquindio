package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;

import java.util.List;

public interface CaracterirsticaServicio {

    Caracteristica crearCaracteristica(Caracteristica c) throws Exception;

    void eliminarCaracteristica(int id) throws Exception;

    void modificarCaracteristica(Caracteristica c) throws Exception;

    Caracteristica obtenerCaracteristica(int id) throws Exception;

    List<Caracteristica> listarCaracteristicas();
}
