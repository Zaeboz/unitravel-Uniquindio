package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Telefono;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.repositorios.TelefonoRepo;
import co.edu.uniquindio.unitravel.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefonoServicioImpl implements TelefonoServicio{

   private final TelefonoRepo telefonoRepo;
   private final UsuarioRepo usuarioRepo;


    public TelefonoServicioImpl(TelefonoRepo telefonoRepo, UsuarioRepo usuarioRepo) {
        this.telefonoRepo = telefonoRepo;

        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Telefono registrarTelefono(Telefono t) throws Exception {

        return telefonoRepo.save(t);
    }

    @Override
    public void actualizarTelefono(Telefono t, int codigoTelefono) throws Exception {

        Telefono telefono = obtenerTelefono(codigoTelefono);

        if (telefono != null){

            telefonoRepo.save(telefono);
        }
    }

    @Override
    public void eliminarTelefono(int id) throws Exception {

        Telefono telefonoEcncontrado = obtenerTelefono(id);

        if(telefonoEcncontrado != null){
            telefonoRepo.delete(telefonoEcncontrado);
        } else {
            throw new Exception("No se encontro el telefono");
        }
    }

    @Override
    public Telefono obtenerTelefono(int id) throws Exception {

        Optional<Telefono> telefono = telefonoRepo.findById(id);

        if(telefono.isEmpty()){
            throw new Exception("No se encontro el telefono");
        }

        return telefono.get();
    }

    @Override
    public List<Telefono> listarTelefonos() {
        return telefonoRepo.findAll();
    }

    @Override
    public List<Telefono> listarTelefonosUsuario(String cedula) throws Exception {

        Optional<Usuario> u = usuarioRepo.findById(cedula);

        if (u.isEmpty()){
            throw new Exception("El usuario no existe");
        }
        return telefonoRepo.obtenerTelefonosUsuarios(u.get().getCedula());
    }


}
