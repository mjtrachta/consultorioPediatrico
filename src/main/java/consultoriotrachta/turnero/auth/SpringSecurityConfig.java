package consultoriotrachta.turnero.auth;

import consultoriotrachta.turnero.auth.filters.JwtAuthenticationFilter;
import consultoriotrachta.turnero.auth.filters.JwtValidationFilter;
import jakarta.servlet.FilterRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authRules -> authRules

                        .requestMatchers(HttpMethod.GET, "/profesionales/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/profesionales/buscarPorApellido").permitAll()
                        .requestMatchers(HttpMethod.GET, "/profesionales/especialidad/{nombreEspecialidad}").hasAnyRole("ADMIN","PROFESIONAL")
                        //NO FUNCIONA
                        .requestMatchers(HttpMethod.POST, "/profesionales/guardar").permitAll()
                        //NO FUNCIONA
                        .requestMatchers(HttpMethod.POST, "/pacientes/guardar").hasAnyRole("ADMIN","PROFESIONAL")
                        .requestMatchers(HttpMethod.GET, "/pacientes/buscar").hasAnyRole("ADMIN","PROFESIONAL","USUARIO")
                        .requestMatchers(HttpMethod.GET, "/pacientes/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes/obra-social").hasAnyRole("ADMIN","PROFESIONAL","USUARIO")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors ->cors.configurationSource(corsConfigurationSource()))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config =  new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> bean  = new FilterRegistrationBean<>(new CorsFilter(
                corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

         return  bean;

    }
}
