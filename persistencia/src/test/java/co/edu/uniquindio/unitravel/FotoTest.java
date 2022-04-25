package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Foto;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.FotoRepo;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FotoTest {

    @Autowired
    private FotoRepo fotoRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    //================================= Método para registrar o crear una imagen =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarImagenTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Foto imagenNueva = new Foto("qeqaddaac");
        imagenNueva.setHotel(hotelNuevo);

        Foto imagenGuardada =fotoRepo.save(imagenNueva);

        Assertions.assertNotNull(imagenGuardada);
    }

    //================================= Método para eliminar una imagen =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarImagenTest() {

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Foto imagenNueva = new Foto("qeqaddaac");
        imagenNueva.setHotel(hotelNuevo);

        fotoRepo.save(imagenNueva);

        fotoRepo.delete(imagenNueva);

        Foto imagenBorrada = fotoRepo.findById(1).orElse(null);

        Assertions.assertNull(imagenBorrada);
    }

    //================================= Método para actualizar o modificar una imagen =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarImagenTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        Foto imagenNueva = new Foto("qeqaddaac");
        imagenNueva.setHotel(hotelNuevo);

        Foto imagenGuardada =fotoRepo.save(imagenNueva);

        imagenGuardada.setUrl("aeqwdasd");
        fotoRepo.save(imagenGuardada);

        Foto imagenBuscada= fotoRepo.findById(1).orElse(null);

        assert imagenBuscada != null;
        Assertions.assertEquals("aeqwdasd",imagenBuscada.getUrl());
    }

    //================================= Método para obtener las imágenes =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarImagenesTest(){
        List<Foto> lista = fotoRepo.findAll();
        System.out.println(lista);
    }

    //================================= Método para obtener una imagen de una habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerImagenHabitacionTest(){
        String imagenEncontrada = fotoRepo.obtenerUrlImagenHabitacion(1);

        System.out.println(imagenEncontrada);
    }

    //================================= Método para obtener una imagen de una habitación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerImagenHotelTest(){
        String imagenEncontrada = fotoRepo.obtenerUrlImagenHotel(1);

        System.out.println(imagenEncontrada);
    }


}


