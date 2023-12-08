package consultoriotrachta.turnero.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ObraSocialDto {
    private String nombreObraSocial;


    public ObraSocialDto(String nombreObraSocial) {
        this.nombreObraSocial = nombreObraSocial;

    }
}