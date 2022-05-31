package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.ComentarioServicio;
import co.edu.uniquindio.unitravel.servicios.UnitravelServicio;
import co.edu.uniquindio.unitravel.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UnitravelServicio unitravelServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarComentarioTest(){

        try {

            LocalDate date = LocalDate.parse("2020-01-08");

            Usuario usuario= usuarioServicio.obtenerUsuario("1");
            Hotel hotel = unitravelServicio.obtenerHotel(1);

            Comentario comentarioNuevo= new Comentario("Buen hotel",3,date,usuario,hotel);

            Comentario comentarioRegistrado = comentarioServicio.registrarComentario(comentarioNuevo);

            Assertions.assertNotNull(comentarioRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarComentarioTest(){

        try {

            Comentario comentario= comentarioServicio.obtenerComentario(1);

            comentario.setComentario("Es interesante");

            comentarioServicio.actualizarComentario(comentario,comentario.getCodigo());

            Comentario comentarioEncontrado = comentarioServicio.obtenerComentario(1);

            Assertions.assertEquals("Es interesante",comentarioEncontrado.getComentario());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarComentarioTest(){

        try {

            Comentario comentarioEncontrado = comentarioServicio.obtenerComentario(1);

            comentarioServicio.eliminarComentario(comentarioEncontrado.getCodigo());
            Comentario comentarioBorrado = comentarioServicio.obtenerComentario(1);

            Assertions.assertNull(comentarioBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosCalificacionTest(){

        List<Comentario> lista = comentarioServicio.obtenerListaPorCalificacion(2);
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosTest(){

        List<Comentario> comentarios = comentarioServicio.listarComentarios();

        comentarios.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosHotelTest(){

        try {
            Hotel hotel = unitravelServicio.obtenerHotel(1);
            List<Comentario> comentarios = comentarioServicio.obtenerComentariosHotel(hotel.getCodigo());

            comentarios.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
