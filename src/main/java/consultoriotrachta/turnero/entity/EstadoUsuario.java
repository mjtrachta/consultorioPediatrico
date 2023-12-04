package consultoriotrachta.turnero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "estados_usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_usuarios")
    private int idEstadoUsuarios;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany
    private List<Paciente> estado2;
    @OneToMany
    private List<Profesional> estado3;


    public int getIdEstadoUsuarios() {
        return idEstadoUsuarios;
    }

    public void setIdEstadoUsuarios(int idEstadoUsuarios) {
        this.idEstadoUsuarios = idEstadoUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}




