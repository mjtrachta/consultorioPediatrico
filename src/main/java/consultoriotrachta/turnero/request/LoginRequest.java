package consultoriotrachta.turnero.request;

import lombok.Data;

@Data
public class LoginRequest {
    private Integer idTipoDocumento;
    private String tipoDocumento;
    private String documento;
    private String password;
    // Getters y setters
}