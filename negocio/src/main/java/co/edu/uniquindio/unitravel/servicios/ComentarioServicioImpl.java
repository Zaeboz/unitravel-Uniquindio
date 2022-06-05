package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    private final ComentarioRepo comentarioRepo;
    private final HotelRepo hotelRepo;

    private final UsuarioRepo usuarioRepo;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo, HotelRepo hotelRepo, UsuarioRepo usuarioRepo) {
        this.comentarioRepo = comentarioRepo;
        this.hotelRepo = hotelRepo;
        this.usuarioRepo = usuarioRepo;
    }


    @Override
    public void ingresarComentario(Comentario c,Hotel h, Usuario u){
        c.setFecha_calificacion(LocalDateTime.now());
        c.setHotel(h);
        c.setUsuario(u);
        h.getComentarios().add(c);
        u.getComentarios().add(c);
        comentarioRepo.save(c);
        hotelRepo.save(h);
        usuarioRepo.save(u);

    }

    @Override
    public void actualizarComentario(Comentario c,int codigoComentario) throws Exception {

        Comentario comentarioObtenido = obtenerComentario(codigoComentario);

        if(comentarioObtenido != null){
            comentarioObtenido.setFecha_calificacion(LocalDateTime.now());
            comentarioObtenido.setComentario(c.getComentario());
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
    public List<Comentario> obtenerListaPorCalificacion(int calificacion) {
        return comentarioRepo.obtenerListaPorCalificacion(calificacion);
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }
}
