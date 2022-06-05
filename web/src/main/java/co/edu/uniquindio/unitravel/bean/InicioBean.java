package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import co.edu.uniquindio.unitravel.servicios.CiudadServicio;
import co.edu.uniquindio.unitravel.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Getter @Setter
    private List<Hotel> hoteles;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @PostConstruct
    public void inicializar(){
        hoteles = usuarioServicio.listarHoteles();
        ciudades = administradorServicio.listarCiudades();
    }

    public String irRegistro(){
        return "registrar_usuario?faces-redirect=true";
    }

    public String irDetalleHotel(String codigoHotel) {

        return "/detalle_hotel?faces-redirect=true&amp;hotel=" + codigoHotel;
    }
}