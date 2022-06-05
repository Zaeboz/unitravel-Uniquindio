package co.edu.uniquindio.unitravel.bean;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.UnitravelServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class HotelBean implements Serializable{
    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private Habitacion habitacion;

    @Getter @Setter
    private List<Habitacion> habitaciones;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Caracteristica> caracteristicasHotel;

    @Getter @Setter
    private List<Caracteristica> caracteristicasHabitacion;

    @Getter @Setter
    private List<Cama> camas;

    @Value("${upload.url}")
    private String urlImagenenes;

    private ArrayList<String> imagenesHotel;

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Autowired
    private UnitravelServicio unitravelServicio;
    private ArrayList<String> imagenesHabitacion;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @PostConstruct
    public void incializar(){
        hotel = new Hotel();
        imagenesHotel = new ArrayList<>();
        imagenesHabitacion = new ArrayList<>();
        habitacion = new Habitacion();
        habitaciones = new ArrayList<>();
        ciudades = unitravelServicio.listarCiudades();
        caracteristicasHotel = unitravelServicio.listarCaracteristicasHoteles();
        caracteristicasHabitacion = unitravelServicio.listarCaracteristicasHabitaciones();
        camas = unitravelServicio.listarCamas();
    }

    public String registrarHotel(){
        try {

            if(imagenesHotel.size() > 0) {

                if (habitaciones.size() > 0) {

                    AdministradorHotel administradorHotel = (AdministradorHotel) personaLogin;

                    hotel.setAdministradorHotel(administradorHotel);
                    hotel.setImagenes(imagenesHotel);

                    Hotel h = administradorHotelServicio.crearHotel(hotel);

                    for (Habitacion habitacion : habitaciones) {
                        habitacion.setHotel(h);
                        administradorHotelServicio.crearHabitacion(habitacion);
                    }

                    return "index?faces-redirect=true";
                } else {
                    FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe subir al menos una imagen");
                    FacesContext.getCurrentInstance().addMessage("msj_bean", ms);
                }
            }else{
                FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "El hotel debe tener al menos una habitacion");
                FacesContext.getCurrentInstance().addMessage("msj_bean", ms);
            }
        } catch (Exception e) {
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj_bean", ms);
        }
        return null;
    }

    public void subirImagenesHotel(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen != null){
            imagenesHotel.add(nombreImagen);
        }
    }

    public void subirImagenesHabitacion(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen != null){
            imagenesHabitacion.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile imagen){
        try {
            File archivo = new File(urlImagenenes +"/"+ imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream );
            return imagen.getFileName();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public void registrarHabitacion() {
        if(!imagenesHabitacion.isEmpty()){
            habitacion.setImagenes(imagenesHabitacion);
            habitaciones.add(habitacion);

            habitacion = new Habitacion();
            imagenesHabitacion = new ArrayList<>();
        } else {
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Debe subir al menos una imagen");
            FacesContext.getCurrentInstance().addMessage("msj_bean", ms);
        }
    }
}