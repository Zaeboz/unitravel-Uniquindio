package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Administrador;
import co.edu.uniquindio.unitravel.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;

    //================================= Método para registrar o crear un administrador =================================//
    @Test
    public void registrarAdministradorTest(){

        Administrador administradorNuevo = new Administrador("1","Dayan","d@gmail.com","dayan123");

        Administrador administradorGuardado= administradorRepo.save(administradorNuevo);

        Assertions.assertNotNull(administradorGuardado);
    }

    //================================= Método para eliminar un administrador =================================//
    @Test
    public void eliminarAdministradorTest(){

        Administrador administradorNuevo = new Administrador("1","Dayan","d@gmail.com","dayan123");

        administradorRepo.save(administradorNuevo);

        administradorRepo.delete(administradorNuevo);

        Administrador administradorBorrado= administradorRepo.findById("1").orElse(null);

        Assertions.assertNull(administradorBorrado);
    }

    //================================= Método para actualizar o modificar un administrador =================================//
    @Test
    public void actualizarAdministradorTest(){

        Administrador administradorNuevo = new Administrador("1","Dayan","d@gmail.com","dayan123");

        Administrador administradorGuardado= administradorRepo.save(administradorNuevo);

        administradorGuardado.setEmail("dayan@gmail.com");
        administradorRepo.save(administradorGuardado);

        Administrador administradorBuscado= administradorRepo.findById("1").orElse(null);

        assert administradorBuscado != null;
        Assertions.assertEquals("dayan@gmail.com",administradorBuscado.getEmail());
    }

    //================================= Método para  =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministradorPorCorreo(){

        Administrador administradorNuevo = new Administrador("1","Dayan","d@gmail.com","dayan123");

        administradorRepo.save(administradorNuevo);

        Optional<Administrador> administradorBuscado= administradorRepo.findByEmail("d@gmail.com");

        Assertions.assertNotNull(administradorBuscado);

    }

    //================================= Método para obtener los administradores =================================//

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradoresTest(){

        List<Administrador> lista = administradorRepo.findAll();
        System.out.println(lista);
    }
}


