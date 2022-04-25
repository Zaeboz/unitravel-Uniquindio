package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    //================================= Método para registrar o crear una ciudad =================================//
    @Test
    public void registrarCiudadTest(){

        Ciudad ciudadNueva = new Ciudad("Armenia");

        Ciudad ciudadGuardada = ciudadRepo.save(ciudadNueva);

        Assertions.assertNotNull(ciudadGuardada);
    }

    //================================= Método para eliminar una ciudad =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCiudadTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;
        ciudadRepo.delete(ciudadEncontrada);

        Ciudad ciudadBorrada= ciudadRepo.findById(1).orElse(null);

        Assertions.assertNull(ciudadBorrada);
    }

    //================================= Método para actualizar o modificar una ciudad =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCiudadTest(){

        Ciudad ciudadEncontrada = ciudadRepo.findById(1).orElse(null);
        assert ciudadEncontrada != null;

        ciudadEncontrada.setNombre("Calarcá");
        ciudadRepo.save(ciudadEncontrada);

        Ciudad ciudadBuscada= ciudadRepo.findById(1).orElse(null);

        assert ciudadBuscada != null;
        Assertions.assertEquals("Calarcá",ciudadBuscada.getNombre());
    }

    //================================= Método para obtener las ciudades =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCiudadesTest(){

        List<Ciudad> lista = ciudadRepo.findAll();
        System.out.println(lista);
    }

    //================================= Método para obtener los hoteles por ciudad =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHoteles() {
        List<Hotel> listaHoteles = ciudadRepo.obtenerHoteles("Armenia");
        System.out.println(listaHoteles);
    }

}


