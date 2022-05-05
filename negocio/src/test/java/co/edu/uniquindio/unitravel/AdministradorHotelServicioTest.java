package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class AdministradorHotelServicioTest {

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Autowired
    private AdministradorServicio administradorServicio;


    @Test
    @Sql("classpath:dataset.sql")
    public void registrarHotelTest() {

        try {

            AdministradorHotel administradorHotel = administradorServicio.obtenerAdminHotel("4");
            Ciudad ciudad = administradorServicio.obtenerCiudad(1);

            Hotel hotel = new Hotel("El castillo","Calle 27a","7423131",1,administradorHotel,ciudad);

            Hotel guardado = administradorHotelServicio.crearHotel(hotel);

            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHotelTest() {
        try {
            Hotel hotelEncontrado = administradorHotelServicio.obtenerHotel(1);

            administradorHotelServicio.eliminarHotel(hotelEncontrado.getCodigo());

            Hotel buscado = administradorHotelServicio.obtenerHotel(1);

            Assertions.assertNull(buscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHotelTest() {

        try {
            Hotel hotelEncontrado = administradorHotelServicio.obtenerHotel(1);
            hotelEncontrado.setNombre("El castillo");
            administradorHotelServicio.modificarHotel(hotelEncontrado,hotelEncontrado.getCodigo());

            Hotel hotelBuscado = administradorHotelServicio.obtenerHotel(1);

            Assertions.assertEquals("El castillo",hotelBuscado.getNombre());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesTest() {

        try {
            AdministradorHotel administradorHotel = administradorServicio.obtenerAdminHotel("4");

            List<Hotel> lista = administradorHotelServicio.listarHoteles(administradorHotel.getCedula());

            lista.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
