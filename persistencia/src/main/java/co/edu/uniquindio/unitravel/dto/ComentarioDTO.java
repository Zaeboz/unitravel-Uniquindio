package co.edu.uniquindio.unitravel.dto;

import co.edu.uniquindio.unitravel.entidades.Comentario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ComentarioDTO {

    private String correo;

    private Comentario comentario;
}
