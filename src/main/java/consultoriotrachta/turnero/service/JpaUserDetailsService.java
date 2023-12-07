package consultoriotrachta.turnero.service;

import consultoriotrachta.turnero.entity.Paciente;
import consultoriotrachta.turnero.entity.Profesional;
import consultoriotrachta.turnero.repository.PacienteRepository;
import consultoriotrachta.turnero.repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    ProfesionalRepository profesionalRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String[] parts = username.split(":");
        if (parts.length != 2) {
            throw new UsernameNotFoundException(String.format("Formato de nombre de usuario incorrecto: %s", username));
        }

        String tipoDocumento = parts[0];
        String nroDocumento = parts[1];

        Optional<Profesional> optionalProfesional = profesionalRepository.findByTipoDocumento_IdTipoDocumentoAndNroDocumento(tipoDocumento,nroDocumento);
        Optional<Paciente> optionalPaciente = pacienteRepository.findByTipoDocumento_IdTipoDocumentoAndNroDocumento(tipoDocumento,nroDocumento);

        List<GrantedAuthority> authorities = new ArrayList<>();

        // Decidir si el usuario es un profesional, un paciente, o no existe
        if (optionalProfesional.isPresent()) {
            Profesional profesional = optionalProfesional.get();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + profesional.getRol().getNombreRol().toUpperCase()));
            return new User(
                    tipoDocumento + ":" + profesional.getNroDocumento(),
                    profesional.getPassword(),
                    true, true, true, true,
                    authorities
            );
        } else if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + paciente.getRol().getNombreRol().toUpperCase()));
            return new User(
                    tipoDocumento + ":" + paciente.getNroDocumento(),
                    paciente.getPassword(),
                    true, true, true, true,
                    authorities
            );
        } else {
            throw new UsernameNotFoundException(String.format("Usuario %s no encontrado en el sistema", username));
        }

    }
}