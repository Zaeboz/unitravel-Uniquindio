package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;

import java.util.List;

public interface CaracterirsticaServicio {

    void crearCaracteristica(Caracteristica c);

    void eliminarCaracteristica(int id) throws Exception;

    void modificarCaracteristica(Caracteristica c) throws Exception;

    Caracteristica obtenerCaracteristica(int id) throws Exception;

    List<Caracteristica> listarCaracteristicas();
}
