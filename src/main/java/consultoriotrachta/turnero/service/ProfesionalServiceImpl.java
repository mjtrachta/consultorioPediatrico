package consultoriotrachta.turnero.service;

import ch.qos.logback.core.encoder.Encoder;
import consultoriotrachta.turnero.dto.ProfesionalDto;
import consultoriotrachta.turnero.entity.Profesional;
import consultoriotrachta.turnero.repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {

    private final ProfesionalRepository profesionalRepository;

    @Autowired
    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ALL X DTO
    @Override
    public List<ProfesionalDto> obtenerTodosLosProfesionales() {
        List<Profesional> profesionales = profesionalRepository.findAll();
        return profesionales.stream()
                .map(profesional -> new ProfesionalDto(profesional.getNombre(), profesional.getApellido()))
                .collect(Collectors.toList());
    }

    // CREAR
    @Override
    @Transactional
    public Profesional guardarProfesional(Profesional profesional) {
        String passwordBCrypt = passwordEncoder.encode(profesional.getPassword());
        profesional.setPassword(passwordBCrypt);
        return profesionalRepository.save(profesional);
    }

    // BUSCAR X APELLIDO
    @Override
    public List<ProfesionalDto> buscarPorApellido(String apellido) {
        List<Profesional> profesionales = profesionalRepository.findByApellido(apellido);
        return profesionales.stream()
                .map(profesional -> new ProfesionalDto(profesional.getNombre(), profesional.getApellido()))
                .collect(Collectors.toList());
    }

    // BUSCAR X ESPECIALIDAD
    @Override
    public List<ProfesionalDto> findProfesionalesByEspecialidadNombre(String nombreEspecialidad) {
        return profesionalRepository.findProfesionalesByEspecialidadNombre(nombreEspecialidad)
                .stream()
                .map(profesional -> new ProfesionalDto(profesional.getNombre(), profesional.getApellido()))
                .collect(Collectors.toList());
    }




    @Override
    public Optional<Profesional> obtenerProfesionalPorId(Integer id) {
        return profesionalRepository.findById(id);
    }

    @Override
    public List<Profesional> listarTodosLosProfesionales() {
        return profesionalRepository.findAll();
    }

    @Override
    public void eliminarProfesional(Integer id) {
        profesionalRepository.deleteById(id);
    }

    @Override
    public Profesional actualizarProfesional(Profesional profesional) {
        return profesionalRepository.save(profesional);
    }

    @Override
    public Optional<Profesional> findByTipoDocumento_IdTipoDocumentoAndNroDocumento(String tipoDocumento, String nroDocumento) {
        return Optional.empty();
    }


}
