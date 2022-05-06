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
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final HotelRepo hotelRepo;
    private final CiudadRepo ciudadRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, HotelRepo hotelRepo, CiudadRepo ciudadRepo) {
        this.usuarioRepo = usuarioRepo;
        this.hotelRepo = hotelRepo;
        this.ciudadRepo = ciudadRepo;
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
        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        return usuario.getReservas();
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
        return hotelRepo.obtenerHotelesCiudad(nombreCiudad);
    }

}
