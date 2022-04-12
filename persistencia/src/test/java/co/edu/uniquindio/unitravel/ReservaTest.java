package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.repositorios.ReservaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaTest {

    @Autowired
    private ReservaRepo reservaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservas() {

        LocalDate fecha = LocalDate.of(2022, 10, 1);

        List<Object[]> reservas = reservaRepo.obtenerReservasPorHotel(1, fecha);
        reservas.forEach(reserva -> System.out.println(reserva[0] + "-" + reserva[1] + "-" + reserva[2] + "-" ));
    }
}


