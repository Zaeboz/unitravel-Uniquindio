package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.dto.ComentarioDTO;
import co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Telefono;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrar(){
        Usuario usuario = new Usuario("92282","pepito","pepe@gmail.com","12345678");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuarioGuardado);
    }

    @Test
    public void eliminar (){
        Usuario usuario = new Usuario("92282","pepito","pepe@gmail.com","12345678");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        usuarioRepo.delete(usuarioGuardado);

        Optional<Usuario> usuarioBuscado = usuarioRepo.findById("92282");
        Assertions.assertNotNull(usuarioBuscado);
    }

    @Test
    public void actualizar (){
        Usuario usuario = new Usuario("92282","pepito","pepe@gmail.com","12345678");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        usuarioGuardado.setPassword("abc123");
        usuarioRepo.save(usuarioGuardado);

        Usuario usuarioBuscado = usuarioRepo.findById("92282").orElse(null);
        assert usuarioBuscado != null;
        Assertions.assertEquals("abc123",usuarioBuscado.getPassword());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuarios(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        System.out.println(usuarios);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosNombre(){
        List<Usuario> usuarios = usuarioRepo.findAllByNombre("Camila");
        usuarios.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarAutenticacion(){
        Optional<Usuario> usuarioEncontrado = usuarioRepo.findByEmailAndPassword("juanenmanuel@gmail.com","123456");
        System.out.println(usuarioEncontrado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosSort(){
        List<Usuario> usuarios = usuarioRepo.findAll(Sort.by("nombre").descending());
        usuarios.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservas(){
        List<Reserva> reservas = usuarioRepo.obtenerListaReservas("juan@gmail.com");
        reservas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentarios(){
        List<ComentarioDTO> comentariod = usuarioRepo.obtenerComentarios();
        comentariod.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservasTotales(){
        List<ReservasTotalesDTO> reservas = usuarioRepo.obtenerReservasTotales();
        reservas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTelefonosUsuario(){
        List<Telefono> telefonos = usuarioRepo.obtenerTelefonosUsuario("1");
        telefonos.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosPorCiudad(){
        List<Usuario> usuarios = ciudadRepo.obtenerCiudadUsuario("Cali");
        usuarios.forEach(System.out::println);
    }

}