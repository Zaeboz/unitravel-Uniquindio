package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;

import java.time.LocalDate;
import java.util.List;

public class CiudadServicioImpl implements CiudadServicio {

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public List<Ciudad> ciudadesMasVisitadas() throws Exception {
        List<Ciudad> ciudades = ciudadRepo.obtenerCiudadesMasReservadas(LocalDate.now().minusDays(30), LocalDate.now());
        return ciudades.subList(0, 5);
    }
}
