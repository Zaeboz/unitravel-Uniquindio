package co.edu.uniquindio.unitravel.repositorios;


import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorHotelRepo extends JpaRepository<AdministradorHotel, String> {

}
