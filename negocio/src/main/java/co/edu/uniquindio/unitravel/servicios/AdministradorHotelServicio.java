package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Hotel;

import java.util.List;

public interface AdministradorHotelServicio {

    Hotel crearHotel(Hotel hotel) throws Exception;

    void eliminarHotel(int id) throws Exception;

    void modificarHotel(Hotel hotel,int id) throws Exception;

    Hotel obtenerHotel(int id) throws Exception;

    List<Hotel> listarHoteles(String codigoAdmin);


}
