package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    private final AdministradorHotelRepo administradorHotelRepo;
    private final CiudadRepo ciudadRepo;
    private final SillaRepo sillaRepo;
    private final VueloRepo vueloRepo;
    private final AdministradorRepo administradorRepo;

    public AdministradorServicioImpl(AdministradorHotelRepo administradorHotelRepo, CiudadRepo ciudadRepo, SillaRepo sillaRepo, VueloRepo vueloRepo, AdministradorRepo administradorRepo) {
        this.administradorHotelRepo = administradorHotelRepo;
        this.ciudadRepo = ciudadRepo;
        this.sillaRepo = sillaRepo;
        this.vueloRepo = vueloRepo;
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Administrador obtenerAdministradorEmail(String email) throws Exception {

        Optional<Administrador> administrador = administradorRepo.findByEmail(email);

        if (administrador.isEmpty()){
            throw new Exception("El administrador no existe");
        }

        return administrador.get();
    }

    @Override
    public void registrarAdmin(Administrador a){
        administradorRepo.save(a);
    }

    @Override
    public List<Administrador> listarAdmins() {
        return administradorRepo.findAll();
    }

    @Override
    public void registrarAdminHotel(AdministradorHotel a){

        administradorHotelRepo.save(a);
    }

    @Override
    public void modificarAdminHotel(AdministradorHotel a,String id) throws Exception {

        AdministradorHotel administradorHotel = obtenerAdminHotel(id);

        if (administradorHotel!=null){

            Optional<AdministradorHotel> admEmail = administradorHotelRepo.findByEmail(a.getEmail());
            if (admEmail.isPresent()) {
                throw new Exception("El correo ya se encuentra registrado");
            }

            administradorHotelRepo.save(a);
        }else {
            throw new Exception("El administrador que desea modificar no existe");
        }
    }

    @Override
    public void eliminarAdminHotel(String idAdminHotel) throws Exception {

        AdministradorHotel administradorHotel = obtenerAdminHotel(idAdminHotel);

        if (administradorHotel!=null){
            administradorHotelRepo.delete(administradorHotel);
        }else{
            throw new Exception("El administrador a eliminar no existe");
        }
    }

    @Override
    public AdministradorHotel obtenerAdminHotel(String idAdminHotel) throws Exception {

        Optional<AdministradorHotel> administrador = administradorHotelRepo.findById(idAdminHotel);

        if(administrador.isEmpty()){
            throw new Exception("No existe un administrador de hotel con la cédula ingresado");
        }

        return administrador.get();
    }

    @Override
    public List<AdministradorHotel> listarAdminsHotel() {
        return administradorHotelRepo.findAll();
    }

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad) throws Exception {

        Ciudad buscada= ciudadRepo.findByNombre(ciudad.getNombre());
        if (buscada!=null) {
            throw new Exception("La ciudad ya existe");
        }

        return ciudadRepo.save(ciudad);
    }

    @Override
    public void actualizarCiudad(Ciudad ciudad) throws Exception {
        ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(int id) throws Exception {

        Ciudad ciudadEncontrada=obtenerCiudad(id);

        if (ciudadEncontrada != null){
            ciudadRepo.delete(ciudadEncontrada);
        }else{
            throw new Exception("La ciudad que desea eliminar no existe");
        }
    }

    @Override
    public Ciudad obtenerCiudad(int id) throws Exception {

        Optional<Ciudad> ciudad= ciudadRepo.findById(id);

        if (ciudad.isEmpty()){
            throw new Exception("La ciudad buscada no existe");
        }

        return ciudad.get();
    }

    @Override
    public List<Hotel> obtenerHoteles(String nombreCiudad){
        return ciudadRepo.obtenerHoteles(nombreCiudad);
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Silla crearSilla(Silla s) throws Exception {

        Optional<Silla> buscada = sillaRepo.findById(s.getCodigo());
        if (buscada.isPresent()){
            throw new Exception("La silla a registrar ya existe");
        }
        return sillaRepo.save(s);
    }

    @Override
    public void modificarSilla(Silla s,String id) throws Exception {

        Silla silla = obtenerSilla(id);

        if (silla != null){

            Optional<Silla> buscada = sillaRepo.findById(s.getCodigo());
            if (buscada.isPresent()){
                throw new Exception("La silla ya existe");
            }
            sillaRepo.save(s);
        }else {
            throw new Exception("La silla a modificar no existe");
        }
    }

    @Override
    public Silla obtenerSilla(String id) throws Exception {

        Optional<Silla> silla = sillaRepo.findById(id);

        if (silla.isEmpty()){
            throw new Exception("La silla a buscada no existe");
        }
        return silla.get();
    }

    @Override
    public void eliminarSilla(String id) throws Exception {

        Silla silla = obtenerSilla(id);

        if (silla !=null){
            sillaRepo.delete(silla);
        }else{
            throw new Exception("La silla a eliminar no existe");
        }

    }

    @Override
    public List<Silla> listarSillas() {
        return sillaRepo.findAll();
    }

    @Override
    public Vuelo crearVuelo(Vuelo v) throws Exception {

        Optional<Vuelo> vuelo = vueloRepo.findById(v.getCodigo());
        if (vuelo.isPresent()){
            throw new Exception("El vuelo ya existe");
        }

        return vueloRepo.save(v);
    }

    @Override
    public void modificarVuelo(Vuelo v,String id) throws Exception {

        Vuelo vuelo = obtenerVuelo(id);

        if (vuelo !=null){
            vueloRepo.save(v);
        }else{
            throw new Exception("El vuelo a modificar no existe");
        }
    }

    @Override
    public void eliminarVuelo(String id) throws Exception {
        Vuelo vuelo = obtenerVuelo(id);

        if (vuelo !=null){
            vueloRepo.delete(vuelo);
        }else{
            throw new Exception("El vuelo a modificar no existe");
        }
    }

    @Override
    public Vuelo obtenerVuelo(String id) throws Exception {

        Optional<Vuelo> vuelo = vueloRepo.findById(id);

        if (vuelo.isEmpty()){
            throw new Exception("El vuelo no existe");
        }
        return vuelo.get();
    }

    @Override
    public List<Vuelo> listarVuelos() {
        return vueloRepo.findAll();
    }

    @Override
    public List<Vuelo> obtenerVuelosPorCiudad(String nombreCiudad) throws Exception {

        Ciudad ciudad = ciudadRepo.findByNombre(nombreCiudad);

        if (ciudad == null){
            throw new Exception("La ciudad no existe");
        }

        return vueloRepo.obtenerVuelos(ciudad.getNombre());
    }
}
