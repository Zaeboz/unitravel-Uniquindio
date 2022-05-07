package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministrador(){

        try {
            Administrador administrador = administradorServicio.obtenerAdministradorEmail("admin1@gmailcom");

            Assertions.assertNotNull(administrador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registrarAdministradorHotelTest() {

        AdministradorHotel administradorHotel = new AdministradorHotel("1","Mauricio","m@gmail.com","mauro");

        try {
            AdministradorHotel guardado = administradorServicio.registrarAdminHotel(administradorHotel);
            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdministradorHotelTest(){
        try {
            AdministradorHotel a = administradorServicio.obtenerAdminHotel("4");

            a.setPassword("contrasenia123");

            administradorServicio.modificarAdminHotel(a,"4");

            AdministradorHotel administradorEncontrado = administradorServicio.obtenerAdminHotel("4");
            Assertions.assertEquals("contrasenia123",administradorEncontrado.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdministradorTest(){
        try{
            AdministradorHotel administradorEncontrado = administradorServicio.obtenerAdminHotel("4");

            administradorServicio.eliminarAdminHotel(administradorEncontrado.getCedula());
            AdministradorHotel administradorBorrado = administradorServicio.obtenerAdminHotel("4");

            Assertions.assertNull(administradorBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministradorHotelTest(){

        try {
            AdministradorHotel admin = administradorServicio.obtenerAdminHotel("4");

            Assertions.assertNotNull(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradoresHotelTest(){

        List<AdministradorHotel> administradores = administradorServicio.listarAdminsHotel();

        administradores.forEach(System.out::println);
    }

    @Test
    public void registrarCiudadTest(){

        try {
            Ciudad ciudadNueva= new Ciudad("Calarcá");

            Ciudad ciudadRegistrada= administradorServicio.registrarCiudad(ciudadNueva);

            Assertions.assertNotNull(ciudadRegistrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCiudadTest(){

        try {

            Ciudad ciudadEncontrada = administradorServicio.obtenerCiudad(1);

            administradorServicio.eliminarCiudad(ciudadEncontrada.getCodigo());

            Ciudad ciudadBorrada = administradorServicio.obtenerCiudad(1);

            Assertions.assertNull(ciudadBorrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCiudadTest(){

        try {

            Ciudad ciudadEncontrada = administradorServicio.obtenerCiudad(1);

            ciudadEncontrada.setNombre("Cali");
            administradorServicio.actualizarCiudad(ciudadEncontrada);

            Ciudad ciudadBuscada = administradorServicio.obtenerCiudad(1);

            Assertions.assertEquals("Cali",ciudadBuscada.getNombre());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesCiudadTest(){

        List<Hotel> lista = administradorServicio.obtenerHoteles("Bogota");
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCiudadesTest(){

        List<Ciudad> listaCiudades = administradorServicio.listarCiudades();

        listaCiudades.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarSillaTest(){

        try {

            Vuelo vuelo= administradorServicio.obtenerVuelo("1");

            Silla silla = new Silla("Fila 1",20.999,vuelo);

            Silla sillaRegistrada = administradorServicio.crearSilla(silla);

            Assertions.assertNotNull(sillaRegistrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSillaTest(){

        try {

            Silla sillaEncontrada = administradorServicio.obtenerSilla("1");

            administradorServicio.eliminarSilla(sillaEncontrada.getCodigo());

            Silla sillaBorrada = administradorServicio.obtenerSilla("1");

            Assertions.assertNull(sillaBorrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSillaTest(){

        try {

            Silla sillaEncontrada = administradorServicio.obtenerSilla("1");
            sillaEncontrada.setPosicion("Fila 1");
            administradorServicio.modificarSilla(sillaEncontrada,sillaEncontrada.getCodigo());

            Silla sillaBuscada = administradorServicio.obtenerSilla("1");

            Assertions.assertEquals("Fila 1",sillaBuscada.getPosicion());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSillasTest(){

        List<Silla> lista = administradorServicio.listarSillas();

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarVueloTest(){

        try {

            Ciudad ciudadOrigen = administradorServicio.obtenerCiudad(1);
            Ciudad ciudadDestino = administradorServicio.obtenerCiudad(2);

            Vuelo vuelo= new Vuelo("1234","Aprobado","Latam",ciudadOrigen,ciudadDestino);

            Silla silla1 = administradorServicio.obtenerSilla("1");
            Silla silla2 = administradorServicio.obtenerSilla("2");

            vuelo.getSillas().add(silla1);
            silla1.setVuelo(vuelo);
            vuelo.getSillas().add(silla2);
            silla2.setVuelo(vuelo);

            Vuelo vueloRegistrado = administradorServicio.crearVuelo(vuelo);

            Assertions.assertNotNull(vueloRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarVueloTest(){

        try {

            Vuelo vueloEncontrado = administradorServicio.obtenerVuelo("1");

            administradorServicio.eliminarVuelo(vueloEncontrado.getCodigo());

            Vuelo vueloBorrado = administradorServicio.obtenerVuelo("1");

            Assertions.assertNull(vueloBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarVueloTest(){

        try {

            Vuelo vueloEncontrado = administradorServicio.obtenerVuelo("1");
            vueloEncontrado.setAerolinea("Avianca");
            administradorServicio.modificarVuelo(vueloEncontrado,vueloEncontrado.getCodigo());

            Vuelo vueloBuscado = administradorServicio.obtenerVuelo("1");

            Assertions.assertEquals("Avianca",vueloBuscado.getAerolinea());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarVuelosTest(){

        List<Vuelo> lista = administradorServicio.listarVuelos();

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarVuelosPorCiudadTest(){

        try {
            List<Vuelo> lista = administradorServicio.obtenerVuelosPorCiudad("Bogota");
            lista.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}


