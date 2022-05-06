package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Telefono;

import java.util.List;

public interface TelefonoServicio {

    Telefono registrarTelefono (Telefono t) throws Exception;

    void actualizarTelefono(Telefono t, int codigoTelefono) throws Exception;

    void eliminarTelefono(int id) throws Exception;

    Telefono obtenerTelefono (int id) throws Exception;

    List<Telefono> listarTelefonos();

    List<Telefono> listarTelefonosUsuario(String cedula) throws Exception;
}
