package co.edu.uniquindio.unitravel.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@Setter
@Getter
public class InicioBean implements Serializable {

    private String mensaje = "Mi primera página en JSF";


}