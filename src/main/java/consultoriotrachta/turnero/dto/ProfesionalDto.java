package consultoriotrachta.turnero.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfesionalDto {

    private String nombre;
    private String apellido;


    public ProfesionalDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}

