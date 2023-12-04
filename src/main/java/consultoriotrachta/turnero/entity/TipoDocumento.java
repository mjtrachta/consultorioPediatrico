package consultoriotrachta.turnero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tipo_documento")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Integer id_tipo_documento;

    @Column(name = "nombre_tipo_documento",nullable = false)
    private String nombre_tipo_documento;

    @OneToMany
    private List<Paciente> tipoDocumento2;
    @OneToMany
    private List<Profesional> tipoDocumento3;

    public Integer getId_tipo_documento() {
        return id_tipo_documento;
    }

    public void setId_tipo_documento(Integer id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public String getNombre_tipo_documento() {
        return nombre_tipo_documento;
    }

    public void setNombre_tipo_documento(String nombre_tipo_documento) {
        this.nombre_tipo_documento = nombre_tipo_documento;
    }
}
