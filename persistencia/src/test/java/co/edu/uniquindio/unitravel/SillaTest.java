package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.SillaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SillaTest {

    @Autowired
    private SillaRepo sillaRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    //================================= Método para registrar o crear una silla =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarSillaTest(){

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        Silla sillaNueva = new Silla("1a","Medio",100.899,vueloNuevo);

        Assertions.assertNotNull(sillaRepo.save(sillaNueva));
    }

    //================================= Método para eliminar una silla =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSillaTest(){

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        Silla sillaNueva = new Silla("1a","Medio",100.899,vueloNuevo);

        sillaRepo.save(sillaNueva);

        sillaRepo.delete(sillaNueva);

        Silla sillaBorrada = sillaRepo.findById("1a").orElse(null);

        Assertions.assertNull(sillaBorrada);
    }

    //================================= Método para modificar una silla =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSillaTest(){

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        Silla sillaNueva = new Silla("1a","Medio",100.899,vueloNuevo);

        Silla sillaGuardada = sillaRepo.save(sillaNueva);

        sillaGuardada.setPrecio(40.099);
        sillaRepo.save(sillaGuardada);

        Silla sillaBuscada = sillaRepo.findById("1a").orElse(null);

        assert sillaBuscada != null;
        Assertions.assertEquals(40.099,sillaBuscada.getPrecio());
    }

    //================================= Método para obtener las sillas =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarSillasTest(){

        List<Silla> lista = sillaRepo.findAll();
        System.out.println(lista);
    }

}


