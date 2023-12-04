package consultoriotrachta.turnero.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ciudades")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Integer id_ciudad;

    @Column(name = "nombre_ciudad", nullable = false)
    private String nombre_ciudad;

    @ManyToOne
    @JoinColumn(name = "id_provincia", updatable = false, insertable = false)
    private Provincia provincia;

    @OneToMany
    private List<Paciente> ciudad2;

    public Integer getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Integer id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre_ciudad() {
        return nombre_ciudad;
    }

    public void setNombre_ciudad(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }
}