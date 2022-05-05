package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    private final ComentarioRepo comentarioRepo;
    private final HotelRepo hotelRepo;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo, HotelRepo hotelRepo) {
        this.comentarioRepo = comentarioRepo;
        this.hotelRepo = hotelRepo;
    }


    @Override
    public Comentario registrarComentario(Comentario c) throws Exception {
       try {
          // c.setFecha_calificacion(new );
           return comentarioRepo.save(c);
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }

    }

    @Override
    public void actualizarComentario(Comentario c,int codigoComentario) throws Exception {

        Comentario comentarioObtenido = obtenerComentario(codigoComentario);

        if(comentarioObtenido != null){
            comentarioObtenido.setComentario(c.getComentario());
           // comentarioObtenido.setFecha_calificacion();
            comentarioObtenido.setCalificacion(c.getCalificacion());

            comentarioRepo.save(comentarioObtenido);
        }

    }

    @Override
    public List<Comentario> obtenerComentariosHotel(int idHotel) throws Exception {

        List<Comentario>comentarios = hotelRepo.obtenerComentariosHotel(idHotel);

        if(comentarios==null){

            throw new Exception("No se encontraron los comentarios");
        }

        return comentarios;
    }

    @Override
    public void eliminarComentario(int id) throws Exception {

        Comentario comentarioEncontrado = obtenerComentario(id);

        if(comentarioEncontrado != null){
            comentarioRepo.delete(comentarioEncontrado);
        } else {
            throw new Exception("No se ha encontrado el comentario");
        }
    }

    @Override
    public Comentario obtenerComentario(int id) throws Exception {

        Optional<Comentario> comentario = comentarioRepo.findById(id);

        if(comentario.isEmpty()){
            throw new Exception("No se ha encontrado el comentario");
        }

       return comentario.get();
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }
}
