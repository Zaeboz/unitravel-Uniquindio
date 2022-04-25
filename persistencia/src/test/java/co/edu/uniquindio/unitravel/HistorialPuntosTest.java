package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.HistorialPuntosRepo;
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
public class HistorialPuntosTest {

    @Autowired
    private HistorialPuntosRepo historialPuntosRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //================================= Método para registrar o crear un historial =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarHistorialTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        LocalDate fecha1 = LocalDate.of(2022, 6, 1);
        LocalDate fecha2 = LocalDate.of(2022, 10, 1);

        HistorialPuntos historialPuntosNuevo = new HistorialPuntos(2,fecha1,fecha2,usuarioBuscado,hotelNuevo);

        HistorialPuntos historialPuntosGuardado = historialPuntosRepo.save(historialPuntosNuevo);

        Assertions.assertNotNull(historialPuntosGuardado);
    }

    //================================= Método para eliminar o crear un historial =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHistorialTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        LocalDate fecha1 = LocalDate.of(2022, 6, 1);
        LocalDate fecha2 = LocalDate.of(2022, 10, 1);

        HistorialPuntos historialPuntosNuevo = new HistorialPuntos(2,fecha1,fecha2,usuarioBuscado,hotelNuevo);

        historialPuntosRepo.save(historialPuntosNuevo);

        historialPuntosRepo.delete(historialPuntosNuevo);

        HistorialPuntos historialPuntosBorrado = historialPuntosRepo.findById(1).orElse(null);

        Assertions.assertNull(historialPuntosBorrado);
    }

    //================================= Método para actualizar o modificar una habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHistorialTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        LocalDate fecha1 = LocalDate.of(2022, 6, 1);
        LocalDate fecha2 = LocalDate.of(2022, 10, 1);

        HistorialPuntos historialPuntosNuevo = new HistorialPuntos(2,fecha1,fecha2,usuarioBuscado,hotelNuevo);

        HistorialPuntos historialPuntosGuardado = historialPuntosRepo.save(historialPuntosNuevo);

        historialPuntosGuardado.setPuntos(1);
        historialPuntosRepo.save(historialPuntosGuardado);

        HistorialPuntos historialPuntosBuscado = historialPuntosRepo.findById(1).orElse(null);

        assert historialPuntosBuscado != null;
        Assertions.assertEquals(1,historialPuntosBuscado.getPuntos());
    }

    //================================= Método para obtener los historiales =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarhistorialesTest(){

        List<HistorialPuntos> lista = historialPuntosRepo.findAll();
        System.out.println(lista);
    }


}


