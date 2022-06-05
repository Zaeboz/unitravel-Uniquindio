package co.edu.uniquindio.unitravel.config;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Autowired
    private CaracterirsticaServicio caracterirsticaServicio;


    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdmins().isEmpty()) {

            Administrador administrador = new Administrador("12345", "Mauricio", "m@gmail.com", "12345");
            administradorServicio.registrarAdmin(administrador);

            Ciudad ciudad1 = new Ciudad("Calarcá");
            Ciudad ciudad3 = new Ciudad("Medellin");
            Ciudad ciudad4 = new Ciudad("Pereira");
            Ciudad ciudad5 = new Ciudad("Armenia");
            Ciudad ciudad6 = new Ciudad("Bogota");
            Ciudad ciudad7 = new Ciudad("Cucuta");
            Ciudad ciudad8 = new Ciudad("Villavicencio");
            Ciudad ciudad9 = new Ciudad("Cali");
            Ciudad ciudad10 = new Ciudad("Tulua");

            administradorServicio.registrarCiudad(ciudad1);
            administradorServicio.registrarCiudad(ciudad3);
            administradorServicio.registrarCiudad(ciudad4);
            administradorServicio.registrarCiudad(ciudad5);
            administradorServicio.registrarCiudad(ciudad6);
            administradorServicio.registrarCiudad(ciudad7);
            administradorServicio.registrarCiudad(ciudad8);
            administradorServicio.registrarCiudad(ciudad9);
            administradorServicio.registrarCiudad(ciudad10);

            AdministradorHotel ah = new AdministradorHotel("1","Emanuel","e@gmail.com","123");
            administradorServicio.registrarAdminHotel(ah);

            Cama cama = new Cama("Doble");
            Cama cama1 = new Cama("Sencilla");

            administradorHotelServicio.crearCama(cama);
            administradorHotelServicio.crearCama(cama1);


            Caracteristica caracteristica = new Caracteristica("Espaciosa",2);
            Caracteristica caracteristica1 = new Caracteristica("PenHouse",2);

            Caracteristica caracteristica2 = new Caracteristica("De lujo",1);
            Caracteristica caracteristica3 = new Caracteristica("Basico",1);

            caracterirsticaServicio.crearCaracteristica(caracteristica);
            caracterirsticaServicio.crearCaracteristica(caracteristica1);
            caracterirsticaServicio.crearCaracteristica(caracteristica2);
            caracterirsticaServicio.crearCaracteristica(caracteristica3);

        }
    }
}
