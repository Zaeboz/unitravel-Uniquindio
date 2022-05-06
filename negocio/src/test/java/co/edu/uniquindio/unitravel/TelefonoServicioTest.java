package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Telefono;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.servicios.TelefonoServicio;
import co.edu.uniquindio.unitravel.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class TelefonoServicioTest {

    @Autowired
    private TelefonoServicio telefonoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarTelefonoTest(){

        try {

            Usuario usuario = usuarioServicio.obtenerUsuario("1");

            Telefono telefonoNuevo = new Telefono("3226346138",usuario);

            Telefono telefonoRegistrado = telefonoServicio.registrarTelefono(telefonoNuevo);

            Assertions.assertNotNull(telefonoRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTelefonoTest(){

        try {

            Telefono telefonoEncontrado = telefonoServicio.obtenerTelefono(1);
            telefonoEncontrado.setNumero("322");

            telefonoServicio.actualizarTelefono(telefonoEncontrado,telefonoEncontrado.getCodigo());

            Telefono buscado = telefonoServicio.obtenerTelefono(1);

            Assertions.assertEquals("322",buscado.getNumero());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTelefonoTest(){

        try {

            Telefono telefonoEncontrado = telefonoServicio.obtenerTelefono(1);

            telefonoServicio.eliminarTelefono(telefonoEncontrado.getCodigo());
            Telefono telefonoBorrado = telefonoServicio.obtenerTelefono(1);

            Assertions.assertNull(telefonoBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTelefonosTest(){

        List<Telefono> telefonos= telefonoServicio.listarTelefonos();
        telefonos.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTelefonosUsuarioTest(){

        try {
            Usuario usuario = usuarioServicio.obtenerUsuario("1");

            List<Telefono> telefonos= telefonoServicio.listarTelefonosUsuario(usuario.getCedula());
            telefonos.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
