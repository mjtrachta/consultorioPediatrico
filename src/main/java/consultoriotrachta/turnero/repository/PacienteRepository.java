package consultoriotrachta.turnero.repository;

import consultoriotrachta.turnero.dto.ObraSocialDto;
import consultoriotrachta.turnero.dto.PacienteDto;
import consultoriotrachta.turnero.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    // (PACIENTE) X NOMBRE TIPO DOCUMENTO Y NRO DOCUMENTO
    @Query("SELECT p FROM Paciente p WHERE p.tipoDocumento.nombre_tipo_documento= :nombreTipoDocumento AND p.nroDocumento = :nroDocumento")
    Optional<Paciente>  findByTipoDocumento_IdTipoDocumentoAndNroDocumento(@Param("nombreTipoDocumento") String nombreTipoDocumento,
                                                                           @Param("nroDocumento") String nroDocumento);

    //(PACIENTE DTO) X NOMBRE TIPO DOCUMENTO Y NRO DOCUMENTO
    @Query("SELECT new consultoriotrachta.turnero.dto.PacienteDto(p.nombre, p.apellido) FROM Paciente p WHERE p.tipoDocumento.nombre_tipo_documento = :nombreTipoDocumento AND p.nroDocumento = :nroDocumento")
    Optional<PacienteDto> buscarPorTipoYNumeroDocumento(@Param("nombreTipoDocumento") String nombreTipoDocumento, @Param("nroDocumento") String nroDocumento);


    //(PACIENTE) BUSCAR OBRA SOCIAL DEL PACIENTE
    @Query("SELECT new consultoriotrachta.turnero.dto.ObraSocialDto(p.obraSocial.nombreObraSocial) " +
            "FROM Paciente p WHERE p.tipoDocumento.nombre_tipo_documento = :nombreTipoDocumento " +
            "AND p.nroDocumento = :nroDocumento")
    Optional<ObraSocialDto> findObraSocialByTipoDocumentoAndNumeroDocumento(
            @Param("nombreTipoDocumento") String nombreTipoDocumento,
            @Param("nroDocumento") String nroDocumento);


}
