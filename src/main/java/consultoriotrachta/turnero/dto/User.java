package consultoriotrachta.turnero.dto;


public class User {

    private  String tipoDocumento;
    private String nroDocumento;
    private String password;


    public User(String tipoDocumento, String nroDocumento, String role, String email, String password) {

        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.password = password;

    }

    public User() {
        // Constructor por defecto necesario para la deserialización de Jackson
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters y setters
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getNroDocumento() {
        return nroDocumento;
    }
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

}
