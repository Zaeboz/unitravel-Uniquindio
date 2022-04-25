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
public class CaracteristicaTest {

    @Autowired
    CaracteristicaRepo caracteristicaRepo;

    //================================= Método para registrar o crear una característica =================================//
    @Test
    public void registrarCaracteristicaTest(){

        Caracteristica caracteristicaNueva = new Caracteristica("Penhuose");

        Caracteristica caracteristicaGuardada = caracteristicaRepo.save(caracteristicaNueva);

        Assertions.assertNotNull(caracteristicaGuardada);
    }

    //================================= Método para eliminar una característica =================================//
    @Test
    public void eliminarCaracteristicaTest(){

        Caracteristica caracteristicaNueva = new Caracteristica("Penhuose");

        caracteristicaRepo.save(caracteristicaNueva);

        caracteristicaRepo.delete(caracteristicaNueva);

        Caracteristica caracteristicaBorrada = caracteristicaRepo.findById(1).orElse(null);

        Assertions.assertNull(caracteristicaBorrada);
    }


    //================================= Método para actualizar o modificar una característica =================================//
    @Test
    public void actualizarCaracteristicaTest(){

        Caracteristica caracteristicaNueva = new Caracteristica("Penhuose");

        Caracteristica caracteristicaGuardada = caracteristicaRepo.save(caracteristicaNueva);

        caracteristicaGuardada.setDescripcion("housePen");
        caracteristicaRepo.save(caracteristicaGuardada);

        Caracteristica caracteristicaBuscada = caracteristicaRepo.findById(1).orElse(null);

        assert caracteristicaBuscada != null;
        Assertions.assertEquals("housePen",caracteristicaBuscada.getDescripcion());
    }

    //================================= Método para obtener las características =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCaracteristicasTest(){

        List<Caracteristica> lista = caracteristicaRepo.findAll();
        System.out.println(lista);
    }

}


