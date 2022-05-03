package co.edu.uniquindio.unitravel.servicios;

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
    public Usuario registrarUsuario(Usuario usuario) {

        Usuario buscado = obtenerUsuario(usuario.getCedula());
        if(buscado != null){
            throw new RuntimeException("El usuario ya existe");
        }

        Usuario usuarioEmail = buscarPorEmail(usuario.getEmail());
        if(usuarioEmail != null){
            throw new RuntimeException("El correo del usaurio ya esta registrado");
        }

        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()){
            throw new RuntimeException("El nombre del usuario no puede estar vacio");
        }

        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            throw new RuntimeException("La contraseña del usuario no puede estar vacia");
        }

        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email){
        return usuarioRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        Usuario buscado = obtenerUsuario(usuario.getCedula());
        if(buscado == null){
            throw new RuntimeException("El usuario no existe");
        }
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario obtenerUsuario(String codigo) {
        return usuarioRepo.findByCedula(codigo).orElse(null);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public void eliminarUsuario(String codigo) {
        Usuario usuario = obtenerUsuario(codigo);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }
        usuarioRepo.delete(usuario);
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
    public List<Reserva> listarReservasUsuario(String cedula) {
        Usuario usuario = obtenerUsuario(cedula);
        if(usuario == null){
            throw new RuntimeException("El usuario no existe");
        }
        return usuario.getReservas();
    }

    @Override
    public String recuperarContrasena(String email) {
        Usuario usuario = buscarPorEmail(email);
        if(usuario == null){
            throw new RuntimeException("El usuario no existe");
        }
        //Se debe de mandar un email al usuario con la nueva contrasena
        return RandomStringUtils.randomAlphanumeric(10);
    }

    @Override
    public void cambiarContrasena(String email, String contrasena) throws Exception {
        Usuario usuario = validarLogin(email, contrasena);
        usuario.setPassword(contrasena);
        //Se debe de mandar un email al usuario notificando el cambio de contrasena
        usuarioRepo.save(usuario);
    }

    /*@Override
    public Reserva enviarDetalleReserva(String cedula, Reserva reserva) {

        //Este servicio se implementa cuando ya podamos enviar mensajes a un correo

    }*/


}
