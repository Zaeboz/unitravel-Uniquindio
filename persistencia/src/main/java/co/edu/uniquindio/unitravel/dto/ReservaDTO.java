package co.edu.uniquindio.unitravel.dto;

import co.edu.uniquindio.unitravel.entidades.Habitacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ReservaDTO {

    private String nombreUsuario;

    private LocalDateTime fechaReserva;

    private Habitacion habitacion;
}
