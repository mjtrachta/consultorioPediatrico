package consultoriotrachta.turnero.repository;



import consultoriotrachta.turnero.dto.ProfesionalDto;
import consultoriotrachta.turnero.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {

    // (PROFESIONALES) X NOMBRE TIPO DE DOCUMENTO Y NRO DOCUMENTO

    @Query("SELECT p FROM Profesional p WHERE p.tipoDocumento.nombre_tipo_documento= :nombreTipoDocumento AND p.nroDocumento = :nroDocumento")
    Optional<Profesional> findByTipoDocumento_IdTipoDocumentoAndNroDocumento(@Param("nombreTipoDocumento") String nombreTipoDocumento,
                                                                             @Param("nroDocumento") String nroDocumento);

    // (PROFESIONALES) X ESPECIALIDAD
    @Query("SELECT new consultoriotrachta.turnero.dto.ProfesionalDto(p.nombre, p.apellido) " +
            "FROM Profesional p " +
            "JOIN p.especialidad e " +
            "WHERE e.nombre = :nombreEspecialidad AND e.nombre != 'Admin'")
    List<ProfesionalDto> findProfesionalesByEspecialidadNombre(@Param("nombreEspecialidad") String nombreEspecialidad);

    // (PROFESIONALES) X APELLIDO

    List<Profesional> findByApellido(String apellido);












    List<Profesional> findByNombreContaining(String nombre);

    Optional<Profesional> findByEmail(String email);

    List<Profesional> findByEstado_Nombre(String nombre);


}