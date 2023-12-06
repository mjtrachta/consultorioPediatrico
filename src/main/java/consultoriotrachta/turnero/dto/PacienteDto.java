package consultoriotrachta.turnero.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PacienteDto {
    private String nombre;
    private String apellido;


    public PacienteDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

}
