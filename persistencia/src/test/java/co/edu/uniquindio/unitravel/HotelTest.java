package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.dto.HotelMayorCalificacionDTO;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelTest {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    //================================= Método para registrar o crear un hotel =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarHotelTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Hotel hotelGuardado = hotelRepo.save(hotelNuevo);

        Assertions.assertNotNull(hotelGuardado);
    }

    //================================= Método para eliminar un hotel =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHotelTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        hotelRepo.save(hotelNuevo);

        hotelRepo.delete(hotelNuevo);

        Hotel hotelBorrado = hotelRepo.findById(1).orElse(null);

        Assertions.assertNull(hotelBorrado);
    }

    //================================= Método para actualizar o modificar un hotel =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHotelTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Hotel hotelGuardado = hotelRepo.save(hotelNuevo);

        hotelGuardado.setNumEstrellas(2);
        hotelRepo.save(hotelGuardado);

        Hotel hotelBuscado = hotelRepo.findById(1).orElse(null);

        assert hotelBuscado != null;
        Assertions.assertEquals(2,hotelBuscado.getNumEstrellas());
    }

    //================================= Método para obtener los hoteles =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelTest(){

        List<Hotel> lista = hotelRepo.findAll();
        System.out.println(lista);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotelCodigo() {
        Hotel hotel = hotelRepo.findById(1).orElse(null);
        System.out.println(hotel);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotelesEstrellas() {
        List<Hotel> hoteles = hotelRepo.obtenerHotelesEstrellas(5);
        System.out.println(hoteles);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotelesEstrellas2() {
        List<Hotel> hoteles = hotelRepo.findAllByNumEstrellas(3);
        System.out.println(hoteles);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHoteles() {
        List<Hotel> hoteles = hotelRepo.findAll();
        System.out.println(hoteles);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesSort() {
        List<Hotel> hoteles = hotelRepo.findAll(Sort.by("numEstrellas").descending());
        hoteles.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerNombreCiudad() {
        String nombreCiudad = hotelRepo.obtenerNombreCiudad(1);
        System.out.println(nombreCiudad);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHotelesCiudad() {
        List<Hotel> hoteles = hotelRepo.obtenerHotelesCiudad("Cali");
        System.out.println(hoteles);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCantidadComentarios() {
        int cantidadComentarios = hotelRepo.obtenerCantidadComentarios(1);
        System.out.println(cantidadComentarios);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCalificacionPromedio() {
        float calificacion = hotelRepo.obtenerCalificacionPromedio(1);
        System.out.println("La calificación promedio es: "+calificacion);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHotelMayorCalificacionCiudad() {
        List<HotelMayorCalificacionDTO> hoteles = hotelRepo.obtenerHotelMayorCalificacion(1);
        System.out.println(hoteles);
    }
}


