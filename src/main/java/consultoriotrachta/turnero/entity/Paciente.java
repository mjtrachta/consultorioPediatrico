package consultoriotrachta.turnero.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "text")
    private String password;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "rol", referencedColumnName = "id_rol")
    private Rol rol = new Rol(2); // Rol predefinido con id 2

    @Column(name = "nro_documento", nullable = false, length = 50)
    private String nroDocumento;

    @ManyToOne
    @JoinColumn(name = "tipo_documento", referencedColumnName = "id_tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_telefono", nullable = false, length = 50)
    private String numeroTelefono;

    @ManyToOne
    @JoinColumn(name = "obra_social", referencedColumnName = "id_obra_social")
    private ObraSocial obraSocial;

    @Column(name = "grupo_sanguineo", length = 50)
    private String grupoSanguineo;

    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id_estado_usuarios")
    private EstadoUsuario estado;

    @Column(name = "barrio")
    private String barrio;

    @ManyToOne
    @JoinColumn(name = "ciudades", referencedColumnName = "id_ciudad")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "provincias", referencedColumnName = "id_provincia")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id_pais")
    private Pais pais;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Turno> turnos;

}