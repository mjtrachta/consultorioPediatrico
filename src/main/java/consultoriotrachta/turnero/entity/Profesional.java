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
@Table(name = "profesionales")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesional")
    private Integer idProfesional;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false) // Ajustado para coincidir con DDL
    private String password;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "rol", referencedColumnName = "id_rol")
    private Rol rol;

    @Column(name = "nro_documento", length = 50, nullable = false)
    private String nroDocumento;

    @ManyToOne
    @JoinColumn(name = "tipo_documento", referencedColumnName = "id_tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_telefono", length = 50)
    private String numeroTelefono;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "especialidad", referencedColumnName = "id_especialidad")
    private Especialidad especialidad;

    @Column(name = "matricula", length = 50)
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id_estado_usuarios")
    private EstadoUsuario estado;

    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    // RELACIONES

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}

