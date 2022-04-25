package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.dto.ReservaDTO;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.ReservaRepo;
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
public class ReservaTest {

    @Autowired
    private ReservaRepo reservaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //================================= Método para registrar o crear una reserva =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarReservaTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        Reserva reservaGuardada = reservaRepo.save(reservaNueva);

        Assertions.assertNotNull(reservaGuardada);
    }

    //================================= Método para eliminar una reserva =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarReservaTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        reservaRepo.save(reservaNueva);

        reservaRepo.delete(reservaNueva);

        Reserva reservaBorrada = reservaRepo.findById(1).orElse(null);

        Assertions.assertNull(reservaBorrada);

    }

    //================================= Método para modificar una reserva =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarReservaTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        LocalDate fechaR = LocalDate.of(2022, 5, 1);
        LocalDate fechaI = LocalDate.of(2022, 10, 1);
        LocalDate fechaF = LocalDate.of(2022, 11, 1);

        Reserva reservaNueva = new Reserva(fechaR,fechaI,fechaF,100.999,"En proceso",3,usuarioBuscado);

        Reserva reservaGuardada = reservaRepo.save(reservaNueva);

        reservaGuardada.setCantidadPersonas(1);
        reservaRepo.save(reservaGuardada);

        Reserva reservaBuscada = reservaRepo.findById(1).orElse(null);

        assert reservaBuscada != null;
        Assertions.assertEquals(1,reservaBuscada.getCantidadPersonas());
    }

    //================================= Método para obtener las reservas =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservasTest(){

        List<Reserva> lista = reservaRepo.findAll();
        System.out.println(lista);
    }

 /*
    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservas() {

        LocalDate fecha = LocalDate.of(2022, 10, 1);

        List<ReservaDTO> reservas = reservaRepo.obtenerReservasPorHotel(1, fecha);
        reservas.forEach(System.out::println);
    }
  */

}


