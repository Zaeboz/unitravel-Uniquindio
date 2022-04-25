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
public class ComentarioTest {

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;


    //================================= Método para registrar o crear un comentario =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarComentarioTest() {

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        LocalDate fecha = LocalDate.of(2022, 10, 1);

        Comentario comentarioNuevo = new Comentario("Excelente servicio", 3, fecha, usuarioBuscado, hotelNuevo);

        Comentario comentarioGuardado = comentarioRepo.save(comentarioNuevo);

        Assertions.assertNotNull(comentarioGuardado);

    }

    //================================= Método para eliminar un comentario =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarComentarioTest() {

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo", "Calle 26a", "736423", 3, administradorEncontrado, ciudadEncontrada);

        LocalDate fecha = LocalDate.of(2022, 10, 1);

        Comentario comentarioNuevo = new Comentario("Excelente servicio", 3, fecha, usuarioBuscado, hotelNuevo);

        comentarioRepo.save(comentarioNuevo);

        comentarioRepo.delete(comentarioNuevo);

        Comentario comentarioBorrado = comentarioRepo.findById(1).orElse(null);

        Assertions.assertNull(comentarioBorrado);

    }

    //================================= Método para actualizar o modificar un comentario =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarComentarioTest() {

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        AdministradorHotel administradorEncontrado = administradorHotelRepo.findById("4").orElse(null);

        Hotel hotelNuevo = new Hotel("El castillo","Calle 26a","736423",3,administradorEncontrado,ciudadEncontrada);

        LocalDate fecha = LocalDate.of(2022, 10, 1);

        Comentario comentarioNuevo = new Comentario("Excelente servicio", 3, fecha, usuarioBuscado, hotelNuevo);

        Comentario comentarioGuardado = comentarioRepo.save(comentarioNuevo);

        comentarioGuardado.setCalificacion(1);
        comentarioRepo.save(comentarioGuardado);

        Comentario comentarioBuscado = comentarioRepo.findById(1).orElse(null);

        assert comentarioBuscado != null;
        Assertions.assertEquals(1,comentarioBuscado.getCalificacion());

    }

    //================================= Método para obtener los comentarios =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosTest(){
        List<Comentario> lista = comentarioRepo.findAll();
        System.out.println(lista);
    }

    //================================= Método para obtener los comentarios por calificación =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarPorCalificacionTest(){
        List<Comentario> lista = comentarioRepo.obtenerListaPorCalificacion(2);
        System.out.println(lista);
    }

    //================================= Método para obtener los usuarios que comentaron un hotel =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosComentariosHotelTest(){
        List<Usuario> lista = comentarioRepo.usuariosComentarios(1);
        System.out.println(lista);
    }


}


