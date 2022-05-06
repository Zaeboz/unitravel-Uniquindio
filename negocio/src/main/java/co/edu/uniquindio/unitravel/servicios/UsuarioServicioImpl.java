package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.dto.ComentarioDTO;
import co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Telefono;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import co.edu.uniquindio.unitravel.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final HotelRepo hotelRepo;
    private final CiudadRepo ciudadRepo;
    private final ComentarioRepo comentarioRepo;
    private final ReservaRepo reservaRepo;
    private final HabitacionRepo habitacionRepo;
    private final TelefonoRepo telefonoRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, TelefonoRepo telefonoRepo, CiudadRepo ciudadRepo, ComentarioRepo comentarioRepo, ReservaRepo reservaRepo, HotelRepo hotelRepo, HabitacionRepo habitacionRepo) {
        this.usuarioRepo = usuarioRepo;
        this.ciudadRepo = ciudadRepo;
        this.telefonoRepo = telefonoRepo;
        this.comentarioRepo = comentarioRepo;
        this.reservaRepo = reservaRepo;
        this.hotelRepo = hotelRepo;
        this.habitacionRepo = habitacionRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) throws Exception {

        Usuario buscado = obtenerUsuario(usuario.getCedula());
        if(buscado != null){
            throw new Exception("El usuario ya existe");
        }

        Usuario usuarioEmail = buscarPorEmail(usuario.getEmail());
        if(usuarioEmail != null){
            throw new Exception("El correo del usaurio ya esta registrado");
        }

        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email){
        return usuarioRepo.findByEmail(email).orElse(null);
    }

    @Override
    public List<Usuario> listarPorNombre(String nombre){
        return usuarioRepo.findAllByNombre(nombre);
    }

    @Override
    public List<Reserva> listarReservas(String email) throws Exception {

        Usuario usuario = buscarPorEmail(email);

        if (usuario== null){
            throw new Exception("El usuario no existe");
        }

        return usuarioRepo.obtenerListaReservas(usuario.getEmail());
    }

    @Override
    public List<ComentarioDTO> obtenerComentarios() {
        return usuarioRepo.obtenerComentarios();
    }

    @Override
    public List<ReservasTotalesDTO> obtenerReservasTotales() {
        return usuarioRepo.obtenerReservasTotales();
    }

    @Override
    public List<Telefono> obtenerTelefonosU(String cedula) throws Exception {

        Usuario usuario = obtenerUsuario(cedula);

        if (usuario == null){
            throw new Exception("El usuario no existe");
        }

        return usuarioRepo.obtenerTelefonosUsuario(usuario.getCedula());
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws Exception{
        Usuario buscado = obtenerUsuario(usuario.getCedula());
        if(buscado == null){
            throw new Exception("El usuario no existe");
        }
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findById(codigo);

        if (usuario.isEmpty()){
            throw new Exception("El usuario no existe");
        }

        return usuario.get();
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Usuario> obtenerUsuariosCiudad(String nombreCiudad){
        return ciudadRepo.obtenerCiudadUsuario(nombreCiudad);
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        Usuario usuario = obtenerUsuario(codigo);

        if (usuario == null) {
            throw new Exception("El usuario no existe");
        }
        usuarioRepo.delete(usuario);
    }

    @Override
    public List<Reserva> listarReservasUsuario(String cedula) throws Exception {
        Usuario usuario = obtenerUsuario(cedula);
        if (usuario == null) {
            throw new Exception("El usuario no existe");
        }
        return usuario.getReservas();
    }

    @Override
    public Comentario registrarComentario(Comentario c) throws Exception {
        Usuario usuario = obtenerUsuario(c.getUsuario().getCedula());
        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        if(c.getComentario().isEmpty()){
            throw new Exception("El comentario no puede estar vacio");
        }
        if(c.getCalificacion() < 0 || c.getCalificacion() >= 5){
            throw new Exception("La calificacion debe estar entre 0 y 5");
        }
        return comentarioRepo.save(c);
    }

    @Override
    public Comentario actualizarComentario(Comentario c, int idComentario) throws Exception {
        Comentario comentario = obtenerComentario(idComentario);
        if(comentario == null){
            throw new Exception("El comentario no existe");
        }
        return registrarComentario(c);
    }

    @Override
    public void eliminarComentario(int id) throws Exception {
        Comentario comentario = obtenerComentario(id);
        if(comentario == null){
            throw new Exception("El comentario no existe");
        }
        comentarioRepo.delete(comentario);
    }

    @Override
    public Comentario obtenerComentario(int id) throws Exception {
        Comentario comentario = comentarioRepo.findById(id).orElse(null);
        if(comentario == null){
            throw new Exception("El comentario no existe");
        }
        return comentario;
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }

    @Override
    public Reserva hacerReserva(Reserva r) throws Exception {
        Usuario usuario = obtenerUsuario(r.getUsuario().getCedula());
        Reserva reserva = obtenerReserva(r.getCodigo());
        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        if(r.getFechaFin().isAfter(r.getFechaInicio())){
            throw new Exception("La fecha fin debe ser mayor a la fecha inicio");
        }
        if(r.getFechaInicio().isBefore(LocalDate.now())){
            throw new Exception("La fecha inicio debe ser mayor o igual a la fecha actual");
        }
        if(r.getFechaInicio().isAfter(r.getFechaFin())){
            throw new Exception("La fecha inicio debe ser menor a la fecha fin");
        }
        if(r.getCantidadPersonas()<= 0){
            throw new Exception("La cantidad de personas debe ser mayor a 0");
        }
        if(reserva != null){
            throw new Exception("La reserva ya existe");
        }

        Object[] totales = reservaRepo.obtenerTotalPorReserva(r.getUsuario().getCedula(), r.getCodigo());
        Double totalHotel = (Double) totales[0];
        double total;
        if(totales[1] != null){
            Double totalVuelo = (Double) totales[1];
            total = totalHotel + totalHotel*0.05 + totalVuelo +totalVuelo*0.05;
        } else {
            total = totalHotel + totalHotel*0.05;
        }
        assert false;
        total = total*reserva.getCantidadPersonas();
        assert false;
        reserva.setPrecioTotal(total);
        return reservaRepo.save(reserva);
    }

    @Override
    public List<Habitacion> listarHabitacionesDisponibles(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        return reservaRepo.obtenerReservaHabitaciones(fechaInicio, fechaFin);
    }

    @Override
    public void eliminarReserva(int id) throws Exception {

        Reserva r = obtenerReserva(id);

        if (r!=null){
            reservaRepo.delete(r);
        }else{
            throw new Exception("La reserva no existe");
        }
    }

    @Override
    public Reserva modificarReserva() throws Exception {
        return null;
    }

    @Override
    public Reserva obtenerReserva(int id) throws Exception {

        Optional<Reserva> r = reservaRepo.findById(id);

        if (r.isEmpty()){
            throw new Exception("La reserva no existe");
        }

        return r.get();
    }

    @Override
    public List<Reserva> obtenerReservas(String emailU) {
        return usuarioRepo.obtenerListaReservas(emailU);
    }

    @Override
    public List<Hotel> buscarHotelesCiudad(String nombreCiudad) throws Exception {

        Ciudad ciudad = ciudadRepo.findByNombre(nombreCiudad);

        if (ciudad==null){
            throw new Exception("La ciudad no existe");
        }
        return hotelRepo.obtenerHotelesCiudad(ciudad.getNombre());
    }

    @Override
    public List<Hotel> buscarHotelesNombre(String nombre){

        return hotelRepo.obtenerHotelesPorNombre(nombre);
    }

    @Override
    public Habitacion buscarHabitacion(int codigo) throws Exception {

        Optional<Habitacion> habitacion = habitacionRepo.findById(codigo);

        if (habitacion.isEmpty()){
            throw new Exception("La habitación no existe");
        }
        return habitacion.get();
    }
}
