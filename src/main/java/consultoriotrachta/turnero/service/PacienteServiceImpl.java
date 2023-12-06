package consultoriotrachta.turnero.service;

import consultoriotrachta.turnero.dto.PacienteDto;
import consultoriotrachta.turnero.entity.Paciente;
import consultoriotrachta.turnero.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Paciente guardarPaciente(Paciente paciente) {
        if (paciente.getRol() == null || paciente.getRol().getIdRol() != 2) {
            throw new IllegalArgumentException("El rol del paciente es incorrecto");
        }
        String passwordBCrypt = passwordEncoder.encode(paciente.getPassword());
        paciente.setPassword(passwordBCrypt);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente obtenerPacientePorId(Integer id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paciente> listarTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Optional<PacienteDto> buscarPorTipoYNumeroDocumento(String tipoDocumento, String nroDocumento) {
        return pacienteRepository.buscarPorTipoYNumeroDocumento(tipoDocumento, nroDocumento);
    }


}
