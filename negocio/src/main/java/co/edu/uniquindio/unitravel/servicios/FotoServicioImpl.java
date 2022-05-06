package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.Foto;
import co.edu.uniquindio.unitravel.repositorios.FotoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotoServicioImpl implements FotoServicio {

    private final FotoRepo fotoRepo;


    public FotoServicioImpl(FotoRepo fotoRepo) {
        this.fotoRepo = fotoRepo;
    }

    @Override
    public Foto registrarImagen(Foto f) throws Exception {

        Foto imagen = obtenerImagen(f.getCodigo());

        if (imagen !=null){
            throw new Exception("La imagen ya esta registrada");
        }

        return fotoRepo.save(f);
    }

    @Override
    public void actualizarImagen(Foto f) throws Exception {

        Foto imagen = obtenerImagen(f.getCodigo());

        if (imagen!=null){
            fotoRepo.save(f);
        }else{
            throw new Exception("No se encontró la imagen");
        }

    }

    @Override
    public void eliminarImagen(int id) throws Exception {

        Foto imagenEncontrada = obtenerImagen(id);

        if(imagenEncontrada != null){
            fotoRepo.delete(imagenEncontrada);
        }else {
            throw new Exception("No se encontró la imagen");
        }
    }

    @Override
    public Foto obtenerImagen(int id) throws Exception {

        Optional<Foto> imagen = fotoRepo.findById(id);

        if(imagen.isEmpty()){
            throw  new Exception("No se encontró la imagen");
        }
        return imagen.get();
    }

    @Override
    public String obtenerUrlHabitacion(int idHabitacion) throws Exception {

        String url = fotoRepo.obtenerUrlImagenHabitacion(idHabitacion);

        if (url == null){
            throw new Exception("La url no es valida");
        }

        return url;
    }

    @Override
    public String obtenerUrlHotel(int idHotel) throws Exception {

        String url = fotoRepo.obtenerUrlImagenHotel(idHotel);

        if (url == null){
            throw new Exception("La url no es valida");
        }

        return url;
    }

    @Override
    public List<Foto> listarImagenes() {
        return fotoRepo.findAll();
    }
}
