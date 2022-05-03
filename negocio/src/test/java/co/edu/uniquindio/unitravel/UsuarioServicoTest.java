package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Telefono;
import co.edu.uniquindio.unitravel.entidades.Usuario;
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

public class UsuarioServicoTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarUsuarioTest() {
        Usuario u = new Usuario("1192786980","Pablito","plabo67@gmail.com","123456789");
        Telefono telefono = new Telefono("123456789",u);
        Telefono telefono2 = new Telefono("123456780",u);

        u.setTelefonos(List.of(new Telefono[]{telefono, telefono2}));

        try {
            Usuario guardado = usuarioServicio.registrarUsuario(u);
            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarUsuarioTest() throws Exception {
        Usuario u = usuarioServicio.obtenerUsuario("1");
        u.setNombre("Pablo");

        try {
            Usuario guardado = usuarioServicio.actualizarUsuario(u);
            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarUsuarioTest() {
        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            Assertions.assertNotNull(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarUsuarioTest() {
        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            usuarioServicio.eliminarUsuario(u.getCedula());
            Assertions.assertNull(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Implementar cascade para que se elimine de todo lado
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservasUsuarioTest() {
        try {
            List<Reserva> reservas = usuarioServicio.listarReservasUsuario("1");
            Assertions.assertNotNull(reservas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void recuperarContrasenaTest() {
        try {
            String nuevaContrasena = usuarioServicio.recuperarContrasena("juanenmanuel@gmail.com");
            Assertions.assertNotNull(nuevaContrasena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarContrasenaTest() {
        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            usuarioServicio.cambiarContrasena(u.getCedula(), "123456");
            Assertions.assertNotNull(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
