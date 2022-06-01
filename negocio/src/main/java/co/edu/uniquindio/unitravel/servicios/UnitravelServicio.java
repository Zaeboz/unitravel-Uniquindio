package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface UnitravelServicio {
    List<Ciudad> listarCiudades();

    Ciudad obtenerCiudadCodigo(int id) throws Exception;

    Ciudad obtenerCiudadNombre(String nombre);

    Persona validarLogin(String correo, String password) throws Exception;

    List<Caracteristica> listarCaracteristicasHoteles();

    List<Caracteristica> listarCaracteristicasHabitaciones();

    Caracteristica obtenerCaracteristica(int id) throws Exception;

    List<Cama> listarCamas();

    Cama obtenerCama(int parseInt) throws Exception;

    Hotel obtenerHotel(int id) throws Exception;
}
