package consultoriotrachta.turnero.dto;

import consultoriotrachta.turnero.entity.Rol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfesionalDto {

    private String nombre;
    private String apellido;
    private Rol rol;

    public ProfesionalDto(String nombre, String apellido, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
    }
}

