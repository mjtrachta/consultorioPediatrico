package consultoriotrachta.turnero.controller;

import consultoriotrachta.turnero.entity.Paciente;
import consultoriotrachta.turnero.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable Integer id) {
        Paciente paciente = pacienteService.obtenerPacientePorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.notFound().build();
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
