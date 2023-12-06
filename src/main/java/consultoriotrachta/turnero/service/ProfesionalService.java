package consultoriotrachta.turnero.service;


import consultoriotrachta.turnero.dto.ProfesionalDto;
import consultoriotrachta.turnero.entity.Profesional;
import consultoriotrachta.turnero.repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface ProfesionalService {

    List<ProfesionalDto> obtenerTodosLosProfesionales();

    List<ProfesionalDto> buscarPorApellido(String apellido);


    Profesional guardarProfesional(Profesional profesional);

    List<ProfesionalDto> findProfesionalesByEspecialidadNombre(String nombreEspecialidad);

    Optional<Profesional> obtenerProfesionalPorId(Integer id);
    List<Profesional> listarTodosLosProfesionales();

    void eliminarProfesional(Integer id);

    Profesional actualizarProfesional(Profesional profesional);

    Optional<Profesional> findByTipoDocumento_IdTipoDocumentoAndNroDocumento(String tipoDocumento, String nroDocumento);
}
