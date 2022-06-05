package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.dto.HotelMayorCalificacionDTO;
import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface AdministradorHotelServicio {

    Hotel crearHotel(Hotel hotel) throws Exception;

    void eliminarHotel(int id) throws Exception;

    void modificarHotel(Hotel hotel,int id) throws Exception;

    List<Hotel> listarHotelesAdmin(java.lang.String idAdmin);

    List<Usuario> usuariosComentarios(int idHotel);

    List<Hotel> obtenerHotelesPorEstrellas(int estrellas);

    java.lang.String obtenerNombreCiudadHotel(int idHotel) throws Exception;

    int obtenerCantidadComentario(int idHotel);

    int obtenerCalificacionPromedio(int idHotel) throws Exception;

    List<HotelMayorCalificacionDTO> obtenerHotelMayorCalificacion(int idCiudad);

    List<Hotel> listarHotelesPorCiudad(java.lang.String nombreCiudad);

    Habitacion crearHabitacion(Habitacion h) throws Exception;

    void eliminarHabitacion(int id) throws Exception;

    void modificarHabitacion(Habitacion h,int id) throws Exception;

    Habitacion obtenerHabitacion(int id) throws Exception;

    List<Habitacion> listarHabitaciones();

    List<Habitacion> listarHabitacionesHotel(int idHotel) throws Exception;

    Cama crearCama(Cama c) throws Exception;

    void eliminarCama(int id) throws Exception;

    void modificarCama(Cama c,int id) throws Exception;

    Cama obtenerCama(int id) throws Exception;

    List<Cama> listarCamas();

    List<Cama> listarCamasHabitacion(int idHabitacion) throws Exception;

    AdministradorHotel obtenerAdminHotel(java.lang.String codigo) throws Exception;
}
