package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements AdministradorHotelServicio{

    private final HotelRepo hotelRepo;
    private final AdministradorHotelRepo administradorHotelRepo;

    public AdministradorHotelServicioImpl(HotelRepo hotelRepo, AdministradorHotelRepo administradorHotelRepo) {
        this.hotelRepo = hotelRepo;
        this.administradorHotelRepo = administradorHotelRepo;
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
}
