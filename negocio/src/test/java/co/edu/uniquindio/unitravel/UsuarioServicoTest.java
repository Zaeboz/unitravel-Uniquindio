package co.edu.uniquindio.unitravel;


import co.edu.uniquindio.unitravel.dto.ComentarioDTO;
import co.edu.uniquindio.unitravel.dto.ReservasTotalesDTO;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.*;
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
    private AdministradorHotelServicio hotelServicio;

    @Autowired
    private UnitravelServicio unitravelServicio;


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
        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosCiudadTest(){
        List<Usuario> lista = usuarioServicio.obtenerUsuariosCiudad("Bogota");
        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesPorCiudadTest() {

        try {
            Ciudad ciudad= administradorServicio.obtenerCiudad(2);

            List<Hotel> hoteles = usuarioServicio.buscarHotelesCiudad(ciudad.getNombre());

            Assertions.assertNotNull(hoteles);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosPorNombreTest() {

       List<Usuario> lista=usuarioServicio.listarPorNombre("Camila");
       Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservasUTest() {

        try {
            List<Reserva> lista = usuarioServicio.listarReservasCorreo("juanenmanuel@gmail.com");
            Assertions.assertNotNull(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComentariosTest() {

        List<ComentarioDTO> lista = usuarioServicio.obtenerComentarios();
        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarComentarioTest() {
        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            Hotel h = unitravelServicio.obtenerHotel(1);
            Comentario comentario = new Comentario("Me encanta el hotel", 4, LocalDate.now(), u, h);
            Assertions.assertNotNull(usuarioServicio.registrarComentario(comentario));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerReservasTotalesTest() {
        List<ReservasTotalesDTO> lista = usuarioServicio.obtenerReservasTotales();
        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTelefonosUsuarioTest() {
        try {
            List<Telefono> lista = usuarioServicio.obtenerTelefonosU("1");
            Assertions.assertNotNull(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
    public void obtenerHotelesNombreTest() {

        List<Hotel> lista = usuarioServicio.buscarHotelesNombre("Hotel el mirador");
        Assertions.assertNotNull(lista);
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
            ReservaHabitacion rh = new ReservaHabitacion(habitacion.getPrecio(),LocalDate.now(),LocalDate.now().plusDays(3),reserva,habitacion);
            reserva.getReservaHabitaciones().add(rh);
            Vuelo vuelo= administradorServicio.obtenerVuelo("1");
            Silla silla = new Silla("Fila 1",20.999,vuelo);
            ReservaSilla rs = new ReservaSilla(silla.getPrecio(),silla,reserva);
            reserva.getReservaSillas().add(rs);
            Reserva guardado = usuarioServicio.hacerReserva(reserva);
            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHabitacionTest() throws Exception {
        Habitacion habitacion = usuarioServicio.buscarHabitacion(1);
        Assertions.assertNotNull(habitacion);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerReservasTest() {

        List<Reserva> lista = usuarioServicio.obtenerReservas("juanenmanuel@gmail.com");
        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarComentarioTest() {

        try {
            Comentario comentario = usuarioServicio.obtenerComentario(1);
            comentario.setComentario("Vacío");

            usuarioServicio.actualizarComentario(comentario,comentario.getCodigo());

            Comentario buscado = usuarioServicio.obtenerComentario(1);

            Assertions.assertEquals("Vacío",buscado.getComentario());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarComentarioTest() {

        try {

            Comentario  comentario = usuarioServicio.obtenerComentario(1);
            usuarioServicio.eliminarComentario(comentario.getCodigo());

            Comentario buscado = usuarioServicio.obtenerComentario(1);

            Assertions.assertNull(buscado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosTest() {

        List<Comentario> lista = usuarioServicio.listarComentarios();
        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHabitacionesDisponiblesTest() {

        try {
            LocalDate fechaInicio = LocalDate.now().plusDays(1);
            LocalDate fechaFin = LocalDate.now().plusDays(4);
            String ciudad = "Bogota";

            List<Habitacion> lista = usuarioServicio.listarHabitacionesDisponibles(fechaInicio,fechaFin,ciudad);
            Assertions.assertNotNull(lista);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarReservaTest() {

        try {
            Reserva reserva= usuarioServicio.obtenerReserva(1);

            usuarioServicio.eliminarReserva(reserva.getCodigo());

            Reserva buscada = usuarioServicio.obtenerReserva(1);

            Assertions.assertNull(buscada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    @Sql("classpath:dataset.sql")
    public void modificarReservaTest() {
        try {
            Reserva reserva= usuarioServicio.obtenerReserva(1);
            reserva.setFechaInicio(LocalDate.now().plusDays(1));
            reserva.setFechaFin(LocalDate.now().plusDays(4));
            Reserva actualizada = usuarioServicio.modificarReserva(reserva.getUsuario().getCedula(),reserva);
            Assertions.assertNotNull(actualizada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPuntosUsuarioTest() {

        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            List<HistorialPuntos> puntos = usuarioServicio.listarPuntosUsuario(u.getCedula());
            Assertions.assertNotNull(puntos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPuntosActivosTest() {

        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            List<HistorialPuntos> puntos = usuarioServicio.listarPuntosActivos(u.getCedula());
            Assertions.assertNotNull(puntos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPuntosVencidosTest() {

        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            List<HistorialPuntos> puntos = usuarioServicio.listarPuntosVencidos(u.getCedula());
            Assertions.assertNotNull(puntos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void agregarPuntosTest() {

        try {
            Usuario u = usuarioServicio.obtenerUsuario("1");
            Reserva r = usuarioServicio.obtenerReserva(1);
            usuarioServicio.agregarPuntos(u.getCedula(),r);
            List<HistorialPuntos> puntos = usuarioServicio.listarPuntosUsuario(u.getCedula());
            Assertions.assertNotNull(puntos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerReservaHabitacionTest() {
        try {
            ReservaHabitacion r = usuarioServicio.obtenerReservaHabitacion(1);
            Assertions.assertNotNull(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void reservaHabitacion(){

        try {
            Habitacion habitacion = hotelServicio.obtenerHabitacion(1);
            Reserva r = usuarioServicio.obtenerReserva(1);

            ReservaHabitacion rh = usuarioServicio.reservarHabitacion(r,habitacion);

            Assertions.assertNotNull(rh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void modificarReservaHabitacion(){

        try {
            Habitacion habitacion = hotelServicio.obtenerHabitacion(1);
            ReservaHabitacion rhEncontrada= usuarioServicio.obtenerReservaHabitacion(1);

            rhEncontrada.setPrecio(20.009);

            usuarioServicio.modificarReservaHabitacion(rhEncontrada,habitacion);

            ReservaHabitacion buscada= usuarioServicio.obtenerReservaHabitacion(1);

            Assertions.assertEquals(20.009,buscada.getPrecio());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarReservaSilla(){

        try {
            Reserva r = usuarioServicio.obtenerReserva(1);
            Vuelo v = administradorServicio.obtenerVuelo("1");
            Silla s= administradorServicio.obtenerSilla("1");

            ReservaSilla rs = usuarioServicio.registrarReservaSilla(r.getCodigo(),s,v.getCodigo());

            Assertions.assertNotNull(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
