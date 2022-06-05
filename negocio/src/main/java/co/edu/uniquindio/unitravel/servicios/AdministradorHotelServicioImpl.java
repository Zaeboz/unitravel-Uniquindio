package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.dto.HotelMayorCalificacionDTO;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements AdministradorHotelServicio{

    private final HotelRepo hotelRepo;
    private final AdministradorHotelRepo administradorHotelRepo;
    private final HabitacionRepo habitacionRepo;
    private final CamaRepo camaRepo;
    private final ComentarioRepo comentarioRepo;
    @Autowired
    private UnitravelServicio unitravelServicio;

    public AdministradorHotelServicioImpl(HotelRepo hotelRepo, AdministradorHotelRepo administradorHotelRepo, HabitacionRepo habitacionRepo, CamaRepo camaRepo, ComentarioRepo comentarioRepo, CiudadRepo ciudadRepo) {
        this.hotelRepo = hotelRepo;
        this.administradorHotelRepo = administradorHotelRepo;
        this.habitacionRepo = habitacionRepo;
        this.camaRepo = camaRepo;
        this.comentarioRepo = comentarioRepo;
    }


    @Override
    public Hotel crearHotel(Hotel hotel) throws Exception {

        if(hotel.getCiudad() == null){
            throw new Exception("El hotel no tiene ciudad");
        }
        if(hotel.getDireccion() == null){
            throw new Exception("El hotel no tiene direccion");
        }
        if(hotel.getNombre() == null){
            throw new Exception("El hotel no tiene nombre");
        }
        if(hotel.getTelefono() == null){
            throw new Exception("El hotel no tiene telefono");
        }
        return hotelRepo.save(hotel);
    }

    @Override
    public void eliminarHotel(int id) throws Exception {

        Hotel hotelEncontrado = unitravelServicio.obtenerHotel(id);

        if (hotelEncontrado!=null){
            hotelRepo.delete(hotelEncontrado);
        }else {
            throw new Exception("El hotel a eliminar no existe");
        }
    }

    @Override
    public void modificarHotel(Hotel hotel,int id) throws Exception {

        Hotel hotelEncontrado = unitravelServicio.obtenerHotel(id);

        if (hotelEncontrado!=null){
            hotelRepo.save(hotelEncontrado);
        }else{
            throw new Exception("El hotel a modificar no existe");
        }
    }

    @Override
    public List<Hotel> listarHotelesAdmin(java.lang.String idAdmin) {
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
    public java.lang.String obtenerNombreCiudadHotel(int idHotel) throws Exception{

        java.lang.String nombre = hotelRepo.obtenerNombreCiudad(idHotel);

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
    public int obtenerCalificacionPromedio(int idHotel) throws Exception {

        Integer calification;
        Optional<Hotel> hotelEncontrado= hotelRepo.findById(idHotel);

        if (hotelEncontrado.isPresent()){

            calification = hotelRepo.obtenerCalificacionPromedio(hotelEncontrado.get().getCodigo());
        }else{
            throw new Exception("El hotel no fue encontrado");
        }

        return Objects.requireNonNullElse(calification, 0);

    }

    @Override
    public List<HotelMayorCalificacionDTO> obtenerHotelMayorCalificacion(int idCiudad) {
        return hotelRepo.obtenerHotelMayorCalificacion(idCiudad);
    }

    @Override
    public List<Hotel> listarHotelesPorCiudad(java.lang.String nombreCiudad) {
        return  hotelRepo.obtenerHotelesCiudad(nombreCiudad);
    }

    @Override
    public Habitacion crearHabitacion(Habitacion h) throws Exception {

        if(h.getCapacidad() == 0){
            throw new Exception("La habitación no tiene capacidad");
        }
        if(h.getPrecio() == 0){
            throw new Exception("La habitación no tiene precio");
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

        Hotel hotel = unitravelServicio.obtenerHotel(idHotel);
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

    @Override
    public AdministradorHotel obtenerAdminHotel(java.lang.String codigo) throws Exception {
        return administradorHotelRepo.findById(codigo).orElse(null);
    }
}
