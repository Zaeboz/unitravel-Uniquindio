package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Foto;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.FotoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class FotoServicioTest {

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarImagenTest(){

        try{

            Hotel hotel = administradorHotelServicio.obtenerHotel(1);

            Foto foto = new Foto("addasda");
            foto.setHotel(hotel);
            hotel.getImagenes().add(foto);

            Habitacion habitacion = administradorHotelServicio.obtenerHabitacion(1);

            Foto foto1 = new Foto("addasdadadsad");
            foto.setHabitacion(habitacion);
            habitacion.getImagenes().add(foto1);

            Foto imagenRegistrada= fotoServicio.registrarImagen(foto);
            Foto imageRegistrada = fotoServicio.registrarImagen(foto1);

            Assertions.assertNotNull(imagenRegistrada);
            Assertions.assertNotNull(imageRegistrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarImagenTest(){

        try{

            Foto imagenBuscada= fotoServicio.obtenerImagen(1);
            imagenBuscada.setUrl("dsffgdsfd");

            fotoServicio.actualizarImagen(imagenBuscada);

            Foto imagenActualizada = fotoServicio.obtenerImagen(1);

            Assertions.assertEquals("dsffgdsfd",imagenActualizada.getUrl());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarImagenTest(){

        try{

            Foto imagenBuscada= fotoServicio.obtenerImagen(1);

            fotoServicio.eliminarImagen(imagenBuscada.getCodigo());

            Foto imagenBorrada = fotoServicio.obtenerImagen(1);

            Assertions.assertNull(imagenBorrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerUrlImagenHotelTest(){

        try {
            String url = fotoServicio.obtenerUrlHotel(1);

            Assertions.assertNotNull(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerUrlImagenHabitacionTest(){

        try {
            String url = fotoServicio.obtenerUrlHabitacion(1);

            Assertions.assertNotNull(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarImagenTest(){

        List<Foto> imagenes= fotoServicio.listarImagenes();

        imagenes.forEach(System.out::println);
    }


}
