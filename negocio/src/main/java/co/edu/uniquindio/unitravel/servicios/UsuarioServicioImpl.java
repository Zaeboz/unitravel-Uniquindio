package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final TelefonoRepo telefonoRepo;

    private final ComentarioRepo comentarioRepo;

    private final ReservaRepo reservaRepo;
    private final HotelRepo hotelRepo;
    private final HabitacionRepo habitacionRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, TelefonoRepo telefonoRepo, ComentarioRepo comentarioRepo, ReservaRepo reservaRepo, HotelRepo hotelRepo, HabitacionRepo habitacionRepo) {
        this.usuarioRepo = usuarioRepo;
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
        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        return usuario.getReservas();
    }

    @Override
    public String recuperarContrasena(String email) throws Exception{
        Usuario usuario = buscarPorEmail(email);
        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        //Se debe de mandar un email al usuario con la nueva contrasena
        return RandomStringUtils.randomAlphanumeric(10);
    }

    @Override
    public Usuario validarLogin(String email, String contrasena) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findByEmailAndPassword(email, contrasena);

        if(usuario.isEmpty()){
            throw new Exception("Los datos de autenticacion son incorrectos");
        }

        return usuario.get();
    }

    @Override
    public void actualizarConstrasena(String email, String contrasena) throws Exception {
        Usuario usuario = validarLogin(email, contrasena);
        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        usuario.setPassword(contrasena);
        usuarioRepo.save(usuario);
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
    public void responderComentario(String respuesta, int idComentario) throws Exception {
        Comentario comentario = obtenerComentario(idComentario);
        if(comentario == null){
            throw new Exception("El comentario no existe");
        }
        if(respuesta.isEmpty()){
            throw new Exception("La respuesta no puede estar vacia");
        }
        comentario.setRespuesta(respuesta);
        comentarioRepo.save(comentario);
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

    }

    @Override
    public Reserva modificarReserva() throws Exception {
        return null;
    }

    @Override
    public Reserva obtenerReserva(int id) throws Exception {
        return null;
    }

    @Override
    public List<Reserva> obtenerReservas(String emailU) {
        return null;
    }

    @Override
    public List<Hotel> buscarHotelesCiudad(String nombreCiudad) throws Exception {
        return null;
    }

    @Override
    public List<Hotel> buscarHotelesNombre(String nombre) throws Exception {
        return hotelRepo.obtenerHotelesPorNombre(nombre);
    }


    @Override
    public Habitacion buscarHabitacion(int codigo) throws Exception {
        return habitacionRepo.findById(codigo).orElse(null);
    }
    /*@Override
    public Reserva enviarDetalleReserva(String cedula, Reserva reserva) {

        //Este servicio se implementa cuando ya podamos enviar mensajes a un correo

    }*/


}
