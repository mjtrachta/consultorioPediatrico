package consultoriotrachta.turnero.response;

public class LoginResponse {
    private String token;
    private String nombre;
    private String apellido;
    private String role;
    private String email;

    public LoginResponse(String token, String nombre, String apellido, String role, String email) {
        this.token = token;
        this.nombre = nombre;
        this.apellido = apellido;
        this.role = role;
        this.email = email;
    }

    // Getters y setters para cada campo
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
