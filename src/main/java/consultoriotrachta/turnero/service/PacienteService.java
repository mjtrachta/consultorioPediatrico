package consultoriotrachta.turnero.service;

import consultoriotrachta.turnero.dto.PacienteDto;
import consultoriotrachta.turnero.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Paciente guardarPaciente(Paciente paciente);
    Paciente obtenerPacientePorId(Integer id);
    List<Paciente> listarTodosLosPacientes();
    Paciente actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id);


        Optional<PacienteDto> buscarPorTipoYNumeroDocumento(String tipoDocumento, String nroDocumento);


}