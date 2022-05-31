package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.servicios.UnitravelServicio;
import co.edu.uniquindio.unitravel.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UnitravelServicio unitravelServicio;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        ciudades = unitravelServicio.listarCiudades();
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);

            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aletar", "Usuario registrado exitosamente");
            FacesContext.getCurrentInstance().addMessage("msj_bean", msj);
        }catch (Exception e){
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aletar", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj_bean", msj);
        }
    }
}
