package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface AdministradorServicio {

    AdministradorHotel registrarAdminHotel(AdministradorHotel a) throws Exception;

    void modificarAdminHotel(AdministradorHotel a,String id) throws Exception;

    void eliminarAdminHotel(String idAdminHotel) throws Exception;

    AdministradorHotel obtenerAdminHotel(String idAdminHotel) throws Exception;

    List<AdministradorHotel> listarAdminsHotel();

    Ciudad registrarCiudad(Ciudad ciudad) throws Exception;

    void actualizarCiudad(Ciudad ciudad) throws Exception;

    void eliminarCiudad(int id) throws Exception;

    Ciudad obtenerCiudad(int id) throws Exception;

    List<Ciudad> listarCiudades();

    Silla crearSilla(Silla s) throws Exception;

    void modificarSilla(Silla s,String id) throws Exception;

    Silla obtenerSilla(String id) throws Exception;

    void eliminarSilla(String id) throws Exception;

    List<Silla> listarSillas();

    Vuelo crearVuelo(Vuelo v) throws Exception;

    void modificarVuelo(Vuelo v,String id) throws Exception;

    void eliminarVuelo(String id) throws Exception;

    Vuelo obtenerVuelo(String id) throws Exception;

    List<Vuelo> listarVuelos();


}
