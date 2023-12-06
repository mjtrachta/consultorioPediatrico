package consultoriotrachta.turnero.repository;

import consultoriotrachta.turnero.dto.PacienteDto;
import consultoriotrachta.turnero.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    // Método para buscar un paciente por tipo y número de documento
    @Query("SELECT p FROM Paciente p WHERE p.tipoDocumento.nombre_tipo_documento= :nombreTipoDocumento AND p.nroDocumento = :nroDocumento")
    Optional<Paciente>  findByTipoDocumento_IdTipoDocumentoAndNroDocumento(@Param("nombreTipoDocumento") String nombreTipoDocumento,
                                                                           @Param("nroDocumento") String nroDocumento);

    @Query("SELECT new consultoriotrachta.turnero.dto.PacienteDto(p.nombre, p.apellido) FROM Paciente p " +
            "INNER JOIN p.tipoDocumento td " +
            "WHERE td.nombre_tipo_documento = :nombreTipoDocumento " +
            "AND p.nroDocumento = :nroDocumento")
    Optional<PacienteDto> buscarPorTipoYNumeroDocumento(String nombreTipoDocumento, String nroDocumento);
}
