package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaHabitacionTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ReservaHabitacionRepo reservaHabitacionRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    //================================= Método para registrar o crear una reserva habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarReservaHabitacionTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Habitacion habitacionNueva = new Habitacion(188.388,2,hotelNuevo);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        ReservaHabitacion reservaHabitacionNueva = new ReservaHabitacion(100.999F,reservaNueva,habitacionNueva);

        ReservaHabitacion reservaHabitacionGuardada = reservaHabitacionRepo.save(reservaHabitacionNueva);

        Assertions.assertNotNull(reservaHabitacionGuardada);
    }

    //================================= Método para eliminar una reserva habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarReservaHabitacionTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Habitacion habitacionNueva = new Habitacion(188.388,2,hotelNuevo);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        ReservaHabitacion reservaHabitacionNueva = new ReservaHabitacion(100.999F,reservaNueva,habitacionNueva);

        reservaHabitacionRepo.save(reservaHabitacionNueva);

        reservaHabitacionRepo.delete(reservaHabitacionNueva);

        ReservaHabitacion reservaHabitacionBorrada = reservaHabitacionRepo.findById(1).orElse(null);

        Assertions.assertNull(reservaHabitacionBorrada);

    }

    //================================= Método para modificar una reserva habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarReservaHabitacionTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Habitacion habitacionNueva = new Habitacion(188.388,2,hotelNuevo);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        ReservaHabitacion reservaHabitacionNueva = new ReservaHabitacion(100.999F,reservaNueva,habitacionNueva);

        ReservaHabitacion reservaHabitacionGuardada = reservaHabitacionRepo.save(reservaHabitacionNueva);

        reservaHabitacionGuardada.setPrecio(10.999F);
        reservaHabitacionRepo.save(reservaHabitacionGuardada);

        ReservaHabitacion reservaHabitacionBuscada = reservaHabitacionRepo.findById(1).orElse(null);

        assert reservaHabitacionBuscada != null;
        Assertions.assertEquals(10.999F,reservaHabitacionBuscada.getPrecio());
    }

    //================================= Método para obtener las reservas de habitaciones =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservasHabitacionesTest(){

        List<ReservaHabitacion> lista = reservaHabitacionRepo.findAll();
        System.out.println(lista);
    }

}


