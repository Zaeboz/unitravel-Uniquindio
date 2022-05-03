package co.edu.uniquindio.unitravel.dto;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class HotelMayorCalificacionDTO {

    private Hotel hotel;

    private Double calificacionPromedio;
}
