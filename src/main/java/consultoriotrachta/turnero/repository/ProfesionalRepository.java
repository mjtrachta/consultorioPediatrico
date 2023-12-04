package consultoriotrachta.turnero.repository;



import consultoriotrachta.turnero.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {

    //Profesional findByTipoDocumentoAndNroDocumento(String tipoDocumento, String nroDocumento);

    @Query("SELECT p FROM Profesional p WHERE p.tipoDocumento.nombre_tipo_documento= :nombreTipoDocumento AND p.nroDocumento = :nroDocumento")
    Optional<Profesional> findByTipoDocumento_IdTipoDocumentoAndNroDocumento(@Param("nombreTipoDocumento") String nombreTipoDocumento,
                                                                             @Param("nroDocumento") String nroDocumento);




    // Método para buscar profesionales por nombre
    List<Profesional> findByNombreContaining(String nombre);

    // Método para buscar profesionales por apellido
    List<Profesional> findByApellido(String apellido);

    // Método para buscar profesionales por especialidad
    List<Profesional> findByEspecialidad_Nombre(String nombreEspecialidad);

    // Método para buscar un profesional por email
    Optional<Profesional> findByEmail(String email);

    // Método para buscar profesionales por estado
    List<Profesional> findByEstado_Nombre(String nombre);


}