package consultoriotrachta.turnero.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import consultoriotrachta.turnero.auth.SimpleGrantedAuthorityJsonCreator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.security.Security;
import java.util.*;

import static consultoriotrachta.turnero.auth.TokenJwtConfig.*;

public class JwtValidationFilter extends BasicAuthenticationFilter {
    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)throws IOException,ServletException {

       String herder = request.getHeader(HEADER_AUTHORIZATION);

       if(herder == null || !herder.startsWith(PREFIX_TOKEN)){
           chain.doFilter(request, response);
           return;
       }
       String token = herder.replace(PREFIX_TOKEN, "");


        try{
            Claims claims = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build().parseSignedClaims(token)
                    .getPayload();
            Object authoritiesCleims = claims.get("authorities");
            String username = claims.getSubject();
            Collection<? extends GrantedAuthority> authorities = Arrays
                    .asList(new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                    .readValue(authoritiesCleims
                    .toString()
                    .getBytes(), SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken  authentication=
                    new UsernamePasswordAuthenticationToken(username, null,authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request,response);
        }catch(JwtException e){
            Map<String,String> body= new HashMap<>();
            body.put("error", e.getMessage());
            body.put("messaje", "El token JWT no es valido");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(403);
            response.setContentType("application/json");
        }
    }
}
