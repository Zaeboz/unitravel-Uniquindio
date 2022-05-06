package co.edu.uniquindio.unitravel;

<<<<<<< HEAD
import co.edu.uniquindio.unitravel.dto.ComentarioDTO;
import co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
=======
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
>>>>>>> 968f295acb75cbcbabc904f949c88660b9ec07a6
import co.edu.uniquindio.unitravel.servicios.EmailService;
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
public class UsuarioServicoTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private EmailService emailService;

    @Autowired
    AdministradorHotelServicio hotelServicio;

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
    public void enviarCorreoTest() {
        emailService.enviarEmail("Prueba", "Este es un mensaje", "juane.gutierrezs@uqvirtual.edu.co");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosTest() {
        List<Usuario> lista = usuarioServicio.obtenerUsuarios();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosCiudadTest(){
        List<Usuario> lista = usuarioServicio.obtenerUsuariosCiudad("Bogota");
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesPorCiudadTest() {

        try {
            Ciudad ciudad= administradorServicio.obtenerCiudad(2);

            List<Hotel> hoteles = usuarioServicio.buscarHotelesCiudad(ciudad.getNombre());

            hoteles.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosPorNombreTest() {

       List<Usuario> lista=usuarioServicio.listarPorNombre("Camila");
       lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservasUTest() {

        try {
            List<Reserva> lista = usuarioServicio.listarReservas("juanenmanuel@gmail.com");
            lista.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComentariosTest() {

        List<ComentarioDTO> lista = usuarioServicio.obtenerComentarios();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerReservasTotalesTest() {
        List<ReservasTotalesDTO> lista = usuarioServicio.obtenerReservasTotales();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTelefonosUsuarioTest() {
        try {
            List<Telefono> lista = usuarioServicio.obtenerTelefonosU("1");
            lista.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

<<<<<<< HEAD
=======
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerUsuariosTest() {
        try {
            List<Usuario> usuarios = usuarioServicio.obtenerUsuarios();
            Assertions.assertNotNull(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarComentarioTest() {
        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            Hotel h = hotelServicio.obtenerHotel(1);
            LocalDate fecha = LocalDate.now();
            Comentario comentario = new Comentario("Excelente hotel",5,fecha,u,h);
            Comentario guardado = usuarioServicio.registrarComentario(comentario);
            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void hacerReservaTest() {
        LocalDate fecha = LocalDate.now();
        LocalDate fechaInicio = LocalDate.now().plusDays(1);
        LocalDate fechaFin = LocalDate.now().plusDays(4);
        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            Reserva reserva = new Reserva(fecha,fechaInicio,fechaFin,0,"en proceso",3,u);
            Habitacion habitacion = usuarioServicio.buscarHabitacion(1);
            ReservaHabitacion rh = new ReservaHabitacion(habitacion.getPrecio(),reserva,habitacion);
            Reserva guardado = usuarioServicio.hacerReserva(reserva);
            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
>>>>>>> 968f295acb75cbcbabc904f949c88660b9ec07a6
}
