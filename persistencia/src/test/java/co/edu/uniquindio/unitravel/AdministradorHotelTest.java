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
public class AdministradorHotelTest {

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;


    @Test
    public void registrarAdministradorHotelTest(){

        AdministradorHotel administradorNuevo = new AdministradorHotel("1","Sebastian","s@gmail.com","21quintero04");

        AdministradorHotel administradorGuardado= administradorHotelRepo.save(administradorNuevo);

        Assertions.assertNotNull(administradorGuardado);
    }

    @Test
    public void eliminarAdministradorTest(){

        AdministradorHotel administradorNuevo = new AdministradorHotel("1","Sebastian","s@gmail.com","21quintero04");

        administradorHotelRepo.save(administradorNuevo);

        administradorHotelRepo.delete(administradorNuevo);

        AdministradorHotel administradorBorrado= administradorHotelRepo.findById("1").orElse(null);

        Assertions.assertNull(administradorBorrado);
    }

    @Test
    public void actualizarAdministradorTest(){

        AdministradorHotel administradorNuevo = new AdministradorHotel("1","Sebastian","s@gmail.com","21quintero04");

        AdministradorHotel administradorGuardado= administradorHotelRepo.save(administradorNuevo);

        administradorGuardado.setEmail("sebas@gmail.com");
        administradorHotelRepo.save(administradorGuardado);

        AdministradorHotel administradorBuscado= administradorHotelRepo.findById("1").orElse(null);

        Assertions.assertEquals("sebas@gmail.com",administradorBuscado.getEmail());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradoresTest(){

        List<AdministradorHotel> lista = administradorHotelRepo.findAll();
        System.out.println(lista);
    }

}


