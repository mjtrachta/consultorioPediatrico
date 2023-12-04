package consultoriotrachta.turnero.auth.filters;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import consultoriotrachta.turnero.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static consultoriotrachta.turnero.auth.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {

        User user = null;
        String tipoDocumento = null;
        String nroDocumento = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            tipoDocumento = user.getTipoDocumento();
            nroDocumento = user.getNroDocumento();
            password = user.getPassword();

           // logger.info("TipoDocumento desde request InputStream (raw): " + tipoDocumento);
          //  logger.info("NroDocumento desde request InputStream (raw): " + nroDocumento);
          //  logger.info("Password desde request InputStream (raw): " + password);

        } catch (StreamReadException e) {
            logger.error("Error al procesar los datos de autenticación", e);
        } catch (DatabindException e) {
            logger.error("Error al procesar los datos de autenticación", e);
        } catch (IOException e) {
            logger.error("Error al procesar los datos de autenticación", e);
        }

        // Combina tipoDocumento y nroDocumento para usar como principal
        String combinedUsername = tipoDocumento + ":" + nroDocumento;
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                combinedUsername,
                password);

        return authenticationManager.authenticate(authToken);
    }

    //SALIO TOD0 BIEN
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException, ServletException {

        String combinedUsername = ((org.springframework.security.core.userdetails.User)authResult.getPrincipal())
                .getUsername();

        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        boolean isAdmin = roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("authorities", new ObjectMapper().writeValueAsString(roles));
        claimsMap.put("isAdmin", isAdmin);
        //String originalInput = SECRET_KEY + ";" + combinedUsername;
        //String token = Base64.getEncoder().encodeToString(originalInput.getBytes()) ;

        String token = Jwts.builder()
                .claims(claimsMap)
                .subject(combinedUsername)
                .signWith(SECRET_KEY)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();


        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("message",String.format("Hola %s, has iniciado sesion con exito!", combinedUsername));
        body.put("username", combinedUsername);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    //SALIO TOD0 MAL
    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed)
            throws IOException, ServletException {

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Error en la autenticacion, tipo de documento, numero de documento o contraseña incorrenta");
        body.put("error", failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
