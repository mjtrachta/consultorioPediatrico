package consultoriotrachta.turnero.controller;

import consultoriotrachta.turnero.dto.ObraSocialDto;
import consultoriotrachta.turnero.dto.PacienteDto;
import consultoriotrachta.turnero.entity.Paciente;
import consultoriotrachta.turnero.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
            return ResponseEntity.ok(pacienteGuardado);
        } catch (IllegalArgumentException e) {
            // Aquí se maneja la excepción específica
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<PacienteDto> buscarPacientePorToken(Principal principal) {
        // Suponiendo que el 'sub' del token es "D.N.I.:12345678"
        String[] datos = principal.getName().split(":");
        String tipoDocumento = datos[0].trim(); // D.N.I.
        String nroDocumento = datos[1].trim(); // 12345678

        Optional<PacienteDto> paciente = pacienteService.buscarPorTipoYNumeroDocumento(tipoDocumento, nroDocumento);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> obtenerPacientePorId(@PathVariable Integer id) {
        return pacienteService.obtenerPacienteDtoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/obra-social")
    public ResponseEntity<ObraSocialDto> findObraSocialByTipoDocumentoAndNumeroDocumento(
            @RequestParam String tipoDocumento,
            @RequestParam String nroDocumento) {
        return pacienteService.findObraSocialByTipoDocumentoAndNumeroDocumento(tipoDocumento, nroDocumento)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }











    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarTodosLosPacientes() {
        return ResponseEntity.ok(pacienteService.listarTodosLosPacientes());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Integer id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok().build();
    }
}
