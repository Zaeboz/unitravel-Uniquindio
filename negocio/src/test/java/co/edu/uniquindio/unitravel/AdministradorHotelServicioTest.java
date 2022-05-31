package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.dto.HotelMayorCalificacionDTO;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.CaracteristicaRepo;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import co.edu.uniquindio.unitravel.servicios.UnitravelServicio;
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

    @Autowired
    private final CaracteristicaRepo caracteristicaRepo;
    @Autowired
    private final UnitravelServicio unitravelServicio;

    public AdministradorHotelServicioTest(CaracteristicaRepo caracteristicaRepo, UnitravelServicio unitravelServicio) {
        this.caracteristicaRepo = caracteristicaRepo;
        this.unitravelServicio = unitravelServicio;
    }


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
            Hotel hotelEncontrado = unitravelServicio.obtenerHotel(1);

            administradorHotelServicio.eliminarHotel(hotelEncontrado.getCodigo());

            Hotel buscado = unitravelServicio.obtenerHotel(1);

            Assertions.assertNull(buscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHotelTest() {

        try {
            Hotel hotelEncontrado = unitravelServicio.obtenerHotel(1);
            hotelEncontrado.setNombre("El castillo");
            administradorHotelServicio.modificarHotel(hotelEncontrado,hotelEncontrado.getCodigo());

            Hotel hotelBuscado = unitravelServicio.obtenerHotel(1);

            Assertions.assertEquals("El castillo",hotelBuscado.getNombre());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosComentarioTest() {

        List<Usuario> lista = administradorHotelServicio.usuariosComentarios(1);
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesEstrellasTest() {

        List<Hotel> lista = administradorHotelServicio.obtenerHotelesPorEstrellas(2);
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerNombreCiudadTest() {
        try {
            String nombre= administradorHotelServicio.obtenerNombreCiudadHotel(1);

            Assertions.assertNotNull(nombre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCalificacionPromedioTest() {

        int calificacionPromedio = administradorHotelServicio.obtenerCalificacionPromedio(1);

        System.out.println(calificacionPromedio);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCantidadComentariosTest() {

        int cantidadComentarios = administradorHotelServicio.obtenerCantidadComentario(1);

        System.out.println(cantidadComentarios);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHotelMayorCalificacionTest() {

        List<HotelMayorCalificacionDTO> lista = administradorHotelServicio.obtenerHotelMayorCalificacion(1);
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesPorCiudadTest() {

        try {
            Ciudad ciudad= administradorServicio.obtenerCiudad(2);

            List<Hotel> hoteles = administradorHotelServicio.listarHotelesPorCiudad(ciudad.getNombre());

            hoteles.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesTest() {

        try {
            AdministradorHotel administradorHotel = administradorServicio.obtenerAdminHotel("4");

            List<Hotel> lista = administradorHotelServicio.listarHotelesAdmin(administradorHotel.getCedula());

            lista.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarHabitacionTest() {

        try {

            Hotel hotel = unitravelServicio.obtenerHotel(1);

            Habitacion habitacion = new Habitacion(60.009, 2, hotel);

            hotel.getHabitaciones().add(habitacion);
            habitacion.setHotel(hotel);

            Habitacion guardado = administradorHotelServicio.crearHabitacion(habitacion);

            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHabitacionTest() {
        try {
            Habitacion habitacion = administradorHotelServicio.obtenerHabitacion(1);

            administradorHotelServicio.eliminarHabitacion(habitacion.getCodigo());

            Habitacion buscado = administradorHotelServicio.obtenerHabitacion(1);

            Assertions.assertNull(buscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHabitacionTest() {

        try {
            Habitacion habitacionEncontrada = administradorHotelServicio.obtenerHabitacion(1);
            habitacionEncontrada.setCapacidad(10);
            administradorHotelServicio.modificarHabitacion(habitacionEncontrada,habitacionEncontrada.getCodigo());

            Habitacion buscada = administradorHotelServicio.obtenerHabitacion(1);

            Assertions.assertEquals(10,buscada.getCapacidad());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHabitacionesTest() {

        List<Habitacion> lista = administradorHotelServicio.listarHabitaciones();

        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHabitacionesHotelTest() {

        try {
            Hotel hotel = unitravelServicio.obtenerHotel(1);

            List<Habitacion> lista = administradorHotelServicio.listarHabitacionesHotel(hotel.getCodigo());

            lista.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCamaTest() {

        try {

            Habitacion habitacion = administradorHotelServicio.obtenerHabitacion(1);

            Cama cama = new Cama("Doble");
            habitacion.getCamas().add(cama);
            cama.getHabitaciones().add(habitacion);

            Cama guardado = administradorHotelServicio.crearCama(cama);

            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCamaTest() {
        try {
            Cama camaEncontrada = administradorHotelServicio.obtenerCama(1);

            administradorHotelServicio.eliminarCama(camaEncontrada.getCodigo());

            Cama buscado = administradorHotelServicio.obtenerCama(1);

            Assertions.assertNull(buscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCamaTest() {

        try {
            Cama camaEncontrada = administradorHotelServicio.obtenerCama(1);
            camaEncontrada.setTipo("Triple");
            administradorHotelServicio.modificarCama(camaEncontrada,camaEncontrada.getCodigo());

            Cama buscada = administradorHotelServicio.obtenerCama(1);

            Assertions.assertEquals("Triple",buscada.getTipo());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCamasTest() {

        List<Cama> lista = administradorHotelServicio.listarCamas();

        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCamasHabitacionTest() {

        try {
            Habitacion habitacion = administradorHotelServicio.obtenerHabitacion(1);

            List<Cama> lista = administradorHotelServicio.listarCamasHabitacion(habitacion.getCodigo());

            lista.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
