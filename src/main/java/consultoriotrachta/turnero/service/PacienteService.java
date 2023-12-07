package consultoriotrachta.turnero.service;

import consultoriotrachta.turnero.dto.ObraSocialDto;
import consultoriotrachta.turnero.dto.PacienteDto;
import consultoriotrachta.turnero.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    // (PACIENTE) CREAR
    Paciente guardarPaciente(Paciente paciente);

    //(PACIENTE X ID)
    Optional<PacienteDto> obtenerPacienteDtoPorId(Integer id);

    //(PACIENTE DTO) X NOMBRE TIPO DOCUMENTO Y NRO DOCUMENTO
    Optional<PacienteDto> buscarPorTipoYNumeroDocumento(String tipoDocumento, String nroDocumento);

    //(PACIENTE) BUSCAR OBRA SOCIAL DEL PACIENTE
    Optional<ObraSocialDto> findObraSocialByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String nroDocumento);







    List<Paciente> listarTodosLosPacientes();
    Paciente actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id);





}