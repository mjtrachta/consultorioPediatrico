package consultoriotrachta.turnero.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "estado_turno")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTurno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_turno")
    private int idEstadoTurno;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany
    private List<Turno> estadoTurno2;

    public int getIdEstadoTurno() {
        return idEstadoTurno;
    }

    public void setIdEstadoTurno(int idEstadoTurno) {
        this.idEstadoTurno = idEstadoTurno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
