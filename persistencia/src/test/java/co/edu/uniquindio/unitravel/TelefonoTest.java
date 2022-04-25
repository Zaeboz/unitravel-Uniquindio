package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TelefonoTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private TelefonoRepo telefonoRepo;

    //================================= Método para registrar o crear un teléfono =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarTelefonoTest(){

        Usuario usuario = usuarioRepo.findById("1").orElse(null);

        Telefono  telefonoNuevo = new Telefono("3222842423",usuario);

        Telefono telefonoGuardado = telefonoRepo.save(telefonoNuevo);

        Assertions.assertNotNull(telefonoGuardado);
    }

    //================================= Método para eliminar un teléfono =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTelefonoTest() {

        Usuario usuario = usuarioRepo.findById("1").orElse(null);

        Telefono  telefonoNuevo = new Telefono("3222842423",usuario);

        telefonoRepo.save(telefonoNuevo);

        telefonoRepo.delete(telefonoNuevo);

        Telefono telefonoBorrado = telefonoRepo.findById(1).orElse(null);

        Assertions.assertNull(telefonoBorrado);
    }

    //================================= Método para actualizar o modificar un teléfono =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTelefonoTest(){

        Usuario usuario = usuarioRepo.findById("1").orElse(null);

        Telefono  telefonoNuevo = new Telefono("3222842423",usuario);

        Telefono telefonoGuardado = telefonoRepo.save(telefonoNuevo);

        telefonoGuardado.setDescripcion("101010");
        telefonoRepo.save(telefonoGuardado);

        Telefono telefonoBuscado = telefonoRepo.findById(1).orElse(null);

        assert telefonoBuscado != null;
        Assertions.assertEquals("101010",telefonoBuscado.getDescripcion());
    }

    //================================= Método para obtener los teléfonos =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarTelefonosTest(){

        List<Telefono> lista = telefonoRepo.findAll();
        System.out.println(lista);
    }
}
