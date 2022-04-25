package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Cama;
import co.edu.uniquindio.unitravel.repositorios.CamaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CamaTest {

    @Autowired
    private CamaRepo camaRepo;

    //================================= Método para registrar o crear una cama =================================//
    @Test
    public void registrarCamaTest(){

        Cama camaNueva = new Cama("Doble");

        Cama camaGuardada = camaRepo.save(camaNueva);

        Assertions.assertNotNull(camaGuardada);
    }

    //================================= Método para eliminar una cama =================================//
    @Test
    public void eliminarAdministradorTest() {

        Cama camaNueva = new Cama("Doble");

        camaRepo.save(camaNueva);

        camaRepo.delete(camaNueva);

        Cama camaBorrada= camaRepo.findById(1).orElse(null);

        Assertions.assertNull(camaBorrada);
    }

    //================================= Método para actualizar o modificar una cama =================================//
    @Test
    public void actualizarCamaTest() {

        Cama camaNueva = new Cama("Doble");

        Cama camaGuardada = camaRepo.save(camaNueva);

        camaGuardada.setTipo("Sencilla");
        camaRepo.save(camaGuardada);

        Cama camaBuscada= camaRepo.findById(1).orElse(null);

        assert camaBuscada != null;
        Assertions.assertEquals("Sencilla",camaBuscada.getTipo());
    }

    //================================= Método para obtener las camas =================================//

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCamasTest(){

        List<Cama> lista = camaRepo.findAll();
        System.out.println(lista);
    }

}


