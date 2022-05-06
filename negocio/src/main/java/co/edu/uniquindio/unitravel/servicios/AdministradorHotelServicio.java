package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.dto.HotelMayorCalificacionDTO;
import co.edu.uniquindio.unitravel.entidades.Cama;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;

import java.util.List;

public interface AdministradorHotelServicio {

    Hotel crearHotel(Hotel hotel) throws Exception;

    void eliminarHotel(int id) throws Exception;

    void modificarHotel(Hotel hotel,int id) throws Exception;

    Hotel obtenerHotel(int id) throws Exception;

    List<Hotel> listarHoteles(String idAdmin);

    List<Usuario> usuariosComentarios(int idHotel);

    List<Hotel> obtenerHotelesPorEstrellas(int estrellas);

    String obtenerNombreCiudadHotel(int idHotel) throws Exception;

    int obtenerCantidadComentario(int idHotel);

    int obtenerCalificacionPromedio(int idHotel);

    List<HotelMayorCalificacionDTO> obtenerHotelMayorCalificacion(int idCiudad);

    List<Hotel> listarHotelesPorCiudad(String nombreCiudad);

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


}
