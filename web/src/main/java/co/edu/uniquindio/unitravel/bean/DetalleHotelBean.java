package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Persona;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.ComentarioServicio;
import co.edu.uniquindio.unitravel.servicios.UnitravelServicio;
import co.edu.uniquindio.unitravel.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleHotelBean implements Serializable {

    @Value("#{param['hotel']}")
    private String codigoHotel;

    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Autowired
    private UnitravelServicio unitravelServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Getter @Setter
    private Integer calificacionPromedio;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @PostConstruct
    public void init() {
        nuevoComentario = new Comentario();
        comentarios = new ArrayList<>();
        if(codigoHotel != null && !codigoHotel.isEmpty()) {
            try {
                hotel = unitravelServicio.obtenerHotel(Integer.parseInt(codigoHotel));
                comentarios = hotel.getComentarios();
                calificacionPromedio = administradorHotelServicio.obtenerCalificacionPromedio(hotel.getCodigo());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void crearComentario(){

        try {

            if (personaLogin!=null){
                hotel.getComentarios().add(nuevoComentario);
                nuevoComentario.setCodigo((int)(Math.random()*10+1));
                usuarioServicio.registrarComentario(nuevoComentario);
                hotel.getComentarios().add(nuevoComentario);
                administradorHotelServicio.modificarHotel(hotel,hotel.getCodigo());
                comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();
                calificacionPromedio = administradorHotelServicio.obtenerCalificacionPromedio(hotel.getCodigo());

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comentario registrado", "El comentario se ha registrado correctamente");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}
