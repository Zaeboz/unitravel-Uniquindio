package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.dto.HotelMayorCalificacionDTO;
import co.edu.uniquindio.unitravel.entidades.Cama;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements AdministradorHotelServicio{

    private final HotelRepo hotelRepo;
    private final AdministradorHotelRepo administradorHotelRepo;
    private final HabitacionRepo habitacionRepo;
    private final CamaRepo camaRepo;
    private final ComentarioRepo comentarioRepo;

    public AdministradorHotelServicioImpl(HotelRepo hotelRepo, AdministradorHotelRepo administradorHotelRepo, HabitacionRepo habitacionRepo, CamaRepo camaRepo, ComentarioRepo comentarioRepo) {
        this.hotelRepo = hotelRepo;
        this.administradorHotelRepo = administradorHotelRepo;
        this.habitacionRepo = habitacionRepo;
        this.camaRepo = camaRepo;
        this.comentarioRepo = comentarioRepo;
    }


    @Override
    public Hotel crearHotel(Hotel hotel) throws Exception {

        Hotel buscado = obtenerHotel(hotel.getCodigo());
        if(buscado != null){
            throw new Exception("El hotel ya existe");
        }

        return hotelRepo.save(hotel);
    }

    @Override
    public void eliminarHotel(int id) throws Exception {

        Hotel hotelEncontrado = obtenerHotel(id);

        if (hotelEncontrado!=null){
            hotelRepo.delete(hotelEncontrado);
        }else {
            throw new Exception("El hotel a eliminar no existe");
        }
    }

    @Override
    public void modificarHotel(Hotel hotel,int id) throws Exception {

        Hotel hotelEncontrado = obtenerHotel(id);

        if (hotelEncontrado!=null){
            hotelRepo.save(hotelEncontrado);
        }else{
            throw new Exception("El hotel a modificar no existe");
        }
    }

    @Override
    public Hotel obtenerHotel(int id) throws Exception {

        Optional<Hotel> hotel = hotelRepo.findById(id);

        if (hotel.isEmpty()){
            throw new Exception("El hotel no existe");
        }

        return hotel.get();
    }

    @Override
    public List<Hotel> listarHoteles(String idAdmin) {
        return administradorHotelRepo.obtenerHotelesAdmin(idAdmin);
    }

    @Override
    public List<Usuario> usuariosComentarios(int idHotel) {
        return comentarioRepo.usuariosComentarios(idHotel) ;
    }

    @Override
    public List<Hotel> obtenerHotelesPorEstrellas(int estrellas) {
        return hotelRepo.obtenerHotelesEstrellas(estrellas);
    }

    @Override
    public String obtenerNombreCiudadHotel(int idHotel) throws Exception{

        String nombre = hotelRepo.obtenerNombreCiudad(idHotel);

        if (nombre==null){
            throw new Exception("No es valida");
        }

        return nombre;
    }

    @Override
    public int obtenerCantidadComentario(int idHotel) {
        return hotelRepo.obtenerCantidadComentarios(idHotel);
    }

    @Override
    public int obtenerCalificacionPromedio(int idHotel) {
        return hotelRepo.obtenerCalificacionPromedio(idHotel);
    }

    @Override
    public List<HotelMayorCalificacionDTO> obtenerHotelMayorCalificacion(int idCiudad) {
        return hotelRepo.obtenerHotelMayorCalificacion(idCiudad);
    }

    @Override
    public List<Hotel> listarHotelesPorCiudad(String nombreCiudad) {
        return  hotelRepo.obtenerHotelesCiudad(nombreCiudad);
    }

    @Override
    public Habitacion crearHabitacion(Habitacion h) throws Exception {

        Optional<Habitacion> habitacion = habitacionRepo.findById(h.getCodigo());
        if(habitacion.isPresent()){
            throw new Exception("La habitación ya existe");
        }

        return habitacionRepo.save(h);

    }

    @Override
    public void eliminarHabitacion(int id) throws Exception {

        Habitacion habitacionEncontrada = obtenerHabitacion(id);

        if (habitacionEncontrada!=null){
            habitacionRepo.delete(habitacionEncontrada);
        }else{
            throw new Exception("La habitación no existe");
        }

    }

    @Override
    public void modificarHabitacion(Habitacion h, int id) throws Exception {

        Habitacion habitacionEncontrada = obtenerHabitacion(id);

        if (habitacionEncontrada!=null){
            habitacionRepo.save(h);
        }else {
            throw new Exception("La habitación no existe");
        }
    }

    @Override
    public Habitacion obtenerHabitacion(int id) throws Exception {

        Optional<Habitacion> habitacion = habitacionRepo.findById(id);

        if(habitacion.isEmpty()){
            throw new Exception("La habitación no existe");
        }
        return habitacion.get();
    }

    @Override
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepo.findAll();
    }

    @Override
    public List<Habitacion> listarHabitacionesHotel(int idHotel) throws Exception {

        Hotel hotel = obtenerHotel(idHotel);
        List<Habitacion> habitaciones = new ArrayList<>();

        if (hotel!=null){
            habitaciones =hotelRepo.obtenerHabitacionesHotel(idHotel);
        }else {
            throw new Exception("El hotel no existe");
        }

        return habitaciones;
    }

    @Override
    public Cama crearCama(Cama c) throws Exception {

        Optional<Cama> cama = camaRepo.findById(c.getCodigo());
        if(cama.isPresent()){
            throw new Exception("La cama ya existe");
        }
        return camaRepo.save(c);
    }

    @Override
    public void eliminarCama(int id) throws Exception {

        Cama camaEncontrada = obtenerCama(id);

        if (camaEncontrada!=null){
            camaRepo.delete(camaEncontrada);
        }else{
            throw new Exception("La cama a eliminar no existe");
        }
    }

    @Override
    public void modificarCama(Cama c,int id) throws Exception {

        Cama camaEncontrada = obtenerCama(id);

        if (camaEncontrada != null) {
            camaRepo.save(c);
        } else {
            throw new Exception("La cama a modificar no existe");
        }
    }

    @Override
    public Cama obtenerCama(int id) throws Exception {

        Optional<Cama> cama = camaRepo.findById(id);

        if(cama.isEmpty()){
            throw new Exception("La cama buscada no existe ya existe");
        }
        return cama.get();
    }

    @Override
    public List<Cama> listarCamas() {
        return camaRepo.findAll();
    }

    @Override
    public List<Cama> listarCamasHabitacion(int idHabitacion) throws Exception {

        Habitacion habitacion = obtenerHabitacion(idHabitacion);
        List<Cama> camas = new ArrayList<>();

        if (habitacion!=null){
            camas = hotelRepo.obtenerCamasPorHotel(habitacion.getCodigo());
        }else{
            throw new Exception("La habitación no existe");
        }

        return camas;
    }
}
