package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.servicios.CaracterirsticaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class CaracteristicaServicioTest {

    @Autowired
    CaracterirsticaServicio caracterirsticaServicio;

    @Test
    public void registrarCaracteristicaTest(){

        try {

            Caracteristica caracteristica = new Caracteristica("Diversión",1);

            Caracteristica caracteristicaRegistrada = caracterirsticaServicio.crearCaracteristica(caracteristica);

            Assertions.assertNotNull(caracteristicaRegistrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCaracteristicaTest(){

        try {
            Caracteristica caracteristica = caracterirsticaServicio.obtenerCaracteristica(1);

            caracterirsticaServicio.eliminarCaracteristica(caracteristica.getCodigo());

            Caracteristica buscado = caracterirsticaServicio.obtenerCaracteristica(1);

            Assertions.assertNull(buscado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCaracteristicaTest(){

        try {
            Caracteristica caracteristica = caracterirsticaServicio.obtenerCaracteristica(1);
            caracteristica.setDescripcion("Vacío");

            caracterirsticaServicio.modificarCaracteristica(caracteristica);

            Caracteristica buscada = caracterirsticaServicio.obtenerCaracteristica(1);

            Assertions.assertEquals("Vacío",buscada.getDescripcion());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCaracteristicasTest(){

        List<Caracteristica> lista = caracterirsticaServicio.listarCaracteristicas();
        lista.forEach(System.out::println);
    }
}
