package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelTest {

    @Autowired
    private HotelRepo hotelRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotelCodigo() {
        Hotel hotel = hotelRepo.findById(1).orElse(null);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotelesEstrellas() {
        List<Hotel> hoteles = hotelRepo.obtenerHotelesEstrellas(5);
        System.out.println(hoteles);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHoteles() {
        List<Hotel> hoteles = hotelRepo.findAll();
        System.out.println(hoteles);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesSort() {
        List<Hotel> hoteles = hotelRepo.findAll(Sort.by("numEstrellas").descending());
        hoteles.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerNombreCiudad() {
        String nombreCiudad = hotelRepo.obtenerNombreCiudad(1);
        System.out.println(nombreCiudad);
    }
}


