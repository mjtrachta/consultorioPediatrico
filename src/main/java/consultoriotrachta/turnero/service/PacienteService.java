package consultoriotrachta.turnero.service;

import consultoriotrachta.turnero.entity.Paciente;

import java.util.List;

public interface PacienteService {
    Paciente guardarPaciente(Paciente paciente);
    Paciente obtenerPacientePorId(Integer id);
    List<Paciente> listarTodosLosPacientes();
    Paciente actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id);
}