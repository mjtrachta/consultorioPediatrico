package consultoriotrachta.turnero.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre_rol", nullable = false)
    private String nombreRol;

    @JsonBackReference
    @OneToMany(mappedBy = "rol") // Mapeo correcto a Paciente
    private List<Paciente> pacientes;

    @JsonBackReference
    @OneToMany(mappedBy = "rol")
    private List<Profesional> profesionales;

    public Rol(int i) {

    }

    // Getters y setters
}