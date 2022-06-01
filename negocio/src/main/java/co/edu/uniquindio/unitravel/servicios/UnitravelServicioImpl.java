package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitravelServicioImpl implements UnitravelServicio {

    @Autowired
    private final CiudadRepo ciudadRepo;
    @Autowired
    private final UsuarioRepo usuarioRepo;

    @Autowired
    private final CaracteristicaRepo caracteristicaRepo;
    @Autowired
    private final CamaRepo camasRepo;
    @Autowired
    private final HotelRepo hotelRepo;
    @Autowired
    private final AdministradorHotelRepo adminHoteRepo;
    @Autowired
    private final AdministradorRepo adminRepo;

    public UnitravelServicioImpl(CiudadRepo ciudadRepo, UsuarioRepo usuarioRepo, CaracteristicaRepo caracteristicaRepo, CamaRepo camasRepo, HotelRepo hotelRepo, AdministradorHotelRepo adminHoteRepo, AdministradorRepo adminRepo) {
        this.ciudadRepo = ciudadRepo;
        this.usuarioRepo = usuarioRepo;
        this.caracteristicaRepo = caracteristicaRepo;
        this.camasRepo = camasRepo;
        this.hotelRepo = hotelRepo;
        this.adminHoteRepo = adminHoteRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public Ciudad obtenerCiudadCodigo(int id) throws Exception {
        Ciudad c = ciudadRepo.findById(id).orElse(null);
        if(c == null){
            throw new Exception("La ciudad no existe");
        }
        return c;
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Ciudad obtenerCiudadNombre(String ciudad) {

        return ciudadRepo.findByNombre(ciudad);
    }

    @Override
    public Persona validarLogin(String correo, String password) throws Exception {
        Persona usuario = usuarioRepo.findByEmailAndPassword(correo, password);

        if (usuario == null){
            usuario = adminHoteRepo.findByEmailAndPassword(correo, password);
        }
        if (usuario == null){
            usuario = adminRepo.findByEmailAndPassword(correo, password);
        }
        if (usuario == null){
            throw new Exception("Los datos de auntenticacion son incorrectos");
        }

        return usuario;
    }

    @Override
    public List<Caracteristica> listarCaracteristicasHoteles(){
        return caracteristicaRepo.listarCaracteristicasHoteles();

    }

    @Override
    public List<Caracteristica> listarCaracteristicasHabitaciones(){
        return caracteristicaRepo.listarCaracteristicasHabitaciones();
    }

    @Override
    public Caracteristica obtenerCaracteristica(int id) throws Exception {
        Caracteristica c = caracteristicaRepo.findById(id).orElse(null);
        if(c == null){
            throw new Exception("La caracteristica no existe");
        }
        return c;
    }

    @Override
    public List<Cama> listarCamas() {
        return camasRepo.findAll();
    }

    @Override
    public Cama obtenerCama(int parseInt) throws Exception {
        Cama c = camasRepo.findById(parseInt).orElse(null);
        if(c == null){
            throw new Exception("La cama no existe");
        }
        return c;
    }

    @Override
    public Hotel obtenerHotel(int id) throws Exception {

        Optional<Hotel> hotel = hotelRepo.findById(id);

        if (hotel.isEmpty()){
            throw new Exception("El hotel no existe");
        }

        return hotel.get();
    }

}
