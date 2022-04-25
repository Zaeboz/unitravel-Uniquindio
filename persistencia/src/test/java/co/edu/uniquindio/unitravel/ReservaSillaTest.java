package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.ReservaSillaRepo;
import co.edu.uniquindio.unitravel.repositorios.UsuarioRepo;
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
public class ReservaSillaTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ReservaSillaRepo reservaSillaRepo;

    @Autowired
    private CiudadRepo ciudadRepo;


    //================================= Método para registrar o crear una reserva silla =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarReservaSillaTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        Silla sillaNueva = new Silla("1a","Medio",100.899,vueloNuevo);

        ReservaSilla reservaSillaNueva = new ReservaSilla(100.899F,sillaNueva,reservaNueva);

        ReservaSilla reservaSillaGuardada = reservaSillaRepo.save(reservaSillaNueva);

        Assertions.assertNotNull(reservaSillaGuardada);
    }

    //================================= Método para eliminar una reserva silla =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarReservaSillaTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        Silla sillaNueva = new Silla("1a","Medio",100.899,vueloNuevo);

        ReservaSilla reservaSillaNueva = new ReservaSilla(100.899F,sillaNueva,reservaNueva);

        reservaSillaRepo.save(reservaSillaNueva);

        reservaSillaRepo.delete(reservaSillaNueva);

        ReservaSilla reservaSillaBorrada = reservaSillaRepo.findById(1).orElse(null);

        Assertions.assertNull(reservaSillaBorrada);
    }

    //================================= Método para modificar una reserva silla =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarReservaSillaTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        Silla sillaNueva = new Silla("1a","Medio",100.899,vueloNuevo);

        ReservaSilla reservaSillaNueva = new ReservaSilla(100.899F,sillaNueva,reservaNueva);

        ReservaSilla reservaSillaGuardada = reservaSillaRepo.save(reservaSillaNueva);

        reservaSillaGuardada.setPrecio(200.999F);
        reservaSillaRepo.save(reservaSillaGuardada);

        ReservaSilla reservaSillaBuscada = reservaSillaRepo.findById(1).orElse(null);

        assert reservaSillaBuscada != null;
        Assertions.assertEquals(200.999F,reservaSillaBuscada.getPrecio());
    }

    //================================= Método para obtener las reservas de sillas =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservasSillasTest(){

        List<ReservaSilla> lista = reservaSillaRepo.findAll();
        System.out.println(lista);
    }

}


