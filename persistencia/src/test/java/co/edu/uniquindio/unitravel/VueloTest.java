package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.VueloRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VueloTest {

    @Autowired
    private VueloRepo vueloRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    //================================= Método para registrar o crear un vuelo =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarVueloTest(){

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        Vuelo vueloGuardado = vueloRepo.save(vueloNuevo);

        Assertions.assertNotNull(vueloGuardado);

    }

    //================================= Método para eliminar un vuelo =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarVueloTest(){

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        vueloRepo.save(vueloNuevo);

        vueloRepo.delete(vueloNuevo);

        Vuelo vueloBorrado = vueloRepo.findById("1234").orElse(null);
        Assertions.assertNull(vueloBorrado);

    }

    //================================= Método para modificar un vuelo =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarVueloTest(){

        Ciudad ciudadOrigen = ciudadRepo.findById(1).orElse(null);
        Ciudad ciudadDestino = ciudadRepo.findById(2).orElse(null);

        Vuelo vueloNuevo = new Vuelo("1234","Activo","CopaAirlines",ciudadOrigen,ciudadDestino);

        Vuelo vueloGuardado = vueloRepo.save(vueloNuevo);

        vueloGuardado.setAerolinea("Latam");
        vueloRepo.save(vueloGuardado);

        Vuelo vueloBuscado = vueloRepo.findById("1234").orElse(null);

        assert vueloBuscado != null;
        Assertions.assertEquals("Latam", vueloBuscado.getAerolinea());

    }

    //================================= Método para obtener los vuelos =================================//
    @Test
    @Sql("classpath:dataset.sql")
    public void listarVuelosTest(){

        List<Vuelo> lista = vueloRepo.findAll();
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerVuelosPorCiudad(){
        List<Vuelo> vuelos = vueloRepo.obtenerVuelos("Medellin");
        System.out.println(vuelos);
    }

}


