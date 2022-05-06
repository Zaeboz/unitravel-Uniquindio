package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Persona;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import co.edu.uniquindio.unitravel.servicios.EmailService;
import co.edu.uniquindio.unitravel.servicios.PersonaServicio;
import co.edu.uniquindio.unitravel.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;


@SpringBootTest
@Transactional
public class PersonaServicioTest {

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    AdministradorServicio administradorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmailService emailService;

    @Test
    @Sql("classpath:dataset.sql")
    public void LoginTest() {

        try {
            Usuario usuario = usuarioServicio.obtenerUsuario("1");
            AdministradorHotel administradorHotel = administradorServicio.obtenerAdminHotel("4");

            Persona persona = personaServicio.login(usuario.getEmail(),usuario.getPassword());
            Persona persona1 = personaServicio.login(administradorHotel.getEmail(),administradorHotel.getPassword());

            Assertions.assertNotNull(persona);
            Assertions.assertNotNull(persona1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPasswordTest() {

        try {
            Usuario usuario = usuarioServicio.obtenerUsuario("1");

            personaServicio.cambiarPassword(usuario.getEmail(),"clave123");

            String mensaje = "Cambias tu contraseña " + usuario.getPassword();

            emailService.enviarEmail("Cambio de contraseña", mensaje, "juane.gutierrezs@uqvirtual.edu.co");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
