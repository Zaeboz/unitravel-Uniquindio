package co.edu.uniquindio.unitravel.bean;
import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
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

    @Value("${upload.url}")
    private String urlImagenenes;

    private ArrayList<String> imagenes;

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @PostConstruct
    public void incializar(){
        hotel = new Hotel();
        imagenes = new ArrayList<>();
    }

    public String registrarHotel(){
        try {

            if(imagenes.size() > 0) {

                List<Foto> fotos = new ArrayList<>();
                Ciudad ciudad = administradorHotelServicio.obtenerCiudad(1);
                AdministradorHotel administradorHotel = administradorHotelServicio.obtenerAdminHotel("4");

                hotel.setCiudad(ciudad);
                hotel.setAdministradorHotel(administradorHotel);

                for (String imagen : imagenes) {
                    Foto foto = new Foto();
                    foto.setUrl(urlImagenenes +"/"+ imagen);
                    fotos.add(administradorHotelServicio.guardarFoto(foto));
                }
                hotel.setImagenes(fotos);

                administradorHotelServicio.crearHotel(hotel);

            /*FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El hotel se ha creado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, ms);*/

                return "registro_exitoso?faces-redirect=true";
            }else{
                FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Debe subir al menos una imagen");
                FacesContext.getCurrentInstance().addMessage(null, ms);
            }
        } catch (Exception e) {
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, ms);
        }
        return null;
    }

    public void subirImagenesHotel(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen != null){
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile imagen){

        try {
            File archivo = new File(urlImagenenes +"/"+ imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public String subirImagenesHabitacion() {
        return null;
    }

    public String registrarHabitacion() {
        return null;
    }
}