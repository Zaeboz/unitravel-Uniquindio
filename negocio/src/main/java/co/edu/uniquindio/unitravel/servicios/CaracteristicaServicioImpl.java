package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.repositorios.CaracteristicaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicaServicioImpl implements CaracterirsticaServicio{

    private final CaracteristicaRepo caracteristicaRepo;

    public CaracteristicaServicioImpl(CaracteristicaRepo caracteristicaRepo) {
        this.caracteristicaRepo = caracteristicaRepo;
    }


    @Override
    public void crearCaracteristica(Caracteristica c) {

         caracteristicaRepo.save(c);
    }

    @Override
    public void eliminarCaracteristica(int id) throws Exception {

        Caracteristica caracteristica = obtenerCaracteristica(id);

        if (caracteristica !=null){
            caracteristicaRepo.delete(caracteristica);
        }else{
            throw new Exception("La característica no existe");
        }
    }

    @Override
    public void modificarCaracteristica(Caracteristica c) throws Exception {

        Caracteristica caracteristica = obtenerCaracteristica(c.getCodigo());

        if (caracteristica !=null){
            caracteristicaRepo.save(c);
        }else{
            throw new Exception("La característica no existe");
        }

    }

    @Override
    public Caracteristica obtenerCaracteristica(int id) throws Exception {

        Optional<Caracteristica> caracteristica = caracteristicaRepo.findById(id);

        if (caracteristica.isEmpty()){
            throw new Exception("La característica no existe");
        }

        return caracteristica.get();
    }

    @Override
    public List<Caracteristica> listarCaracteristicas() {
        return caracteristicaRepo.findAll();
    }
}
