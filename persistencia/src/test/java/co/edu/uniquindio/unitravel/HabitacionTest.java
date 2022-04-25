package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.HabitacionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HabitacionTest {

    @Autowired
    private HabitacionRepo habitacionRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    //================================= Método para registrar o crear una habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarHabitacionTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Habitacion habitacionNueva = new Habitacion(188.388,2,hotelNuevo);

        Habitacion habitacionGuardada = habitacionRepo.save(habitacionNueva);

        Assertions.assertNotNull(habitacionGuardada);
    }

    //================================= Método para eliminar o crear una habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHabitacionTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Habitacion habitacionNueva = new Habitacion(188.388,2,hotelNuevo);

        habitacionRepo.save(habitacionNueva);

        habitacionRepo.delete(habitacionNueva);

        Habitacion habitacionBorrada = habitacionRepo.findById(1).orElse(null);

        Assertions.assertNull(habitacionBorrada);
    }

    //================================= Método para actualizar o modificar una habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHabitacionTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Habitacion habitacionNueva = new Habitacion(188.388,2,hotelNuevo);

        Habitacion habitacionGuardada = habitacionRepo.save(habitacionNueva);

        habitacionGuardada.setCapacidad(4);
        habitacionRepo.save(habitacionGuardada);

        Habitacion habitacionBuscada = habitacionRepo.findById(1).orElse(null);

        assert habitacionBuscada != null;
        Assertions.assertEquals(4,habitacionBuscada.getCapacidad());
    }

    //================================= Método para obtener las habitaciones =================================//

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHabitacionesTest(){

        List<Habitacion> lista = habitacionRepo.findAll();
        System.out.println(lista);
    }

}


