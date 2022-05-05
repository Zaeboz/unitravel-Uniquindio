package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.repositorios.TelefonoRepo;
import co.edu.uniquindio.unitravel.repositorios.UsuarioRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final TelefonoRepo telefonoRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, TelefonoRepo telefonoRepo) {
        this.usuarioRepo = usuarioRepo;
        this.telefonoRepo = telefonoRepo;
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
    public Comentario registrarComentario(Comentario c) throws Exception {
        return null;
    }

    @Override
    public void actualizarComentario(Comentario c, int idComentario) throws Exception {

    }

    @Override
    public void eliminarComentario(int id) throws Exception {

    }

    @Override
    public void responderComentario(String respuesta, int idComentario) throws Exception {

    }

    @Override
    public Comentario obtenerComentario(int id) throws Exception {
        return null;
    }

    @Override
    public List<Comentario> listarComentarios() {
        return null;
    }

    @Override
    public Reserva hacerReserva(Reserva r) throws Exception {
        return null;
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

    /*@Override
    public Reserva enviarDetalleReserva(String cedula, Reserva reserva) {

        //Este servicio se implementa cuando ya podamos enviar mensajes a un correo

    }*/


}
